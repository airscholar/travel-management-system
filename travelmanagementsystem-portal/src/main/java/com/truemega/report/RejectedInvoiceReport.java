package com.truemega.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelReportBean;
import com.truemega.reportgenerator.DataType;
import com.truemega.reportgenerator.GenerateDynamicReport;

@ManagedBean(name = "rejectedsInvoiceReport")
@ViewScoped
public class RejectedInvoiceReport extends TravelReportBean {


	private GenerateDynamicReport dynamicReport;
	private String to;
	private String from;
	private String name ;
	
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
			
			if (name != null && name.length() > 0){
				whereClause += " and lower( NAME )like lower('%"
						+ name + "%')";
				}
	

			if (to != null && to.length() > 0)
				whereClause += " and ACTION_TAKEN_DATE <= TO_DATE ('"
						+ dateToString(toDate)
						+ "', 'yyyy-MM-dd') ";

			if (from != null && from.length() > 0)
				whereClause += " and ACTION_TAKEN_DATE >= TO_DATE ('"
						+ dateToString(fromDate)
						+ "', 'yyyy-MM-dd') ";

			String queury = " SELECT * FROM REJECTED_INVOICE_REPORT " + whereClause;

			System.out.println(queury);







			dynamicReport = new GenerateDynamicReport();
			dynamicReport.setReportName("RejectedInvoicesReport");
			dynamicReport.setReportTitle("Rejected Invoices Report");
			dynamicReport.setReportQuery(queury);
			





			dynamicReport.columnsNames.add(" Modification Date");
			dynamicReport.columnsNames.add(" Name ");
			dynamicReport.columnsNames.add("System user ");
			dynamicReport.columnsNames.add("Invoices Month");
			dynamicReport.columnsNames.add("Templet Table");
			dynamicReport.columnsNames.add("Action Tacken Date ");
			dynamicReport.columnsNames.add("Action Taker");
			dynamicReport.columnsNames.add("Rejection Reason ");
			dynamicReport.columnsNames.add("Approved  ");
			

			dynamicReport.fieldsNames.add("MODIFICATION_DATE");
			dynamicReport.fieldsNames.add("NAME");
			dynamicReport.fieldsNames.add("SYSTEM_USER");
			dynamicReport.fieldsNames.add("INVOICES_MONTH");
			dynamicReport.fieldsNames.add("TEMPLATE_TABLE");
			dynamicReport.fieldsNames.add("ACTION_TAKEN_DATE");
			dynamicReport.fieldsNames.add("ACTION_TAKER");
			dynamicReport.fieldsNames.add("REJECTION_REASON");
			dynamicReport.fieldsNames.add("APPROVED");
			
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.INT.toString());
			dynamicReport.dataTypes.add(DataType.DATE.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.INT.toString());
			
			
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
