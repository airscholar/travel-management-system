package com.truemega.controllers;

public abstract class ListBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	
	protected String operationMessage;
	
	public String getOperationMessage() {
		return operationMessage;
	}
	public void setOperationMessage(String operationMessage) {
		this.operationMessage = operationMessage;
	}
	public abstract void delete();
}
