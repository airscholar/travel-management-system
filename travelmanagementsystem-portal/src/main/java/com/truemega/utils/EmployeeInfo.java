package com.truemega.utils;

public class EmployeeInfo {
	
	private String employeeId;
	private String employeeName;
	private String accountName;
	private String department;
	private String subDepartment;
	private String exco;
	private String employeeMail;
	private String manager;
	private String managerMail;
	private String mobile;
	private String costCenter;
	
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(String subDepartment) {
		this.subDepartment = subDepartment;
	}

	public String getExco() {
		return exco;
	}
	public void setExco(String exco) {
		this.exco = exco;
	}
	public String getEmployeeMail() {
		return employeeMail;
	}
	public void setEmployeeMail(String employeeMail) {
		this.employeeMail = employeeMail;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getManagerMail() {
		return managerMail;
	}
	public void setManagerMail(String managerMail) {
		this.managerMail = managerMail;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	@Override
	public String toString() {
		return "EmployeeInfo [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", departmentId=" + department
				+ ", subDepartmentId=" + subDepartment + ", exco=" + exco
				+ ", employeeMail=" + employeeMail + ", manager=" + manager
				+ ", managerMail=" + managerMail + "]";
	}
	
	
	
}
