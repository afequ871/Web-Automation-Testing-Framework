
package gov.va.sep.automatedtesting.suites;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Properties;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import gov.va.sep.automatedtesting.template.*;
import gov.va.sep.automatedtesting.utils.LogbackFileUtils;

public class Profile extends AutomatedTestingSuite {
	//private static StringBuffer verificationErrors = new StringBuffer();
	//public Properties propertyFile = getTestProperties("vet1");
	LoginTemplate loginTemp = new LoginTemplate(propertyFile);
	ProfileTemplate profileTemp = new ProfileTemplate(propertyFile);
	
	
	@Test
	public HashMap RunProfile() {
		try{
			strTime = System.currentTimeMillis();
			LogbackFileUtils.start(this.getClass());			
			logger.info(":: In Start of RunProfile() method");
			//r.delay(5000);
			logger.info("**********Start open application************");
			driver.get(getProperties().getProperty("URL"));
			logger.info("::getting the URL from the properties file :: The URL is ::"+getProperties().getProperty("URL"));
			r.delay(5000);
			
			//Login SEP
			LoginLogout.login(loginTemp);
			r.delay(5000);

			Profile.updateAddress(profileTemp);
			r.delay(5000);
			
		    // Go back to dashboard
		    //driver.switchTo().defaultContent();
		    //driver.findElement(By.id("dashboard")).click();	    
		    //r.delay(5000);
		    
		    //Logout
		    LoginLogout.logout();		    
		    LogbackFileUtils.stop();
		    
		    return returnObj;
		}catch(Throwable e){
			logger.error("Test Failure: " + e.toString());
			closeDriver();
			endTime = System.currentTimeMillis();
			buildTestStatusObj(true);
			LogbackFileUtils.stop();
			return returnObj;
		}	    
	}
	
	public static void updateAddress(ProfileTemplate profileTemp){
		driver.findElement(By.linkText("PERSONAL INFORMATION")).click();
		r.delay(5000);
	    driver.findElement(By.linkText("My Profile")).click();
	    r.delay(5000);
	    verify("Test -- Verify the Update Profile header label text",By.xpath("//div[@class='mhv-portlet-title']/h2"), profileTemp.getProfileHeaderLbl());
	    
	    driver.findElement(By.id("street1")).clear();
	    driver.findElement(By.id("street1")).sendKeys(profileTemp.getProfileStreet1());
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys(profileTemp.getProfileCity());
	    driver.findElement(By.id("postalCode")).clear();
	    driver.findElement(By.id("postalCode")).sendKeys(profileTemp.getProfilePostalCode());
	    new Select(driver.findElement(By.id("state"))).selectByVisibleText(profileTemp.getProfileState());
	    driver.findElement(By.cssSelector("input.mhv-input-button")).click();
	    r.delay(5000);
	    
	    if(driver.findElement(By.id("middleName")).getText().trim().isEmpty())
	    	driver.findElement(By.name("manageUserProfile_profilesactionOverride:saveNoMiddleNameAction")).click();
	    r.delay(3000);
	    verify("Test -- Verify update success",By.xpath("//div[@id='mhv-error-message-informational']"), profileTemp.getProfileUpdateSuccess());
	}
}//end of class.
