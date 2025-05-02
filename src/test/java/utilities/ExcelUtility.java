package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelUtility {

	
	
	public String path;
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;

    public ExcelUtility(String path)  {
        this.path = path;
       
    }
    public int getRowCount(String sheetName) throws IOException
    {
    	fi = new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	sheet = workbook.getSheet(sheetName);
    	int rowCount = sheet.getLastRowNum();
    	workbook.close();
    	fi.close();
    	return rowCount;
    }
    
    public int getCellCount(String sheetName, int rownum) throws IOException
    {
    	fi = new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	sheet = workbook.getSheet(sheetName);
    	row = sheet.getRow(rownum);
    	int cellCount = row.getLastCellNum();
    	workbook.close();
    	fi.close();
    	return cellCount;
    }

    public String getCellData(String sheetName , int rownum, int colnum) throws IOException
    {
    	fi = new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	sheet = workbook.getSheet(sheetName);
    	row = sheet.getRow(rownum);
    	cell = row.getCell(colnum);
    	
    	DataFormatter formatter = new DataFormatter();
    	String data;
    	try
    	{
    		data= formatter.formatCellValue(cell);
    		
    	}
    	catch(Exception e)
    	{
    		data = "";
    	}
    	workbook.close();
    	fi.close();
    	return data;

         
    }

    public void setCellData(String sheetName , int rownum, int colnum, String data) throws IOException 
    {
        File xlfile =  new File(path);
        if(!xlfile.exists())
        {
        	workbook = new XSSFWorkbook();
        	fo = new FileOutputStream(path);
    		workbook.write(fo);
        	
        }
        
        fi = new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	if(workbook.getSheetIndex(sheetName)== -1) 
    	
    		workbook.createSheet(sheetName);
    	sheet = workbook.getSheet(sheetName);
    	
    	
    	if(sheet.getRow(rownum)==null)
    		  sheet.createRow(rownum);
    	row = sheet.getRow(rownum);
    	
    	cell = row.createCell(colnum);
    	cell.setCellValue(data);
    	fo = new FileOutputStream(path);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();
    	
    	  	
    	
    }
    public void fillRedColor(String sheetName , int rownum, int colnum) throws IOException
	{
		fi = new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	sheet = workbook.getSheet(sheetName);
    	row = sheet.getRow(rownum);
    	cell = row.getCell(colnum);
    	
    	style = workbook.createCellStyle();
    	
    	style.setFillForegroundColor(IndexedColors.RED.getIndex());
    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	
    	cell.setCellStyle(style);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();
	}
    

   
}
