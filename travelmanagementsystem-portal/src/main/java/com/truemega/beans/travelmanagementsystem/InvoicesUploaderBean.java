package com.truemega.beans.travelmanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import com.truemega.beans.TravelSingleBean;
import com.truemega.dto.InvoiceAttachmentDTO;
import com.truemega.dto.UploadedInvoiceFileDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.travelmanagementsystem.InvoicesUploaderService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "invoicesUploader")
@ViewScoped
public class InvoicesUploaderBean extends TravelSingleBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private InvoicesUploaderService invoicesUploaderService;

	private LoggerService loggerService = new LoggerService();

	private UploadedInvoiceFileDTO uploadedInvoiceFileDTO = new UploadedInvoiceFileDTO();

	List<UploadedInvoiceFileDTO> uploadedInvoiceFileDTOs = new ArrayList<UploadedInvoiceFileDTO>();

	private boolean uploaded = false;

	@PostConstruct
	public void init() {
		loggerService.logPortalInfo("Start init userName >> " + getUserName());

		Screen screen = (Screen) HttpJSFUtils.getSession().getAttribute(
				"screen");
		switch (screen.getScreenMode()) {
		case 0:
			screenMode = UIOperation.ADD;
			break;
		case 1:
			screenMode = UIOperation.UPDATE;
			load();
			break;
		case 2:
			screenMode = UIOperation.VIEW;
			load();
			break;
		}
	}

	public void uploadInvoiceFile(FileUploadEvent event) {

		loggerService.logPortalInfo(getUserName()
				+ ">> Start uploadAttachmentFile()");
		byte[] bFile = new byte[(int) event.getFile().getSize()];

		try {
			event.getFile().getInputstream().read(bFile);

			uploadedInvoiceFileDTO.setSystemUser(getUserName());

			uploadedInvoiceFileDTO.setContent(bFile);
			uploadedInvoiceFileDTO.setName(event.getFile().getFileName());
			uploadedInvoiceFileDTO.setType(event.getFile().getContentType());
			uploadedInvoiceFileDTOs.add(uploadedInvoiceFileDTO);
			if (uploadedInvoiceFileDTOs.size() > 1)
				uploadedInvoiceFileDTOs.remove(0);
			loggerService.logPortalInfo(getUserName()
					+ ">> End uploadAttachmentFile() Successfully");
		} catch (Exception e) {
			loggerService.logPortalError(getUserName()
					+ ">> End uploadAttachmentFile() With Error", e);
		}

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

		// here to save
		if (validate()) {
			long start = new Date().getTime();
			loggerService.logPortalInfo("Start uploadFile userName >> "
					+ getUserName());
			try {

				uploadedInvoiceFileDTO = invoicesUploaderService
						.uploadInvoicesExcelSheet(uploadedInvoiceFileDTO,
								getUserName(), getYear(uploadedInvoiceFileDTO
										.getInvoicesMonth()));
				if (uploadedInvoiceFileDTO.getOperationMsg() != null) {
					operationMessage = uploadedInvoiceFileDTO.getOperationMsg();
					operationStatus = Status.FAIL;
				} else {
					operationMessage = "Invoice Excel Sheet Uploaded Successfully";
					operationStatus = Status.SUCCESS;
				}

				uploaded = true;
				loggerService
						.logPortalInfo("End uploadFile Successfully userName >> "
								+ getUserName());
			} catch (Exception e) {
				operationMessage = "Invoice Excel Sheet Uploading Failed.";
				operationStatus = Status.FAIL;
				loggerService
						.logPortalInfo("End uploadFile With Error userName >> "
								+ e.getMessage());

				loggerService.logPortalError(
						"End uploadFile With Error userName >> "
								+ e.getMessage(), e);

			}

			System.out
					.println("taken time = " + (new Date().getTime() - start));
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		System.out.println("looooooooooooooooooood");
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub

		if (uploadedInvoiceFileDTOs.size() == 0) {
			operationMessage = "You have to Upload Invoices Excel Sheet";
			operationStatus = Status.FAIL;
			loggerService
					.logPortalInfo("You have to Upload Invoices Excel Sheet");

			return false;
		} else {
			return true;
		}
	}

	public void uploadAttachmentFile(FileUploadEvent event) {
		loggerService.logPortalInfo(getUserName()
				+ ">> Start uploadAttachmentFile()");
		byte[] bFile = new byte[(int) event.getFile().getSize()];
		InvoiceAttachmentDTO invoiceAttachmentDTO;
		try {
			event.getFile().getInputstream().read(bFile);
			invoiceAttachmentDTO = new InvoiceAttachmentDTO();
			invoiceAttachmentDTO.setSystemUser(getUserName());

			invoiceAttachmentDTO.setContent(bFile);
			invoiceAttachmentDTO.setName(event.getFile().getFileName());
			invoiceAttachmentDTO.setType(event.getFile().getContentType());
			uploadedInvoiceFileDTO.getInvoiceAttachmentDTOs().add(
					invoiceAttachmentDTO);
			loggerService.logPortalInfo(getUserName()
					+ ">> End uploadAttachmentFile() Successfully");
		} catch (Exception e) {
			loggerService.logPortalError(getUserName()
					+ ">> End uploadAttachmentFile() With Error", e);
		}
	}

	public void uploadAttachmentFile66(FileUploadEvent event) {
		loggerService.logPortalInfo(getUserName()
				+ ">> Start uploadAttachmentFile()");
		byte[] bFile = new byte[(int) event.getFile().getSize()];
		InvoiceAttachmentDTO invoiceAttachmentDTO;
		try {
			event.getFile().getInputstream().read(bFile);

			uploadedInvoiceFileDTO.setSystemUser(getUserName());

			uploadedInvoiceFileDTO.setContent(bFile);
			uploadedInvoiceFileDTO.setName(event.getFile().getFileName());
			uploadedInvoiceFileDTO.setType(event.getFile().getContentType());
			uploadedInvoiceFileDTOs.add(uploadedInvoiceFileDTO);
			if (uploadedInvoiceFileDTOs.size() > 1)
				uploadedInvoiceFileDTOs.remove(0);
			loggerService.logPortalInfo(getUserName()
					+ ">> End uploadAttachmentFile() Successfully");
		} catch (Exception e) {
			loggerService.logPortalError(getUserName()
					+ ">> End uploadAttachmentFile() With Error", e);
		}
	}

	public void deleteAttachmentFile(ActionEvent event) {
		loggerService.logPortalInfo(getUserName()
				+ ">> Start deleteAttachmentFile()");
		InvoiceAttachmentDTO invoiceAttachmentDTO = (InvoiceAttachmentDTO) event
				.getComponent().getAttributes().get("attachedfile");
		List<InvoiceAttachmentDTO> tempList = uploadedInvoiceFileDTO
				.getInvoiceAttachmentDTOs();
		int index = 0;
		for (int i = 0; i < tempList.size(); i++) {
			if (invoiceAttachmentDTO.getName().equalsIgnoreCase(
					tempList.get(i).getName())) {
				index = i;
				break;
			}
		}
		uploadedInvoiceFileDTO.getInvoiceAttachmentDTOs().remove(index);
	}

	public void deleteInvoiceFile(ActionEvent event) {
		loggerService.logPortalInfo(getUserName()
				+ ">> Start deleteAttachmentFile()");
		UploadedInvoiceFileDTO uploadedInvoiceFileDTOTemp = (UploadedInvoiceFileDTO) event
				.getComponent().getAttributes().get("attachedfile");

		int index = 0;
		for (int i = 0; i < uploadedInvoiceFileDTOs.size(); i++) {
			if (uploadedInvoiceFileDTOTemp.getName().equalsIgnoreCase(
					uploadedInvoiceFileDTOs.get(i).getName())) {
				index = i;
				break;
			}
		}
		uploadedInvoiceFileDTOs.remove(index);
	}

	public UploadedInvoiceFileDTO getUploadedInvoiceFileDTO() {
		return uploadedInvoiceFileDTO;
	}

	public void setUploadedInvoiceFileDTO(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO) {
		this.uploadedInvoiceFileDTO = uploadedInvoiceFileDTO;
	}

	public boolean isUploaded() {
		return uploaded;
	}

	public void setUploaded(boolean uploaded) {
		this.uploaded = uploaded;
	}

	public List<UploadedInvoiceFileDTO> getUploadedInvoiceFileDTOs() {
		return uploadedInvoiceFileDTOs;
	}

	public void setUploadedInvoiceFileDTOs(
			List<UploadedInvoiceFileDTO> uploadedInvoiceFileDTOs) {
		this.uploadedInvoiceFileDTOs = uploadedInvoiceFileDTOs;
	}

	// --------
	public void testStored() {
		try {
			invoicesUploaderService.testStored();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getYear(String dateStr) {
		return dateStr.substring(3);
	}

}
