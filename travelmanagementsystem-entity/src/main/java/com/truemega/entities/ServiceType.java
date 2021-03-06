/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * @author Administrator
 */
@Entity
@Table(name = "SERVICE_TYPE")
public class ServiceType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "seq_service_type")
	// @SequenceGenerator(name = "seq_service_type", sequenceName =
	// "SEQ_SERVICE_TYPE", allocationSize = 1)
	// ------------------------------------
	// @TableGenerator(name = "TABLE_GEN", table = "SEQUENCE_TABLE",
	// pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue =
	// "SERVICE_SEQ")
	// @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "NAME")
	private String name;

	public ServiceType() {
	}

	public ServiceType(Integer id) {
		this.id = id;
	}

	public ServiceType(Integer id, String name) {
		this.id = id;
		this.name = name;
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
		if (!(object instanceof ServiceType)) {
			return false;
		}
		ServiceType other = (ServiceType) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.truemega.entities.ServiceType[ id=" + id + " ]";
	}

}
