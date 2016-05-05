package com.truemega.utils;

import java.io.Serializable;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.asset.ldap.common.LDAPIntegration;
import com.asset.ldap.model.LdapUserModel;
import com.truemega.logger.LoggerService;

public class ActiveDirectory implements Serializable {

	private static final long serialVersionUID = 1L;
	private LoggerService loggerService = new LoggerService();
	private static Logger logger = Logger.getLogger("lpLogger");

	public EmployeeInfo getEmployeeInfoByID(String employeeId, String userName,
			String password, String ldapURL, String ldapDomainName,
			String LDAP_USERS) throws Exception {
		loggerService
				.logPortalInfo(" start getEmployeeInfoByID method of ActiveDirectory "
						+ "name " + userName);
		LdapUserModel employeeUserMoedl = new LdapUserModel();
		employeeUserMoedl.setAttribute("employeeid", employeeId);

		return getEmployeeInfo(employeeUserMoedl, userName, password, ldapURL,
				ldapDomainName, LDAP_USERS);
	}

	public EmployeeInfo getEmployeeInfoByName(String employeeName,
			String userName, String password, String ldapURL,
			String ldapDomainName, String LDAP_USERS) throws Exception {

		LdapUserModel employeeUserMoedl = new LdapUserModel();
		employeeUserMoedl.setAttribute("displayName", employeeName);

		return getEmployeeInfo(employeeUserMoedl, userName, password, ldapURL,
				ldapDomainName, LDAP_USERS);
	}

	private EmployeeInfo getEmployeeInfo(LdapUserModel employeeUserMoedl,
			String userName, String password, String ldapURL,
			String ldapDomainName, String LDAP_USERS) throws Exception {

		LDAPIntegration ldapIntegration = new LDAPIntegration(ldapURL,
				ldapDomainName);

		EmployeeInfo employeeInfo = null;

		try {
			ldapIntegration.authenticateUser(userName, password, LDAP_USERS);

			employeeUserMoedl = ldapIntegration.searchForUser(
					employeeUserMoedl, LDAP_USERS);

			employeeInfo = new EmployeeInfo();

			employeeInfo.setEmployeeId(employeeUserMoedl.getLdapObjectModel()
					.getAttributes().get("employeeid").get().toString());
			employeeInfo.setDepartment(employeeUserMoedl.getLdapObjectModel()
					.getAttributes().get("department").get().toString());
			employeeInfo.setEmployeeMail(employeeUserMoedl.getLdapObjectModel()
					.getAttributes().get("mail").get().toString());
			employeeInfo.setEmployeeName(employeeUserMoedl.getLdapObjectModel()
					.getAttributes().get("displayName").get().toString());
			employeeInfo.setAccountName(employeeUserMoedl.getLdapObjectModel()
					.getAttributes().get("samaccountname").get().toString());
			employeeInfo.setMobile(employeeUserMoedl.getLdapObjectModel()
					.getAttributes().get("mobile").get().toString());
			employeeInfo.setCostCenter(employeeUserMoedl.getLdapObjectModel()
					.getAttributes().get("costcenter").get().toString());
			employeeInfo.setSubDepartment(employeeUserMoedl
					.getLdapObjectModel().getAttributes()
					.get("extensionattribute6").get().toString());

			/* get manager info */
			String managerName = employeeUserMoedl.getLdapObjectModel()
					.getAttributes().get("extensionattribute7").get()
					.toString();

			LdapUserModel managerUserModel = new LdapUserModel();
			managerUserModel.setAttribute("samaccountname", managerName);
			managerUserModel = ldapIntegration.searchForUser(managerUserModel,
					LDAP_USERS);

			employeeInfo.setManager(managerUserModel.getLdapObjectModel()
					.getAttributes().get("displayName").get().toString());
			employeeInfo.setManagerMail(managerUserModel.getLdapObjectModel()
					.getAttributes().get("mail").get().toString());
		} catch (Exception e) {
			loggerService.logPortalError("can't getEmployeeInfoByID  ", e);
			throw e;
		}
		loggerService
				.logPortalInfo(" end getEmployeeInfoByID method of ActiveDirectory ");
		return employeeInfo;
	}

	public boolean authenticateUser(String userName, String password,
			String ldapURL, String ldapDomainName, String LDAP_USERS_SB) {

		loggerService
				.logPortalInfo(" start authenticateUser method of ActiveDirectory "
						+ "name " + userName);
		LDAPIntegration ldapIntegration = new LDAPIntegration(ldapURL,
				ldapDomainName);
		LdapUserModel user = null;

		try {
			user = ldapIntegration.authenticateUser(userName, password,
					LDAP_USERS_SB);
		} catch (NamingException e) {
			loggerService
					.logPortalError(
							"ActiveDirectoryBean ---> authenticateUser() NamingException Error during login",
							e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			loggerService
					.logPortalError(
							"ActiveDirectoryBean ---> authenticateUser() Error during login",
							e);
		}

		if (user != null && user.getLdapObjectModel() != null)
			return true;
		else
			return false;
	}

	public String getUserStaffId(String userName, String password,
			String ldapURL, String ldapDomainName, String LDAP_USERS_SB) {

		loggerService
				.logPortalInfo(" start getUserStaffId method of ActiveDirectory "
						+ "name " + userName);
		LDAPIntegration ldapIntegration = new LDAPIntegration(ldapURL,
				ldapDomainName);
		LdapUserModel user = null;
		String staffId = "NotFound";
		try {
			user = ldapIntegration.authenticateUser(userName, password,
					LDAP_USERS_SB);
			staffId = user.getAttribute("employeeid");
			loggerService.logPortalInfo("staffId ==== " + staffId);
		} catch (NamingException e) {
			loggerService
					.logPortalError(
							"ActiveDirectoryBean ---> authenticateUser() NamingException Error during login",
							e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			loggerService
					.logPortalError(
							"ActiveDirectoryBean ---> authenticateUser() Error during login",
							e);
		}
		loggerService
				.logPortalInfo(" end getUserStaffId method of ActiveDirectory  id == "
						+ staffId);
		return staffId;
	}

	public String getUserMail(String userName, String password, String ldapURL,
			String ldapDomainName, String LDAP_USERS_SB) {
		loggerService
				.logPortalInfo(" start getUserMail method of ActiveDirectory "
						+ "name " + userName);
		LDAPIntegration ldapIntegration = new LDAPIntegration(ldapURL,
				ldapDomainName);
		LdapUserModel user = null;
		String mail = "NotFound";
		try {
			user = ldapIntegration.authenticateUser(userName, password,
					LDAP_USERS_SB);
			mail = user.getAttribute("mail");
			loggerService.logPortalInfo("mail ==== " + mail);
		} catch (NamingException e) {
			loggerService
					.logPortalError(
							"ActiveDirectoryBean ---> authenticateUser() NamingException Error during login",
							e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			loggerService
					.logPortalError(
							"ActiveDirectoryBean ---> authenticateUser() Error during login",
							e);
		}
		loggerService
				.logPortalInfo(" end getUserMail method of ActiveDirectory "
						+ mail);
		return mail;
	}

	public String getUserFullName(String userName, String password,
			String ldapURL, String ldapDomainName, String LDAP_USERS_SB) {
		loggerService
				.logPortalInfo(" start getUserFullName method of ActiveDirectory "
						+ "name " + userName );
		LDAPIntegration ldapIntegration = new LDAPIntegration(ldapURL,
				ldapDomainName);
		LdapUserModel user = null;
		String fullName = "";
		try {
			user = ldapIntegration.authenticateUser(userName, password,
					LDAP_USERS_SB);
			fullName = user.getAttribute("displayName");
			loggerService.logPortalInfo("mail ==== " + fullName);
		} catch (NamingException e) {
			loggerService
					.logPortalError(
							"ActiveDirectoryBean ---> authenticateUser() NamingException Error during login",
							e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			loggerService
					.logPortalError(
							"ActiveDirectoryBean ---> authenticateUser() Error during login",
							e);
		}
		loggerService
				.logPortalInfo(" end getUserMail method of ActiveDirectory  full name  = : "
						+ fullName);
		return fullName;
	}
}
