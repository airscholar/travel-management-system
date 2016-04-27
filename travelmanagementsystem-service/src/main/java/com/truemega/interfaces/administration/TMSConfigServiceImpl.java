package com.truemega.interfaces.administration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.ConfigDTO;
import com.truemega.dto.TravelUserDTO;
import com.truemega.framework.entities.TravelConfig;
import com.truemega.logger.LoggerService;
import com.truemega.lookups.TravelConfigEnum;
import com.truemega.mapping.MappingFactory;
import com.truemega.menu.TMSEmployeeService;

@Stateless
public class TMSConfigServiceImpl implements TMSConfigService {
	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;
	@EJB
	private TMSEmployeeService employeeService;

	List<ConfigDTO> travelConfigDTOs;

	LoggerService loggerService = new LoggerService();

	@Override
	public List<ConfigDTO> findAllConfiguration(String userName) {
		loggerService
				.logServiceInfo("Start findAllConfiguration Method With userName +"
						+ userName);
		travelConfigDTOs = null;
		try {
			travelConfigDTOs = mapper.mapAsList(
					baseDao.findListByQuery("select f from TravelConfig f"),
					ConfigDTO.class);

		} catch (Exception e) {
			loggerService.logServiceError("can't findAllConfiguration  ", e);
		}
		loggerService
				.logServiceInfo("End findAllConfiguration Method with List<ConfigDTO> +  "
						+ travelConfigDTOs.size());
		return travelConfigDTOs;
	}

	public ConfigDTO findConfigurationByName(String configName, String userName) {
		ConfigDTO configDTO = null;
		try {

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("configName", configName);
			TravelConfig travelConfig = baseDao
					.findEntityByQuery(
							"select f from TravelConfig f where f.configName = :configName ",
							parameters);
			configDTO = mapper.map(travelConfig, ConfigDTO.class);

		} catch (Exception e) {
			loggerService.logServiceError("can't findConfigurationByName  ", e);
		}
		return configDTO;
	}

	@Override
	public TravelUserDTO getAdministrator(String userName) {
		loggerService
				.logServiceInfo("Start getAdministrator Method With userName +"
						+ userName);
		try {
			TravelUserDTO admin = null;
			Map<String, String> configMap = new HashMap<String, String>();
			for (ConfigDTO config : findAllConfiguration(userName)) {
				configMap.put(config.getConfigName(), config.getConfigValue());
			}

			admin = employeeService.findUserByUserName(
					configMap.get(TravelConfigEnum.SYS_ADMIN.name()), userName);
			if (admin != null)
				admin.setAdminPassword(configMap
						.get(TravelConfigEnum.SYS_PASSWORD.name()));

			loggerService
					.logServiceInfo("End getAdministrator Method with EmployeeDTO  "
							+ admin);
			return admin;
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't getAdministrator  ", e);
			return null;
		}

	}

}
