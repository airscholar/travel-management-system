package com.truemega.interfaces.notification;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.ConfigDTO;
import com.truemega.interfaces.administration.TMSConfigService;
import com.truemega.lookups.TravelConfigEnum;
import com.truemega.mapping.MappingFactory;
import com.truemega.notification.interfaces.NotificationService;

@Stateless
public class UploadNotificationSenderImpl implements UploadNotificationSender {

	@EJB
	private NotificationService notificationService;

	@EJB
	private TMSConfigService configService;

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	@Override
	public void sendNotificationInvoiceUploaded(String month, String to) {

		String body = getBody(month);
		String subject = month + " Invoice is Uploaded";
		notificationService.sendEMail(body, subject, to);

	}

	private String getBody(String month) {

		String str = "Dear(Travel Team)" + "<br/><br/>"
				+ "Kindly be informed that Invoice for the  month (" + month
				+ ") is  uploaded" + " <br/><br/>" + "Thank You";

		return str;
	}

	@Override
	public void sendNotificationNotificationReservationUnknownHotel(
			String month, String to, int fileID) {

		List<Object[]> results = getUnknownHotels(fileID);
		if (results.size() > 0) {
			String body = getBodyUnknownHotels(month, results);
			String subject = month
					+ "Notification Reservation was on an Unknown Hotel";
			notificationService.sendEMail(body, subject, to);
		}

	}

	private String getBodyUnknownHotels(String month, List<Object[]> results) {
		UnknownHotelsHTML unknownHotelsHTML = new UnknownHotelsHTML();
		String str = "Dear(Travel Team)"
				+ "<br/><br/>"
				+ "Kindly be informed that the below are the Unknown Hotels for this month ("
				+ month + ") " + unknownHotelsHTML.buildHTMLTable(results)
				+ " <br/><br/>" + "Thank You";

		return str;
	}

	public List<Object[]> getUnknownHotels(int fileID) {
		// TODO Auto-generated method stub

		String query = " SELECT INVOICE_ORDER ,  INVOICE_NUMBER, SUPPLIER_NAME "
				+ "FROM INVOICES i"
				+ " WHERE i.UPLOADED_INVOICE_FILE_ID="
				+ fileID
				+ " AND "
				+ "lower (i.SERVICE_TYPE) LIKE 'hotel'  "
				+ "AND lower (i.SUPPLIER_NAME) NOT IN ( SELECT DISTINCT(lower (s.NAME))FROM SUPPLIER_PRODUCT sp"
				+ " INNER JOIN SUPPLIER s  ON s.ID = sp.SUPPLIER_ID "
				+ " INNER JOIN PRODUCT_TYPE pt ON pt.ID = sp.PRODUCT_ID "
				+ " INNER JOIN SERVICE_TYPE st "
				+ " ON st.ID = pt.SERVICE_ID  AND lower(st.NAME) LIKE 'hotel' )";

		List<Object[]> resultUnknownHotels = baseDao.executeNativeQuery(query);

		return resultUnknownHotels;

	}

	public List<Object[]> maxInsurancePeriod(int fileId) {

		String query = "SELECT i.INVOICE_ORDER , i.INVOICE_NUMBER ,i.SERVICE_TYPE ,i.SUPPLIER_NAME ,"
				+ " TO_CHAR(i.FROM_DATE, 'DD/MM/YYYY') as FROM_DATE , TO_CHAR(i.TO_DATE, 'DD/MM/YYYY') as TO_DATE ,( i.TO_DATE - i.FROM_DATE + 1) AS Period"
				+ " FROM INVOICES i"
				+ " WHERE lower ( i.SERVICE_TYPE) = lower ('insurance')"
				+ " AND i.UPLOADED_INVOICE_FILE_ID ="
				+ fileId
				+ " AND ( i.TO_DATE - i.FROM_DATE + 1) > (SELECT MAX_MIN_VALUE FROM MAX_MIN_VALUES WHERE ID = 2 )";

		List<Object[]> maxPeriods = baseDao.executeNativeQuery(query);

		return maxPeriods;

	}

	private String getBodyMaxPeriod(String month, List<Object[]> results) {
		MaxInsurancePeriod maxInsurancePeriod = new MaxInsurancePeriod();
		String str = "Dear(Travel Team)"
				+ "<br/><br/>"
				+ "Kindly be informed that the below invoices hit the Max Insurance Periods for this month ("
				+ month + ") " + "<br/><br/>"
				+ maxInsurancePeriod.buildHTMLTable(results) + " <br/><br/>"
				+ "Thank You";

		return str;
	}

	@Override
	public void sendNotificationMaxInsurancePeriod(String month, String to,
			int filedId) {

		List<Object[]> results = maxInsurancePeriod(filedId);
		if (results.size() > 0) {
			String body = getBodyMaxPeriod(month, results);
			String subject = month
					+ " Medical Insurance Period is Higher than Maximum ";
			notificationService.sendEMail(body, subject, to);
		}

	}

	public List<Object[]> getVisaMaxPeriod(int filedId) {

		String query = "SELECT  i.INVOICE_ORDER , i.INVOICE_NUMBER , i.SERVICE_TYPE ,i.SUPPLIER_NAME ,"
				+ " TO_CHAR(i.FROM_DATE, 'DD/MM/YYYY') as FROM_DATE , TO_CHAR(i.TO_DATE, 'DD/MM/YYYY') as TO_DATE ,( i.TO_DATE - i.FROM_DATE + 1) AS Period"
				+ " FROM INVOICES i"
				+ " WHERE Upper ( i.SERVICE_TYPE) =  'CWT FEE'"
				+ " AND   Upper (i.SERVICE_DESC)='VISA/TRAVEL DOCUMENTATION'"
				+ " AND i.UPLOADED_INVOICE_FILE_ID    = "
				+ filedId
				+ " AND ( i.TO_DATE - i.FROM_DATE + 1) > (SELECT MAX_MIN_VALUE FROM MAX_MIN_VALUES WHERE ID = 1 )";
		System.out.println(" Query = " + query);
		List<Object[]> maxVisaPeriod = baseDao.executeNativeQuery(query);

		return maxVisaPeriod;

	}

	private String getBodyVisaPeriod(String month, List<Object[]> results) {
		MaxVisaPeriod maxVisaPeriod = new MaxVisaPeriod();
		String str = "Dear(Travel Team)"
				+ "<br/><br/>"
				+ "Kindly be informed that the below invoices hit the Max Visa Periods for this month ("
				+ month + ") " + "<br/><br/>"
				+ maxVisaPeriod.buildHTMLTable(results) + " <br/><br/>"
				+ "Thank You";

		return str;
	}

	@Override
	public void sendNotificationMaxVisaPeriod(String month, String to,
			int filedId) {

		List<Object[]> results = getVisaMaxPeriod(filedId);
		if (results.size() > 0) {
			String body = getBodyVisaPeriod(month, results);
			String subject = month + " Visa Period is Higher than Maximum ";
			notificationService.sendEMail(body, subject, to);
		}

	}

	public List<Object[]> getmaxAirPlanTecket(int fileId) {

		String query = "SELECT i.INVOICE_ORDER ,i.INVOICE_NUMBER ,i.SERVICE_TYPE ,  i.SERVICE_DESC , "
				+ " i.SUPPLIER_NAME ,i.NET_AMOUNT FROM INVOICES i "
				+ " WHERE ((Upper ( i.SERVICE_TYPE) = Upper ('Air') "
				+ " AND Upper (i.SERVICE_DESC)    = Upper ('AIR TICKET DOMESTIC' )) "
				+ " OR (Upper ( i.SERVICE_TYPE)    = Upper ('Air')"
				+ " AND Upper (i.SERVICE_DESC)     = Upper ('AIR TICKET EUROPE')) "
				+ " OR (Upper ( i.SERVICE_TYPE)    = Upper ('Air')"
				+ " AND Upper (i.SERVICE_DESC)     =Upper ('AIR TICKET INTERNATIONAL' )))"
				+ " AND i.UPLOADED_INVOICE_FILE_ID = "
				+ fileId
				+ " AND i.NET_AMOUNT   >  (SELECT MAX_MIN_VALUE FROM MAX_MIN_VALUES WHERE ID = 3 )";

		List<Object[]> maxAirPanlTecket = baseDao.executeNativeQuery(query);
		return maxAirPanlTecket;

	}

	private String getBodyAirLineTicketMax(String month, List<Object[]> results) {
		MaxAirLineTicket maxAirLineTicket = new MaxAirLineTicket();
		String str = "Dear(Travel Team)"
				+ "<br/><br/>"
				+ "Kindly be informed that the below invoices hit the Max Net Amount of Ticket  for this month ("
				+ month + ") " + "<br/><br/>"
				+ maxAirLineTicket.buildHTMLTable(results) + " <br/><br/>"
				+ "Thank You";

		return str;
	}

	@Override
	public void sendNotificationMaxAirLineTicket(String month, String to,
			int fileId) {

		List<Object[]> results = getmaxAirPlanTecket(fileId);
		if (results.size() > 0) {
			String body = getBodyAirLineTicketMax(month, results);
			String subject = month
					+ " Plane ticket Price is higher than Maximum ";
			notificationService.sendEMail(body, subject, to);
		}

	}

	public List<Object[]> getminAirPlanTecket(int fileId) {

		String query = "SELECT i.INVOICE_ORDER ,i.INVOICE_NUMBER ,i.SERVICE_TYPE ,i.SERVICE_DESC ,i.SUPPLIER_NAME ,i.NET_AMOUNT"
				+ " FROM INVOICES i WHERE "
				+ " ( (Upper ( i.SERVICE_TYPE) = Upper ('Air') AND Upper (i.SERVICE_DESC) = Upper ('AIR TICKET DOMESTIC' ))"
				+ " OR (Upper ( i.SERVICE_TYPE) = Upper ('Air') AND   Upper (i.SERVICE_DESC)= Upper ('AIR TICKET EUROPE'))"
				+ " OR (Upper ( i.SERVICE_TYPE) =  Upper ('Air')AND   Upper (i.SERVICE_DESC)= Upper ('AIR TICKET INTERNATIONAL')))"
				+ " AND i.UPLOADED_INVOICE_FILE_ID = "
				+ fileId
				+ " AND i.NET_AMOUNT    < (SELECT MAX_MIN_VALUE FROM MAX_MIN_VALUES WHERE ID = 4 ) ";

		List<Object[]> maxAirPanlTecket = baseDao.executeNativeQuery(query);
		return maxAirPanlTecket;

	}

	private String getBodyAirLineTicketMin(String month, List<Object[]> results) {
		MinAirLineTicket minAirLineTicket = new MinAirLineTicket();
		String str = "Dear(Travel Team)"
				+ "<br/><br/>"
				+ "Kindly be informed that the below invoices hit the Min Net Amount of Ticket  for this month ("
				+ month + ") " + "<br/><br/>"
				+ minAirLineTicket.buildHTMLTable(results) + " <br/><br/>"
				+ "Thank You";

		return str;
	}

	@Override
	public void sendNotificationMinAirLineTicket(String month, String to,
			int fileId) {

		List<Object[]> results = getminAirPlanTecket(fileId);
		if (results.size() > 0) {
			String body = getBodyAirLineTicketMin(month, results);
			String subject = month
					+ " Plane ticket Price is lower than Minimum ";
			notificationService.sendEMail(body, subject, to);
		}

	}

	public List<Object[]> getHotelRateAbovePreNegotiatedRates(String year,
			int fileID) {

		String query = "SELECT \n" + " RESULT2.INVOICE_ORDER, \n"
				+ " RESULT2.INVOICE_NUMBER, \n RESULT1.SERVICE_DESC, \n"
				+ " RESULT1.SUPPLIER_NAME, \n" + " RESULT1.ROOM_TYPE, \n"
				+ " RESULT1.RATE, \n" + " RESULT2.NET_AMOUNT_ONE_NIGHT \n"
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
				+ " AND uif.ID= "
				+ fileID
				+ " \n"
				+ " And LOWER(i.SERVICE_TYPE) like 'hotel') RESULT2 \n"

				+ " ON RESULT2.ROOM_TYPE LIKE RESULT1.ROOM_TYPE \n"
				+ " AND  RESULT2.SERVICE_DESC LIKE RESULT1.SERVICE_DESC \n"
				+ " AND  RESULT2.SUPPLIER_NAME LIKE RESULT1.SUPPLIER_NAME \n"
				+ " AND  RESULT1.RATE < RESULT2.NET_AMOUNT_ONE_NIGHT";

		List<Object[]> result = null;
		result = baseDao.executeNativeQuery(query);

		return result;
	}

	public List<Object[]> getHotelRateBelowPreNegotiatedRates(String year,
			int fileID) {

		String query = "SELECT \n"
				+ " RESULT2.INVOICE_ORDER , RESULT2.INVOICE_NUMBER , RESULT1.SERVICE_DESC, \n"
				+ " RESULT1.SUPPLIER_NAME, \n" + " RESULT1.ROOM_TYPE, \n"
				+ " RESULT1.RATE, \n" + " RESULT2.NET_AMOUNT_ONE_NIGHT \n"
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
				+ " AND uif.ID= "
				+ fileID
				+ " \n"
				+ " And LOWER(i.SERVICE_TYPE) like 'hotel') RESULT2 \n"

				+ " ON RESULT2.ROOM_TYPE LIKE RESULT1.ROOM_TYPE \n"
				+ " AND  RESULT2.SERVICE_DESC LIKE RESULT1.SERVICE_DESC \n"
				+ " AND  RESULT2.SUPPLIER_NAME LIKE RESULT1.SUPPLIER_NAME \n"
				+ " AND  RESULT1.RATE > RESULT2.NET_AMOUNT_ONE_NIGHT";

		List<Object[]> result = null;
		result = baseDao.executeNativeQuery(query);

		System.out.println(result.size());
		return result;
	}

	private String getBodyHotelRateIsAbovePreNegotiatedRates(String month,
			List<Object[]> results) {
		HotelRateIsAbovePreNegotiatedRates hotelRateIsAbovePreNegotiatedRates = new HotelRateIsAbovePreNegotiatedRates();
		String str = "Dear(Travel Team)"
				+ "<br/><br/>"
				+ "Kindly be informed that the below invoices hit the Hotels Rate is Above Pre-Negotiated Rates  for this month ("
				+ month + ") " + "<br/><br/>"
				+ hotelRateIsAbovePreNegotiatedRates.buildHTMLTable(results)
				+ " <br/><br/>" + "Thank You";

		return str;
	}

	@Override
	public void sendNotificationHotelRateAbovePreNegotiatedRates(String month,
			String to, int fileId, String year) {

		List<Object[]> results = getHotelRateAbovePreNegotiatedRates(year,
				fileId);
		if (results.size() > 0) {
			String body = getBodyHotelRateIsAbovePreNegotiatedRates(month,
					results);
			String subject = month
					+ " Hotel Rate is Above Pre-Negotiated Rates ";
			notificationService.sendEMail(body, subject, to);
		}

	}

	private String getBodyHotelRateIsBelowPreNegotiatedRates(String month,
			List<Object[]> results) {
		HotelRateIsAbovePreNegotiatedRates hotelRateIsAbovePreNegotiatedRates = new HotelRateIsAbovePreNegotiatedRates();
		String str = "Dear(Travel Team)"
				+ "<br/><br/>"
				+ "Kindly be informed that the below invoices hit the Hotels Rate is Below Pre-Negotiated Rates  for this month ("
				+ month + ") " + "<br/><br/>"
				+ hotelRateIsAbovePreNegotiatedRates.buildHTMLTable(results)
				+ " <br/><br/>" + "Thank You";

		return str;
	}

	@Override
	public void sendNotificationHotelRateBelowPreNegotiatedRates(String month,
			String to, int fileId, String year) {

		List<Object[]> results = getHotelRateBelowPreNegotiatedRates(year,
				fileId);
		if (results.size() > 0) {
			String body = getBodyHotelRateIsBelowPreNegotiatedRates(month,
					results);
			String subject = month
					+ " Hotel Rate is Below Pre-Negotiated Rates ";
			notificationService.sendEMail(body, subject, to);
		}

	}
}
