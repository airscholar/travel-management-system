package com.truemega.beans.travelmanagementsystem;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelSingleBean;
import com.truemega.dto.UploadedInvoiceFileDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.travelmanagementsystem.InvoicesUploaderService;
import com.truemega.interfaces.travelmanagementsystem.UploadedInvoicesFileService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "invoicesActionTaker")
@ViewScoped
public class InvoicesActionTakerBean extends TravelSingleBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private InvoicesUploaderService invoicesUploaderService;

	private LoggerService loggerService = new LoggerService();

	private Integer invoiceFileId;

	private String action;

	@EJB
	private UploadedInvoicesFileService uploadedInvoicesFileService;

	private UploadedInvoiceFileDTO uploadedInvoiceFileDTO;

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

	@Override
	public void save() {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(getUserName() + ">> Start save()");
		boolean isValid = validate();
		loggerService.logPortalInfo(getUserName() + ">> isValid = " + isValid);

		if (isValid) {

			try {
				uploadedInvoiceFileDTO.setApproved(new Boolean(action));
				uploadedInvoiceFileDTO.setActionTakenDate(new Date());
				uploadedInvoiceFileDTO.setActionTaker(getUserName());
				uploadedInvoicesFileService.takeAction(uploadedInvoiceFileDTO,
						getUserName());
				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;

				loggerService.logPortalInfo(getUserName()
						+ ">> End save() Successfully");
			} catch (Exception e) {
				operationStatus = Status.FAIL;
				operationMessage = "Error While The Taking Action";
				loggerService.logPortalError(getUserName()
						+ ">> End save() With Error", e);
			}

		} else {
			// Error Message
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.FAIL;
			operationMessage = "Error While The Taking Action";
			loggerService.logPortalInfo(getUserName()
					+ ">> End save() With Invalid");
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public void testNotifiction() {
		uploadedInvoicesFileService.testNotification();
	}

	public void testNotifictionBELOW() {
		uploadedInvoicesFileService.testNotification();
	}

	@Override
	public void load() {
		loggerService.logPortalInfo(getUserName() + ">> Start load()");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null) {
				invoiceFileId = entity.getEntityId();
				System.out.println("invoiceFileId  = " + invoiceFileId);
				uploadedInvoiceFileDTO = uploadedInvoicesFileService
						.findUploadedInvoiceFileById(invoiceFileId,
								getUserName());

				action = new String(uploadedInvoiceFileDTO.getApproved()
						.toString());

			}
			loggerService.logPortalInfo(getUserName()
					+ ">> End load() Successfully");
		} catch (Exception e) {
			loggerService.logPortalError(getUserName()
					+ ">> End load() With Error", e);
		}
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub

		return true;

	}

	public UploadedInvoiceFileDTO getUploadedInvoiceFileDTO() {
		return uploadedInvoiceFileDTO;
	}

	public void setUploadedInvoiceFileDTO(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO) {
		this.uploadedInvoiceFileDTO = uploadedInvoiceFileDTO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
