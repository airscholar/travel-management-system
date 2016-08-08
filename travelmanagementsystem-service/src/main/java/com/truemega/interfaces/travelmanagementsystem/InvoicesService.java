package com.truemega.interfaces.travelmanagementsystem;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.InvoicesDTO;

@Remote
public interface InvoicesService {

	public List<InvoicesDTO> listInvoicesByFileID(Integer fileID,
			String userName) throws Exception;
}
