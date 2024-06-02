package seleniummavenproj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class DataHandler {

	
	// this class deals with handling excel sheet
	
	
	
	Properties obj = new Properties();
	List<String> testcases = new ArrayList<String>();
	List<String> headers= new ArrayList<String>();
	List<Integer> rownumbers = new ArrayList<Integer>();
	org.apache.poi.ss.usermodel.Workbook workbook = null;
	Sheet sheet;
	int columnnumber;
	int testcase_name_columnnumber;
	int tempcolumnnumber=0;
	
	

	public void loadobjectfile() throws IOException
	{
	
	FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\objectrepo.properties");
	obj.load(fs);
	}
	
	public String getdata(String sheetname, String testcasename, String columnname) throws IOException
	{
		loadobjectfile();
		System.out.println("in the method " +Thread.currentThread().getStackTrace()[1].getMethodName());
		workbook=loadexcel();
		sheet = workbook.getSheet(sheetname);
		int rownumber= getRowNumber(sheetname,testcasename);
		
		int columnnumber=getheadercellnumber(sheetname,columnname);
		
		Row row = sheet.getRow(rownumber);
		String data = row.getCell(columnnumber).toString();
		return data;
		
		
	}
	
	
	public void execute() throws IOException
	{
	//readExcel(System.getProperty("user.dir")+obj.getProperty("datasheet_path"),"Sheet1");
		loadexcel();
		
		/*
		int getheadercellnumber=getheadercellnumber("sheet1","TestCaseName");
		int getheadercellnumber1=getheadercellnumber("sheet1","URL");
		int rownumber = getRowNumber("sheet1","thirdtest");
	
		int rownumber = getRowNumber("sheet1","thirdtest");
		int columnnumber = getcolumnnumber("sheet1",rownumber, "URL");
		System.out.println("Row number is "+rownumber);
		System.out.println("column number is "+columnnumber);
		*/
		
		String value = getcelldata("sheet1", "thirdtest", "URL");
		System.out.println("cell value is " +value);
		
	}
	
	public String getcelldata(String sheetname, String testcasename, String columnname)
	{
		sheet = workbook.getSheet(sheetname);
		int rownumber= getRowNumber(sheetname,testcasename);
		int columnnumber=getheadercellnumber(sheetname,columnname);
		Row row = sheet.getRow(rownumber);
		String data = row.getCell(columnnumber).toString();
		return data;
	}
	
	public int getheadercellnumber(String sheetname, String headername)
	{
		int cellnumber=0;
		sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(0);
		int totalcolumns = row.getLastCellNum();
		
		for(int i=0;i<totalcolumns;i++)
		{
			if(row.getCell(i).toString().equalsIgnoreCase(headername))
			{
				cellnumber=i;
			}
		}
		return cellnumber;
	}
	public org.apache.poi.ss.usermodel.Workbook loadexcel() throws IOException
	{
		File file =    new File(System.getProperty("user.dir")+obj.getProperty("datasheet_path"));

	    FileInputStream inputStream = new FileInputStream(file);

	    //org.apache.poi.ss.usermodel.Workbook workbook = null;
	    String fileExtensionName = file.toString().substring(file.toString().indexOf("."));
	    if(fileExtensionName.equals(".xlsx")){
	    	workbook = new XSSFWorkbook(inputStream);

	    }
	    else if(fileExtensionName.equals(".xls")){
	    	workbook = new HSSFWorkbook(inputStream);

	    }
		return workbook;
	}
	
	
	
	public int getRowNumber(String sheetname, String testcasename)
	{
		int colnumber=0;
		int rownumber=0;
		sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(0);
		int totalcolumns = row.getLastCellNum();
		
		for(int i=0;i<totalcolumns;i++)
		{
			if(row.getCell(i).toString().equalsIgnoreCase("TestCaseName"))
			{
				colnumber=i;
			}
		}
		
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			
			if(sheet.getRow(i).getCell(colnumber).toString().equalsIgnoreCase(testcasename))
			{
			
				rownumber=i;
			}
			
		}
		
		return rownumber;
	}
	
	
	//Method to get cell number from rownumber and columnname
			public Integer getcolumnnumber(String sheetname,int rownum, String columnname)
			{
				
				
				int colnumber=0;
				sheet = workbook.getSheet(sheetname);
				Row row = sheet.getRow(0);
				int totalcolumns = row.getLastCellNum();
				
				for(int i=0;i<totalcolumns;i++)
				{
					if(row.getCell(i).toString().equalsIgnoreCase(columnname))
					{
						colnumber=i;
					}
				}
				
				
				row = sheet.getRow(colnumber);
				for (int j = 0; j < row.getLastCellNum(); j++) 
		        {
		        	System.out.println(row.getCell(j).getStringCellValue());
		        	headers.add(row.getCell(j).getStringCellValue());
		        	
		        	//if(row.getCell(j).getStringCellValue().toString().equalsIgnoreCase("TestCaseName"))
		        		if(row.getCell(j).getStringCellValue().toString().equalsIgnoreCase(columnname))
		        			
		        		tempcolumnnumber=j;
		        }
				return tempcolumnnumber;
			}
			
			
		

	// this method will get the testcases that are having runstatus as yes, based the fields runfield and testcasefield from object repository
	public List<String> runnableTestCases(String filePath,String sheetName) throws IOException
	{
		List<String> testcaseslist = new ArrayList<String>();
		File file =    new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		loadobjectfile();
	    //org.apache.poi.ss.usermodel.Workbook workbook = null;
	    String fileExtensionName = file.toString().substring(file.toString().indexOf("."));
	    if(fileExtensionName.equals(".xlsx")){
	    	workbook = new XSSFWorkbook(inputStream);

	    }
	    else if(fileExtensionName.equals(".xls")){
	    	workbook = new HSSFWorkbook(inputStream);

	    }

	    Sheet sheet = workbook.getSheet(sheetName);
	    int runfield_columnnumber=getheadercellnumber(sheetName,obj.getProperty("runfield"));
	    int testcase_columnnumber=getheadercellnumber(sheetName,obj.getProperty("testcasefield"));
	    
	    for(int i=1;i<=sheet.getLastRowNum();i++)
	    {
	    	if(sheet.getRow(i).getCell(runfield_columnnumber).toString().equalsIgnoreCase("yes"))
	    	{
	    		testcaseslist.add(sheet.getRow(i).getCell(testcase_columnnumber).toString());
	    	}
	    	
	    }
		
		return testcaseslist;
		
	    
	 }	  
	
	//Method to get column name from column number
	public Integer getcolumnnumber(Row row, String columnname)
	{
		
		for (int j = 0; j < row.getLastCellNum(); j++) 
        {
        	//System.out.println(row.getCell(j).getStringCellValue());
        	//headers.add(row.getCell(j).getStringCellValue());
        	
        	//if(row.getCell(j).getStringCellValue().toString().equalsIgnoreCase("TestCaseName"))
			System.out.println(row.getCell(j).toString());
        		if(row.getCell(j).toString().equalsIgnoreCase(columnname))
        		{	
        			
        		tempcolumnnumber=j;
        		
        		}
        }
		return tempcolumnnumber;
	}
	
	
	
	
	//method to get the testcase names with RunStatus as yes
			
	public List<String> getTestCaselist(List<Integer> rownumbers,int columnnumber)
	{
		List<String> temptestcases = new ArrayList<String>();
		System.out.println("Rownumber size is "+rownumbers.size());
		System.out.println("column number is " +columnnumber);
				
		for(int i=0;i<rownumbers.size();i++)
				{
					Row row = sheet.getRow(rownumbers.get(i));
					temptestcases.add((row.getCell(columnnumber).getStringCellValue()));
				} 
				
				System.out.println("Testcases that have runstatus as yes are");
				for(int i=0;i<testcases.size();i++)
				{
				    System.out.println(testcases.get(i));
				}
				return temptestcases;
	}
	
}
