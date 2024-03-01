package Page_Object_Model;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPracticeExcel {
	 public static String getCellValueAsString(Cell cell) {
	        String cellValue = "";
	        if (cell != null) {
	            switch (cell.getCellType()) {
	                case STRING:
	                    cellValue = cell.getStringCellValue();
	                    break;
	                case NUMERIC:
	                    // Check if the numeric value is an integer
	                    if (cell.getNumericCellValue() == (int) cell.getNumericCellValue()) {
	                        cellValue = String.valueOf((int) cell.getNumericCellValue());
	                    } else {
	                        // If it's not an integer, treat it as a string
	                        cellValue = String.valueOf(cell.getNumericCellValue());
	                    }
	                    break;
	            }
	        }
	        return cellValue;
	    }
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://edmstest.infoman-edms.com/General/pgGLogin.aspx?LogOut=True");
		driver.manage().window().maximize();
		
		LoginPage login_page = new LoginPage(driver); // making an object of LoginPage class
		
		login_page.login("admin@tmpl", "ADMIN@#"); //calling the method
		
		TransactionPage transaction = new TransactionPage(driver);
		transaction.clickTransaction();
		
		PurchaseEntryPage purchase = new PurchaseEntryPage(driver);
		purchase.clickOnPurchaseEntry();
        driver.switchTo().frame("frmContent");
		purchase.selectLoc();
		Thread.sleep(2000);
		
		File excelFile = new File("./Data/VehicleDetail.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		
		Workbook workbook = WorkbookFactory.create(fis);
		
		Sheet sheet = workbook.getSheet("Sheet1");

		
		 Row row = sheet.getRow(1);
	        String purchaseType = getCellValueAsString(row.getCell(0));
	        String supplier = getCellValueAsString(row.getCell(1));
	        String date = getCellValueAsString(row.getCell(2));
	        String invoice = getCellValueAsString(row.getCell(3));
	        String gatepass = getCellValueAsString(row.getCell(4));
	        String invoiceDate = getCellValueAsString(row.getCell(5));
	        String plantNo = getCellValueAsString(row.getCell(6));
	        String financier = getCellValueAsString(row.getCell(7));
	        String sendBY = getCellValueAsString(row.getCell(8));
	        String transporterName = getCellValueAsString(row.getCell(9));
	        String transporterReg = getCellValueAsString(row.getCell(10));
	        String chassis_prefix = getCellValueAsString(row.getCell(11));
	        String eWay_bill_no = getCellValueAsString(row.getCell(12));
	        String eWay_bill_date = getCellValueAsString(row.getCell(13));
	        String placeofsupply = getCellValueAsString(row.getCell(14));
	        String model = getCellValueAsString(row.getCell(15));
	        String subModel = getCellValueAsString(row.getCell(17));
	        String color = getCellValueAsString(row.getCell(19));
	        String chassisNo = getCellValueAsString(row.getCell(16));
	        String engineNo = getCellValueAsString(row.getCell(18));
	        String keyNo = getCellValueAsString(row.getCell(20));
	        String receivedAT = getCellValueAsString(row.getCell(21));
	        String remarks = getCellValueAsString(row.getCell(22));
	        String status = getCellValueAsString(row.getCell(23));
	        String purchase_price = getCellValueAsString(row.getCell(24));
	        String dlr_vin_no = getCellValueAsString(row.getCell(25));
	        String man_discount = getCellValueAsString(row.getCell(26));
	        String emission = getCellValueAsString(row.getCell(27));
	        // Perform purchase entry for each set of data
	     /* purchase.purchaseEntry(purchaseType, supplier, date, invoice, gatepass, invoiceDate, plantNo, financier,
	                             sendBY, transporterName, transporterReg, chassis_prefix, eWay_bill_no, eWay_bill_date,
	                             placeofsupply, model, chassisNo, subModel, engineNo, color, keyNo, receivedAT, remarks, status,
	                             purchase_price, dlr_vin_no, man_discount, emission); */
	        purchase.purchaseEntry(purchaseType, supplier, date, invoice, gatepass, invoiceDate, plantNo, financier,
	    	                    sendBY, transporterName, transporterReg, chassis_prefix, eWay_bill_no, eWay_bill_date,
	    	                    placeofsupply, model, subModel, color, chassisNo, engineNo, keyNo, receivedAT, remarks, status,
	    	                    purchase_price, dlr_vin_no, man_discount, emission);      
	                    
	            Thread.sleep(2000);
	    		WebElement saveButton = driver.findElement(By.xpath("//input[@id=\"ctl00_cpForm_btnSubmit\"]"));
	    	    saveButton.click();

	    	    Thread.sleep(3000);
	    	    driver.switchTo().alert().accept();
	    	    Thread.sleep(3000);
	    	    purchase.chassis_no(chassisNo);
	    		driver.switchTo().parentFrame();
	    		LogoutPage logout = new LogoutPage(driver);
	    		logout.clicklogoutButton();
	    		//driver.close();
	
		 
	}
}