package com.truemega.mailsender;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class MailSenderThread implements Runnable {

	private String to;
	private String subject;
	private String body;
	private List<String> ccs;
	private List<NotificationFile> attachements;
	private MailConfig mailConfig;
	private List<String> paths;

	public MailSenderThread(String to, String subject, String body,
			List<String> ccs, List<NotificationFile> attachements,
			MailConfig config) {
		this.attachements = attachements;
		this.ccs = ccs;
		this.body = body;
		this.subject = subject;
		this.mailConfig = config;
		this.to = to;
	}

	public MailSenderThread(String to, String subject, String body,
			List<String> ccs, MailConfig config,List<String> attachments) {
		this.ccs = ccs;
		this.body = body;
		this.subject = subject;
		this.mailConfig = config;
		this.to = to;
		this.paths = attachments;
	}

	public void run() {
		boolean outcome = false;
		try {

			Session session = null;
			if (mailConfig.getAuthentication())
				session = getMailSessionWithAuth(mailConfig.getSmtpHost(),
						mailConfig.getSmtpHostPort(),
						mailConfig.getMailUserName(),
						mailConfig.getMailPassword());

			else
				session = getMailSessionWithAuth(mailConfig.getSmtpHost(),
						mailConfig.getSmtpHostPort());

			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(mailConfig.getFrom()));

				message.addRecipients(Message.RecipientType.TO, to);

				if (ccs != null) {
					if (!ccs.isEmpty())
						for (String cc : ccs) {
							if (cc != null) {
								message.addRecipients(Message.RecipientType.CC,
										cc);
							}
						}
				}

				// Set Subject: header field
				message.setSubject(subject);
				// Send the actual HTML message, as big as you like
				// message.setContent(body, "text/html");

				// *****************************************************
				// delete: message.setContent(body, "text/html");
				// create the message part
				
				if (attachements != null && attachements.size() > 0) {
					MimeBodyPart messageBodyPart = new MimeBodyPart();
					// fill message
					messageBodyPart.setText(body);
					messageBodyPart.setContent(body, "text/html");
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);
					// Part two is attachment
					if (attachements != null && attachements.size() > 0) {
						for (NotificationFile notificationFile : attachements) {
							messageBodyPart = new MimeBodyPart();
							ByteArrayInputStream byteIS = new ByteArrayInputStream(
									notificationFile.getFileContent());
							ByteArrayDataSource ds = new ByteArrayDataSource(
									byteIS, notificationFile.getMimeType());
							messageBodyPart = new MimeBodyPart();
							messageBodyPart.setDataHandler(new DataHandler(ds));
							messageBodyPart.setFileName(notificationFile
									.getFileName());
							multipart.addBodyPart(messageBodyPart);
						}
					}
					message.setContent(multipart);
				} else if (paths != null && paths.size() > 0) {
					MimeBodyPart messageBodyPart = new MimeBodyPart();
					// fill message
					messageBodyPart.setText(body);
					messageBodyPart.setContent(body, "text/html");
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);
					// Part two is attachment
					if (paths != null && paths.size() > 0) {
						for (String notificationFile : paths) {
							messageBodyPart = new MimeBodyPart();

							messageBodyPart = new MimeBodyPart();
							messageBodyPart.setDataHandler(new DataHandler(
									new FileDataSource(notificationFile)));
							if(notificationFile.contains("./RoadManagement/ViolationNotificationExcelOutput/"))
								notificationFile=notificationFile.substring("./RoadManagement/ViolationNotificationExcelOutput/".length());
							
							messageBodyPart.setFileName(notificationFile
									.substring(notificationFile
											.lastIndexOf(File.separator) + 1));
							multipart.addBodyPart(messageBodyPart);
						}
					}
					message.setContent(multipart);
				} else if ((paths == null || paths.size() == 0) && (attachements == null || attachements.size() == 0)){
					message.setText(body, "utf-8", "html");
				}
				// Put parts in message

				// *****************************************************
				// Send message
				
				
				Transport.send(message);
				outcome = true;
			} catch (MessagingException mex) {
				throw mex;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Session getMailSessionWithAuth(String smtpHost, String smtpPort,
			final String username, final String password) {
		Session session = null;
		try {

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.port", smtpPort);

			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"vftestmails@gmail.com", "Vfdev123");
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return session;
	}

	private Session getMailSessionWithAuth(String smtpHost, String smtpPort) {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		Session session = Session.getInstance(properties);
		return session;
	}

	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

}
