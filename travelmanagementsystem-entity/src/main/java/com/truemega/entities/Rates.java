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
@Table(name = "RATES")
public class Rates implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rate")
	@SequenceGenerator(name = "seq_rate", sequenceName = "SEQ_RATES", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "RATE")
	private Double rate;
	@Basic(optional = false)
	@Column(name = "YEAR")
	private int year;
	@Column(name = "MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	@Column(name = "SYSTEM_USER")
	private String systemUser;
	@Column(name = "STATUS")
	private Boolean status;
	@Column(name = "DESCRIPTION")
	private String description;
	@JoinColumn(name = "SUPPLIER_PRODUCT_ID", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private SupplierProduct supplierProductId;
	@JoinColumn(name = "ROOM_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne
	private RoomType roomTypeId;
	@JoinColumn(name = "AIRLINE_ID", referencedColumnName = "ID")
	@ManyToOne
	private Airline airlineId;

	public Rates() {
	}

	public Rates(Integer id) {
		this.id = id;
	}

	public Rates(Integer id, Double rate, int year) {
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

	public SupplierProduct getSupplierProductId() {
		return supplierProductId;
	}

	public void setSupplierProductId(SupplierProduct supplierProductId) {
		this.supplierProductId = supplierProductId;
	}

	public RoomType getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(RoomType roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Airline getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Airline airlineId) {
		this.airlineId = airlineId;
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
		if (!(object instanceof Rates)) {
			return false;
		}
		Rates other = (Rates) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.truemega.entities.Rates[ id=" + id + " ]";
	}

}
