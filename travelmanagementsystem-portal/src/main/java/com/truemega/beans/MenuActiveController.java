package com.truemega.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.dto.CategoryDTO;
import com.truemega.dto.TravelUserDTO;
import com.truemega.dto.TraveluserScreensDTO;
import com.truemega.dto.SystemScreensDTO;
import com.truemega.interfaces.administration.TMSUserService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "menuactive")
@ViewScoped
public class MenuActiveController {

	private SystemScreensDTO screen;;
	private CategoryDTO category;
	private String getCategoryCSSClass;
	private String screenCSS;
	private List<CategoryDTO> categories;
	private LoggerService loggerService = new LoggerService();

	@EJB
	public TMSUserService userService;

	public String getGetCategoryCSSClass() {
		return "true";
	}

	public void setGetCategoryCSSClass(String getCategoryCSSClass) {
		this.getCategoryCSSClass = getCategoryCSSClass;
	}

	@PostConstruct
	public void init() {
		loggerService
				.logPortalInfo(" Start init method of MenuActiveController ");
		try {
			Object loggedObject = HttpJSFUtils.getSession().getAttribute(
					"employee");
			TravelUserDTO loggedInUser = (TravelUserDTO) loggedObject;

			categories = (List<CategoryDTO>) HttpJSFUtils.getSession()
					.getAttribute("employeeScreens");

			Object screenObject = HttpJSFUtils.getSession().getAttribute(
					"screen");

			setScreen((screenObject != null ? userService.findScreenByID(
					((Screen) screenObject).getScreenId(),
					loggedInUser.getAccountname()) : null));
			if (screen != null) {
				category = screen.getCategory();
				for (int i = 0; i < categories.size(); i++) {
					CategoryDTO categoryDTO = new CategoryDTO();
					categoryDTO = categories.get(i);
					if (categoryDTO.getCategoryId() == category.getCategoryId()) {
						categoryDTO.setExpandedCSSClass("true");
						categoryDTO.setCollapse("collapse in");
					} else {
						// categoryDTO.setExpandedCSSClass("false");
						// categoryDTO.setCollapse("collapse");
					}
					// if (categoryDTO.getEmployeeScreens().size() < 1)

					for (int j = 0; j < categoryDTO.getEmployeeScreens().size(); j++) {
						TraveluserScreensDTO employeeScreensDTO = categoryDTO
								.getEmployeeScreens().get(j);
						if (employeeScreensDTO.getScreen().getScreenId()
								.intValue() == screen.getScreenId()) {
							employeeScreensDTO.getScreen()
									.setOpenedScreenCSSClass(" solso-active");
						} else {
							employeeScreensDTO.getScreen()
									.setOpenedScreenCSSClass("");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("error in  init  ", e);
		}
		loggerService
				.logPortalInfo(" End init method of MenuActiveController ");
	}

	private String getScreenCSSClass(String screenURL) {
		return "Working screen";
	}

	public SystemScreensDTO getScreen() {
		return screen;
	}

	public void setScreen(SystemScreensDTO screen) {
		this.screen = screen;
	}

	public String getScreenCSS() {
		return "working";
	}

	public void setScreenCSS(String screenCSS) {
		this.screenCSS = screenCSS;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

}
