package com.truemega.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.truemega.dto.ConfigDTO;
import com.truemega.dto.TravelUserDTO;
import com.truemega.interfaces.administration.TMSConfigService;
import com.truemega.logger.LoggerService;

@ManagedBean(name = "configuration", eager = true)
@SessionScoped
public class Configuration implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, String> configMap;
	private List<ConfigDTO> configList;

	@EJB
	private TMSConfigService configService;

	private final static String logLayout = "%d{dd MMM yyyy HH:mm:ss.SSS}, %C{1} , %M , %L , %m %n";
	private final static String logDatePattern = "'.'yyyy-MM-dd";

	private LoggerService loggerService = new LoggerService();
	private static Logger logger;
	private TravelUserDTO loggedInUser;
	private String empty;

	@PostConstruct
	public void init() {
		loggerService.logPortalInfo(" start init method of Configuration ");
		try {
			logger = loggerService.getLoggerService();
			loadConfiguration();
			System.out.println("QQQQQQQQQQQQQQQQQQ");
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't load init  ", e);
		}
		loggerService.logPortalInfo(" end init method of Configuration ");
	}

	public String getPropertyValue(String name) {
		return configMap.get(name);
	}

	public void loadConfiguration() {
		loggerService
				.logPortalInfo(" start loadConfiguration method of Configuration ");
		try {
			loggerService
					.logPortalInfo(" Load Configuratoin() .....................");

			configList = configService.findAllConfiguration(getUserName());
			configMap = new HashMap<String, String>();
			for (ConfigDTO config : configList) {
				configMap.put(config.getConfigName(), config.getConfigValue());
			}
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't load Configuration  ", e);
		}

		loggerService
				.logPortalInfo(" end loadConfiguration method of Configuration ");
	}

	public TravelUserDTO getLoggedInUser() {
		loggerService
				.logPortalInfo(" start getLoggedInUser method of Configuration ");
		try {
			if (loggedInUser == null) {
				Object loggedObject = HttpJSFUtils.getSession().getAttribute(
						"employee");
				loggedInUser = (TravelUserDTO) loggedObject;
			}
			loggerService
					.logPortalInfo(" end getLoggedInUser method of Configuration ");
			return loggedInUser;
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't  getLoggedInUser  ", e);
			return null;
		}

	}

	public String getUserName() {
		if (getLoggedInUser() != null)
			return getLoggedInUser().getAccountname();
		return null;
	}

	public String getUserPassword() {
		return null;
	}

	public String getEmpty() {
		return empty;
	}

	public void setEmpty(String empty) {
		this.empty = empty;
	}
}
