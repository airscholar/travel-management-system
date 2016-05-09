package com.truemega.beans.administration;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelSingleBean;
import com.truemega.dto.RoomTypeDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.RoomTypeService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "roomtypesingle")
@ViewScoped
public class RoomTypeSingleBean  extends TravelSingleBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private LoggerService loggerService = new LoggerService();
	
	@EJB
	private RoomTypeService roomTypeService ;
	
	private  RoomTypeDTO roomTypeDTO  = new RoomTypeDTO() ;
	private RoomTypeDTO oldroomTypeDTO  = new RoomTypeDTO() ;
	private Integer roomtypeId;
	
	
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
		
		loggerService.logPortalInfo(" start save method of RoomTypeSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				roomTypeDTO.setSystemUser(getUserName());
				roomTypeDTO.setModificationDate(new Date());

				roomTypeDTO = roomTypeService.saveRoomType(roomTypeDTO,
						getUserName());

				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't save roomTypeDTO ", e);
			}
			
		}else {
			// Error Message
			screenMode = UIOperation.ADD;
			operationStatus = Status.FAIL;
			operationMessage = "This Room Type already exist. ";
		}
		
		loggerService.logPortalInfo(" end save method of RoomTypeSingleBean ");
	
		
	}

	@Override
	public void update() {

		
		loggerService.logPortalInfo(" start update method of RoomTypeSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				roomTypeDTO.setSystemUser(getUserName());
				roomTypeDTO.setModificationDate(new Date());

				roomTypeDTO = roomTypeService.updateRoomType(roomTypeDTO,
						getUserName());

				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't save roomTypeDTO ", e);
			}
			
		}else {
			// Error Message
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.FAIL;
			operationMessage = "This Room Type already exist. ";
		}
		
		loggerService.logPortalInfo(" end save method of RoomTypeSingleBean ");
		
	}

	@Override
	public void load() {

		loggerService
				.logPortalInfo(" start load method of AirLineSingleBean ");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null)
				roomtypeId = entity.getEntityId();

			roomTypeDTO = roomTypeService.findRoomTypeById(roomtypeId,
					getUserName());

			
			oldroomTypeDTO = roomTypeDTO ;
			
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

		// TODO Auto-generated method stub


		if (screenMode.equals(UIOperation.ADD)) {
			return roomTypeService.checkUniqueRoomTypeName(roomTypeDTO.getName());
		} else {
			if (oldroomTypeDTO.getName().equals(roomTypeDTO.getName()) )
				return true;
			else
				return false;
		}
	
	
	
	}

	public RoomTypeDTO getRoomTypeDTO() {
		return roomTypeDTO;
	}

	public void setRoomTypeDTO(RoomTypeDTO roomTypeDTO) {
		this.roomTypeDTO = roomTypeDTO;
	}

	public RoomTypeDTO getOldroomTypeDTO() {
		return oldroomTypeDTO;
	}

	public void setOldroomTypeDTO(RoomTypeDTO oldroomTypeDTO) {
		this.oldroomTypeDTO = oldroomTypeDTO;
	}

	public Integer getRoomtypeId() {
		return roomtypeId;
	}

	public void setRoomtypeId(Integer roomtypeId) {
		this.roomtypeId = roomtypeId;
	}

}
