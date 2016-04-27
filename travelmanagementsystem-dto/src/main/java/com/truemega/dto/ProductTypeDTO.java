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
 * @author Administrator
 */

public class ProductTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String name;
	private Date modificationDate;

	private String systemUser;

	private Boolean status;

	private String description;

	private ServiceTypeDTO serviceId;

	public ProductTypeDTO() {
	}

	public ProductTypeDTO(Integer id) {
		this.id = id;
	}

	public ProductTypeDTO(Integer id, String name, ServiceTypeDTO serviceId) {
		this.id = id;
		this.name = name;
		this.serviceId = serviceId;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ServiceTypeDTO getServiceId() {
		return serviceId;
	}

	public void setServiceId(ServiceTypeDTO serviceId) {
		this.serviceId = serviceId;
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
		if (!(object instanceof ProductTypeDTO)) {
			return false;
		}
		ProductTypeDTO other = (ProductTypeDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.truemega.entities.ProductType[ id=" + id + " ]";
	}

}
