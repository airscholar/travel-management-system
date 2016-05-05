package com.truemega.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.truemega.dto.CategoryDTO;
import com.truemega.dto.ConfigDTO;
import com.truemega.dto.TravelUserDTO;
import com.truemega.interfaces.administration.TMSConfigService;
import com.truemega.logger.LoggerService;
import com.truemega.lookups.TravelConfigEnum;
import com.truemega.menu.TMSEmployeeService;
import com.truemega.menu.TMSMenuService;
import com.truemega.utils.ActiveDirectory;
import com.truemega.utils.Configuration;
import com.truemega.utils.HttpJSFUtils;

/**
 * @author workshop
 * 
 */
@ManagedBean(name = "securityBean")
@SessionScoped
public class SecurityBean {
	private LoggerService loggerService = new LoggerService();
	private String userName;
	private String password;

	private Map<String, String> configMap;
	private List<ConfigDTO> configList;

	@ManagedProperty(value = "#{configuration}")
	private Configuration configuration;

	@EJB
	private TMSConfigService configService;
	@EJB
	private TMSMenuService menuService;
	private List<CategoryDTO> categories;

	@EJB
	private TMSEmployeeService employeeService;

	private ActiveDirectory activeDirectory;

	@PostConstruct
	public void init() {

	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {
		loggerService.logPortalInfo(" start login method of SecurityBean ");
		try {

			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext
					.getExternalContext().getSession(true);
			if (configMap == null)
				loadConfiguration();
			// if (configuration == null)
			// logout();
			String ldapDomainName = getPropertyValue(TravelConfigEnum.LDAP_DOMAIN_NAME
					.toString());
			String ldapURL = getPropertyValue(TravelConfigEnum.LDAP_URL
					.toString());
			String LDAP_USERS_SB = getPropertyValue(TravelConfigEnum.LDAP_USERS_SB
					.toString());

			activeDirectory = new ActiveDirectory();
			boolean ldapAuthinticated = activeDirectory.authenticateUser(
					userName, password, ldapURL, ldapDomainName, LDAP_USERS_SB);
			TravelUserDTO admin = null;
			if (!ldapAuthinticated)
				admin = configService.getAdministrator(userName);
			if (ldapAuthinticated
					|| (admin.getAccountname().equals(userName) && admin
							.getAdminPassword().equals(password))) {
				userName = userName.toLowerCase();

				TravelUserDTO employee = employeeService.findUserByUserName(
						userName, userName);
				if (employee != null) {
					session.setAttribute("employee", employee);
					session.setAttribute("password", password);
					getUserScreen(employee);
					HttpJSFUtils.redirect("index.xhtml");
				} else {
					loggerService
							.logPortalInfo("Your Are Not One Of The System Users Please Contact With Administrator.");
					String strMsg = "Your are not one of the system users please contact the Administrator.";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									strMsg, " "));
				}
			} else {
				loggerService
						.logPortalInfo("Login Bean  The User Can't authenticated By LDAP Please be Sure That You Enter Correct Name. ");
				String strMsg = "";
				if (ldapAuthinticated)
					strMsg = "You can't authenticated by LDAP please make sure that you enter correct name.";
				else
					strMsg = "You can't authenticated by Administrator please  make  sure that you enter correct administrator name or password.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg,
								" "));
			}
		} catch (Exception e) {
			loggerService
					.logPortalError("Login Bean Inside Login Method : ", e);
			e.printStackTrace();
		}
		loggerService.logPortalInfo(" end login method of SecurityBean ");
	}

	public void logout() {
		loggerService.logPortalInfo(" start logout method of SecurityBean ");
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext
					.getExternalContext().getSession(true);
			session.invalidate();
			HttpJSFUtils.redirect("login.xhtml");
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError(
					"Logout  Bean Inside logout  Method : ", e);
		}
		loggerService.logPortalInfo(" end logout method of SecurityBean ");
	}

	public void getUserScreen(TravelUserDTO employeeDTO) {
		loggerService
				.logPortalInfo(" start getUserScreen method of SecurityBean ");
		try {
			loggerService.logPortalInfo("Stat Menu Preparation");
			System.out.println("Stat Menu Preparation");
			categories = new ArrayList<CategoryDTO>();
			categories = menuService.listAllCategories(userName);
			List<CategoryDTO> userCategories = new ArrayList<CategoryDTO>();
			for (CategoryDTO category : categories) {
				System.out.println("Category Name ======== "
						+ category.getCategoryName());
				category.setEmployeeScreens(menuService
						.listAllScreenByCategoryAndEmployee(
								category.getCategoryId(),
								employeeDTO.getEmployeeId(), userName));
				category.setScreenSize(category.getEmployeeScreens().size());
				if (category.getEmployeeScreens() != null
						&& category.getEmployeeScreens().size() > 0) {
					category.setFlage(true);
					userCategories.add(category);
				}
			}

			categories = userCategories;
			System.out.println("Menu Preparation DONE");
			HttpJSFUtils.getSession().setAttribute("employeeScreens",
					categories);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError(" error in getUserScreen   ", e);
		}
		loggerService
				.logPortalInfo(" end getUserScreen method of SecurityBean ");
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void loadConfiguration() {
		loggerService
				.logPortalInfo(" start loadConfiguration method of Configuration ");
		try {
			loggerService
					.logPortalInfo(" Load Configuratoin() .....................");

			configList = configService.findAllConfiguration(getUserName());
			configMap = new HashMap<String, String>();
			for (ConfigDTO config : configList) {
				configMap.put(config.getConfigName(), config.getConfigValue());
			}
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't load Configuration  ", e);
		}

		loggerService
				.logPortalInfo(" end loadConfiguration method of Configuration ");
	}

	public String getPropertyValue(String name) {
		return configMap.get(name);
	}
}
