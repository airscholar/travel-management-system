package com.truemega.beans.administration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.TravelUserDTO;
import com.truemega.interfaces.administration.TMSUserService;
import com.truemega.logger.LoggerService;

@ManagedBean(name = "userlist")
@ViewScoped
public class UserListBean extends TravelListBean {

	private static final long serialVersionUID = 1L;
	private LoggerService loggerService = new LoggerService();
	@EJB
	public TMSUserService tmsUserService;

	private List<TravelUserDTO> travelUserDTOs;
	private  TravelUserDTO travelUserDTO;

	// boolean privilegesFlag;

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@PostConstruct
	@Override
	public void load() {
		loggerService.logPortalInfo(" start load method of UserListBean ");
		try {
			travelUserDTOs = tmsUserService.listAllEmployeeUsers(getUserName());
			
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't load travelUserDTOs ", e);
			e.printStackTrace();
		}

		loggerService.logPortalInfo(" start load method of UserListBean ");
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	public List<TravelUserDTO> gettravelUserDTOs() {
		return travelUserDTOs;
	}

	public void settravelUserDTOs(List<TravelUserDTO> travelUserDTOs) {
		this.travelUserDTOs = travelUserDTOs;
	}




	public void changeStatus(AjaxBehaviorEvent event) {
		loggerService
				.logPortalInfo(" start changeStatus method of UserListBean ");
		try {
			TravelUserDTO user = (TravelUserDTO) event.getComponent().getAttributes()
					.get("user");
			tmsUserService.changeStatus(user.getStatus(), user.getEmployeeId(),
					getUserName());
			load();
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't changeStatus ", e);
		}
		loggerService
				.logPortalInfo(" end changeStatus method of UserListBean ");
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	public TravelUserDTO getTravelUserDTO() {
		return travelUserDTO;
	}

	public void setTravelUserDTO(TravelUserDTO travelUserDTO) {
		this.travelUserDTO = travelUserDTO;
	}

}
