package com.truemega.beans.travelmanagementsystem;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.InvoicesDTO;
import com.truemega.dto.InvoicesTempDTO;
import com.truemega.interfaces.travelmanagementsystem.InvoicesService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "uploadedInvoiceRecordsListBean")
@ViewScoped
public class UploadedInvoiceRecordsListBean extends TravelListBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoggerService loggerService = new LoggerService();

	@EJB
	private InvoicesService invoicesService;

	private List<InvoicesDTO> invoicesDTOs;
	private Integer uploadedFileId;

	@PostConstruct
	@Override
	public void init() {
		loggerService.logPortalInfo("Start init userName >> " + getUserName());
		load();

	}

	public void load() {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(getUserName() + ">> Start load()");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null) {
				uploadedFileId = entity.getEntityId();
				invoicesDTOs = invoicesService.listInvoicesByFileID(
						uploadedFileId, getUserName());
			}
			loggerService.logPortalInfo(getUserName()
					+ ">> End load() Successfully");
		} catch (Exception e) {
			loggerService.logPortalError(getUserName()
					+ ">> End load() With Error", e);
		}
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

	public List<InvoicesDTO> getInvoicesDTOs() {
		return invoicesDTOs;
	}

	public void setInvoicesDTOs(List<InvoicesDTO> invoicesDTOs) {
		this.invoicesDTOs = invoicesDTOs;
	}

}
