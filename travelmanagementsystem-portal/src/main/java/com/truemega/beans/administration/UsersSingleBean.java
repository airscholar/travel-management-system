package com.truemega.beans.administration;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.truemega.beans.TravelSingleBean;
import com.truemega.dto.TravelUserDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.TMSUserService;
import com.truemega.logger.LoggerService;
import com.truemega.lookups.TravelConfigEnum;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.ActiveDirectory;
import com.truemega.utils.Configuration;
import com.truemega.utils.EmployeeInfo;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "users")
@ViewScoped
public class UsersSingleBean extends TravelSingleBean {

	private static final long serialVersionUID = 1L;
	private LoggerService loggerService = new LoggerService();
	@EJB
	public TMSUserService tmsUserService;
	private boolean flag = false;
	private TravelUserDTO travelUserDTO = new TravelUserDTO();
//	private RmsUserDTO travelUserDTO = new RmsUserDTO();
	private Integer userId;
	private int userLdapSearch;
	private ActiveDirectory activeDirectory;
	@ManagedProperty(value = "#{configuration}")
	private Configuration configuration;

	private static Logger logger = Logger.getLogger("lpLogger");

	@PostConstruct
	public void init() {
		loggerService.logPortalInfo(" start init method of UsersSingleBean ");
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
		loggerService.logPortalInfo(" end init method of UsersSingleBean ");
	}

	@Override
	public void save() {
		// Validate Parameters
		loggerService.logPortalInfo(" start save method of UsersSingleBean ");
		boolean isValid = validate();
		if (isValid) {
			try {
				// Save Fleet User Entity

//				travelUserDTO.setEmployeeType("TMSUSER");
				travelUserDTO.setStatus(true);
				travelUserDTO.setAccountname(travelUserDTO.getAccountname()
						.toLowerCase());
				tmsUserService.saveEmployee(travelUserDTO, getUserName());
				// show screen in View Mode
				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				operationStatus = Status.FAIL;
				screenMode = UIOperation.ADD;
				loggerService.logPortalError("can't save Road User ", e);
				operationMessage = "Error While Adding Road User";
				e.printStackTrace();
			}

		} else {
			screenMode = UIOperation.ADD;
			operationStatus = Status.FAIL;
//			operationMessage = "This User  Exists";
		}
		loggerService.logPortalInfo(" end save method of UsersSingleBean ");
	}

	@Override
	public void update() {
		// boolean isValid = validate();
		// if (isValid) {
		loggerService.logPortalInfo(" start update method of UsersSingleBean ");
		try {
			// update Supplier Entity
			tmsUserService.updateEmployee(travelUserDTO, getUserName());
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.SUCCESS;
		} catch (Exception e) {
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.FAIL;
			operationMessage = "Error While Updating Road User";
			loggerService.logPortalError("can't update roadUserDTO ", e);
			e.printStackTrace();
		}

		// }else{
		// screenMode = UIOperation.UPDATE;
		// operationStatus = Status.FAIL;
		// }
		loggerService.logPortalInfo(" end update method of UsersSingleBean ");
	}

	@Override
	public void load() {
		loggerService.logPortalInfo(" start load method of UsersSingleBean ");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null)
				userId = entity.getEntityId().intValue();
			travelUserDTO = tmsUserService.findEmployeeById(userId, getUserName());
			System.out.println("userId = " + userId);
			System.out.println("travelUserDTO = " + travelUserDTO);

		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logPortalError("can't load  ", e);
			e.printStackTrace();
		}
		loggerService.logPortalInfo(" end load method of UsersSingleBean ");
	}

	@Override
	public boolean validate() {
		loggerService
				.logPortalInfo(" start validate method of UsersSingleBean ");
		TravelUserDTO employee = null;
		try {
			System.out.println("  userLdapSearch  ====" + userLdapSearch);
			if (userLdapSearch == travelUserDTO.getStaffid()) {
				employee = tmsUserService.findEmployeeByStaffID(
						travelUserDTO.getStaffid(), getUserName());
				if (employee == null)
					return true;
				else {
					operationMessage = "This employee is already exist in the system.";
					return false;
				}
			} else {
				operationMessage = "The employee ID doesn't match it's data.";
				return false;
			}
		} catch (Exception e) {
			loggerService.logPortalError("validation error  ", e);
			e.printStackTrace();
			return false;
		}

	}

	public void fetchUserDataFromLDAP() {
		loggerService
				.logPortalInfo(" start fetchUserDataFromLDAP method of UsersSingleBean ");
		try {
			loggerService.logPortalInfo(" travelUserDTO.getStaffId  = "
					+ travelUserDTO.getStaffid());

			if (travelUserDTO.getStaffid() != null && travelUserDTO.getStaffid() != 0) {
				try {
					userLdapSearch = travelUserDTO.getStaffid();
					EmployeeInfo info = null;
					FacesContext fc = FacesContext.getCurrentInstance();
					HttpSession currentSession = (HttpSession) fc
							.getExternalContext().getSession(true);
					String username = "";
					String password = currentSession.getAttribute("password")
							.toString();

					TravelUserDTO emoployee = (TravelUserDTO) currentSession
							.getAttribute("employee");
					username = emoployee.getAccountname();

					String ldapDomainName = configuration
							.getPropertyValue(TravelConfigEnum.LDAP_DOMAIN_NAME
									.toString());
					String ldapURL = configuration
							.getPropertyValue(TravelConfigEnum.LDAP_URL
									.toString());
					String LDAP_USERS_SB = configuration
							.getPropertyValue(TravelConfigEnum.LDAP_USERS_SB
									.toString());
					activeDirectory = new ActiveDirectory();
					String.valueOf(userId);
					info = activeDirectory.getEmployeeInfoByID(travelUserDTO
							.getStaffid().toString(), username, password,
							ldapURL, ldapDomainName, LDAP_USERS_SB);

					if (info != null) {
						travelUserDTO.setStaffid(Integer.parseInt(info
								.getEmployeeId()));
						travelUserDTO.setAccountname(info.getAccountName()
								.replaceAll(", Vodafone Egypt", ""));
						travelUserDTO.setFullname(info.getEmployeeName()
								.replaceAll(", Vodafone Egypt", ""));
						travelUserDTO.setEmail(info.getEmployeeMail());
						travelUserDTO.setDepartment(info.getDepartment());
						travelUserDTO.setPhone(info.getMobile());
						travelUserDTO.setCostcenter(info.getCostCenter());
					} else {
						travelUserDTO.setStaffid(0);
						String strMsg = "This Employee Doesn't Exist in The LDAP.";
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										strMsg, " "));
					}

				} catch (Exception e) {
					e.printStackTrace();
					String strMsg = "This Employee Doesn't Exist in The LDAP.";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									strMsg, " "));
					logger.error(e);
					loggerService.logPortalError("Ldap error  ", e);
					e.printStackTrace();
				}
			} else {
				String strMsg = "Please Enter The User Id to Can Fetch User Data From LDAP.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, strMsg,
								" "));
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		loggerService
				.logPortalInfo(" end fetchUserDataFromLDAP method of UsersSingleBean ");
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getUserLdapSearch() {
		return userLdapSearch;
	}

	public void setUserLdapSearch(int userLdapSearch) {
		this.userLdapSearch = userLdapSearch;
	}

	public ActiveDirectory getActiveDirectory() {
		return activeDirectory;
	}

	public void setActiveDirectory(ActiveDirectory activeDirectory) {
		this.activeDirectory = activeDirectory;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public TravelUserDTO getTravelUserDTO() {
		return travelUserDTO;
	}

	public void setTravelUserDTO(TravelUserDTO travelUserDTO) {
		this.travelUserDTO = travelUserDTO;
	}
  
}
