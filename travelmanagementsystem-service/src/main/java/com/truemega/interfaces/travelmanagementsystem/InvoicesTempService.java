package com.truemega.interfaces.travelmanagementsystem;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.InvoicesTempDTO;

@Remote
public interface InvoicesTempService {

	public List<InvoicesTempDTO> listInvoicesTempsByFileID(Integer fileID,
			String userName) throws Exception;
}
