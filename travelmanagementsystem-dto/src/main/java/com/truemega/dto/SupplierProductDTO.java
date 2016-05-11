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

public class SupplierProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date modificationDate;

	private String systemUser;

	private Boolean status = true;

	private String description;

	private SupplierDTO supplierId;

	private ProductTypeDTO productId;

	public SupplierProductDTO() {
	}

	public SupplierProductDTO(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public SupplierDTO getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(SupplierDTO supplierId) {
		this.supplierId = supplierId;
	}

	public ProductTypeDTO getProductId() {
		return productId;
	}

	public void setProductId(ProductTypeDTO productId) {
		this.productId = productId;
	}

	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SupplierProductDTO)) {
			return false;
		}
		SupplierProductDTO other = (SupplierProductDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "com.truemega.entities.SupplierProduct[ id=" + id + " ]";
	}

}
