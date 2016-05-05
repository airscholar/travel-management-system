package com.truemega.interfaces.travelmanagementsystem;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.UploadedInvoiceFileDTO;

@Remote
public interface UploadedInvoicesFileService {

	public List<UploadedInvoiceFileDTO> listUploadedInvoiceFiles(String userName);
}
