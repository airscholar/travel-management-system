package com.truemega.controllers;

import java.io.Serializable;

import com.truemega.enums.UIOperation;

public abstract class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean redirectLogin = true, notAuthorized = true;

	public abstract void redirect(String url, UIOperation mode);
	
	public void log(String message) {
		// TODO
		/*
		 * Logging implementation
		 */
	}

	public abstract void load();

	public abstract boolean validate();

	public Boolean getNotAuthorized() {
		return notAuthorized;
	}

	public void setNotAuthorized(Boolean notAuthorized) {
		this.notAuthorized = notAuthorized;
	}

	public Boolean getRedirectLogin() {
		return redirectLogin;
	}

	public void setRedirectLogin(Boolean redirectLogin) {
		this.redirectLogin = redirectLogin;
	}
}
