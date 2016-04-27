/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.dto;

import java.io.Serializable;

/**
 * 
 * @author hp
 */

public class TravelUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation

	private Integer employeeId;

	private String fullname;

	private String accountname;

	private String email;

	private String phone;

	private String department;

	private String costcenter;

	private String comments;

	private String employeeType;

	private Boolean status;

	private Integer staffid;

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	private String adminPassword;

	public TravelUserDTO() {
	}

	public TravelUserDTO(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public TravelUserDTO(Integer employeeId, Integer staffid) {
		this.employeeId = employeeId;
		this.staffid = staffid;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getStaffid() {
		return staffid;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (employeeId != null ? employeeId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TravelUserDTO)) {
			return false;
		}
		TravelUserDTO other = (TravelUserDTO) object;
		if ((this.employeeId == null && other.employeeId != null)
				|| (this.employeeId != null && !this.employeeId
						.equals(other.employeeId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.truemega.entities.TmsUser[ employeeId=" + employeeId + " ]";
	}

}
