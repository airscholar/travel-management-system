package com.truemega.reportgenerator;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import com.truemega.logger.LoggerService;

public class GenerateDynamicReport {

	private LoggerService loggerService = new LoggerService();
	private JasperReportBuilder report = DynamicReports.report();
	private JasperXlsExporterBuilder xlsExporter;
	private String reportQuery, reportTitle, reportName, reportPath;

	public final String reportsDir = "." + File.separator + "TravelManagement"
			+ File.separator + "Reports" + File.separator;
	public List<String> columnsNames = new ArrayList<String>();
	public List<String> fieldsNames = new ArrayList<String>();
	public List<String> dataTypes = new ArrayList<String>();
	private Connection conn = null;

	public String exportDynamicReportToExcel() {
		try {
			// generate report path on weblogic server
			reportName = generateReportName() + ".xls";
			reportPath = reportsDir + reportName;
			// build the report
			buildDynamicReport();
			// export the report to a Excel file
			report.toXls(xlsExporter);
			return reportPath;
		} catch (DRException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public String exportDynamicReportToPDF() {
		try {
			// generate report path on weblogic server
			reportName = generateReportName() + ".pdf";
			reportPath = reportsDir + reportName;
			// build the report
			buildDynamicReport();
			// export the report to a pdf file
			report.toPdf(new FileOutputStream(reportPath));
			return reportPath;
		} catch (DRException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void buildDynamicReport() {
		try {

			xlsExporter = Exporters.xlsExporter(reportPath)
					.setDetectCellType(true).setIgnorePageMargins(true)
					.setWhitePageBackground(false)
					.setRemoveEmptySpaceBetweenColumns(true);
			StyleBuilder columnTitleStyle = stl.style()
					.setBorder(stl.penThin())
					.setBackgroundColor(Color.LIGHT_GRAY);
			StyleBuilder columnStyle = stl.style().setBorder(stl.penThin());
			report = DynamicReports.report();
			buildReportColumns();
			report.title(
					Components.text(reportTitle).setHorizontalAlignment(
							HorizontalAlignment.CENTER))
					.setColumnTitleStyle(columnTitleStyle)
					.setColumnStyle(columnStyle)
					.pageFooter(Components.pageXofY())
					.setDataSource(reportQuery, getDataSourceConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void buildReportColumns() {
		try {

			for (int i = 0; i < columnsNames.size(); i++) {
				if (dataTypes.get(i).equals("INT"))
					report.columns(Columns.column(columnsNames.get(i),
							fieldsNames.get(i), DataTypes.integerType()));
				else if (dataTypes.get(i).equals("STRING"))
					report.columns(Columns.column(columnsNames.get(i),
							fieldsNames.get(i), DataTypes.stringType()));
				else if (dataTypes.get(i).equals("DOUBLE"))
					report.columns(Columns.column(columnsNames.get(i),
							fieldsNames.get(i), DataTypes.doubleType()));
				else if (dataTypes.get(i).equals("DATE"))
					report.columns(Columns.column(columnsNames.get(i),
							fieldsNames.get(i), DataTypes.dateType()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String generateReportName() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					"dd-MM-yyyy 'at' h-mm a");
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(System.currentTimeMillis());
			reportName = reportName + sdf.format(calendar.getTime());
			return reportName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Connection getDataSourceConnection() {
		Context ctxLookup = null;
		DataSource ds = null;

		try {
			ctxLookup = new InitialContext();
			ds = (DataSource) ctxLookup.lookup("jdbc/tmsDS");
			conn = ds.getConnection();
			return conn;
		} catch (NamingException e) {
			loggerService
					.logPortalError(
							"GenerateDynamicReport.getDataSource : Couldn't Lookup to Datasource.",
							e);
			return null;
		} catch (SQLException e) {
			loggerService
					.logPortalError(
							"GenerateDynamicReport.getDataSource : Couldn't excuete SQL query.",
							e);
			return null;
		} catch (Exception e) {
			loggerService
					.logPortalError(
							"GenerateDynamicReport.getDataSource : Couldn't generate the report.",
							e);
			return null;
		}
	}

	public String getReportQuery() {
		return reportQuery;
	}

	public void setReportQuery(String reportQuery) {
		this.reportQuery = reportQuery;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
}
