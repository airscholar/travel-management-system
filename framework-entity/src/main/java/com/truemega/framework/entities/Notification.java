package com.truemega.framework.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION")
public class Notification implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notification_seq")
	@SequenceGenerator(name="notification_seq",sequenceName="NOTIFICATION_SEQ",allocationSize=1)
    @Column(name = "ID")
    private Integer id;
	
	@Lob()
    @Column(name = "ATTACH_FILE", unique = false, nullable = false)
    private byte[] attachFile;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="TEMPLATENAME")
	private String templateName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(byte[] attachFile) {
		this.attachFile = attachFile;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
}
