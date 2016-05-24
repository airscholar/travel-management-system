package com.truemega.service.travelreminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.ConfigDTO;
import com.truemega.dto.UploadedInvoiceFileDTO;
import com.truemega.interfaces.administration.TMSConfigService;
import com.truemega.interfaces.travelmanagementsystem.UploadedInvoicesFileService;
import com.truemega.logger.LoggerService;
import com.truemega.lookups.TravelConfigEnum;
import com.truemega.mapping.MappingFactory;
import com.truemega.notification.interfaces.NotificationService;

@Stateless(name = "ReminderServiceInterface", mappedName = "ejb/stateless/ReminderServiceInterface")
public class ReminderServiceImpl implements ReminderServiceInterface {

	private LoggerService loggerService = new LoggerService();

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	@EJB
	private TMSConfigService configService;

	@EJB
	private NotificationService notificationService;

	@EJB
	private UploadedInvoicesFileService uploadedInvoicesFileService;

	@Override
	public void sendUploadMandatoryFrom27To5() {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		Date date = new Date();
		String currentMonth = dateFormat.format(date);
		System.out.println(currentMonth); // 2013/10/15 16:16:39
		List<UploadedInvoiceFileDTO> list = uploadedInvoicesFileService
				.listUploadedInvoiceByMonth(currentMonth, "System");
		System.out.println(list + "list");
		if (!(list != null && list.size() > 0)) {
			ConfigDTO configDTO = null;

			configDTO = configService.findConfigurationByName(
					TravelConfigEnum.TravelTeam.toString(), "");
			String cc = configDTO.getConfigValue();

			configDTO = configService.findConfigurationByName(
					TravelConfigEnum.AgentTeam.toString(), "");
			String to = configDTO.getConfigValue();
			System.out.println("cc" + cc);
			List<String> ccs = new ArrayList<String>();
			ccs.add(cc);
			String body = null;
			String subject = null;

			subject = currentMonth + " Invoice is Not Uploaded";
			body = getUploadMandatoryFrom27To5Body(currentMonth);
			notificationService.sendEMail(body, subject, ccs, to);

		}
	}

	private String getUploadMandatoryFrom27To5Body(String month) {

		String str = "Dear(Travel Agent)" + "<br/><br/>"
				+ "Kindly be informed that Invoice for the current month ("
				+ month + ") is not uploaded" + " <br/><br/>" + "Thank You";

		return str;
	}

}
