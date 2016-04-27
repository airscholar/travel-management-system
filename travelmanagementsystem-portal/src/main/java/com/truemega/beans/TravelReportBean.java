package com.truemega.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public abstract class TravelReportBean {

	@ManagedProperty(value = "#{reportFile}")
	private StreamedContent reportFile;

	public abstract void search();

	public StreamedContent getDownloadableReportFile(String outputFilePath) {
		try {
			File reportingFile = new File(outputFilePath);
			InputStream stream = null;
			stream = new FileInputStream(reportingFile);
			reportFile = new DefaultStreamedContent(stream,
					"application/vnd.ms-outlook", reportingFile.getName());
			return reportFile;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public StreamedContent getReportFile() {
		return reportFile;
	}

	public void setReportFile(StreamedContent reportFile) {
		this.reportFile = reportFile;
	}
}
