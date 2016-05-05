package com.truemega.beans;

import javax.annotation.PostConstruct;

import com.truemega.controllers.SingleBean;
import com.truemega.dto.TravelUserDTO;
import com.truemega.enums.UIOperation;
import com.truemega.logger.LoggerService;
import com.truemega.utils.HttpJSFUtils;

public abstract class TravelSingleBean extends SingleBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TravelUserDTO loggedInUser;
	private LoggerService loggerService = new LoggerService();

	@PostConstruct
	public void init() {

	}

	@Override
	public void redirect(String url, UIOperation mode) {
		// TODO Auto-generated method stub

	}

	public TravelUserDTO getLoggedInUser() {

		if (loggedInUser == null) {
			Object loggedObject = HttpJSFUtils.getSession().getAttribute(
					"employee");
			loggedInUser = (TravelUserDTO) loggedObject;
		}

		return loggedInUser;
	}

	public String getUserName() {
		return getLoggedInUser().getAccountname();
	}

	public String getUserPassword() {
		return null;
	}

}
