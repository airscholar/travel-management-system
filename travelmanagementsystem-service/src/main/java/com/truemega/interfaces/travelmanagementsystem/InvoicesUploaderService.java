package com.truemega.interfaces.travelmanagementsystem;

import java.io.InputStream;

import javax.ejb.Remote;

import com.truemega.dto.UploadedInvoiceFileDTO;

@Remote
public interface InvoicesUploaderService {

	public UploadedInvoiceFileDTO uploadInvoicesExcelSheet(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName,String year)
			throws Exception;

	
}
