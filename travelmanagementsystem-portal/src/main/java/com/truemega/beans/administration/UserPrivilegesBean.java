package com.truemega.beans.administration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelSingleBean;
import com.truemega.dto.SystemScreensDTO;
import com.truemega.dto.TravelUserDTO;
import com.truemega.dto.TraveluserScreensDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.TMSUserService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.Configuration;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "userPRV")
@ViewScoped
public class UserPrivilegesBean extends TravelSingleBean {
	private static final long serialVersionUID = 1L;

	private LoggerService loggerService = new LoggerService();
	@EJB
	public TMSUserService userService;
	private boolean flag = false;
	List<SystemScreensDTO> systemScreens;
	List<TraveluserScreensDTO> tmsuserScreensDTOs;
	private TraveluserScreensDTO tmsuserScreensDTO;
	private Integer employeeId;
	private TravelUserDTO tmsUserDTO;
	private Integer screenId;
	private List<TravelUserDTO> roadUserList;

	@ManagedProperty(value = "#{configuration}")
	private Configuration configuration;

	@PostConstruct
	public void init() {
		loggerService
				.logPortalInfo(" start init method of UserPrivilegesBean ");
		try {
			roadUserList = userService.listAllEmployeeUsers(getUserName());
			systemScreens = userService.findAllSystemScreens(roadUserList
					.get(0).getEmployeeId(), getUserName());
			tmsuserScreensDTOs = userService.findAllUserPrivileges(roadUserList
					.get(0).getEmployeeId(), getUserName());
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't  load init method ", e);
		}

		loggerService.logPortalInfo(" end init method of UserPrivilegesBean ");
	}

	@Override
	public void save() {
		loggerService
				.logPortalInfo(" start save method of UserPrivilegesBean ");
		flag = true;
		// Validate Parameters
		boolean isValid = validate();
		if (isValid) {
			try {
				tmsuserScreensDTO = new TraveluserScreensDTO();
				tmsUserDTO = userService.findEmployeeById(employeeId,
						getUserName());
				if (screenId == null) {
					for (int i = 0; i < systemScreens.size(); i++) {
						assignScreenToEmployee(systemScreens.get(i)
								.getScreenId());
					}
				} else {
					assignScreenToEmployee(screenId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				operationStatus = Status.FAIL;
				loggerService.logPortalError(
						"can't  save employeeScreensDTO  ", e);
			}
			load();
			operationStatus = Status.SUCCESS;

		} else {
			screenMode = UIOperation.VIEW;
			operationStatus = Status.FAIL;
			Screen screen = (Screen) HttpJSFUtils.getSession().getAttribute(
					"screen");
			screen.statusMessage = "This user is Exsit.";
		}
		loggerService.logPortalInfo(" end save method of UserPrivilegesBean ");
	}

	private void assignScreenToEmployee(Integer screenId) {
		SystemScreensDTO screensDTO = userService.findSystemScreenByID(
				screenId, getUserName());
		tmsuserScreensDTO.setScreen(screensDTO);
		tmsuserScreensDTO.setEmployee(tmsUserDTO);
		tmsuserScreensDTO.setAddMode(false);
		tmsuserScreensDTO.setDeleteMode(false);
		tmsuserScreensDTO.setEditMode(false);
		tmsuserScreensDTO.setViewMode(false);
		userService.saveEmployeeScreen(tmsuserScreensDTO, getUserName());
	}

	public void updateUserStatus(AjaxBehaviorEvent event) {
		loggerService
				.logPortalInfo(" start updateUserStatus method of UserPrivilegesBean ");
		TraveluserScreensDTO targetScreen = (TraveluserScreensDTO) event
				.getComponent().getAttributes().get("targetscreen");
		try {
			userService.updateEmployeeScreen(targetScreen, getUserName());
		} catch (Exception e) {
			loggerService.logPortalError("can't updateUserStatus  ", e);
		}
		loggerService
				.logPortalInfo(" end updateUserStatus method of UserPrivilegesBean ");
	}

	public void removeScreen(ActionEvent event) {
		loggerService
				.logPortalInfo(" start updateUserStatus method of UserPrivilegesBean ");
		TraveluserScreensDTO targetScreen = (TraveluserScreensDTO) event
				.getComponent().getAttributes().get("targetscreen");
		try {
			userService.deleteEmployeeScreen(targetScreen, getUserName());
			load();
		} catch (Exception e) {
			loggerService.logPortalError("can't updateUserStatus  ", e);
		}
		loggerService
				.logPortalInfo(" end updateUserStatus method of UserPrivilegesBean ");
	}

	public void getUserScreens() {
		loggerService
				.logPortalInfo(" start getUserScreens method of UserPrivilegesBean ");
		try {
			systemScreens = userService.findAllSystemScreens(employeeId,
					getUserName());
			tmsuserScreensDTOs = userService.findAllUserPrivileges(employeeId,
					getUserName());
		} catch (Exception e) {
			loggerService.logPortalError("can't updateUserStatus  ", e);
		}
		loggerService
				.logPortalInfo(" end getUserScreens method of UserPrivilegesBean ");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		loggerService
				.logPortalInfo(" start load method of UserPrivilegesBean ");
		try {
			systemScreens = userService.findAllSystemScreens(employeeId,
					getUserName());
			tmsuserScreensDTOs = userService.findAllUserPrivileges(employeeId,
					getUserName());
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't load   ", e);
		}

		loggerService.logPortalInfo(" end load method of UserPrivilegesBean ");
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	public void removeAllScreensFromEmployee() {
		loggerService
				.logPortalInfo(" start removeAllScreensFromEmployee method of UserPrivilegesBean ");
		try {
			for (int i = 0; i < systemScreens.size(); i++) {
				userService.deleteEmployeeScreen(tmsuserScreensDTOs.get(i),
						getUserName());
			}
			load();
		} catch (Exception e) {
			loggerService.logPortalError(
					"can't removeAllScreensFromEmployee  ", e);
		}
		loggerService
				.logPortalInfo(" end removeAllScreensFromEmployee method of UserPrivilegesBean ");
	}

	public void selectAllprivilege() {
		loggerService
				.logPortalInfo(" start selectAllprivilege method of UserPrivilegesBean ");
		try {
			for (int i = 0; i < tmsuserScreensDTOs.size(); i++) {
				TraveluserScreensDTO targetScreen = tmsuserScreensDTOs.get(i);
				targetScreen.setAddMode(true);
				targetScreen.setEditMode(true);
				targetScreen.setDeleteMode(true);
				targetScreen.setViewMode(true);
				userService.updateEmployeeScreen(targetScreen, getUserName());
			}
		} catch (Exception e) {
			loggerService
					.logPortalInfo(" end selectAllprivilege method of UserPrivilegesBean ");
		}

		loggerService
				.logPortalInfo(" end selectAllprivilege method of UserPrivilegesBean ");
	}

	public void unSelectAllprivilege() {
		loggerService
				.logPortalInfo(" start unSelectAllprivilege method of UserPrivilegesBean ");
		try {
			for (int i = 0; i < tmsuserScreensDTOs.size(); i++) {
				TraveluserScreensDTO targetScreen = tmsuserScreensDTOs.get(i);
				targetScreen.setAddMode(false);
				targetScreen.setEditMode(false);
				targetScreen.setDeleteMode(false);
				targetScreen.setViewMode(false);
				userService.updateEmployeeScreen(targetScreen, getUserName());
			}
		} catch (Exception e) {
			loggerService
					.logPortalInfo(" end unSelectAllprivilege method of UserPrivilegesBean ");
		}

		loggerService
				.logPortalInfo(" end unSelectAllprivilege method of UserPrivilegesBean ");
	}

	public void checkAll(AjaxBehaviorEvent event) {
		loggerService
				.logPortalInfo(" start checkAll method of UserPrivilegesBean ");
		try {
			TraveluserScreensDTO targetScreen = (TraveluserScreensDTO) event
					.getComponent().getAttributes().get("targetscreen");
			targetScreen.setAddMode(true);
			targetScreen.setEditMode(true);
			targetScreen.setDeleteMode(true);
			targetScreen.setViewMode(true);
			userService.updateEmployeeScreen(targetScreen, getUserName());
		} catch (Exception e) {
			loggerService.logPortalError("can't checkAll  ", e);
		}

		loggerService
				.logPortalInfo(" end checkAll method of UserPrivilegesBean ");
	}

	public void unCheckAll(AjaxBehaviorEvent event) {
		loggerService
				.logPortalInfo(" start unCheckAll method of UserPrivilegesBean ");
		try {
			TraveluserScreensDTO targetScreen = (TraveluserScreensDTO) event
					.getComponent().getAttributes().get("targetscreen");
			targetScreen.setAddMode(false);
			targetScreen.setEditMode(false);
			targetScreen.setDeleteMode(false);
			targetScreen.setViewMode(false);
			userService.updateEmployeeScreen(targetScreen, getUserName());
		} catch (Exception e) {
			loggerService.logPortalError("can't unCheckAll  ", e);
		}

		loggerService
				.logPortalInfo(" end unCheckAll method of UserPrivilegesBean ");
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<SystemScreensDTO> getSystemScreens() {
		return systemScreens;
	}

	public void setSystemScreens(List<SystemScreensDTO> systemScreens) {
		this.systemScreens = systemScreens;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public TMSUserService getUserService() {
		return userService;
	}

	public void setUserService(TMSUserService userService) {
		this.userService = userService;
	}

	public List<TraveluserScreensDTO> getTmsuserScreensDTOs() {
		return tmsuserScreensDTOs;
	}

	public void setTmsuserScreensDTOs(
			List<TraveluserScreensDTO> tmsuserScreensDTOs) {
		this.tmsuserScreensDTOs = tmsuserScreensDTOs;
	}

	public TraveluserScreensDTO getTmsuserScreensDTO() {
		return tmsuserScreensDTO;
	}

	public void setTmsuserScreensDTO(TraveluserScreensDTO tmsuserScreensDTO) {
		this.tmsuserScreensDTO = tmsuserScreensDTO;
	}

	public TravelUserDTO getTmsUserDTO() {
		return tmsUserDTO;
	}

	public void setTmsUserDTO(TravelUserDTO tmsUserDTO) {
		this.tmsUserDTO = tmsUserDTO;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public List<TravelUserDTO> getRoadUserList() {
		return roadUserList;
	}

	public void setRoadUserList(List<TravelUserDTO> roadUserList) {
		this.roadUserList = roadUserList;
	}

}
