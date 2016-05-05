/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Administrator
 */
@Embeddable
public class InvoicesTempPK implements Serializable {
	@Basic(optional = false)
	@Column(name = "INVOICE_ORDER")
	private Integer invoiceOrder;
	@Basic(optional = false)
	@Column(name = "TRANSACTION_ID")
	private String transactionId;

	public InvoicesTempPK() {
	}

	public InvoicesTempPK(Integer invoiceOrder, String transactionId) {
		this.invoiceOrder = invoiceOrder;
		this.transactionId = transactionId;
	}

	public Integer getInvoiceOrder() {
		return invoiceOrder;
	}

	public void setInvoiceOrder(Integer invoiceOrder) {
		this.invoiceOrder = invoiceOrder;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (invoiceOrder != null ? invoiceOrder.hashCode() : 0);
		hash += (transactionId != null ? transactionId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof InvoicesTempPK)) {
			return false;
		}
		InvoicesTempPK other = (InvoicesTempPK) object;
		if ((this.invoiceOrder == null && other.invoiceOrder != null)
				|| (this.invoiceOrder != null && !this.invoiceOrder
						.equals(other.invoiceOrder))) {
			return false;
		}
		if ((this.transactionId == null && other.transactionId != null)
				|| (this.transactionId != null && !this.transactionId
						.equals(other.transactionId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.truemega.entities.InvoicesTempPK[ invoiceOrder="
				+ invoiceOrder + ", transactionId=" + transactionId + " ]";
	}

}
