package com.truemega.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelReportBean;
import com.truemega.reportgenerator.DataType;
import com.truemega.reportgenerator.GenerateDynamicReport;


@ManagedBean(name = "invoiceReport")
@ViewScoped
public class InvoiceReport extends TravelReportBean{
	private GenerateDynamicReport dynamicReport;
	private String to;
	private String from;
	
    @PostConstruct 
    public void init()
    {
    }
	
	@Override
	public void search() {
		// TODO Auto-generated method stub

		try {

			String whereClause = " where 1=1 ";
			
			Date toDate = convertStringToDate(to);
			Date fromDate = convertStringToDate(from);
//			if (accidentClass != null && accidentClass.length() > 0){
//				whereClause += " and lower( ACCIDENT_CLASS )like lower('%"
//						+ accidentClass + "%')";
//				}
//	

			if (to != null && to.length() > 0)
				whereClause += " and INVOICE_DATE <= TO_DATE ('"
						+ dateToString(toDate)
						+ "', 'yyyy-MM-dd') ";

			if (from != null && from.length() > 0)
				whereClause += " and INVOICE_DATE >= TO_DATE ('"
						+ dateToString(fromDate)
						+ "', 'yyyy-MM-dd') ";

			String queury = " SELECT * FROM INVOICE_REPORT " + whereClause;

			System.out.println(queury);







			dynamicReport = new GenerateDynamicReport();
			dynamicReport.setReportName("InvoiceReport");
			dynamicReport.setReportTitle("Invoice Report");
			dynamicReport.setReportQuery(queury);
			
			
			dynamicReport.columnsNames.add(" Invoice Order");
			dynamicReport.columnsNames.add(" Transaction ID ");
			dynamicReport.columnsNames.add("Invoice Number");
			dynamicReport.columnsNames.add("Booking File Number");
			dynamicReport.columnsNames.add("departure Date");
			dynamicReport.columnsNames.add("Arrival Date ");
			dynamicReport.columnsNames.add("Employee Id");
			dynamicReport.columnsNames.add("Cost Center");
			dynamicReport.columnsNames.add("Cost Center Department");
			dynamicReport.columnsNames.add("Passenger Name");
			dynamicReport.columnsNames.add("Service Type ");
			dynamicReport.columnsNames.add("Service Description ");
			dynamicReport.columnsNames.add("Routing ");
			dynamicReport.columnsNames.add("Inter Dom");
			dynamicReport.columnsNames.add("Check In");
			dynamicReport.columnsNames.add("Check Out");
			dynamicReport.columnsNames.add("Number Of Nights ");
			dynamicReport.columnsNames.add("Number Of Rooms ");
			dynamicReport.columnsNames.add("AirLine ");
			dynamicReport.columnsNames.add("Room Type ");
			dynamicReport.columnsNames.add("Supplier Name ");
			dynamicReport.columnsNames.add("Net Amount ");
			dynamicReport.columnsNames.add("Operation Fees ");
			dynamicReport.columnsNames.add("Total Amount");
			dynamicReport.columnsNames.add("Ticket No");
			dynamicReport.columnsNames.add("Travel Form Number ");
			dynamicReport.columnsNames.add("Description ");
			dynamicReport.columnsNames.add("From Date");
			dynamicReport.columnsNames.add("To Date");
			dynamicReport.columnsNames.add("Employee Department ");
			dynamicReport.columnsNames.add("Invoice Date");
			dynamicReport.columnsNames.add("Approved ");
			dynamicReport.columnsNames.add("Rejection reason");
			dynamicReport.columnsNames.add("Invoices Month");
			dynamicReport.columnsNames.add(" Name");
			
			
			
			
			


			dynamicReport.fieldsNames.add("INVOICE_ORDER");
			dynamicReport.fieldsNames.add("TRANSACTION_ID");
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
			dynamicReport.fieldsNames.add("APPROVED");
			dynamicReport.fieldsNames.add("REJECTION_REASON");
			dynamicReport.fieldsNames.add("INVOICES_MONTH");
			dynamicReport.fieldsNames.add("NAME");
			
			


			dynamicReport.dataTypes.add(DataType.INT.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.INT.toString());
			dynamicReport.dataTypes.add(DataType.INT.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.INT.toString());
			dynamicReport.dataTypes.add(DataType.INT.toString());
			dynamicReport.dataTypes.add(DataType.INT.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.INT.toString());
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


	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
