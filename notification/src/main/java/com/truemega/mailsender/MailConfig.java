package com.truemega.mailsender;

public class MailConfig {
	private Boolean authentication;
	private String smtpHost;
	private String smtpHostPort;
	private String mailUserName;
	private String mailPassword;
	private String from;

	public Boolean getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Boolean authentication) {
		this.authentication = authentication;
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
