package Page_Object_Model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    WebDriver driver;
    
    By logoutButton = By.xpath("//input[@id=\"imgLogout\"]");
    
    public LogoutPage(WebDriver driver)
    {
    	this.driver = driver;
    } 
    
    public void clicklogoutButton()
    {
    	driver.findElement(logoutButton).click();
		System.out.println("Successfully loggedout");

    }
    
    
    
    

}
