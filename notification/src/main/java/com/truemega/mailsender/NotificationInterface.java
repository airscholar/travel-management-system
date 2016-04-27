package com.truemega.mailsender;

import java.util.List;

import javax.ejb.Local;

@Local
public interface NotificationInterface {
	public boolean sendNotification(String to, List<String> Ccs,
			String messageSubject, String messageBody);

	public boolean sendNotification(String to, String Subject, String body);

	public boolean sendNotification(String to, String Subject, String body,
			List<String> attachementPaths);

	public boolean sendEmailWithAttachments(List<String> to, String subject,
			String body, List<String> ccs,
			List<NotificationFile> attachementsPaths);
}
