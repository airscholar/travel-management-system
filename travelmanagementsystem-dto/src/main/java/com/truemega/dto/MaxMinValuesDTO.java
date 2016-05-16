package com.truemega.dto;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 */

public class MaxMinValuesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Double maxMinValue;

	public MaxMinValuesDTO() {
	}

	public MaxMinValuesDTO(Integer id) {
		this.id = id;
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

	public Double getMaxMinValue() {
		return maxMinValue;
	}

	public void setMaxMinValue(Double maxMinValue) {
		this.maxMinValue = maxMinValue;
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
		if (!(object instanceof MaxMinValuesDTO)) {
			return false;
		}
		MaxMinValuesDTO other = (MaxMinValuesDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.truemega.entities.MaxMinValues[ id=" + id + " ]";
	}

}
