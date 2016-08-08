package com.truemega.services.travelmanagementsystem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.ConfigDTO;
import com.truemega.dto.InvoiceAttachmentDTO;
import com.truemega.dto.UploadedInvoiceFileDTO;
import com.truemega.dto.utils.UploadStatus;
import com.truemega.entities.InvoiceAttachment;
import com.truemega.entities.InvoicesTemp;
import com.truemega.entities.UploadedInvoiceFile;
import com.truemega.interfaces.administration.TMSConfigService;
import com.truemega.interfaces.notification.UploadNotificationSender;
import com.truemega.interfaces.travelmanagementsystem.InvoicesUploaderService;
import com.truemega.logger.LoggerService;
import com.truemega.lookups.TravelConfigEnum;
import com.truemega.mapping.MappingFactory;
import com.truemega.notification.interfaces.NotificationService;

@Stateless
public class InvoicesUploaderServiceImpl implements InvoicesUploaderService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	private Workbook wb;

	@EJB
	private NotificationService notificationService;

	@EJB
	private TMSConfigService configService;

	@EJB
	private UploadNotificationSender uploadNotificationSender;

	Calendar cal = Calendar.getInstance();

	@PostConstruct
	public void init() {

	}

	private InvoicesTemp getInvoicesTempRecord(Integer invoiceOrder,
			String transactionId, Row row, Cell cell) {

		InvoicesTemp invoicesTemp = null;
		invoicesTemp = new InvoicesTemp(invoiceOrder, transactionId);

		cell = row.getCell(0);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setInvoiceNumber(cell.toString());
		}

		cell = row.getCell(1);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setBookingFileNumber(cell.getStringCellValue());
		}

		cell = row.getCell(2);
		if (cell != null && cell.getDateCellValue() != null) {//
			invoicesTemp.setDepartureDate(getCellAsDate(cell));
		}

		cell = row.getCell(3);
		if (cell != null && cell.getDateCellValue() != null)
			invoicesTemp.setArrivalDate(getCellAsDate(cell));

		cell = row.getCell(4);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setEmployeeId(cell.getStringCellValue());
		}

		cell = row.getCell(5);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setCostCenter(cell.getStringCellValue());
		}

		cell = row.getCell(6);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setEmployeeDepartment(cell.getStringCellValue());
		}

		cell = row.getCell(7);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setCostCenterDepartment(cell.getStringCellValue());
		}

		cell = row.getCell(8);
		if (cell != null)
			invoicesTemp.setPassengerName(cell.getStringCellValue());

		cell = row.getCell(9);
		if (cell != null)
			invoicesTemp.setServiceType(cell.getStringCellValue());

		cell = row.getCell(10);
		if (cell != null)
			invoicesTemp.setServiceDesc(cell.getStringCellValue());

		cell = row.getCell(11);
		if (cell != null)
			invoicesTemp.setRouting(cell.getStringCellValue());

		cell = row.getCell(12);
		if (cell != null)
			invoicesTemp.setInterDom(cell.getStringCellValue());

		cell = row.getCell(13);
		if (cell != null && cell.getDateCellValue() != null)
			invoicesTemp.setCheckIn(getCellAsDate(cell));

		cell = row.getCell(14);
		if (cell != null && cell.getDateCellValue() != null)
			invoicesTemp.setCheckOut(getCellAsDate(cell));

		cell = row.getCell(15);
		if (cell != null)
			invoicesTemp.setNumberOfNights(getCellAsInteger(cell));

		cell = row.getCell(16);
		if (cell != null)
			invoicesTemp.setNumberOfRooms(getCellAsInteger(cell));

		cell = row.getCell(17);
		if (cell != null)
			invoicesTemp.setAirline(cell.getStringCellValue());

		cell = row.getCell(18);
		if (cell != null)
			invoicesTemp.setRoomType(cell.getStringCellValue());

		cell = row.getCell(19);
		if (cell != null)
			invoicesTemp.setSupplierName(cell.getStringCellValue());

		cell = row.getCell(20);
		if (cell != null)
			invoicesTemp.setNetAmount(cell.getNumericCellValue());

		cell = row.getCell(21);
		if (cell != null)
			invoicesTemp.setOperationFees(cell.getNumericCellValue());

		cell = row.getCell(22);
		if (cell != null)
			invoicesTemp.setTotalAmount(cell.getNumericCellValue());

		cell = row.getCell(23);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setTicketNo(cell.getStringCellValue());
		}

		cell = row.getCell(24);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setTravelFormNumber(cell.getStringCellValue());
		}

		cell = row.getCell(25);
		if (cell != null && cell.getDateCellValue() != null)
			invoicesTemp.setFrom(getCellAsDate(cell));

		cell = row.getCell(26);
		if (cell != null && cell.getDateCellValue() != null)
			invoicesTemp.setTo(getCellAsDate(cell));

		cell = row.getCell(27);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setDestination(cell.getStringCellValue());
		}

		cell = row.getCell(28);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setCity(cell.getStringCellValue());
		}

		cell = row.getCell(29);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setCountry(cell.getStringCellValue());
		}

		cell = row.getCell(30);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setTripPurpose(cell.getStringCellValue());
		}

		cell = row.getCell(31);
		if (cell != null && cell.getDateCellValue() != null) {

			invoicesTemp.setInvoiceDate(getCellAsDate(cell));
		}
		cell = row.getCell(32);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			invoicesTemp.setDescription(cell.getStringCellValue());
		}

		return invoicesTemp;

	}

	private final Workbook getWorkbook(InputStream ins, String fileName)
			throws IOException {

		Workbook wb = null;

		if (fileName.endsWith(".xls"))
			wb = new HSSFWorkbook(ins);
		else {
			wb = new XSSFWorkbook(ins);
		}

		return wb;
	}

	public Date getCellAsDate(Cell cell) {
		cal.setTime(cell.getDateCellValue());
		cal.set(Calendar.HOUR_OF_DAY, 8);
		return cal.getTime();
	}

	public Integer getCellAsInteger(Cell cell) {

		return ((int) cell.getNumericCellValue());
	}

	int count = 0;

	@Override
	public UploadedInvoiceFileDTO uploadInvoicesExcelSheet(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName,
			String year) throws Exception {
		UploadedInvoiceFileDTO uploadedInvoiceFileDTOResult = null;
		count = 0;
		cal = Calendar.getInstance();
		wb = null;
		loggerService
				.logServiceInfo("Start uploadInvoicesExcelSheet >> userName "
						+ userName);

		UploadStatus uploadStatus = getUploadStatus(uploadedInvoiceFileDTO
				.getInvoicesMonth());

		try {
			// TODO Auto-generated method stub

			if (!uploadStatus.isExistRecord()) {
				uploadedInvoiceFileDTOResult = addNewInvoicesExcelSheet(
						uploadedInvoiceFileDTO, userName, year);
			} else {
				if (uploadStatus.isTempData()) {
					loggerService.logServiceInfo("upload  and update master "
							+ uploadStatus.getFileUploadId());
					uploadedInvoiceFileDTO
							.setId(uploadStatus.getFileUploadId());
					uploadedInvoiceFileDTOResult = updateInvoicesExcelSheet(
							uploadedInvoiceFileDTO, userName, year);

				} else {
					if (uploadStatus.isApproved()) {
						loggerService.logServiceInfo("cannot upload");
						uploadedInvoiceFileDTOResult = new UploadedInvoiceFileDTO();
						uploadedInvoiceFileDTOResult
								.setOperationMsg("cannot upload there is an approved request");
					}

					else if (uploadStatus.isNoActionTaken()) {
						loggerService
								.logServiceInfo("Wait until travel team take an action");
						uploadedInvoiceFileDTOResult = new UploadedInvoiceFileDTO();
						uploadedInvoiceFileDTOResult
								.setOperationMsg("Wait until travel team take an action");
					}

					else if (uploadStatus.isRejected()) {
						loggerService
								.logServiceInfo("Upload  and add master as new");

						uploadedInvoiceFileDTOResult = addNewInvoicesExcelSheet(
								uploadedInvoiceFileDTO, userName, year);
					}

					else {
						uploadedInvoiceFileDTOResult = new UploadedInvoiceFileDTO();
						uploadedInvoiceFileDTOResult
								.setOperationMsg("Uncaught Case");
						loggerService.logServiceInfo("Uncaught Case");
					}

				}
			}

			loggerService
					.logServiceInfo("End uploadInvoicesExcelSheet successfully");
			return uploadedInvoiceFileDTOResult;
		} catch (Exception e) {

			loggerService
					.logServiceInfo("End uploadInvoicesExcelSheet With Exception at row # "
							+ (count + 1));
			loggerService.logServiceError(
					"End uploadInvoicesExcelSheet With Exception at row # "
							+ (count + 1), e);
			// throw new Exception(e);
			// baseDao.findListByQuery("select f from noExistEntityTothrowException f");
			throw new EJBTransactionRolledbackException(e.getMessage());

		}
	}

	private String getMergeStatement(int uploadedInvoiceFileId, String year) {
		String query = " Merge into INVOICES_TEMP t1 using ( SELECT \n"
				+ " INVOICE_ORDER, \n"
				+ " TRANSACTION_ID, \n"
				+ " (CASE WHEN  INVOICE_NUMBER is null OR BOOKING_FILE_NUMBER is null OR EMPLOYEE_ID is null OR COST_CENTER is null \n"
				+ " OR EMPLOYEE_DEPARTMENT is null OR COST_CENTER_DEPARTMENT is null OR PASSENGER_NAME is null OR SERVICE_TYPE is null \n"
				+ " OR SERVICE_DESC is null OR SUPPLIER_NAME is null OR NET_AMOUNT is null OR OPERATION_FEES is null OR TOTAL_AMOUNT is null OR \n"
				+ " TICKET_NO is null OR TRAVEL_FORM_NUMBER is null OR INVOICE_DATE is null OR TRIP_PURPOSE is null   \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) GENERAL_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  add_months(TO_DATE('26/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy'), -1) > TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " OR TO_DATE('25/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy') < TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) INVOICE_DATE_RANGE_VALID_N, \n"
				+ " (CASE WHEN  LOWER(SERVICE_TYPE) LIKE 'air' AND  (DEPARTURE_DATE is null OR ARRIVAL_DATE is null OR ROUTING is null OR INTER_DOM is null OR AIRLINE is null OR DESTINATION is null OR  CITY is null OR  COUNTRY is null ) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) AIR_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  LOWER(SERVICE_TYPE) LIKE 'hotel' AND  (CHECK_IN is null OR CHECK_OUT is null OR NUMBER_OF_NIGHTS is null OR NUMBER_OF_ROOMS is null OR ROOM_TYPE is null OR DESTINATION is null OR  CITY is null OR  COUNTRY is null ) \n"
				+ " THEN 0  \n"
				+ " ELSE 1 END) HOTEL_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  NOT (LOWER(SERVICE_TYPE) LIKE 'air' OR LOWER(SERVICE_TYPE) LIKE 'hotel') AND NOT ( (LOWER(SERVICE_TYPE) LIKE 'car') OR ( LOWER(SERVICE_TYPE) LIKE 'cwt fee' AND (UPPER(SERVICE_DESC) LIKE 'MEET AND GREET' OR (UPPER(SERVICE_DESC) LIKE 'MEET AND GREET EMERGENCY') ) ) ) AND (FROM_DATE IS NULL OR TO_DATE IS NULL)  \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) OTHER_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  NOT (UPPER(INTER_DOM)  LIKE 'INTERNATIONAL' OR UPPER(INTER_DOM)  LIKE 'DOMESTIC') \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) INTER_DOM_VALID_N, \n"
				+ " (CASE WHEN   (CHECK_OUT-CHECK_IN)+1 !=  NUMBER_OF_NIGHTS \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) NUMBER_OF_NIGHTS_VALID_N, \n"
				+ " (CASE WHEN   NET_AMOUNT+OPERATION_FEES !=  TOTAL_AMOUNT \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) TOTAL_AMOUNT_VALID_N, \n"
				+ " ( CASE WHEN LOWER(SERVICE_TYPE) not in (select LOWER(name) from service_type) \n"
				+ "  THEN 0 \n"
				+ "  ELSE 1 \n"
				+ "  END)  SERVICE_TYPE_VALID_N1, \n"
				+ " ( CASE WHEN (LOWER(SERVICE_DESC),LOWER(SERVICE_TYPE)) not in ((SELECT LOWER(PRODUCT_TYPE.NAME),LOWER(st1.NAME) FROM PRODUCT_TYPE INNER JOIN SERVICE_TYPE st1 ON st1.ID = PRODUCT_TYPE.SERVICE_ID)) \n"
				+ "   THEN 0 \n"
				+ "  ELSE 1 \n"
				+ "  END) SERVICE_DESC_VALID_N1, \n"
				+ " ( CASE WHEN  LOWER(INVOICES_TEMP.SERVICE_TYPE) not like 'hotel' and  (LOWER(SUPPLIER_NAME),LOWER(SERVICE_DESC),LOWER(SERVICE_TYPE)) not in ((SELECT LOWER(s.NAME),LOWER(pt2.NAME),LOWER(st2.NAME) FROM PRODUCT_TYPE pt2 INNER JOIN SERVICE_TYPE st2 ON st2.ID = pt2.SERVICE_ID INNER JOIN SUPPLIER_PRODUCT sp ON pt2.ID = sp.PRODUCT_ID INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID)) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 \n"
				+ " END) SUPPLIER_NAME_VALID_N1, \n"
				+ " ( CASE  WHEN LOWER(SERVICE_TYPE) LIKE 'air' AND  (LOWER(AIRLINE) not in (select LOWER(name) from AIRLINE)) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 \n"
				+ " END) AIRLINE_VALID_N1, \n"
				+ "  ( CASE \n"
				+ "  WHEN INVOICE_NUMBER in (select DISTINCT INVOICE_NUMBER from INVOICES INNER JOIN UPLOADED_INVOICE_FILE ON UPLOADED_INVOICE_FILE.ID=INVOICES.UPLOADED_INVOICE_FILE_ID WHERE UPLOADED_INVOICE_FILE.APPROVED=1) \n"
				+ "  THEN 0 \n"
				+ "  ELSE 1 \n"
				+ "  END) INVOICE_NUMBER_VALID_N1, \n"
				+ " ( CASE  WHEN LOWER(SERVICE_TYPE) LIKE 'hotel' AND  (LOWER(ROOM_TYPE) not in (select LOWER(name) from ROOM_TYPE)) \n"
				+ " THEN 0 \n"
				+ "   ELSE 1 \n"
				+ " END) ROOM_TYPE_VALID_N1, \n"

				+ " (CASE WHEN  ( \n"
				+ " LOWER(SERVICE_TYPE) like 'hotel' \n"
				+ " AND \n"
				+ " (LOWER(SERVICE_TYPE),LOWER(SERVICE_DESC),LOWER(SUPPLIER_NAME),LOWER(ROOM_TYPE)) not in (SELECT lower(st.NAME),lower(pt.NAME),lower(s.NAME),lower(rt.NAME) FROM SERVICE_TYPE st INNER JOIN PRODUCT_TYPE pt ON st.ID = pt.SERVICE_ID AND LOWER(st.NAME) LIKE 'hotel' INNER JOIN SUPPLIER_PRODUCT sp ON pt.ID = sp.PRODUCT_ID INNER JOIN RATES r ON sp.ID   = r.SUPPLIER_PRODUCT_ID AND r.YEAR = "
				+ year
				+ " INNER JOIN ROOM_TYPE rt ON rt.ID = r.ROOM_TYPE_ID INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID ) \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " LOWER(SERVICE_TYPE) like 'air' \n"
				+ " AND \n"
				+ " (LOWER(SERVICE_TYPE),LOWER(SERVICE_DESC),LOWER(SUPPLIER_NAME),LOWER(AIRLINE),LOWER(ROUTING)) not in (SELECT  lower(st.NAME), lower(pt.NAME), lower(s.NAME), lower(al.NAME), lower(r.ROUTING) FROM SERVICE_TYPE st INNER JOIN PRODUCT_TYPE pt ON st.ID = pt.SERVICE_ID AND LOWER(st.NAME) LIKE 'air' INNER JOIN SUPPLIER_PRODUCT sp ON pt.ID = sp.PRODUCT_ID INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID INNER JOIN RATES r ON sp.ID = r.SUPPLIER_PRODUCT_ID AND r.YEAR = "
				+ year
				+ " INNER JOIN AIRLINE al ON al.ID = r.AIRLINE_ID) \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " (LOWER(SERVICE_TYPE) not like 'air' and  LOWER(SERVICE_TYPE) not like 'hotel') \n"
				+ " AND \n"
				+ " (LOWER(SERVICE_TYPE),LOWER(SERVICE_DESC),LOWER(SUPPLIER_NAME)) not in (SELECT lower(st.NAME), lower(pt.NAME), lower(s.NAME) FROM SERVICE_TYPE st INNER JOIN PRODUCT_TYPE pt ON st.ID = pt.SERVICE_ID AND LOWER(st.NAME) not LIKE 'hotel' AND LOWER(st.NAME) not LIKE 'air' INNER JOIN SUPPLIER_PRODUCT sp ON pt.ID = sp.PRODUCT_ID INNER JOIN RATES r ON sp.ID   = r.SUPPLIER_PRODUCT_ID AND r.YEAR = "
				+ year
				+ " INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID) \n"
				+ " ) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) RATES_COMBINATION_VALID_N \n"

				+ " FROM UPLOADED_INVOICE_FILE INNER JOIN INVOICES_TEMP \n"
				+ " ON UPLOADED_INVOICE_FILE.ID = "
				+ uploadedInvoiceFileId
				+ " and UPLOADED_INVOICE_FILE.ID = INVOICES_TEMP.UPLOADED_INVOICE_FILE_ID \n"
				+ " WHERE \n"
				+ " (  \n"
				+ " add_months(TO_DATE('26/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy'), -1) > TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " OR \n"
				+ " TO_DATE('25/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy') < TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " )  \n"
				+ " OR \n"
				+ " ( \n"
				+ " INVOICE_NUMBER is null OR BOOKING_FILE_NUMBER is null OR EMPLOYEE_ID is null OR COST_CENTER is null \n"
				+ " OR EMPLOYEE_DEPARTMENT is null OR COST_CENTER_DEPARTMENT is null OR PASSENGER_NAME is null OR SERVICE_TYPE is null \n"
				+ " OR SERVICE_DESC is null OR SUPPLIER_NAME is null OR NET_AMOUNT is null OR OPERATION_FEES is null OR TOTAL_AMOUNT is null OR \n"
				+ " TICKET_NO is null OR TRAVEL_FORM_NUMBER is null OR INVOICE_DATE is null OR TRIP_PURPOSE is null  \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " LOWER(SERVICE_TYPE) LIKE 'air' AND  (DEPARTURE_DATE is null OR ARRIVAL_DATE is null OR ROUTING is null OR INTER_DOM is null OR AIRLINE is null OR DESTINATION is null OR  CITY is null OR  COUNTRY is null ) \n"
				+ " ) \n"
				+ " OR  \n"
				+ " (  \n"
				+ " LOWER(SERVICE_TYPE) LIKE 'hotel' AND  (CHECK_IN is null OR CHECK_OUT is null OR NUMBER_OF_NIGHTS is null OR NUMBER_OF_ROOMS is null OR ROOM_TYPE is null OR DESTINATION is null OR  CITY is null OR  COUNTRY is null ) \n"
				+ " ) \n"
				+ " OR \n"
				+ " (   NOT (LOWER(SERVICE_TYPE) LIKE 'air' OR LOWER(SERVICE_TYPE) LIKE 'hotel') AND NOT ( (LOWER(SERVICE_TYPE) LIKE 'car') OR ( LOWER(SERVICE_TYPE) LIKE 'cwt fee' AND (UPPER(SERVICE_DESC) LIKE 'MEET AND GREET' OR (UPPER(SERVICE_DESC) LIKE 'MEET AND GREET EMERGENCY') ) ) ) AND (FROM_DATE IS NULL OR TO_DATE IS NULL)  \n"
				+ " ) \n"
				+ " OR \n"
				+ " (  \n"
				+ " NOT (UPPER(INTER_DOM)  LIKE 'INTERNATIONAL' OR UPPER(INTER_DOM)  LIKE 'DOMESTIC') \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " (CHECK_OUT-CHECK_IN)+1 !=  NUMBER_OF_NIGHTS \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " NET_AMOUNT+OPERATION_FEES !=  TOTAL_AMOUNT \n"
				+ " ) \n"
				+ " OR \n"
				+ " LOWER(INVOICES_TEMP.SERVICE_TYPE) not in (select LOWER(name) from service_type) \n"
				+ "  OR \n"
				+ "  (LOWER(INVOICES_TEMP.SERVICE_DESC),LOWER(INVOICES_TEMP.SERVICE_TYPE)) not in ((SELECT LOWER(PRODUCT_TYPE.NAME),LOWER(st1.name) FROM  PRODUCT_TYPE INNER JOIN SERVICE_TYPE st1 ON st1.ID = PRODUCT_TYPE.SERVICE_ID )) \n"
				+ "  OR \n"
				+ "   LOWER(INVOICES_TEMP.SERVICE_TYPE) LIKE 'air' AND  (LOWER(INVOICES_TEMP.AIRLINE) not in (select LOWER(name) from AIRLINE)) \n"
				+ "  OR \n"
				+ "  LOWER(INVOICES_TEMP.SERVICE_TYPE) LIKE 'hotel' AND  (LOWER(INVOICES_TEMP.ROOM_TYPE) not in (select LOWER(name) from ROOM_TYPE)) \n"
				+ "  OR \n"
				+ " INVOICES_TEMP.INVOICE_NUMBER in (select DISTINCT INVOICE_NUMBER from INVOICES INNER JOIN UPLOADED_INVOICE_FILE ON UPLOADED_INVOICE_FILE.ID=INVOICES.UPLOADED_INVOICE_FILE_ID WHERE UPLOADED_INVOICE_FILE.APPROVED=1) \n"
				+ "  OR \n"
				+ " ( \n"
				+ " LOWER(INVOICES_TEMP.SERVICE_TYPE) not like 'hotel' and \n"
				+ " (LOWER(INVOICES_TEMP.SUPPLIER_NAME),LOWER(INVOICES_TEMP.SERVICE_DESC),LOWER(INVOICES_TEMP.SERVICE_TYPE)) not in ((SELECT LOWER(s.NAME),LOWER(pt2.NAME),LOWER(st2.NAME) FROM PRODUCT_TYPE pt2 INNER JOIN SERVICE_TYPE st2 ON st2.ID = pt2.SERVICE_ID INNER JOIN SUPPLIER_PRODUCT sp ON pt2.ID = sp.PRODUCT_ID INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID)) \n"
				+ " ) \n"

				+ " OR \n"
				+ " ( \n"
				+ "  ( \n"
				+ "      LOWER(INVOICES_TEMP.SERVICE_TYPE) like 'hotel' \n"
				+ "      AND \n"
				+ "      (LOWER(INVOICES_TEMP.SERVICE_TYPE),LOWER(INVOICES_TEMP.SERVICE_DESC),LOWER(INVOICES_TEMP.SUPPLIER_NAME),LOWER(INVOICES_TEMP.ROOM_TYPE)) not in (SELECT lower(st.NAME),lower(pt.NAME),lower(s.NAME),lower(rt.NAME) FROM SERVICE_TYPE st INNER JOIN PRODUCT_TYPE pt ON st.ID = pt.SERVICE_ID AND LOWER(st.NAME) LIKE 'hotel' INNER JOIN SUPPLIER_PRODUCT sp ON pt.ID = sp.PRODUCT_ID INNER JOIN RATES r ON sp.ID   = r.SUPPLIER_PRODUCT_ID AND r.YEAR = "
				+ year
				+ " INNER JOIN ROOM_TYPE rt ON rt.ID = r.ROOM_TYPE_ID INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID ) \n"
				+ "  ) \n"
				+ "  OR \n"
				+ "  ( \n"
				+ "      LOWER(INVOICES_TEMP.SERVICE_TYPE) like 'air' \n"
				+ "      AND \n"
				+ "      (LOWER(INVOICES_TEMP.SERVICE_TYPE),LOWER(INVOICES_TEMP.SERVICE_DESC),LOWER(INVOICES_TEMP.SUPPLIER_NAME),LOWER(INVOICES_TEMP.AIRLINE),LOWER(INVOICES_TEMP.ROUTING)) not in (SELECT  lower(st.NAME), lower(pt.NAME), lower(s.NAME), lower(al.NAME), lower(r.ROUTING) FROM SERVICE_TYPE st INNER JOIN PRODUCT_TYPE pt ON st.ID = pt.SERVICE_ID AND LOWER(st.NAME) LIKE 'air' INNER JOIN SUPPLIER_PRODUCT sp ON pt.ID = sp.PRODUCT_ID INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID INNER JOIN RATES r ON sp.ID = r.SUPPLIER_PRODUCT_ID AND r.YEAR = "
				+ year
				+ " INNER JOIN AIRLINE al ON al.ID = r.AIRLINE_ID) \n"
				+ "  ) \n"
				+ " OR \n"
				+ "  ( \n"
				+ "      (LOWER(INVOICES_TEMP.SERVICE_TYPE) not like 'air' and  LOWER(INVOICES_TEMP.SERVICE_TYPE) not like 'hotel') \n"
				+ "      AND \n"
				+ "      (LOWER(INVOICES_TEMP.SERVICE_TYPE),LOWER(INVOICES_TEMP.SERVICE_DESC),LOWER(INVOICES_TEMP.SUPPLIER_NAME)) not in (SELECT lower(st.NAME), lower(pt.NAME), lower(s.NAME) FROM SERVICE_TYPE st INNER JOIN PRODUCT_TYPE pt ON st.ID = pt.SERVICE_ID AND LOWER(st.NAME) not LIKE 'hotel' AND LOWER(st.NAME) not LIKE 'air' INNER JOIN SUPPLIER_PRODUCT sp ON pt.ID = sp.PRODUCT_ID INNER JOIN RATES r ON sp.ID   = r.SUPPLIER_PRODUCT_ID AND r.YEAR = "
				+ year
				+ " INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID) \n"
				+ "  ) \n"
				+ " ) \n"
				+ " ) t2 \n"
				+ " on( t1.INVOICE_ORDER   = t2.INVOICE_ORDER AND t1.TRANSACTION_ID = t2.TRANSACTION_ID ) \n"
				+ " when matched then \n"
				+ " update set  \n"
				+ " t1.GENERAL_MANDATORY_VALID=t2.GENERAL_MANDATORY_VALID_N, \n"
				+ " t1.INVOICE_DATE_RANGE_VALID=t2.INVOICE_DATE_RANGE_VALID_N, \n"
				+ " t1.AIR_MANDATORY_VALID=t2.AIR_MANDATORY_VALID_N, \n"
				+ " t1.OTHER_MANDATORY_VALID=t2.OTHER_MANDATORY_VALID_N, \n"
				+ " t1.NUMBER_OF_NIGHTS_VALID=t2.NUMBER_OF_NIGHTS_VALID_N, \n"
				+ " t1.TOTAL_AMOUNT_VALID=t2.TOTAL_AMOUNT_VALID_N, \n"
				+ " t1.INTER_DOM_VALID=t2.INTER_DOM_VALID_N, \n"
				+ " t1.HOTEL_MANDATORY_VALID=t2.HOTEL_MANDATORY_VALID_N, "
				+ " t1.SERVICE_TYPE_VALID=t2.SERVICE_TYPE_VALID_N1, \n"
				+ " t1.SERVICE_DESC_VALID=t2.SERVICE_DESC_VALID_N1, \n"
				+ " t1.SUPPLIER_NAME_VALID=t2.SUPPLIER_NAME_VALID_N1, \n"
				+ " t1.AIRLINE_VALID=t2.AIRLINE_VALID_N1, \n"
				+ " t1.ROOM_TYPE_VALID=t2.ROOM_TYPE_VALID_N1, \n"
				+ " t1.INVOICE_NUMBER_VALID=t2.INVOICE_NUMBER_VALID_N1, \n"
				+ " t1.RATES_COMBINATION_VALID=t2.RATES_COMBINATION_VALID_N \n";
		return query;
	}

	public UploadedInvoiceFileDTO saveUploadedInvoiceFile(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO) {

		loggerService.logServiceInfo("Start saveUploadedInvoiceFile ");
		UploadedInvoiceFileDTO uploadedInvoiceFileDTOResult = mapper.map(
				baseDao.saveEntity(mapper.map(uploadedInvoiceFileDTO,
						UploadedInvoiceFile.class)),
				UploadedInvoiceFileDTO.class);

		baseDao.flush();

		uploadedInvoiceFileDTOResult = saveUploadedInvoiceFileAttachments(
				uploadedInvoiceFileDTO, uploadedInvoiceFileDTOResult);

		return uploadedInvoiceFileDTOResult;
	}

	public UploadedInvoiceFileDTO saveUploadedInvoiceFileAttachments(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO,
			UploadedInvoiceFileDTO uploadedInvoiceFileDTOResult) {

		List<InvoiceAttachmentDTO> list = uploadedInvoiceFileDTO
				.getInvoiceAttachmentDTOs();

		InvoiceAttachmentDTO invoiceAttachmentDTO = null;
		InvoiceAttachmentDTO invoiceAttachmentDTOResult = null;
		for (int i = 0; i < list.size(); i++) {
			invoiceAttachmentDTO = list.get(i);
			invoiceAttachmentDTO
					.setUploadedInvoiceFileId(uploadedInvoiceFileDTOResult);

			invoiceAttachmentDTOResult = mapper.map(baseDao.saveEntity(mapper
					.map(invoiceAttachmentDTO, InvoiceAttachment.class)),
					InvoiceAttachmentDTO.class);

			uploadedInvoiceFileDTOResult.getInvoiceAttachmentDTOs().add(
					invoiceAttachmentDTOResult);

		}
		return uploadedInvoiceFileDTOResult;
	}

	public UploadedInvoiceFileDTO updateUploadedInvoiceFileAttachments(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO,
			UploadedInvoiceFileDTO uploadedInvoiceFileDTOResult) {

		String q = "delete from INVOICE_ATTACHMENT where UPLOADED_INVOICE_FILE_ID="
				+ uploadedInvoiceFileDTOResult.getId();

		baseDao.executeUpdateNativeQuery(q);
		List<InvoiceAttachmentDTO> list = uploadedInvoiceFileDTO
				.getInvoiceAttachmentDTOs();

		InvoiceAttachmentDTO invoiceAttachmentDTO = null;
		InvoiceAttachmentDTO invoiceAttachmentDTOResult = null;
		for (int i = 0; i < list.size(); i++) {
			invoiceAttachmentDTO = list.get(i);
			invoiceAttachmentDTO
					.setUploadedInvoiceFileId(uploadedInvoiceFileDTOResult);

			invoiceAttachmentDTOResult = mapper.map(baseDao.saveEntity(mapper
					.map(invoiceAttachmentDTO, InvoiceAttachment.class)),
					InvoiceAttachmentDTO.class);

			uploadedInvoiceFileDTOResult.getInvoiceAttachmentDTOs().add(
					invoiceAttachmentDTOResult);

		}
		return uploadedInvoiceFileDTOResult;
	}

	public UploadStatus getUploadStatus(String invoiceMonth) {

		boolean noActionTaken = false;
		boolean approved = false;
		boolean rejected = false;
		boolean tempData = false;
		int fileUploadId = -1;

		UploadStatus uploadStatus = new UploadStatus();
		String q = " select approved,TEMPLATE_TABLE,id from UPLOADED_INVOICE_FILE where INVOICES_MONTH='"
				+ invoiceMonth + "'  order  by TEMPLATE_TABLE desc   ";

		List<Object[]> result = null;
		result = baseDao.executeNativeQuery(q);
		if (result.size() == 0)
			uploadStatus.setExistRecord(false);
		else {

			for (Object[] objects : result) {

				if (objects[1].toString().equalsIgnoreCase("1")) {
					{
						tempData = true;
						fileUploadId = new Integer(objects[2].toString());
						break;
					}
				} else {
					tempData = false;

					if (objects[0] == null) {
						noActionTaken = true;
						break;

					} else {
						if (objects[0].toString().equalsIgnoreCase("1")) {
							approved = true;
							noActionTaken = false;
							rejected = false;
							break;
						} else {
							noActionTaken = false;
							approved = false;
							rejected = true;
						}
					}

				}

			}

			if (tempData)
				loggerService.logServiceInfo("upload  and update master "
						+ fileUploadId);
			else {
				if (approved)
					loggerService.logServiceInfo("cannot upload");

				else if (noActionTaken)
					loggerService
							.logServiceInfo("Wait until user take an action");

				else if (rejected)
					loggerService
							.logServiceInfo("Upload  and add master as new");

				else
					loggerService.logServiceInfo("Uncaught Case");

			}
		}

		uploadStatus.setApproved(approved);
		uploadStatus.setFileUploadId(fileUploadId);
		uploadStatus.setNoActionTaken(noActionTaken);
		uploadStatus.setRejected(rejected);
		uploadStatus.setTempData(tempData);

		return uploadStatus;
	}

	public void saveInvoicesTempRecords(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO,
			Integer uploadedInvoiceFileDTOResultId) throws IOException {

		loggerService.logServiceInfo("Start saveInvoicesTempRecords ");

		UUID uid = UUID.randomUUID();
		String transactionId = uid.toString();
		InvoicesTemp invoicesTemp = null;
		InputStream ins = null;
		wb = null;

		ins = new ByteArrayInputStream(uploadedInvoiceFileDTO.getContent());
		wb = getWorkbook(ins, uploadedInvoiceFileDTO.getName());
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();

		Row row = null;
		Cell cell = null;
		loggerService.logServiceInfo("count = " + count);
		while (rowIterator.hasNext()) {
			if (count == 1)
				break;
			count++;
			rowIterator.next();
		}
		count--;

		while (rowIterator.hasNext()) {

			row = rowIterator.next();
			count++;
			invoicesTemp = getInvoicesTempRecord(count, transactionId, row,
					cell);
			invoicesTemp.setUploadedInvoiceFileId(new UploadedInvoiceFile(
					uploadedInvoiceFileDTOResultId));
			baseDao.saveEntity(invoicesTemp);
		}

	}

	public UploadedInvoiceFileDTO addNewInvoicesExcelSheet(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName,
			String year) throws IOException {

		loggerService
				.logServiceInfo("Start addNewInvoicesExcelSheet >> userName "
						+ userName);

		UploadedInvoiceFileDTO uploadedInvoiceFileDTOResult = saveUploadedInvoiceFile(uploadedInvoiceFileDTO);
		saveInvoicesTempRecords(uploadedInvoiceFileDTO,
				uploadedInvoiceFileDTOResult.getId());
		// baseDao.flush();
		baseDao.executeUpdateNativeQuery(getMergeStatement(
				uploadedInvoiceFileDTOResult.getId(), year));
		List<Object[]> result = null;
		result = baseDao
				.executeNativeQuery(getValidationQuery(uploadedInvoiceFileDTOResult
						.getId()));

		Object[] objects = result.get(0);
		if (objects[0].toString().equalsIgnoreCase(objects[1].toString())) {
			baseDao.flush();
			baseDao.executeUpdateNativeQuery(getInsertIntoActualTableQuery(uploadedInvoiceFileDTOResult
					.getId()));
			

			baseDao.executeUpdateNativeQuery(getDeleteFromTempTableQuery(uploadedInvoiceFileDTOResult
					.getId()));
			baseDao.executeUpdateNativeQuery(getUpdateDataAsActualQuery(uploadedInvoiceFileDTOResult
					.getId()));

			sendNotifications(uploadedInvoiceFileDTOResult.getInvoicesMonth(),
					uploadedInvoiceFileDTOResult.getId(), year);
			loggerService.logServiceInfo("persist int the actual");
		}

		else {
			loggerService.logServiceInfo("not persist int the actual");
		}
		return uploadedInvoiceFileDTOResult;

	}

	public UploadedInvoiceFileDTO updateInvoicesExcelSheet(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName,
			String year) throws IOException {

		// update master
		UploadedInvoiceFileDTO uploadedInvoiceFileDTOResult = mapper.map(
				baseDao.updateEntity(mapper.map(uploadedInvoiceFileDTO,
						UploadedInvoiceFile.class)),
				UploadedInvoiceFileDTO.class);

		// update Attachments
		uploadedInvoiceFileDTOResult = updateUploadedInvoiceFileAttachments(
				uploadedInvoiceFileDTO, uploadedInvoiceFileDTOResult);

		// delete temp table
		String q = "delete from( "
				+ " SELECT INVOICES_TEMP.* "
				+ " FROM UPLOADED_INVOICE_FILE "
				+ " INNER JOIN INVOICES_TEMP "
				+ " ON UPLOADED_INVOICE_FILE.ID = INVOICES_TEMP.UPLOADED_INVOICE_FILE_ID where UPLOADED_INVOICE_FILE.ID= "
				+ uploadedInvoiceFileDTOResult.getId() + ")";
		baseDao.executeUpdateNativeQuery(q);

		baseDao.flush();

		// add to temp
		saveInvoicesTempRecords(uploadedInvoiceFileDTO,
				uploadedInvoiceFileDTOResult.getId());
		// baseDao.flush();
		baseDao.executeUpdateNativeQuery(getMergeStatement(
				uploadedInvoiceFileDTOResult.getId(), year));

		List<Object[]> result = null;
		result = baseDao
				.executeNativeQuery(getValidationQuery(uploadedInvoiceFileDTOResult
						.getId()));

		Object[] objects = result.get(0);
		if (objects[0].toString().equalsIgnoreCase(objects[1].toString())) {
			baseDao.executeUpdateNativeQuery(getInsertIntoActualTableQuery(uploadedInvoiceFileDTOResult
					.getId()));

			baseDao.executeUpdateNativeQuery(getDeleteFromTempTableQuery(uploadedInvoiceFileDTOResult
					.getId()));
			baseDao.executeUpdateNativeQuery(getUpdateDataAsActualQuery(uploadedInvoiceFileDTOResult
					.getId()));

			sendNotifications(uploadedInvoiceFileDTOResult.getInvoicesMonth(),
					uploadedInvoiceFileDTOResult.getId(), year);

			loggerService.logServiceInfo("persist int the actual");
		}

		else {
			loggerService.logServiceInfo("not persist int the actual");
		}
		return uploadedInvoiceFileDTOResult;
	}

	public String getValidationQuery(int fileID) {
		String q = " SELECT * from  ( "
				+ " SELECT max(INVOICES_TEMP.INVOICE_ORDER) as Number_inserted_records "
				+ " FROM  INVOICES_TEMP where UPLOADED_INVOICE_FILE_ID="
				+ fileID
				+ " "
				+ " )  , "
				+ " ( "
				+ " SELECT count(*)  as Number_Valid_records "
				+ " FROM  INVOICES_TEMP where UPLOADED_INVOICE_FILE_ID= "
				+ fileID
				+ " and "
				+ " GENERAL_MANDATORY_VALID =1  and INVOICE_DATE_RANGE_VALID =1  and "
				+ " AIR_MANDATORY_VALID =1  and OTHER_MANDATORY_VALID =1  and "
				+ " INTER_DOM_VALID =1  and NUMBER_OF_NIGHTS_VALID =1  and  "
				+ " TOTAL_AMOUNT_VALID =1  and HOTEL_MANDATORY_VALID =1  and "
				+ " SERVICE_TYPE_VALID =1  and SERVICE_DESC_VALID =1  and  "
				+ " SUPPLIER_NAME_VALID =1  and AIRLINE_VALID =1  and ROOM_TYPE_VALID =1  and "
				+ " INVOICE_NUMBER_VALID =1   and RATES_COMBINATION_VALID =1     ) ";

		return q;
	}

	public String getInsertIntoActualTableQuery(int fileID) {
		String query = "insert into INVOICES \n"
				+ " (ID,INVOICE_ORDER,TRANSACTION_ID,INVOICE_NUMBER,BOOKING_FILE_NUMBER,DEPARTURE_DATE,ARRIVAL_DATE, \n"
				+ " EMPLOYEE_ID,COST_CENTER,COST_CENTER_DEPARTMENT,PASSENGER_NAME,SERVICE_TYPE,SERVICE_DESC,ROUTING, \n"
				+ " INTER_DOM,CHECK_IN,CHECK_OUT,NUMBER_OF_NIGHTS,NUMBER_OF_ROOMS,AIRLINE,ROOM_TYPE,SUPPLIER_NAME, \n"
				+ " NET_AMOUNT,OPERATION_FEES,TOTAL_AMOUNT,TICKET_NO,TRAVEL_FORM_NUMBER,DESCRIPTION,FROM_DATE,TO_DATE, \n"
				+ " UPLOADED_INVOICE_FILE_ID,EMPLOYEE_DEPARTMENT,INVOICE_DATE,DESTINATION, CITY, COUNTRY, TRIP_PURPOSE ) \n"
				+ " SELECT SEQ_INVOICES.nextval,INVOICE_ORDER,TRANSACTION_ID,INVOICE_NUMBER,BOOKING_FILE_NUMBER,DEPARTURE_DATE, \n"
				+ " ARRIVAL_DATE,EMPLOYEE_ID,COST_CENTER,COST_CENTER_DEPARTMENT,PASSENGER_NAME,SERVICE_TYPE,SERVICE_DESC, \n"
				+ " ROUTING,INTER_DOM,CHECK_IN,CHECK_OUT,NUMBER_OF_NIGHTS,NUMBER_OF_ROOMS,AIRLINE,ROOM_TYPE,SUPPLIER_NAME, \n"
				+ " NET_AMOUNT,OPERATION_FEES,TOTAL_AMOUNT,TICKET_NO,TRAVEL_FORM_NUMBER,DESCRIPTION,FROM_DATE,TO_DATE, \n"
				+ " UPLOADED_INVOICE_FILE_ID,EMPLOYEE_DEPARTMENT,INVOICE_DATE,DESTINATION, CITY, COUNTRY, TRIP_PURPOSE \n"
				+ " from INVOICES_TEMP where UPLOADED_INVOICE_FILE_ID="
				+ fileID + " \n";
		return query;
	}

	public String getDeleteFromTempTableQuery(int fileID) {
		String query = "delete from INVOICES_TEMP where UPLOADED_INVOICE_FILE_ID="
				+ fileID;
		return query;
	}

	public String getUpdateDataAsActualQuery(int fileID) {
		String query = "update UPLOADED_INVOICE_FILE set TEMPLATE_TABLE=0 where ID="
				+ fileID;
		return query;
	}

	private void sendNotifications(String month, int fileID, String year) {
		ConfigDTO configDTO = null;

		configDTO = configService.findConfigurationByName(
				TravelConfigEnum.TravelTeam.toString(), "");
		String to = configDTO.getConfigValue();

		configDTO = configService.findConfigurationByName(
				TravelConfigEnum.AgentTeam.toString(), "");
		String toAgentTeam = configDTO.getConfigValue();
		// //
		uploadNotificationSender.sendNotificationInvoiceUploaded(month, to);
		uploadNotificationSender
				.sendNotificationNotificationReservationUnknownHotel(month, to,
						fileID);
		uploadNotificationSender.sendNotificationMaxInsurancePeriod(month, to,
				fileID);

		uploadNotificationSender.sendNotificationMaxVisaPeriod(month, to,
				fileID);

		uploadNotificationSender.sendNotificationMaxAirLineTicket(month, to,
				fileID);

		uploadNotificationSender.sendNotificationMinAirLineTicket(month, to,
				fileID);

		uploadNotificationSender
				.sendNotificationHotelRateAbovePreNegotiatedRates(month, to,
						fileID, year);

		uploadNotificationSender
				.sendNotificationHotelRateBelowPreNegotiatedRates(month, to,
						fileID, year);

	}
}
