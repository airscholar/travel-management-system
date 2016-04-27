package com.truemega.utils;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.truemega.dto.TravelUserDTO;
import com.truemega.logger.LoggerService;

public class HttpJSFUtils {

	public static void redirect(String page) {
		LoggerService loggerService1 = new LoggerService();
		loggerService1.logPortalInfo(" start redirect method of HttpJSFUtils ");
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							FacesContext.getCurrentInstance()
									.getExternalContext()
									.getRequestContextPath()
									+ "/faces/" + page);
		} catch (IOException e) {
			loggerService1.logPortalError("can't redirect   ", e);
		}
		loggerService1.logPortalInfo(" end redirect method of HttpJSFUtils ");
	}

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static TravelUserDTO getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return (TravelUserDTO) session.getAttribute("username");
	}
}
