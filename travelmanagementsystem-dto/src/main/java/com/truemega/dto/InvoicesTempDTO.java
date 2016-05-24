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

public class InvoicesTempDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected InvoicesTempPKDTO invoicesTempPK;

	private String invoiceNumber;

	private String bookingFileNumber;

	private Date departureDate;

	private Date arrivalDate;

	private String employeeId;

	private String costCenter;

	private String costCenterDepartment;

	private String passengerName;

	private String serviceType;

	private String serviceDesc;

	private String routing;

	private String interDom;

	private Date checkIn;

	private Date checkOut;

	private Integer numberOfNights;

	private Integer numberOfRooms;

	private String airline;

	private String roomType;

	private String supplierName;

	private Double netAmount;

	private Double operationFees;

	private Double totalAmount;

	private String ticketNo;

	private String travelFormNumber;

	private String description;

	private Date from;

	private Date to;

	private Date invoiceDate;

	private UploadedInvoiceFileDTO uploadedInvoiceFileId;

	private String employeeDepartment;

	private Boolean generalMandatoryValid;

	private Boolean invoiceDateRangeValid;

	private Boolean airMandatoryValid;

	private Boolean otherMandatoryValid;

	private Boolean interDomValid;

	private Boolean numberOfNightsValid;

	private Boolean totalAmountValid;

	private Boolean hotelMandatoryValid;

	private Boolean serviceTypeValid;

	private Boolean serviceDescriptionValid;

	private Boolean supplierNameValid;

	private Boolean airlineValid;

	private Boolean roomTypeValid;

	private Boolean invoiceNumberValid;
	
	private Boolean ratesCombinationValid;

	public InvoicesTempDTO() {
	}

	public InvoicesTempDTO(InvoicesTempPKDTO invoicesTempPK) {
		this.invoicesTempPK = invoicesTempPK;
	}

	public InvoicesTempDTO(Integer invoiceOrder, String transactionId) {
		this.invoicesTempPK = new InvoicesTempPKDTO(invoiceOrder, transactionId);
	}

	public InvoicesTempPKDTO getInvoicesTempPK() {
		return invoicesTempPK;
	}

	public void setInvoicesTempPK(InvoicesTempPKDTO invoicesTempPK) {
		this.invoicesTempPK = invoicesTempPK;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getBookingFileNumber() {
		return bookingFileNumber;
	}

	public void setBookingFileNumber(String bookingFileNumber) {
		this.bookingFileNumber = bookingFileNumber;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenterDepartment() {
		return costCenterDepartment;
	}

	public void setCostCenterDepartment(String costCenterDepartment) {
		this.costCenterDepartment = costCenterDepartment;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getRouting() {
		return routing;
	}

	public void setRouting(String routing) {
		this.routing = routing;
	}

	public String getInterDom() {
		return interDom;
	}

	public void setInterDom(String interDom) {
		this.interDom = interDom;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public Integer getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(Integer numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getOperationFees() {
		return operationFees;
	}

	public void setOperationFees(Double operationFees) {
		this.operationFees = operationFees;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getTravelFormNumber() {
		return travelFormNumber;
	}

	public void setTravelFormNumber(String travelFormNumber) {
		this.travelFormNumber = travelFormNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public UploadedInvoiceFileDTO getUploadedInvoiceFileId() {
		return uploadedInvoiceFileId;
	}

	public void setUploadedInvoiceFileId(
			UploadedInvoiceFileDTO uploadedInvoiceFileId) {
		this.uploadedInvoiceFileId = uploadedInvoiceFileId;
	}

	public int hashCode() {
		int hash = 0;
		hash += (invoicesTempPK != null ? invoicesTempPK.hashCode() : 0);
		return hash;
	}

	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof InvoicesTempDTO)) {
			return false;
		}
		InvoicesTempDTO other = (InvoicesTempDTO) object;
		if ((this.invoicesTempPK == null && other.invoicesTempPK != null)
				|| (this.invoicesTempPK != null && !this.invoicesTempPK
						.equals(other.invoicesTempPK))) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "com.truemega.entities.InvoicesTemp[ invoicesTempPK="
				+ invoicesTempPK + " ]";
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Boolean getGeneralMandatoryValid() {
		return generalMandatoryValid;
	}

	public void setGeneralMandatoryValid(Boolean generalMandatoryValid) {
		this.generalMandatoryValid = generalMandatoryValid;
	}

	public Boolean getInvoiceDateRangeValid() {
		return invoiceDateRangeValid;
	}

	public void setInvoiceDateRangeValid(Boolean invoiceDateRangeValid) {
		this.invoiceDateRangeValid = invoiceDateRangeValid;
	}

	public Boolean getAirMandatoryValid() {
		return airMandatoryValid;
	}

	public void setAirMandatoryValid(Boolean airMandatoryValid) {
		this.airMandatoryValid = airMandatoryValid;
	}

	public Boolean getOtherMandatoryValid() {
		return otherMandatoryValid;
	}

	public void setOtherMandatoryValid(Boolean otherMandatoryValid) {
		this.otherMandatoryValid = otherMandatoryValid;
	}

	public Boolean getInterDomValid() {
		return interDomValid;
	}

	public void setInterDomValid(Boolean interDomValid) {
		this.interDomValid = interDomValid;
	}

	public Boolean getNumberOfNightsValid() {
		return numberOfNightsValid;
	}

	public void setNumberOfNightsValid(Boolean numberOfNightsValid) {
		this.numberOfNightsValid = numberOfNightsValid;
	}

	public Boolean getTotalAmountValid() {
		return totalAmountValid;
	}

	public void setTotalAmountValid(Boolean totalAmountValid) {
		this.totalAmountValid = totalAmountValid;
	}

	public Boolean getHotelMandatoryValid() {
		return hotelMandatoryValid;
	}

	public void setHotelMandatoryValid(Boolean hotelMandatoryValid) {
		this.hotelMandatoryValid = hotelMandatoryValid;
	}

	public Boolean getServiceTypeValid() {
		return serviceTypeValid;
	}

	public void setServiceTypeValid(Boolean serviceTypeValid) {
		this.serviceTypeValid = serviceTypeValid;
	}

	public Boolean getServiceDescriptionValid() {
		return serviceDescriptionValid;
	}

	public void setServiceDescriptionValid(Boolean serviceDescriptionValid) {
		this.serviceDescriptionValid = serviceDescriptionValid;
	}

	public Boolean getSupplierNameValid() {
		return supplierNameValid;
	}

	public void setSupplierNameValid(Boolean supplierNameValid) {
		this.supplierNameValid = supplierNameValid;
	}

	public Boolean getAirlineValid() {
		return airlineValid;
	}

	public void setAirlineValid(Boolean airlineValid) {
		this.airlineValid = airlineValid;
	}

	public Boolean getRoomTypeValid() {
		return roomTypeValid;
	}

	public void setRoomTypeValid(Boolean roomTypeValid) {
		this.roomTypeValid = roomTypeValid;
	}

	public Boolean getInvoiceNumberValid() {
		return invoiceNumberValid;
	}

	public void setInvoiceNumberValid(Boolean invoiceNumberValid) {
		this.invoiceNumberValid = invoiceNumberValid;
	}

	public Boolean getRatesCombinationValid() {
		return ratesCombinationValid;
	}

	public void setRatesCombinationValid(Boolean ratesCombinationValid) {
		this.ratesCombinationValid = ratesCombinationValid;
	}

}
