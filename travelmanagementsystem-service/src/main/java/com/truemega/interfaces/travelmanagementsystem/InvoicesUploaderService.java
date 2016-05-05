package com.truemega.interfaces.travelmanagementsystem;

import java.io.InputStream;

import javax.ejb.Remote;

import com.truemega.dto.UploadedInvoiceFileDTO;

@Remote
public interface InvoicesUploaderService {

	public void uploadInvoicesExcelSheet(InputStream ins, String fileName,
			String fileType, String userName) throws Exception;

	public void uploadInvoicesExcelSheet(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName)
			throws Exception;

	public void testStored() throws Exception;
}
