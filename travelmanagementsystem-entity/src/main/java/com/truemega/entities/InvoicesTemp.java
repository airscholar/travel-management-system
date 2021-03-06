/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Administrator
 */
@Entity
@Table(name = "INVOICES_TEMP")
public class InvoicesTemp implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected InvoicesTempPK invoicesTempPK;
	@Column(name = "INVOICE_NUMBER")
	private String invoiceNumber;
	@Column(name = "BOOKING_FILE_NUMBER")
	private String bookingFileNumber;
	@Column(name = "DEPARTURE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDate;
	@Column(name = "ARRIVAL_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDate;
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	@Column(name = "EMPLOYEE_DEPARTMENT")
	private String employeeDepartment;
	@Column(name = "COST_CENTER")
	private String costCenter;
	@Column(name = "COST_CENTER_DEPARTMENT")
	private String costCenterDepartment;
	@Column(name = "PASSENGER_NAME")
	private String passengerName;
	@Column(name = "SERVICE_TYPE")
	private String serviceType;
	@Column(name = "SERVICE_DESC")
	private String serviceDesc;
	@Column(name = "ROUTING")
	private String routing;
	@Column(name = "INTER_DOM")
	private String interDom;
	@Column(name = "CHECK_IN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkIn;
	@Column(name = "CHECK_OUT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOut;
	@Column(name = "NUMBER_OF_NIGHTS")
	private Integer numberOfNights;
	@Column(name = "NUMBER_OF_ROOMS")
	private Integer numberOfRooms;
	@Column(name = "AIRLINE")
	private String airline;
	@Column(name = "ROOM_TYPE")
	private String roomType;
	@Column(name = "SUPPLIER_NAME")
	private String supplierName;
	@Column(name = "NET_AMOUNT")
	private Double netAmount;
	@Column(name = "OPERATION_FEES")
	private Double operationFees;
	@Column(name = "TOTAL_AMOUNT")
	private Double totalAmount;
	@Column(name = "TICKET_NO")
	private String ticketNo;
	@Column(name = "TRAVEL_FORM_NUMBER")
	private String travelFormNumber;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "FROM_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date from;
	@Column(name = "TO_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date to;

	@Column(name = "DESTINATION")
	private String destination;

	@Column(name = "CITY")
	private String city;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "TRIP_PURPOSE")
	private String tripPurpose;

	@Column(name = "INVOICE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceDate;

	@JoinColumn(name = "UPLOADED_INVOICE_FILE_ID", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private UploadedInvoiceFile uploadedInvoiceFileId;

	@Column(name = "GENERAL_MANDATORY_VALID")
	private Boolean generalMandatoryValid = true;
	@Column(name = "INVOICE_DATE_RANGE_VALID")
	private Boolean invoiceDateRangeValid = true;
	@Column(name = "AIR_MANDATORY_VALID")
	private Boolean airMandatoryValid = true;
	@Column(name = "OTHER_MANDATORY_VALID")
	private Boolean otherMandatoryValid = true;
	@Column(name = "INTER_DOM_VALID")
	private Boolean interDomValid = true;
	@Column(name = "NUMBER_OF_NIGHTS_VALID")
	private Boolean numberOfNightsValid = true;
	@Column(name = "TOTAL_AMOUNT_VALID")
	private Boolean totalAmountValid = true;
	@Column(name = "HOTEL_MANDATORY_VALID")
	private Boolean hotelMandatoryValid = true;
	@Column(name = "SERVICE_TYPE_VALID")
	private Boolean serviceTypeValid = true;
	@Column(name = "SERVICE_DESC_VALID")
	private Boolean serviceDescriptionValid = true;
	@Column(name = "SUPPLIER_NAME_VALID")
	private Boolean supplierNameValid = true;
	@Column(name = "AIRLINE_VALID")
	private Boolean airlineValid = true;
	@Column(name = "ROOM_TYPE_VALID")
	private Boolean roomTypeValid = true;
	@Column(name = "INVOICE_NUMBER_VALID")
	private Boolean invoiceNumberValid = true;
	@Column(name = "RATES_COMBINATION_VALID")
	private Boolean ratesCombinationValid = true;

	public InvoicesTemp() {
	}

	public InvoicesTemp(InvoicesTempPK invoicesTempPK) {
		this.invoicesTempPK = invoicesTempPK;
	}

	public InvoicesTemp(Integer invoiceOrder, String transactionId) {
		this.invoicesTempPK = new InvoicesTempPK(invoiceOrder, transactionId);
	}

	public InvoicesTempPK getInvoicesTempPK() {
		return invoicesTempPK;
	}

	public void setInvoicesTempPK(InvoicesTempPK invoicesTempPK) {
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
		hash += (invoicesTempPK != null ? invoicesTempPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof InvoicesTemp)) {
			return false;
		}
		InvoicesTemp other = (InvoicesTemp) object;
		if ((this.invoicesTempPK == null && other.invoicesTempPK != null)
				|| (this.invoicesTempPK != null && !this.invoicesTempPK
						.equals(other.invoicesTempPK))) {
			return false;
		}
		return true;
	}

	@Override
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

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTripPurpose() {
		return tripPurpose;
	}

	public void setTripPurpose(String tripPurpose) {
		this.tripPurpose = tripPurpose;
	}

}
