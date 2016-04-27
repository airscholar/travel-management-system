package com.truemega.reports;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public abstract class ExportReportToExcel {

	
	Workbook wb;
	
	public final <T> boolean export(String templateFileName,String outputFileName, List<T> list)
	  {
		 wb=null;
		
		try {
			wb=getWorkbook(templateFileName);
			//writeDataToWorkSheet(wb);
			
			writeDataToWorkSheet1(wb, list);
			writeReportToFile(wb,outputFileName);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	 
	  }
	
	abstract  public <T> void writeDataToWorkSheet1(Workbook wb, List<T> myList);
	
		private  final void writeReportToFile(Workbook wb,String outputFileName) throws IOException
	  {
	    FileOutputStream fileOut = null;
				
					fileOut = new FileOutputStream(outputFileName);
					wb.write(fileOut);
					fileOut.close();
				
	  }
	private final Workbook getWorkbook(String templateFileName) throws IOException
	  {
		InputStream ins = null;
		
			ins = new FileInputStream(templateFileName);  // read from source
		
		Workbook wb=null;
		
		if(templateFileName.endsWith(".xls"))
			wb = new HSSFWorkbook(ins);
		else
		   {
			 wb = new XSSFWorkbook(ins);
		   }
	        
		
		return wb;
	  }
	
	public final  Cell prepareCell(Row row, int cellIndex, int CELL_TYPE){
		Cell cell = row.getCell(cellIndex);

		if (cell == null)
			cell = row.createCell(cellIndex);

		cell.setCellType(CELL_TYPE);
		
		return cell;
	}
	
	public  final Row getRow(Sheet sheet,int rowIndex) {
		Row row = sheet.getRow(rowIndex);
		if(row == null)
			row = sheet.createRow(rowIndex);
			
		return row;
	}
	
}
