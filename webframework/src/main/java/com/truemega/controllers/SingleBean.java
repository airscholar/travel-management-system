package com.truemega.controllers;

import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;

public abstract class SingleBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected UIOperation screenMode;
	protected Status operationStatus;
	protected String operationMessage;
	
	public String getOperationMessage() {
		return operationMessage;
	}
	public void setOperationMessage(String operationMessage) {
		this.operationMessage = operationMessage;
	}
	public UIOperation getScreenMode() {
		return screenMode;
	}
	public void setScreenMode(UIOperation screenMode) {
		this.screenMode = screenMode;
	}
	
	public Status getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(Status operationStatus) {
		this.operationStatus = operationStatus;
	}
	public abstract void save();
	public abstract void update();
}
