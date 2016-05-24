package com.truemega.services.travelmanagementsystem;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.ConfigDTO;
import com.truemega.dto.UploadedInvoiceFileDTO;
import com.truemega.entities.UploadedInvoiceFile;
import com.truemega.interfaces.administration.TMSConfigService;
import com.truemega.interfaces.travelmanagementsystem.UploadedInvoicesFileService;
import com.truemega.logger.LoggerService;
import com.truemega.lookups.TravelConfigEnum;
import com.truemega.mapping.MappingFactory;
import com.truemega.notification.interfaces.NotificationService;

@Stateless
public class UploadedInvoicesFileServiceImpl implements
		UploadedInvoicesFileService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public List<UploadedInvoiceFileDTO> listUploadedInvoiceTempFiles(
			String userName) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  listUploadedInvoiceTempFiles Method with userName  "
						+ userName);
		// String query = "select model from UploadedInvoiceFile model ";

		// String query =
		// "select model1 from UploadedInvoiceFile model1 where model1.id in "
		// +
		// " (select DISTINCT model.uploadedInvoiceFileId.id from InvoicesTemp model )";

		String query = "select model1 from UploadedInvoiceFile model1 where model1.templateTable=true ";
		return mapper.mapAsList(baseDao.findListByQuery(query),
				UploadedInvoiceFileDTO.class);

	}

	@Override
	public List<UploadedInvoiceFileDTO> listUploadedInvoiceActualFiles(
			String userName) {
		loggerService
				.logServiceInfo("Start  listUploadedInvoiceActualFilesWithNoActionTaken Method with userName  "
						+ userName);

		// String query =
		// "select model1 from UploadedInvoiceFile model1 where model1.templateTable=false and model1.approved IS NULL";
		String query = "select model1 from UploadedInvoiceFile model1 where model1.templateTable=false";
		return mapper.mapAsList(baseDao.findListByQuery(query),
				UploadedInvoiceFileDTO.class);
	}

	@Override
	public UploadedInvoiceFileDTO findUploadedInvoiceFileById(Integer fileId,
			String userName) {
		loggerService.logServiceInfo(userName
				+ ">> Start findUploadedInvoiceFileById() fileId= " + fileId);
		UploadedInvoiceFileDTO result = null;

		UploadedInvoiceFile uploadedInvoiceFile = baseDao.findEntityById(
				UploadedInvoiceFile.class, fileId);
		result = mapper.map(uploadedInvoiceFile, UploadedInvoiceFileDTO.class);

		loggerService.logServiceInfo(userName
				+ ">> End findUploadedInvoiceFileById() With Successfully");

		return result;
	}

	@Override
	public UploadedInvoiceFileDTO updateUploadedInvoice(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName)
			throws Exception {
		// TODO Auto-generated method stub

		UploadedInvoiceFile uploadedInvoiceFile = baseDao.updateEntity(mapper
				.map(uploadedInvoiceFileDTO, UploadedInvoiceFile.class));
		UploadedInvoiceFileDTO result = mapper.map(uploadedInvoiceFile,
				UploadedInvoiceFileDTO.class);

		return result;
	}

	private String getActionNotificationBody(String actionName,
			String actionTaker, String month) {

		String str = "Dear(Travel Agent)" + "<br/><br/>"
				+ "Kindly be informed that Vodafone Travel Team(" + actionTaker
				+ ") " + actionName + " " + month + " Invoice " + " <br/><br/>"
				+ "Thank You";

		return str;
	}

	@Override
	public void testNotification() {

		String year = "2016";
		int fileID=141;

		String query = "SELECT \n" + " RESULT1.SERVICE_DESC, \n"
				+ " RESULT1.SUPPLIER_NAME, \n" + " RESULT1.ROOM_TYPE, \n"
				+ " RESULT1.RATE, \n" + " RESULT2.NET_AMOUNT_ONE_NIGHT, \n"
				+ " RESULT2.INVOICE_ORDER, \n" + " RESULT2.INVOICE_NUMBER \n"
				+ " FROM \n" + " (SELECT lower(st.NAME) as SERVICE_TYPE, \n"
				+ " lower(pt.NAME) as SERVICE_DESC, \n"
				+ " lower(s.NAME) as SUPPLIER_NAME , \n"
				+ " lower(rt.NAME) as ROOM_TYPE, \n" + " r.RATE AS RATE \n"
				+ " FROM SERVICE_TYPE st \n" + " INNER JOIN PRODUCT_TYPE pt \n"
				+ " ON st.ID = pt.SERVICE_ID \n"
				+ " AND LOWER(st.NAME) LIKE 'hotel' \n"
				+ " INNER JOIN SUPPLIER_PRODUCT sp \n"
				+ " ON pt.ID = sp.PRODUCT_ID \n" + " INNER JOIN RATES r \n"
				+ " ON sp.ID   = r.SUPPLIER_PRODUCT_ID \n" + " AND r.YEAR = "
				+ year
				+ " \n"
				+ " INNER JOIN ROOM_TYPE rt \n"
				+ " ON rt.ID = r.ROOM_TYPE_ID \n"
				+ " INNER JOIN SUPPLIER s \n"
				+ " ON s.ID = sp.SUPPLIER_ID) RESULT1 \n"

				+ " INNER JOIN (SELECT i.INVOICE_ORDER, \n"
				+ " i.INVOICE_NUMBER, \n"
				+ " lower(i.ROOM_TYPE) AS ROOM_TYPE, \n"
				+ " lower(i.SUPPLIER_NAME) AS SUPPLIER_NAME, \n"
				+ " lower(i.SERVICE_DESC) AS SERVICE_DESC, \n"
				+ " lower(i.SERVICE_TYPE) AS SERVICE_TYPE, \n"
				+ " i.NET_AMOUNT /((i.CHECK_OUT-i.CHECK_IN)+1) As NET_AMOUNT_ONE_NIGHT \n"

				+ " FROM INVOICES i \n"
				+ " INNER JOIN UPLOADED_INVOICE_FILE uif \n"
				+ " ON uif.ID = i.UPLOADED_INVOICE_FILE_ID \n"
				+ " AND uif.ID= "+fileID+" \n"
				+ " And LOWER(i.SERVICE_TYPE) like 'hotel') RESULT2 \n"

				+ " ON RESULT2.ROOM_TYPE LIKE RESULT1.ROOM_TYPE \n"
				+ " AND  RESULT2.SERVICE_DESC LIKE RESULT1.SERVICE_DESC \n"
				+ " AND  RESULT2.SUPPLIER_NAME LIKE RESULT1.SUPPLIER_NAME \n"
				+ " AND  RESULT1.RATE > RESULT2.NET_AMOUNT_ONE_NIGHT";

		List<Object[]> result = null;
		result = baseDao.executeNativeQuery(query);

		System.out.println(result.size());

	}

	@EJB
	private NotificationService notificationService;

	@EJB
	private TMSConfigService configService;

	@Override
	public UploadedInvoiceFileDTO takeAction(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName)
			throws Exception {

		try {

			UploadedInvoiceFileDTO result = updateUploadedInvoice(
					uploadedInvoiceFileDTO, userName);

			ConfigDTO configDTO = null;

			configDTO = configService.findConfigurationByName(
					TravelConfigEnum.TravelTeam.toString(), "");
			String to = configDTO.getConfigValue();
			String body = null;
			String subject = null;

			if (result.getApproved()) {
				subject = result.getInvoicesMonth()
						+ " Invoice is Accepted by Vodafone Travel Team";
				body = getActionNotificationBody("approved",
						result.getActionTaker(), result.getInvoicesMonth());
			} else {
				subject = result.getInvoicesMonth()
						+ " Invoice is Rejected by Vodafone Travel Team";
				body = getActionNotificationBody("rejected",
						result.getActionTaker(), result.getInvoicesMonth());
			}

			notificationService.sendEMail(body, subject, to);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<UploadedInvoiceFileDTO> listUploadedInvoiceByMonth(
			String month, String userName) {
		System.out.println(month);
		loggerService
				.logServiceInfo("Start  listUploadedInvoiceByMonth Method with userName  "
						+ userName);

		String query = "select model1 from UploadedInvoiceFile model1 where model1.invoicesMonth like '"
				+ month + "'";
		List<Object> result = baseDao.findListByQuery(query);
		System.out.println(result + "reult");
		return mapper.mapAsList(result, UploadedInvoiceFileDTO.class);
	}
}
