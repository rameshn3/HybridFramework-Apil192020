package com.qa.linkedin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;

import com.qa.linkedin.base.TestBase;

public class TestUtil extends TestBase{
	public String path;
	public static String screenshotPath;
	public static String screenshotName;
	public static Logger log=Logger.getLogger(TestUtil.class);
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	/**
	 * this Method is used to read the data from excelsheets
	 * @param fpath
	 * @param sheetName
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public Object[][] getTestData(String path,String sheetName) throws InvalidFormatException, IOException {
		
	//Specify the path of file
	File srcFile=new File(path);

	//load file
	 fis=new FileInputStream(srcFile);
	//Load workbook
	 workbook=new XSSFWorkbook(fis);

	//Load sheet- Here we are loading first sheetonly
	 sheet= workbook.getSheet(sheetName);
		//two d array declaration
	int rowCount=sheet.getLastRowNum();
	log.debug("number of rows in the excel sheet is-->"+rowCount);
	int colCount=sheet.getRow(0).getLastCellNum();
	log.debug("number of columns in the excel sheet is -->"+colCount);
	Object[][] data = new Object[rowCount][colCount];
		for (int i = 0; i < rowCount; i++) {
			for (int k = 0; k < colCount; k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
		
	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}


	public static String timeStamp(){
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	 /**
	 * this method highlights the given element
	 * @param driver
	 * @param element
	 * @throws InterruptedException
	 */
	public static void highLightElement1(WebDriver driver, WebElement element) throws InterruptedException
	{
	JavascriptExecutor js=(JavascriptExecutor)driver; //downcasting

	js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 5px red')", element); 

	Thread.sleep(5000);
	
	js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element); 

	}
	/**
	 * this method types given keyword without using sendKeys()
	 * @param element
	 * @param attributeName
	 * @param value
	 */
	public static void setAttribute(WebElement element, String attributeName, String value)
	{
	WrapsDriver wrappedElement = (WrapsDriver) element;
	JavascriptExecutor js = (JavascriptExecutor)wrappedElement.getWrappedDriver();
	js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attributeName, value);
	}
	
public static void scrollForElement(WebDriver driver,WebElement elementname) {
	//scroll for this element
			JavascriptExecutor jsx=(JavascriptExecutor) driver;
			jsx.executeScript("arguments[0].scrollIntoView(true);", elementname);
}


public static void safeJavaScriptClick(WebDriver driver,WebElement element){
	
	try{
		if(element.isEnabled()&&element.isDisplayed()){
		JavascriptExecutor js=  (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		}
		
	}catch(StaleElementReferenceException se){
		System.out.println("Element is no more attached to the DOM:");
		se.printStackTrace();
	}catch(NoSuchElementException ne){
		System.out.println("No Element present in the DOM:");
		ne.printStackTrace();
		
	}catch(Exception e){
		System.out.println("Exception is different:");
		e.printStackTrace();
	}
}
}