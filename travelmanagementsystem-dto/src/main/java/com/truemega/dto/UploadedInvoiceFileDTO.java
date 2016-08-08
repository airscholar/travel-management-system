/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.dto;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * 

 */

public class UploadedInvoiceFileDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private byte[] content;

	private String type;

	private Date modificationDate = new Date();

	private String systemUser;

	private Boolean approved = null;

	private String rejectionReason;

	private String actionTaker;

	private Date actionTakenDate = null;

	private List<InvoiceAttachmentDTO> invoiceAttachmentDTOs = new ArrayList<InvoiceAttachmentDTO>();

	private Boolean templateTable = true;

	private String invoicesMonth;

	private String operationMsg;

	private StreamedContent file;

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

	public List<InvoiceAttachmentDTO> getInvoiceAttachmentDTOs() {
		return invoiceAttachmentDTOs;
	}

	public void setInvoiceAttachmentDTOs(
			List<InvoiceAttachmentDTO> invoiceAttachmentDTOs) {
		this.invoiceAttachmentDTOs = invoiceAttachmentDTOs;
	}

	public String getActionTaker() {
		return actionTaker;
	}

	public void setActionTaker(String actionTaker) {
		this.actionTaker = actionTaker;
	}

	public Boolean getTemplateTable() {
		return templateTable;
	}

	public void setTemplateTable(Boolean templateTable) {
		this.templateTable = templateTable;
	}

	public String getInvoicesMonth() {
		return invoicesMonth;
	}

	public void setInvoicesMonth(String invoicesMonth) {
		this.invoicesMonth = invoicesMonth;
	}

	public String getOperationMsg() {
		return operationMsg;
	}

	public void setOperationMsg(String operationMsg) {
		this.operationMsg = operationMsg;
	}

	public StreamedContent getFile() {
		InputStream is = new ByteArrayInputStream(content);
		file = new DefaultStreamedContent(is, type, name);
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
