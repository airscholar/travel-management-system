package com.truemega.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.truemega.beans.TravelReportBean;
import com.truemega.reportgenerator.DataType;
import com.truemega.reportgenerator.GenerateDynamicReport;

@ManagedBean(name = "invoicesComparisonReport")
@ViewScoped
public class InvoicesComparisonReport extends TravelReportBean {

	private GenerateDynamicReport dynamicReport;

	private String firstMonth;
	private String secondMonth;

	@PostConstruct
	public void init() {
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

		if (firstMonth.equalsIgnoreCase(secondMonth)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"please choose different months to compare"));
			return;
		}

		try {

			String queury = "SELECT nvl(TO_CHAR(INVOICES.INVOICE_ORDER) ,'NA') AS INVOICE_ORDER \n"
					+ " ,nvl(INVOICES.INVOICE_NUMBER ,'NA') AS INVOICE_NUMBER \n"
					+ " ,nvl(INVOICES.BOOKING_FILE_NUMBER ,'NA') AS BOOKING_FILE_NUMBER \n"
					+ " ,nvl(TO_CHAR(INVOICES.DEPARTURE_DATE, 'dd/mm/yyyy') ,'NA') AS DEPARTURE_DATE \n"
					+ " ,nvl(TO_CHAR(INVOICES.ARRIVAL_DATE, 'dd/mm/yyyy') ,'NA') AS ARRIVAL_DATE \n"
					+ " ,nvl(INVOICES.EMPLOYEE_ID ,'NA') AS EMPLOYEE_ID \n"
					+ " ,nvl(INVOICES.COST_CENTER ,'NA') AS COST_CENTER \n"
					+ " ,nvl(INVOICES.COST_CENTER_DEPARTMENT ,'NA') AS COST_CENTER_DEPARTMENT \n"
					+ " ,nvl(INVOICES.PASSENGER_NAME ,'NA') AS PASSENGER_NAME \n"
					+ " ,nvl(INVOICES.SERVICE_TYPE ,'NA') AS SERVICE_TYPE \n"
					+ " ,nvl(INVOICES.SERVICE_DESC ,'NA') AS SERVICE_DESC \n"
					+ " ,nvl(INVOICES.ROUTING ,'NA') AS ROUTING \n"
					+ " ,nvl(INVOICES.INTER_DOM ,'NA') AS INTER_DOM \n"
					+ " ,nvl(TO_CHAR(INVOICES.CHECK_IN, 'dd/mm/yyyy') ,'NA') AS CHECK_IN \n"
					+ " ,nvl(TO_CHAR(INVOICES.CHECK_OUT, 'dd/mm/yyyy') ,'NA') AS CHECK_OUT \n"
					+ " ,nvl(TO_CHAR(INVOICES.NUMBER_OF_NIGHTS) ,0) AS NUMBER_OF_NIGHTS \n"
					+ " ,nvl(TO_CHAR(INVOICES.NUMBER_OF_ROOMS) ,0) AS NUMBER_OF_ROOMS \n"
					+ " ,nvl(INVOICES.AIRLINE ,'NA') AS AIRLINE \n"
					+ " ,nvl(INVOICES.ROOM_TYPE ,'NA') AS ROOM_TYPE \n"
					+ " ,nvl(INVOICES.SUPPLIER_NAME ,'NA') AS SUPPLIER_NAME \n"
					+ " ,nvl(INVOICES.NET_AMOUNT ,0) AS NET_AMOUNT \n"
					+ " ,nvl(INVOICES.OPERATION_FEES ,0) AS OPERATION_FEES \n"
					+ " ,nvl(INVOICES.TOTAL_AMOUNT ,0) AS TOTAL_AMOUNT \n"
					+ " ,nvl(INVOICES.TICKET_NO ,'NA') AS TICKET_NO \n"
					+ " ,nvl(INVOICES.TRAVEL_FORM_NUMBER ,'NA') AS TRAVEL_FORM_NUMBER \n"
					+ " ,nvl(INVOICES.DESCRIPTION ,'NA') AS DESCRIPTION \n"
					+ " ,nvl(TO_CHAR(INVOICES.FROM_DATE, 'dd/mm/yyyy') ,'NA') AS FROM_DATE \n"
					+ " ,nvl(TO_CHAR(INVOICES.TO_DATE, 'dd/mm/yyyy') ,'NA') AS TO_DATE \n"
					+ " ,nvl(INVOICES.EMPLOYEE_DEPARTMENT ,'NA') AS EMPLOYEE_DEPARTMENT \n"
					+ " ,nvl(TO_CHAR(INVOICES.INVOICE_DATE, 'dd/mm/yyyy') ,'NA') AS INVOICE_DATE \n"
					+ " ,nvl(UPLOADED_INVOICE_FILE.INVOICES_MONTH ,'NA') AS INVOICES_MONTH \n"
					+ " FROM INVOICES INNER JOIN UPLOADED_INVOICE_FILE \n"
					+ " ON INVOICES.UPLOADED_INVOICE_FILE_ID = UPLOADED_INVOICE_FILE.ID \n"
					+ " WHERE UPLOADED_INVOICE_FILE.INVOICES_MONTH IN ('"
					+ firstMonth
					+ "','"
					+ secondMonth
					+ "') \n"
					+ " ORDER BY UPLOADED_INVOICE_FILE.INVOICES_MONTH,INVOICES.INVOICE_ORDER \n";

			dynamicReport = new GenerateDynamicReport();
			dynamicReport.setReportName("InvoicesComparisonReport");
			dynamicReport.setReportTitle("Invoices Comparison Report");
			dynamicReport.setReportQuery(queury);

			dynamicReport.columnsNames.add("Invoice Order");
			dynamicReport.columnsNames.add("Invoice Number");
			dynamicReport.columnsNames.add("Booking File Number");
			dynamicReport.columnsNames.add("Departure Date");
			dynamicReport.columnsNames.add("Arrival Date");
			dynamicReport.columnsNames.add("Employee Id");
			dynamicReport.columnsNames.add("Cost Center");
			dynamicReport.columnsNames.add("Cost Center Department");
			dynamicReport.columnsNames.add("Passenger Name");
			dynamicReport.columnsNames.add("Service Type");
			dynamicReport.columnsNames.add("Service Description");
			dynamicReport.columnsNames.add("Routing");
			dynamicReport.columnsNames.add("Int'l / Dom");
			dynamicReport.columnsNames.add("Check In");
			dynamicReport.columnsNames.add("Check Out");
			dynamicReport.columnsNames.add("Number of Nights");
			dynamicReport.columnsNames.add("Number of Rooms");
			dynamicReport.columnsNames.add("Airline");
			dynamicReport.columnsNames.add("Room Type");
			dynamicReport.columnsNames.add("Supplier Name");
			dynamicReport.columnsNames.add("Net Amount");
			dynamicReport.columnsNames.add("Operation Fees");
			dynamicReport.columnsNames.add("Total Amount");
			dynamicReport.columnsNames.add("Ticket No");
			dynamicReport.columnsNames.add("Travel Form Number");
			dynamicReport.columnsNames.add("Description");
			dynamicReport.columnsNames.add("From Date");
			dynamicReport.columnsNames.add("To Date");
			dynamicReport.columnsNames.add("Employee Department");
			dynamicReport.columnsNames.add("Invoice Date");
			dynamicReport.columnsNames.add("Invoices Month");

			dynamicReport.fieldsNames.add("INVOICE_ORDER");
			dynamicReport.fieldsNames.add("INVOICE_NUMBER");
			dynamicReport.fieldsNames.add("BOOKING_FILE_NUMBER");
			dynamicReport.fieldsNames.add("DEPARTURE_DATE");
			dynamicReport.fieldsNames.add("ARRIVAL_DATE");
			dynamicReport.fieldsNames.add("EMPLOYEE_ID");
			dynamicReport.fieldsNames.add("COST_CENTER");
			dynamicReport.fieldsNames.add("COST_CENTER_DEPARTMENT");
			dynamicReport.fieldsNames.add("PASSENGER_NAME");
			dynamicReport.fieldsNames.add("SERVICE_TYPE");
			dynamicReport.fieldsNames.add("SERVICE_DESC");
			dynamicReport.fieldsNames.add("ROUTING");
			dynamicReport.fieldsNames.add("INTER_DOM");
			dynamicReport.fieldsNames.add("CHECK_IN");
			dynamicReport.fieldsNames.add("CHECK_OUT");
			dynamicReport.fieldsNames.add("NUMBER_OF_NIGHTS");
			dynamicReport.fieldsNames.add("NUMBER_OF_ROOMS");
			dynamicReport.fieldsNames.add("AIRLINE");
			dynamicReport.fieldsNames.add("ROOM_TYPE");
			dynamicReport.fieldsNames.add("SUPPLIER_NAME");
			dynamicReport.fieldsNames.add("NET_AMOUNT");
			dynamicReport.fieldsNames.add("OPERATION_FEES");
			dynamicReport.fieldsNames.add("TOTAL_AMOUNT");
			dynamicReport.fieldsNames.add("TICKET_NO");
			dynamicReport.fieldsNames.add("TRAVEL_FORM_NUMBER");
			dynamicReport.fieldsNames.add("DESCRIPTION");
			dynamicReport.fieldsNames.add("FROM_DATE");
			dynamicReport.fieldsNames.add("TO_DATE");
			dynamicReport.fieldsNames.add("EMPLOYEE_DEPARTMENT");
			dynamicReport.fieldsNames.add("INVOICE_DATE");
			dynamicReport.fieldsNames.add("INVOICES_MONTH");

			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DOUBLE.toString());
			dynamicReport.dataTypes.add(DataType.DOUBLE.toString());
			dynamicReport.dataTypes.add(DataType.DOUBLE.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());

			String reportPath = dynamicReport.exportDynamicReportToExcel();
			getDownloadableReportFile(reportPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String dateToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public Date convertStringToDate(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (s != null && !s.isEmpty()) {
				Date date = formatter.parse(s);
				return date;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public String getFirstMonth() {
		return firstMonth;
	}

	public void setFirstMonth(String firstMonth) {
		this.firstMonth = firstMonth;
	}

	public String getSecondMonth() {
		return secondMonth;
	}

	public void setSecondMonth(String secondMonth) {
		this.secondMonth = secondMonth;
	}

}
