package com.truemega.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Administrator
 */
@Entity
@Table(name = "MAX_MIN_VALUES")
public class MaxMinValues implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "MAX_MIN_VALUE")
	private Double maxMinValue;

	public MaxMinValues() {
	}

	public MaxMinValues(Integer id) {
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
		if (!(object instanceof MaxMinValues)) {
			return false;
		}
		MaxMinValues other = (MaxMinValues) object;
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
