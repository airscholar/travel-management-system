package com.truemega.interfaces.travelmanagementsystem;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.entities.InvoicesTemp;

@Remote
public interface InvoicesTempService {

	public List<InvoicesTemp> listInvoicesTempsByFileID(Integer fileID)
			throws Exception;
}
