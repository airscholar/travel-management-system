/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 

 */

public class UploadedInvoiceFileDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private byte[] content;

	private String type;

	private Date modificationDate;

	private String systemUser;

	private Boolean approved;

	private String rejectionReason;

	private String rejectingUser;

	private Date actionTakenDate;

	public UploadedInvoiceFileDTO() {
	}

	public UploadedInvoiceFileDTO(Integer id) {
		this.id = id;
	}

	public UploadedInvoiceFileDTO(Integer id, String name, byte[] content,
			String type) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(String systemUser) {
		this.systemUser = systemUser;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public String getRejectingUser() {
		return rejectingUser;
	}

	public void setRejectingUser(String rejectingUser) {
		this.rejectingUser = rejectingUser;
	}

	public Date getActionTakenDate() {
		return actionTakenDate;
	}

	public void setActionTakenDate(Date actionTakenDate) {
		this.actionTakenDate = actionTakenDate;
	}

	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof UploadedInvoiceFileDTO)) {
			return false;
		}
		UploadedInvoiceFileDTO other = (UploadedInvoiceFileDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "com.truemega.entities.UploadedInvoiceFile[ id=" + id + " ]";
	}

}
