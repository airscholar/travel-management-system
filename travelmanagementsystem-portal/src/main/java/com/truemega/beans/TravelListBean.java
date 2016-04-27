package com.truemega.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import com.truemega.controllers.ListBean;
import com.truemega.dto.TravelUserDTO;
import com.truemega.dto.TraveluserScreensDTO;
import com.truemega.enums.UIOperation;
import com.truemega.logger.LoggerService;
import com.truemega.menu.TMSMenuService;
import com.truemega.utils.HttpJSFUtils;

public abstract class TravelListBean extends ListBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private TMSMenuService menuService;

	private Boolean add, view, delete, edit;
	private TravelUserDTO loggedInUser;
	private LoggerService loggerService = new LoggerService();

	@PostConstruct
	public void init() {
		loggerService.logPortalInfo(" start init method of TravelListBean ");
		try {
			Object EmployeeObject = HttpJSFUtils.getSession().getAttribute(
					"employee");
			if (EmployeeObject != null && EmployeeObject instanceof TravelUserDTO) {
				setRedirectLogin(false);
				loggedInUser = (TravelUserDTO) EmployeeObject;
				TraveluserScreensDTO employeeScreen = menuService
						.checkScreenForEmployee(getScreenName(), loggedInUser
								.getEmployeeId().intValue(), getUserName());
				if (employeeScreen != null) {
					setNotAuthorized(false);
					add = employeeScreen.isAddMode();
					delete = employeeScreen.isDeleteMode();
					edit = employeeScreen.isEditMode();
					view = employeeScreen.isViewMode();
				} else {

				}
			} else {
				setRedirectLogin(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't load init  ", e);
			e.printStackTrace();
		}

		loggerService.logPortalInfo(" end init method of TravelListBean ");
	}

	@Override
	public void redirect(String url, UIOperation mode) {
		HttpJSFUtils.redirect(url);
	}

	public abstract String getScreenName();

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Boolean getAdd() {
		return add;
	}

	public void setAdd(Boolean add) {
		this.add = add;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public Boolean getView() {
		return view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

	public TravelUserDTO getLoggedInUser() {
		if (loggedInUser == null) {
			Object loggedObject = HttpJSFUtils.getSession().getAttribute(
					"employee");
			loggedInUser = (TravelUserDTO) loggedObject;
		}
		return loggedInUser;
	}

	public String getUserName() {
		return getLoggedInUser().getAccountname();
	}

	public String getUserPassword() {
		return null;
	}

	public abstract void search();

	public abstract void reset();
}
