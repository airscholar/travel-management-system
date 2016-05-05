/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.dto;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * 
 * @author Administrator
 */

public class InvoiceAttachmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private byte[] content;

	private String type;

	private Date modificationDate = new Date();

	private String systemUser;

	private UploadedInvoiceFileDTO uploadedInvoiceFileId;

	private StreamedContent file;

	public InvoiceAttachmentDTO() {
	}

	public InvoiceAttachmentDTO(Integer id) {
		this.id = id;
	}

	public InvoiceAttachmentDTO(Integer id, String name, byte[] content,
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

	public UploadedInvoiceFileDTO getUploadedInvoiceFileId() {
		return uploadedInvoiceFileId;
	}

	public void setUploadedInvoiceFileId(
			UploadedInvoiceFileDTO uploadedInvoiceFileId) {
		this.uploadedInvoiceFileId = uploadedInvoiceFileId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof InvoiceAttachmentDTO)) {
			return false;
		}
		InvoiceAttachmentDTO other = (InvoiceAttachmentDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.truemega.entities.InvoiceAttachment[ id=" + id + " ]";
	}

	public StreamedContent getFile() {

		InputStream is = new ByteArrayInputStream(content); // read
															// from
		// source
		file = new DefaultStreamedContent(is, type, name);

		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
