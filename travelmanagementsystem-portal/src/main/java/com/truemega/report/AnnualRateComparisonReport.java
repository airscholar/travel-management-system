package com.truemega.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelReportBean;
import com.truemega.reportgenerator.DataType;
import com.truemega.reportgenerator.GenerateDynamicReport;

@ManagedBean(name = "annualRateComparisonReport")
@ViewScoped
public class AnnualRateComparisonReport extends TravelReportBean {

	private GenerateDynamicReport dynamicReport;

	private String firstYear;
	private String secondYear;

	@PostConstruct
	public void init() {
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

		try {

			String queury = "SELECT RATES_VIEW1.SERVICE_NAME        AS SERVICE_NAME1, \n"
					+ " RATES_VIEW1.PRODUCT_NAME             AS PRODUCT_NAME1, \n"
					+ " RATES_VIEW1.SUPPLIER_NAME            AS SUPPLIER_NAME1, \n"
					+ " RATES_VIEW1.AIRLINE_NAME             AS AIRLINE_NAME1, \n"
					+ " RATES_VIEW1.ROUTING                  AS ROUTING1, \n"
					+ " RATES_VIEW1.RATE                     AS RATE1, \n"
					+ " RATES_VIEW1.ROOM_TYPE_NAME           AS ROOM_TYPE_NAME1, \n"
					+ " TO_CHAR(RATES_VIEW1.YEAR)            AS YEAR1, \n"
					+ " RATES_VIEW2.SERVICE_NAME             AS SERVICE_NAME2, \n"
					+ " RATES_VIEW2.PRODUCT_NAME             AS PRODUCT_NAME2, \n"
					+ " RATES_VIEW2.SUPPLIER_NAME            AS SUPPLIER_NAME2, \n"
					+ " RATES_VIEW2.AIRLINE_NAME             AS AIRLINE_NAME2, \n"
					+ " RATES_VIEW2.ROUTING                  AS ROUTING2, \n"
					+ " RATES_VIEW2.RATE                     AS RATE2, \n"
					+ " RATES_VIEW2.ROOM_TYPE_NAME           AS ROOM_TYPE_NAME2, \n"
					+ " TO_CHAR(RATES_VIEW2.YEAR)            AS YEAR2 \n"
					+ " FROM RATES_VIEW RATES_VIEW1 \n"
					+ " FULL OUTER JOIN RATES_VIEW RATES_VIEW2 \n"
					+ " ON RATES_VIEW1.SUPPLIER_PRODUCT_ID = RATES_VIEW2.SUPPLIER_PRODUCT_ID \n"
					+ " AND RATES_VIEW1.AIRLINE_ID         = RATES_VIEW2.AIRLINE_ID \n"
					+ " AND RATES_VIEW1.ROOM_TYPE_ID       = RATES_VIEW2.ROOM_TYPE_ID \n"
					+ " AND LOWER(RATES_VIEW1.ROUTING)     = LOWER(RATES_VIEW1.ROUTING) \n"
					+ " WHERE RATES_VIEW1.YEAR             ="
					+ firstYear
					+ " \n"
					+ " AND RATES_VIEW2.YEAR               ="
					+ secondYear + " \n";
			dynamicReport = new GenerateDynamicReport();
			dynamicReport.setReportName("AnnualRateComparisonReport");
			dynamicReport.setReportTitle("Annual Rate Comparison Report");
			dynamicReport.setReportQuery(queury);

			dynamicReport.columnsNames.add("Service Name_" + firstYear);
			dynamicReport.columnsNames.add("Product Name_" + firstYear);
			dynamicReport.columnsNames.add("Supplier Name_" + firstYear);
			dynamicReport.columnsNames.add("Airline Name_" + firstYear);
			dynamicReport.columnsNames.add("Routing_" + firstYear);
			dynamicReport.columnsNames.add("Rate_" + firstYear);
			dynamicReport.columnsNames.add("Room Type_" + firstYear);
			dynamicReport.columnsNames.add("Year_" + firstYear);
			dynamicReport.columnsNames.add("Service Name_" + secondYear);
			dynamicReport.columnsNames.add("Product Name_" + secondYear);
			dynamicReport.columnsNames.add("Supplier Name_" + secondYear);
			dynamicReport.columnsNames.add("Airline Name_" + secondYear);
			dynamicReport.columnsNames.add("Routing_" + secondYear);
			dynamicReport.columnsNames.add("Rate_" + secondYear);
			dynamicReport.columnsNames.add("Room Type_" + secondYear);
			dynamicReport.columnsNames.add("Year_" + secondYear);

			dynamicReport.fieldsNames.add("SERVICE_NAME1");
			dynamicReport.fieldsNames.add("PRODUCT_NAME1");
			dynamicReport.fieldsNames.add("SUPPLIER_NAME1");
			dynamicReport.fieldsNames.add("AIRLINE_NAME1");
			dynamicReport.fieldsNames.add("ROUTING1");
			dynamicReport.fieldsNames.add("RATE1");
			dynamicReport.fieldsNames.add("ROOM_TYPE_NAME1");
			dynamicReport.fieldsNames.add("YEAR1");
			dynamicReport.fieldsNames.add("SERVICE_NAME2");
			dynamicReport.fieldsNames.add("PRODUCT_NAME2");
			dynamicReport.fieldsNames.add("SUPPLIER_NAME2");
			dynamicReport.fieldsNames.add("AIRLINE_NAME2");
			dynamicReport.fieldsNames.add("ROUTING2");
			dynamicReport.fieldsNames.add("RATE2");
			dynamicReport.fieldsNames.add("ROOM_TYPE_NAME2");
			dynamicReport.fieldsNames.add("YEAR2");

			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DOUBLE.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DOUBLE.toString());
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

	public String getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(String firstYear) {
		this.firstYear = firstYear;
	}

	public String getSecondYear() {
		return secondYear;
	}

	public void setSecondYear(String secondYear) {
		this.secondYear = secondYear;
	}

}
