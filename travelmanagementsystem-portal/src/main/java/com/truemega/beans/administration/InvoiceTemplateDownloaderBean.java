package com.truemega.beans.administration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.truemega.logger.LoggerService;

@ManagedBean(name = "invoiceTemplate")
@ViewScoped
public class InvoiceTemplateDownloaderBean {

	private static final long serialVersionUID = 1L;

	private LoggerService loggerService = new LoggerService();

	private StreamedContent reportFile;

	@PostConstruct
	public void init() {
	}

	public StreamedContent getReportFile() {
		String outputFilePath = ".\\TravelManagement\\InvoiceTemplate\\Travel_Template.xlsx";

		try {
			File reportingFile = new File(outputFilePath);
			InputStream stream = null;
			stream = new FileInputStream(reportingFile);
			reportFile = new DefaultStreamedContent(stream,
					"application/vnd.ms-outlook", reportingFile.getName());
			return reportFile;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			loggerService.logPortalError("Error reading template", e);
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			loggerService.logPortalError("Error reading template", e);
			e.printStackTrace();
			return null;
		}

	}

	public void setReportFile(StreamedContent reportFile) {
		this.reportFile = reportFile;
	}
}
