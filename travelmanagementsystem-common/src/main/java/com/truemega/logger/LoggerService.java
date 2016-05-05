package com.truemega.logger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

import javax.annotation.PreDestroy;

import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String logLayout = "%d{dd MMM yyyy HH:mm:ss.SSS}, %C{1} , %M , %L , %m %n";
	private final static String logDatePattern = "'.'yyyy-MM-dd";

	static Logger lplogger = Logger.getLogger("lpLogger");
	private static Logger logger = Logger.getLogger("lpLogger");

	private static int count = 0;

	public Logger getLoggerService() {
		if (count == 0) {
			initLogger();
			count++;
		}
		return lplogger;
	}

	public void initLogger() {
		Appender logFileAppender = null;
		try {

			String path = "TravelManagement" + File.separator + "Logs"
					+ File.separator + "TravelManagement.log";
			logFileAppender = new DailyRollingFileAppender(new PatternLayout(
					logLayout), path, logDatePattern);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lplogger.addAppender(logFileAppender);
		lplogger.setLevel(Level.DEBUG);
		lplogger.setAdditivity(false);
	}

	@PreDestroy
	public void destroy() {
		Enumeration<Appender> e = lplogger.getAllAppenders();

		while (e.hasMoreElements()) {
			Appender app = (Appender) e.nextElement();
			app.close();
		}

		LogManager.shutdown();
	}

	public void versionLog(String version) {
		logger.error("Portal Layer Version : " + version);
	}

	public void logPortalError(String message, Exception e) {
		if (message != null)
			logger.error("Portal Layer : " + message, e);
		else
			logger.error("Portal Layer : ", e);
	}

	public void logServiceError(String message, Exception e) {
		if (message != null)
			logger.error("Service Layer : " + message, e);
		else
			logger.error("Service Layer : ", e);
	}

	public void logMapperError(String message, Exception e) {
		if (message != null)
			logger.error("Mapper Layer : " + message, e);
		else
			logger.error("Mapper Layer : ", e);
	}

	public void logPortalInfo(String message) {
		logger.info("Portal Layer : " + message);
	}

	public void logServiceInfo(String message) {
		logger.info("Service Layer : " + message);
	}

	public void logMapperInfo(String message) {
		logger.info("Mapper Layer : " + message);
	}

}
