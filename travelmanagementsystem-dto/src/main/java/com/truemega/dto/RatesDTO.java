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

public class RatesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Double rate;

	private int year;

	private Date modificationDate;

	private String systemUser;

	private Boolean status = true;

	private String description;

	private SupplierProductDTO supplierProductId;

	private RoomTypeDTO roomTypeId;

	private AirlineDTO airlineId;

	public RatesDTO() {
	}

	public RatesDTO(Integer id) {
		this.id = id;
	}

	public RatesDTO(Integer id, Double rate, int year) {
		this.id = id;
		this.rate = rate;
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public SupplierProductDTO getSupplierProductId() {
		return supplierProductId;
	}

	public void setSupplierProductId(SupplierProductDTO supplierProductId) {
		this.supplierProductId = supplierProductId;
	}

	public RoomTypeDTO getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(RoomTypeDTO roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public AirlineDTO getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(AirlineDTO airlineId) {
		this.airlineId = airlineId;
	}

	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof RatesDTO)) {
			return false;
		}
		RatesDTO other = (RatesDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "com.truemega.entities.Rates[ id=" + id + " ]";
	}

}
