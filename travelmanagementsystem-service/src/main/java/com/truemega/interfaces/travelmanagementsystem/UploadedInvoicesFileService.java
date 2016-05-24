package com.truemega.interfaces.travelmanagementsystem;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.UploadedInvoiceFileDTO;

@Remote
public interface UploadedInvoicesFileService {

	public List<UploadedInvoiceFileDTO> listUploadedInvoiceTempFiles(
			String userName);

	public List<UploadedInvoiceFileDTO> listUploadedInvoiceActualFiles(
			String userName);

	public UploadedInvoiceFileDTO findUploadedInvoiceFileById(Integer fileId,
			String userName);

	public UploadedInvoiceFileDTO updateUploadedInvoice(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName)
			throws Exception;

	public UploadedInvoiceFileDTO takeAction(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName)
			throws Exception;
	
	public List<UploadedInvoiceFileDTO> listUploadedInvoiceByMonth(
			String month, String userName);

	public void testNotification();
}
