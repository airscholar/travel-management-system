/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author hp
 */
@Entity
@Table(name = "TMS_USER")
public class TravelUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "traveluser_seq")
	@SequenceGenerator(name = "traveluser_seq", sequenceName = "TRAVELUSER_SEQ", allocationSize = 1)
	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;

	@Column(name = "FULLNAME")
	private String fullname;

	@Column(name = "ACCOUNTNAME")
	private String accountname;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	// message="Invalid email")//if the field contains email address consider
	// using this annotation to enforce field validation

	@Column(name = "EMAIL")
	private String email;
	// @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
	// message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the
	// field contains phone or fax number consider using this annotation to
	// enforce field validation

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "DEPARTMENT")
	private String department;

	@Column(name = "COSTCENTER")
	private String costcenter;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "EMPLOYEE_TYPE")
	private String employeeType;
	@Column(name = "STATUS")
	private Boolean status;

	@Column(name = "STAFFID")
	private Integer staffid;

	public TravelUser() {
	}

	public TravelUser(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public TravelUser(Integer employeeId, Integer staffid) {
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
		if (!(object instanceof TravelUser)) {
			return false;
		}
		TravelUser other = (TravelUser) object;
		if ((this.employeeId == null && other.employeeId != null)
				|| (this.employeeId != null && !this.employeeId
						.equals(other.employeeId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.truemega.entities.RmsUser[ employeeId=" + employeeId + " ]";
	}

}
