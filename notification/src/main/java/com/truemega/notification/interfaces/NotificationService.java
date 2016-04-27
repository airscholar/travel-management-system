package com.truemega.notification.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.mailsender.NotificationFile;

@Remote
public interface NotificationService {

	public Boolean sendEmailWithAttachments(String template, Object entity,
			String from, List<String> to, String subject, List<String> ccs,
			List<NotificationFile> attachementsPaths);

	public void sendEMail(String mailTemplateName, Object entity, String from,
			String to, List<String> attachments);

	public void sendEMail(String body, String subject, String to);

	public void sendEMail(String body, String subject, String to,
			List<String> attachments);

}