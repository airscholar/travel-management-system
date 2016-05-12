package com.truemega.beans.administration;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelSingleBean;
import com.truemega.dto.AirlineDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.AirLineService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;


@ManagedBean(name = "airlinesingle")
@ViewScoped
public class AirLineSingleBean  extends TravelSingleBean {

	
	private static final long serialVersionUID = 1L;
	

    
	private LoggerService loggerService = new LoggerService();
	
	@EJB
	private AirLineService  airLineService ;
	
	private AirlineDTO airlineDTO  = new AirlineDTO() ;
	private AirlineDTO oldAirlineDTO  = new AirlineDTO() ;
	private Integer airLineId;
	
	
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
		
		loggerService.logPortalInfo(" start save method of AirLineSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				airlineDTO.setSystemUser(getUserName());
				airlineDTO.setModificationDate(new Date());

				airlineDTO = airLineService.saveAirLine(airlineDTO,
						getUserName());

				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't save airlineDTO ", e);
			}
			
		}else {
			// Error Message
			screenMode = UIOperation.ADD;
			operationStatus = Status.FAIL;
			operationMessage = "This AirLine already exist. ";
		}
		
		loggerService.logPortalInfo(" end save method of AirLineSingleBean ");
		
	
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub

		loggerService.logPortalInfo(" start update method of AirLineSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				airlineDTO.setSystemUser(getUserName());
				airlineDTO.setModificationDate(new Date());

				airlineDTO = airLineService.updateAirLine(airlineDTO,
						getUserName());

				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't update airlineDTO ", e);
			}
			
		}else {
			// Error Message
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.FAIL;
			operationMessage = "This AirLine already exist. ";
		}
		
		loggerService.logPortalInfo(" end update method of AirLineSingleBean ");
		
	
		
	
	}
	@Override
	public void load() {

		loggerService
				.logPortalInfo(" start load method of AirLineSingleBean ");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null)
				airLineId = entity.getEntityId();

			airlineDTO = airLineService.findAirLineById(airLineId,
					getUserName());

			
			oldAirlineDTO = airlineDTO ;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logPortalError("can't load  ", e);
		}
		loggerService.logPortalInfo(" end load method of AirLineSingleBean ");
	
	
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub


		if (screenMode.equals(UIOperation.ADD)) {
			return airLineService.checkUniqueAirLineName(airlineDTO.getName());
		} else {
		
		 AirlineDTO  tempAirLineDTO =  airLineService.findAirLineByName(airlineDTO.getName(), getUserName());
		 if(tempAirLineDTO == null)
			 return true  ;
			 else
			 {
				 if(tempAirLineDTO.getId() == airlineDTO.getId() )
					 return true ;
				 else
				 {
					 operationMessage = "This AirLine already exist. ";
					 return false ;
					 
				 }
				 
			 }
		}
	
	
	}
	public AirlineDTO getAirlineDTO() {
		return airlineDTO;
	}
	public void setAirlineDTO(AirlineDTO airlineDTO) {
		this.airlineDTO = airlineDTO;
	}
	public AirlineDTO getOldAirlineDTO() {
		return oldAirlineDTO;
	}
	public void setOldAirlineDTO(AirlineDTO oldAirlineDTO) {
		this.oldAirlineDTO = oldAirlineDTO;
	}
	public Integer getAirLineId() {
		return airLineId;
	}
	public void setAirLineId(Integer airLineId) {
		this.airLineId = airLineId;
	}
	
}
