/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Administrator
 */
@Entity
@Table(name = "INVOICE_ATTACHMENT")
public class InvoiceAttachment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_invoice_attachment")
	@SequenceGenerator(name = "seq_invoice_attachment", sequenceName = "SEQ_INVOICE_ATTACHMENT", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "NAME")
	private String name;
	@Basic(optional = false)
	@Lob
	@Column(name = "CONTENT")
	private byte[] content;
	@Basic(optional = false)
	@Column(name = "TYPE")
	private String type;
	@Column(name = "MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	@Column(name = "SYSTEM_USER")
	private String systemUser;
	@JoinColumn(name = "UPLOADED_INVOICE_FILE_ID", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private UploadedInvoiceFile uploadedInvoiceFileId;

	public InvoiceAttachment() {
	}

	public InvoiceAttachment(Integer id) {
		this.id = id;
	}

	public InvoiceAttachment(Integer id, String name, byte[] content,
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

	public UploadedInvoiceFile getUploadedInvoiceFileId() {
		return uploadedInvoiceFileId;
	}

	public void setUploadedInvoiceFileId(
			UploadedInvoiceFile uploadedInvoiceFileId) {
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
		if (!(object instanceof InvoiceAttachment)) {
			return false;
		}
		InvoiceAttachment other = (InvoiceAttachment) object;
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

}
