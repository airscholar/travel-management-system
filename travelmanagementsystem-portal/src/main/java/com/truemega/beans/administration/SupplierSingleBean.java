package com.truemega.beans.administration;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelSingleBean;
import com.truemega.dto.SupplierDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.SupplierService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "supportsingle")
@ViewScoped
public class SupplierSingleBean extends TravelSingleBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private LoggerService loggerService = new LoggerService();
	
	@EJB
	private SupplierService  supplierService ;
	
	private SupplierDTO supplierDTO  = new SupplierDTO() ;
	private SupplierDTO oldsupplierDTO  = new SupplierDTO() ;
	private Integer supplierId;
	
	@PostConstruct
	public void init() {

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

		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start save method of SupplierSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				supplierDTO.setSystemUser(getUserName());
				supplierDTO.setModificationDate(new Date());

				supplierDTO = supplierService.saveSupplier(supplierDTO,
						getUserName());

				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't save supplierDTO ", e);
			}
			
		}else {
			// Error Message
			screenMode = UIOperation.ADD;
			operationStatus = Status.FAIL;
			operationMessage = "This Supplier already exist. ";
		}
		
		loggerService.logPortalInfo(" end save method of SupplierSingleBean ");
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		loggerService.logPortalInfo(" start update method of SupplierSingleBean ");
		boolean isValid = validate();
		if (isValid) {
			// Save Entity
			try {
				supplierDTO.setSystemUser(getUserName());
				supplierDTO.setModificationDate(new Date());
				supplierService.updateSupplier(supplierDTO, getUserName());
				screenMode = UIOperation.VIEW;
			} catch (Exception e) {
				operationStatus = Status.FAIL;
				loggerService.logPortalError("can't update productTypeDTO ", e);
			}
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.SUCCESS;
		} else {
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.FAIL;
			operationMessage = "This Supplier is already exist." ;
		}
		loggerService.logPortalInfo(" end update method of SupplierSingleBean ");
		
		
	
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logPortalInfo(" start load method of SupplierSingleBean ");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null)
				supplierId = entity.getEntityId();

			supplierDTO = supplierService.findSupplierById(supplierId,
					getUserName());

			
			oldsupplierDTO = supplierDTO ;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logPortalError("can't load  ", e);
		}
		loggerService.logPortalInfo(" end load method of SupplierSingleBean ");
	
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		if (screenMode.equals(UIOperation.ADD)) {
			return supplierService.checkUniqueSupplierName(supplierDTO.getName());
		} else {
	  SupplierDTO tempSupplierDTO = supplierService.findSupplierByName(supplierDTO.getName(), getUserName());
	  
	      if(tempSupplierDTO == null)
	    	  return true ;
	      else
	      {
	    	  if(tempSupplierDTO.getId() == supplierDTO.getId())
	    		  return true ;
	    	  else
	    	  {
	    		  operationMessage = "This Supplier is already exist." ;
	    		  return false ;
	    	  }
	    	  
	      }
	  
		}
	
	}


	public SupplierDTO getSupplierDTO() {
		return supplierDTO;
	}


	public void setSupplierDTO(SupplierDTO supplierDTO) {
		this.supplierDTO = supplierDTO;
	}


	public SupplierDTO getOldsupplierDTO() {
		return oldsupplierDTO;
	}


	public void setOldsupplierDTO(SupplierDTO oldsupplierDTO) {
		this.oldsupplierDTO = oldsupplierDTO;
	}


	public Integer getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

}
