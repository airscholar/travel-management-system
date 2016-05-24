package com.truemega.beans.travelmanagementsystem;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.UploadedInvoiceFileDTO;
import com.truemega.interfaces.travelmanagementsystem.UploadedInvoicesFileService;
import com.truemega.logger.LoggerService;

@ManagedBean(name = "uploadedActualInvoicesList")
@ViewScoped
public class UploadedActualInvoicesListBean extends TravelListBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoggerService loggerService = new LoggerService();

	@EJB
	private UploadedInvoicesFileService uploadedInvoicesFileService;

	private List<UploadedInvoiceFileDTO> uploadedInvoiceFileDTOs;

	@PostConstruct
	@Override
	public void load() {
		loggerService.logPortalInfo("Start load userName >> " + getUserName());

		uploadedInvoiceFileDTOs = uploadedInvoicesFileService
				.listUploadedInvoiceActualFiles(getUserName());

	}

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<UploadedInvoiceFileDTO> getUploadedInvoiceFileDTOs() {
		return uploadedInvoiceFileDTOs;
	}

	public void setUploadedInvoiceFileDTOs(
			List<UploadedInvoiceFileDTO> uploadedInvoiceFileDTOs) {
		this.uploadedInvoiceFileDTOs = uploadedInvoiceFileDTOs;
	}

}
