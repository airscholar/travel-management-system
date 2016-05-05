package com.truemega.services.travelmanagementsystem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
import com.truemega.dto.InvoiceAttachmentDTO;
import com.truemega.dto.UploadedInvoiceFileDTO;
import com.truemega.entities.InvoiceAttachment;
import com.truemega.entities.InvoicesTemp;
import com.truemega.entities.UploadedInvoiceFile;
import com.truemega.interfaces.travelmanagementsystem.InvoicesUploaderService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class InvoicesUploaderServiceImpl implements InvoicesUploaderService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	private Workbook wb;

	Calendar cal = Calendar.getInstance();

	@Override
	public void uploadInvoicesExcelSheet(InputStream ins, String fileName,
			String fileType, String userName) throws Exception {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start uploadInvoicesExcelSheet >> userName "
						+ userName);
		wb = null;
		UUID uid = UUID.randomUUID();
		String transactionId = uid.toString();
		InvoicesTemp invoicesTemp = null;

		int count = 0;
		try {
			// TODO Auto-generated method stub
			wb = getWorkbook(ins, fileName);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			Row row = null;
			Cell cell = null;

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

				invoicesTemp
						.setUploadedInvoiceFileId(new UploadedInvoiceFile(1));
				baseDao.saveEntity(invoicesTemp);
			}
			// baseDao.flush();
			loggerService
					.logServiceInfo("End uploadInvoicesExcelSheet successfully");
		} catch (Exception e) {

			loggerService
					.logServiceInfo("End uploadInvoicesExcelSheet With Exception at row # "
							+ (count + 1));
			loggerService.logServiceError(
					"End uploadInvoicesExcelSheet With Exception at row # "
							+ (count + 1), e);
			throw new Exception(e);

		}

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
		if (cell != null && cell.getDateCellValue() != null) {

			invoicesTemp.setInvoiceDate(getCellAsDate(cell));
		}

		cell = row.getCell(28);
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

	@Override
	public void uploadInvoicesExcelSheet(
			UploadedInvoiceFileDTO uploadedInvoiceFileDTO, String userName)
			throws Exception {

		loggerService
				.logServiceInfo("Start uploadInvoicesExcelSheet >> userName "
						+ userName);

		UploadedInvoiceFileDTO uploadedInvoiceFileDTOResult = mapper.map(
				baseDao.saveEntity(mapper.map(uploadedInvoiceFileDTO,
						UploadedInvoiceFile.class)),
				UploadedInvoiceFileDTO.class);

		baseDao.flush();
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

		// --------------------------------------------------------------------------
		wb = null;
		UUID uid = UUID.randomUUID();
		String transactionId = uid.toString();
		InvoicesTemp invoicesTemp = null;
		InputStream ins = null;

		int count = 0;
		try {
			// TODO Auto-generated method stub
			ins = new ByteArrayInputStream(uploadedInvoiceFileDTO.getContent());
			wb = getWorkbook(ins, uploadedInvoiceFileDTO.getName());
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			Row row = null;
			Cell cell = null;

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
						uploadedInvoiceFileDTOResult.getId()));
				baseDao.saveEntity(invoicesTemp);
			}
			// baseDao.flush();
			baseDao.executeDeleteNativeQuery(getMergeStatement(uploadedInvoiceFileDTOResult
					.getId()));
			loggerService
					.logServiceInfo("End uploadInvoicesExcelSheet successfully");
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

	@Override
	public void testStored() throws Exception {
		// TODO Auto-generated method stub
		baseDao.callStoredProcedure("TestStored");
	}

	private String getMergeStatement(int uploadedInvoiceFileId) {
		String query = " Merge into INVOICES_TEMP t1 using ( SELECT \n"
				+ " INVOICE_ORDER, \n"
				+ " TRANSACTION_ID, \n"
				+ " (CASE WHEN  INVOICE_NUMBER is null OR BOOKING_FILE_NUMBER is null OR EMPLOYEE_ID is null OR COST_CENTER is null \n"
				+ " OR EMPLOYEE_DEPARTMENT is null OR COST_CENTER_DEPARTMENT is null OR PASSENGER_NAME is null OR SERVICE_TYPE is null \n"
				+ " OR SERVICE_DESC is null OR SUPPLIER_NAME is null OR NET_AMOUNT is null OR OPERATION_FEES is null OR TOTAL_AMOUNT is null OR \n"
				+ " TICKET_NO is null OR TRAVEL_FORM_NUMBER is null OR INVOICE_DATE is null  \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) GENERAL_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  add_months(TO_DATE('26/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy'), -1) > TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " OR TO_DATE('25/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy') < TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) INVOICE_DATE_RANGE_VALID_N, \n"
				+ " (CASE WHEN  SERVICE_TYPE LIKE 'Air' AND  (DEPARTURE_DATE is null OR ARRIVAL_DATE is null OR ROUTING is null OR INTER_DOM is null OR AIRLINE is null) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) AIR_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  NOT (SERVICE_TYPE  LIKE 'Air' OR SERVICE_TYPE  LIKE 'Hotel')  AND  ( FROM_DATE is null OR TO_DATE is null ) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) OTHER_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  NOT (INTER_DOM  LIKE 'INTERNATIONAL' OR INTER_DOM  LIKE 'DOMESTIC') \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) INTER_DOM_VALID_N, \n"
				+ " (CASE WHEN   (CHECK_OUT-CHECK_IN)+1 !=  NUMBER_OF_NIGHTS \n"
				+ " THEN 0 \n" + " ELSE 1 END) NUMBER_OF_NIGHTS_VALID_N, \n"
				+ " (CASE WHEN   NET_AMOUNT+OPERATION_FEES !=  TOTAL_AMOUNT \n"
				+ " THEN 0 \n" + " ELSE 1 END) TOTAL_AMOUNT_VALID_N \n"
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
				+ " TICKET_NO is null OR TRAVEL_FORM_NUMBER is null OR INVOICE_DATE is null  \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " SERVICE_TYPE LIKE 'Air' AND  (DEPARTURE_DATE is null OR ARRIVAL_DATE is null OR ROUTING is null OR INTER_DOM is null OR AIRLINE is null) \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " NOT (SERVICE_TYPE  LIKE 'Air' OR SERVICE_TYPE  LIKE 'Hotel') \n"
				+ " AND  \n"
				+ " ( FROM_DATE is null OR TO_DATE is null ) \n"
				+ " ) \n"
				+ " OR \n"
				+ " (  \n"
				+ " NOT (INTER_DOM  LIKE 'INTERNATIONAL' OR INTER_DOM  LIKE 'DOMESTIC') \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " (CHECK_OUT-CHECK_IN)+1 !=  NUMBER_OF_NIGHTS \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " NET_AMOUNT+OPERATION_FEES !=  TOTAL_AMOUNT \n"
				+ " ) ) t2 \n"
				+ " on( t1.INVOICE_ORDER   = t2.INVOICE_ORDER AND t1.TRANSACTION_ID = t2.TRANSACTION_ID ) \n"
				+ " when matched then \n"
				+ " update set  \n"
				+ " t1.GENERAL_MANDATORY_VALID=t2.GENERAL_MANDATORY_VALID_N, \n"
				+ " t1.INVOICE_DATE_RANGE_VALID=t2.INVOICE_DATE_RANGE_VALID_N, \n"
				+ " t1.AIR_MANDATORY_VALID=t2.AIR_MANDATORY_VALID_N, \n"
				+ " t1.OTHER_MANDATORY_VALID=t2.OTHER_MANDATORY_VALID_N, \n"
				+ " t1.NUMBER_OF_NIGHTS_VALID=t2.NUMBER_OF_NIGHTS_VALID_N, \n"
				+ " t1.TOTAL_AMOUNT_VALID=t2.TOTAL_AMOUNT_VALID_N, \n"
				+ " t1.INTER_DOM_VALID=t2.INTER_DOM_VALID_N \n";
		return query;
	}
}
