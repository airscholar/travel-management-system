package com.truemega.mailsender;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.framework.entities.TravelConfig;

@Stateless
public class NotificationSender implements NotificationInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mailUserName, mailPassword, mailFrom, smtpHost,
			smtpHostPort;
	private boolean mailAuth;
	@EJB
	private GenericDAO baseDao;

	public boolean isMailAuth() {
		return mailAuth;
	}

	public void setMailAuth(boolean mailAuth) {
		this.mailAuth = mailAuth;
	}

	public String getMailUserName() {
		return mailUserName;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpHostPort() {
		return smtpHostPort;
	}

	public void setSmtpHostPort(String smtpHostPort) {
		this.smtpHostPort = smtpHostPort;
	}

	public boolean sendNotification(String to, List<String> Ccs,
			String messageSubject, String messageBody) {
		try {
			MailSenderThread senderThread = new MailSenderThread(to,
					messageSubject, messageBody, Ccs, null, getMailConfig());
			Thread mailSenderThread = new Thread(senderThread);
			mailSenderThread.start();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean sendNotification(String to, String messageSubject,
			String messageBody, List<String> attachments) {
		try {
			MailSenderThread senderThread = new MailSenderThread(to,
					messageSubject, messageBody, null, getMailConfig(),attachments);
			Thread mailSenderThread = new Thread(senderThread);
			mailSenderThread.start();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// @Override
	// public boolean sendEmailWithAttachments(String to, String subject,
	// String body, List<String> ccs, List<String> attachementsPaths) {
	//
	// try{
	// MailSenderThread senderThread = new MailSenderThread(to, subject, body,
	// ccs, null, getMailConfig());
	// Thread mailSenderThread = new Thread(senderThread);
	// mailSenderThread.start();
	// return true;
	// } catch(Exception e){
	// return false;
	// }
	// }

	public boolean sendNotification(String to, String messageSubject,
			String messageBody) {
		try {
			MailSenderThread senderThread = new MailSenderThread(to,
					messageSubject, messageBody, null, null, getMailConfig());
			Thread mailSenderThread = new Thread(senderThread);
			mailSenderThread.start();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// @Override
	// public boolean sendNotification(String to, String subject, String body,
	// List<String> attachementsPaths) {
	// try{
	// MailSenderThread senderThread = new MailSenderThread(to, subject, body,
	// null, null, getMailConfig());
	// Thread mailSenderThread = new Thread(senderThread);
	// mailSenderThread.start();
	// return true;
	// } catch(Exception e){
	// return false;
	// }
	// }

	@PostConstruct
	public void init() {
		String query = "select  model from travelConfig model";
		List<TravelConfig> travelConfigs = baseDao.executeDynamicQuery(query,
				TravelConfig.class, false);
		for (Iterator iterator = travelConfigs.iterator(); iterator.hasNext();) {
			TravelConfig travelConfig = (TravelConfig) iterator.next();
			if (travelConfig.getConfigName().equals("MAIL_PORT"))
				smtpHostPort = travelConfig.getConfigValue();
			if (travelConfig.getConfigName().equals("MAIL_HOST"))
				smtpHost = travelConfig.getConfigValue();
			if (travelConfig.getConfigName().equals("MAIL_FROM"))
				mailFrom = travelConfig.getConfigValue();
			if (travelConfig.getConfigName().equals("MAIL_AUTH"))
				mailAuth = Boolean.valueOf(travelConfig.getConfigValue());
			if (travelConfig.getConfigName().equals("MAIL_USERNAME"))
				mailUserName = travelConfig.getConfigValue();
			if (travelConfig.getConfigName().equals("MAIL_PASSWORD"))
				mailPassword = travelConfig.getConfigValue();
		}
	}

	public boolean sendEmailWithAttachments(List<String> tos, String subject,
			String body, List<String> ccs, List<NotificationFile> attachements) {
		Boolean status = true;
		for (String to : tos) {
			try {
				MailSenderThread senderThread = new MailSenderThread(to,
						subject, body, ccs, attachements, getMailConfig());
				Thread mailSenderThread = new Thread(senderThread);
				mailSenderThread.start();
			} catch (Exception e) {
				status = false;
			}
		}
		return status;
	}

	private MailConfig getMailConfig() {
		MailConfig config = new MailConfig();
		config.setAuthentication(this.mailAuth);
		config.setFrom(mailFrom);
		config.setMailPassword(mailPassword);
		config.setMailUserName(mailPassword);
		config.setSmtpHost(smtpHost);
		config.setSmtpHostPort(smtpHostPort);
		return config;
	}
}
