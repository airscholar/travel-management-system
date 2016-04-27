package com.truemega.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "controller")
@RequestScoped
public class NavigationController {
	private LoggerService loggerService = new LoggerService();
	public String screenURL;
	public int screenMode;
	public String screeName;
	private int screenId;

	public String entityId;

	public String getScreeName() {
		return screeName;
	}

	public void setScreeName(String screeName) {
		this.screeName = screeName;
	}

	public void showPage() {
		// add screen in session ;
		loggerService.logPortalInfo(" Start showPage method of NavigationController ");
		try {
			Screen screen = new Screen();
			screen.setScreenName(screeName);
			screen.setScreenMode(screenMode);
			screen.setScreenURL(screenURL);
			screen.setScreenId(screenId);
			HttpJSFUtils.getSession().setAttribute("screen", screen);

			// add entity in session
			
			if (entityId != null){
				Entity entity = new Entity();
				entity.setEntityId(Integer.parseInt( (entityId)));
				HttpJSFUtils.getSession().setAttribute("entity", entity);
			}
			// Redirect to screen URL
			HttpJSFUtils.redirect(screenURL);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't  show Page ", e);
		}
		loggerService.logPortalInfo(" End showPage method of NavigationController ");
	}

	public void showPopup() {
		// add entity in session
		loggerService.logPortalInfo(" Start showPopup method of NavigationController ");
		try {
			if (entityId != null) {
				Entity entity = new Entity();
				entity.setEntityId(Integer.parseInt (entityId));
				HttpJSFUtils.getSession().setAttribute("entity", entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't   showPopup ", e);	
		}

		loggerService.logPortalInfo(" End showPopup method of NavigationController ");
	}

	public int getScreenMode() {
		return screenMode;
	}

	public void setScreenMode(int screenMode) {
		this.screenMode = screenMode;
	}

	public String getScreenURL() {
		return screenURL;
	}

	public void setScreenURL(String screenURL) {
		this.screenURL = screenURL;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}
}
