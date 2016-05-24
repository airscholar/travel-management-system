package com.truemega.notification.services;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.framework.entities.Notification;
import com.truemega.mailsender.NotificationFile;
import com.truemega.mailsender.NotificationInterface;
import com.truemega.notification.interfaces.NotificationService;

@Stateless
public class NotificationServiceImpl implements NotificationService {
	@EJB
	private GenericDAO baseDao;
	@EJB
	private NotificationInterface notificationInterface;
	private String fileContent;

	public Notification loadTemplate(String templateName)
			throws UnsupportedEncodingException {
		String query = "select model from Notification model where model.templateName=:templateName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("templateName", templateName);
		Notification notification = baseDao.findEntityByQuery(query, params);
		return notification;
	}

	public String populateEMailTemplate(Notification notification, Object input) {
		try {
			fileContent = new String(notification.getAttachFile(), "UTF-8");
			// Replace list tags
			replaceListValues(input);
			// replace Single tags
			replaceSingleValues(fileContent, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(fileContent);
		return fileContent;
	}

	public void sendEMail(String template, Object object, String from,
			String to, List<String> attachementPaths) {
		Notification notification = null;
		// Get Template from DB
		try {
			notification = loadTemplate(template);
			String subject = notification.getSubject();
			// Replace Data
			String email = populateEMailTemplate(notification, object);

			// TODO: Send Mail
			if (attachementPaths != null && attachementPaths.size() > 0)
				notificationInterface.sendNotification(to, subject, email,
						attachementPaths);
			else
				notificationInterface.sendNotification(to, subject, email);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendEMail(String body, String subject, String to) {
		try {
			notificationInterface.sendNotification(to, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean sendEMail(String template, Object object, String from,
			List<String> multipleTo, List<String> attachementPaths) {
		Boolean result = false;
		Notification notification = null;
		// Get Template from DB
		try {
			notification = loadTemplate(template);
			String subject = notification.getSubject();
			// Replace Data
			String email = populateEMailTemplate(notification, object);

			for (String to : multipleTo) {
				// TODO: Send Mail
				if (attachementPaths != null && attachementPaths.size() > 0)
					notificationInterface.sendNotification(to, subject, email,
							attachementPaths);
				else
					notificationInterface.sendNotification(to, subject, email);
			}

			result = true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Boolean sendHTMLEmail(String from, String to, String subject,
			String content) {
		return false;
	}

	public Boolean sendHTMLEmailWithAttachments(String from, String to,
			String subject, String content, List<File> attachments) {
		return false;
	}

	private String[] splitSingleTag(String content) {
		String[] result = null;
		String row = "";
		final int STARTLENGTH = 8;
		final int ENDLENGTH = 9;
		StringBuilder columnBuilder = new StringBuilder(content);
		StringBuilder contentBuilder = new StringBuilder(fileContent);
		int _stridx = columnBuilder.indexOf("<single>");
		int _endidx = columnBuilder.indexOf("</single>");
		while (_stridx != -1) {
			String column = columnBuilder.substring(_stridx + STARTLENGTH,
					_endidx);
			columnBuilder = columnBuilder.replace(_stridx, _endidx + 9, column);

			int strpointer = contentBuilder.indexOf("_SPNTR");
			int endpointer = contentBuilder.indexOf("_EPNTR");
			if (strpointer != -1 && endpointer != -1) {
				contentBuilder = contentBuilder.replace(strpointer,
						endpointer + 6, columnBuilder.toString());
				fileContent = contentBuilder.toString();
			} else {
				fileContent = columnBuilder.toString();
			}

			row = row + "," + column;
			_stridx = columnBuilder.indexOf("<single>", _stridx + 1);
			_endidx = columnBuilder.indexOf("</single>", _endidx + 1);
		}
		row = row.replaceFirst(",", "");
		result = row.split(",");

		return result;
	}

	private String[] splitListTag() {
		String[] result = null;
		String row = "";
		final int START = 6;
		final int END = 7;
		StringBuilder contentBuilder = new StringBuilder(fileContent);
		int _stridx = contentBuilder.indexOf("<list>");
		int _endidx = contentBuilder.indexOf("</list>");
		while (_stridx != -1) {
			String columnWithSingleTag = contentBuilder.substring(_stridx
					+ START, _endidx);
			// remove list tag
			contentBuilder = contentBuilder.replace(_stridx, _endidx + END,
					"_SPNTR" + columnWithSingleTag + "_EPNTR");
			fileContent = contentBuilder.toString();
			String[] single = splitSingleTag(columnWithSingleTag);
			row = row + "," + single[0];
			_stridx = contentBuilder.indexOf("<list>", _stridx + 1);
			_endidx = contentBuilder.indexOf("</list>", _endidx + 1);
		}
		if (_stridx != -1 && _endidx != -1) {
			row = row.replaceFirst(",", "");
			result = row.split(",");
		}
		return result;
	}

	private String getMethodName(String singlePath) {
		final String GET = "get";
		return GET + singlePath.substring(0, 1).toUpperCase()
				+ singlePath.substring(1);
	}

	private Object getObject(String methodName, Object obj)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Class cls = obj.getClass();
		Method method = cls.getDeclaredMethod(methodName);
		return method.invoke(obj, null);

	}

	private String[] getSingleValues(String[] singleRows, Object input)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		final int START = 8;
		Object param = input;
		String[] values = new String[singleRows.length];
		int counter = 0;
		for (String row : singleRows) {
			String str = row.replace('.', '_');
			String[] fields = str.split("_");
			for (String field : fields) {
				String methodName = getMethodName(field);
				try {
					param = getObject(methodName, param);
					if (param == null)
						break;
					values[counter] = param.toString();
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
			System.out.println(param.toString());
			counter = counter + 1;
			param = input;
		}
		return values;
	}

	private String[][] getListValues(String[] listRows, Object input)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Object param = input;
		int rows = 0;
		String values = "";
		String[] fieldVlaues = null;
		for (String row : listRows) {
			String str = row.replace('.', '_');
			String[] fields = str.split("_");
			String listMethodName = getMethodName(fields[0]);
			param = getObject(listMethodName, param);
			for (Object obj : (List) param) {
				Object listParam = obj;
				for (int i = 1; i < fields.length; i++) {
					String singleMethodName = getMethodName(fields[i]);
					listParam = getObject(singleMethodName, listParam);
				}
				System.out.println(listParam.toString());
				values = values + "," + listParam.toString();
			}
			param = input;
			values = values.replaceFirst(",", "");
			fieldVlaues = values.split(",");
			rows++;
		}
		String[][] listValues = new String[rows][fieldVlaues.length];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < fieldVlaues.length; j++) {
				listValues[i][j] = fieldVlaues[j];
			}
		}
		return listValues;
	}

	private void fillSingleTagsWithValues(String content, String[] tags,
			String[] values) {
		int length = tags.length;
		for (int i = 0; i < length; i++) {
			content = content.replaceFirst(tags[i], values[i]);
		}
		fileContent = content;
	}

	private void fillListTagsWithValues(String[] tags, String[][] listValues) {
		String values = "";
		for (int i = 0; i < listValues.length; i++) {
			for (int j = 0; j < listValues[i].length; j++) {
				values = values + "<li>" + listValues[i][j] + "</li>";
			}
		}
		for (int i = 0; i < tags.length; i++) {
			fileContent = fileContent.replace(tags[i], values);
		}
	}

	private void replaceSingleValues(String content, Object input)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		String[] singleRows = splitSingleTag(content);
		String[] values = getSingleValues(singleRows, input);
		fillSingleTagsWithValues(fileContent, singleRows, values);
	}

	private void replaceListValues(Object input) throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		String[] listRows = splitListTag();
		if (listRows != null) {
			String[][] dd = getListValues(listRows, input);
			fillListTagsWithValues(listRows, dd);
		}
	}

	public Boolean sendEmailWithAttachments(String template, Object entity,
			String from, List<String> to, String subject, List<String> ccs,
			List<NotificationFile> attachements) {
		Boolean result = false;
		Notification notification = null;
		// Get Template from DB
		try {
			notification = loadTemplate(template);

			// Replace Data
			String email = populateEMailTemplate(notification, entity);

			// TODO: Send Mail
			notificationInterface.sendEmailWithAttachments(to, subject, email,
					ccs, attachements);
			result = true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void sendEMail(String body, String subject, String to,
			List<String> attachments) {
		try {
			notificationInterface.sendNotification(to, subject, body,
					attachments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendEMail(String body, String subject, List<String> ccs,
			String to) {
		try {
			notificationInterface.sendNotification(to, ccs, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}