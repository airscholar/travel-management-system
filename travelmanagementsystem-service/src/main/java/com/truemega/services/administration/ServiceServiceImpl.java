package com.truemega.services.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.ServiceTypeDTO;
import com.truemega.entities.ServiceType;
import com.truemega.interfaces.administration.ServiceService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class ServiceServiceImpl implements ServiceService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public ServiceTypeDTO findServiceById(Integer serviceId, String userName) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  findServiceById Method with  medicaltestId "
						+ serviceId + "userName  " + userName);
		try {

			ServiceType serviceType = baseDao.findEntityById(ServiceType.class,
					serviceId);
			loggerService.logServiceInfo("End  findServiceById Method");
			return mapper.map(serviceType, ServiceTypeDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  findServiceById", e);
			return null;

		}
	}

	@Override
	public List<ServiceTypeDTO> getAllServices(String userName) {

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  getAllServices Method with userName  "
						+ userName);

		try {

			String query = "select model from ServiceType model";
			List<ServiceType> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllServices Method");
			return mapper.mapAsList(result, ServiceTypeDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllServices ", e);
			return null;
		}

	}
}