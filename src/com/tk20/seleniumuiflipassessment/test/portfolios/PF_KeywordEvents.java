package com.tk20.seleniumuiflipassessment.test.portfolios;
import com.tk20.seleniumuiflipassessment.util.ApplicationSpecificKeywordEventsUtil;

import static com.tk20.seleniumuiflipassessment.base.DataProvider.logger;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explictWaitForElementList;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explictWaitForElementUsingFluent;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.driver;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Date;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;



import org.openqa.selenium.TimeoutException;

import com.tk20.seleniumuiflipassessment.base.Constants;

public class PF_KeywordEvents {
	

	Map<String, String> dateTime = new HashMap<String, String>();  
    /**
	 * Added by Kritika on 22/06/2015 
	 * This method is used to match Column partial Value ie First Name(Yan, Aaron) with given value
	 * @param object: xpath of the column values
	 * @param data: Value with Which you want to match Column Values
	 */
	public String verifyColumnPartialFirstName(String object, String data) {
		try {

			logger.debug("Entered into verifyColumnValues()");

			List<WebElement> expected = explictWaitForElementList(object);
			expected = explictWaitForElementList(object);
			String data1=data.toLowerCase();
			for (int i = 0; i < expected.size(); i++) {
			    String last_val[]=expected.get(i).getText().trim().toLowerCase().split(",");
				if (last_val[1].contains(data1)) //modified on 13/05/2015 replace equal with contains.
				{
					logger.debug(last_val[1]+"--Present");
				}
				else
				{
					return Constants.KEYWORD_FAIL + "---"+ "Element not Matched";
				}
			}
			return Constants.KEYWORD_PASS + "--" + "All Elements Matched"; 
		} 
		catch (TimeoutException ex) 
		{
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} 
		catch (Exception e) 
		{

			return Constants.KEYWORD_FAIL + e.getMessage();
		}

	}
	/**
	 * Added by Kritika on 22/06/2015 
	 * This method is used to match Column partial Value ie Last Name(Yan, Aaron) with given value
	 * @param object: xpath of the column values
	 * @param data: Value with Which you want to match Column Values
	 */
	public String verifyColumnPartialLasttName(String object, String data) {
		try {

			logger.debug("Entered into verifyColumnValues()");

			List<WebElement> expected = explictWaitForElementList(object);

			expected = explictWaitForElementList(object);
			String data1=data.toLowerCase();
			for (int i = 0; i < expected.size(); i++) {
			    String last_val[]=expected.get(i).getText().trim().toLowerCase().split(",");
				if (last_val[0].contains(data1)) //modified on 13/05/2015 replace equal with contains.
				{
					logger.debug(last_val[0]+"--Present");
				}
				else
				{
					return Constants.KEYWORD_FAIL + "---"+ "Element not Matched";
				}
			}
			return Constants.KEYWORD_PASS + "--" + "All Elements Matched"; 
		} 
		catch (TimeoutException ex) 
		{
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} 
		catch (Exception e) 
		{

			return Constants.KEYWORD_FAIL + e.getMessage();
		}

	}
	
	/**
	 * This method clicks on an element whose xpath is given in the object 
	 * For ex:- For clicking on div,label,text,row etc.
	 * Added By Rashmi Mittal on 08/11/2016
	 * @param object=xpath of the button at which the date and time will be saved
	 * @param data=PASS data i.e label[Assignment,Project,Quiz,Exam,FieldExperience,Video,Artifact for matching the key]
	 * @return
	 */
	public String clickWebElementAndSaveDateTimeOneByOne(String object, String data) {
		logger.debug("Clicking on element");
		//boolean b;
		WebElement ele=null;
		//WebElement ele1=null;
		try {

				ele = explictWaitForElementUsingFluent(object);
				String st1=data;
				logger.debug(st1);
			    JavascriptExecutor executor = (JavascriptExecutor) driver;
			   executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			   	ele.click();
			
				Calendar calendar = Calendar.getInstance();
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm a");
		        dateFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
		        Date date= new Date();
		        String dateAndTime=dateFormat.format(date);
		       
		        dateTime.put(st1,dateAndTime);
					        			       
		        
				
		}
		catch(TimeoutException ex)
		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch(ElementNotVisibleException ex)
        {
        	if(new ApplicationSpecificKeywordEventsUtil().clickJs(ele).equals(Constants.KEYWORD_PASS))
				return Constants.KEYWORD_PASS;
		else
				return Constants.KEYWORD_FAIL;
        }
		catch(StaleElementReferenceException ex){
			ele.click();
		}
		catch(WebDriverException ex){
			try{
				String exceptionMessage=ex.getMessage();
					if(exceptionMessage.contains("Element is not clickable at point"))
					{
				if(new ApplicationSpecificKeywordEventsUtil().clickJs(ele).equals(Constants.KEYWORD_PASS))
							return Constants.KEYWORD_PASS;
					else
							return Constants.KEYWORD_FAIL;
					}
				else
						return Constants.KEYWORD_FAIL+"not able to Click"+ex.getMessage();
				}
				catch(Exception e){
				
					return Constants.KEYWORD_FAIL+e.getMessage();
				}
				
				} 
		catch (Exception e) {
		
			return Constants.KEYWORD_FAIL + " " + e.getMessage() + " Not able to click";
		}
		 return Constants.KEYWORD_PASS +"Clicked on Element and Saves date and time";
	}
	
	
	/**
	* This method is used to verify date and time saved in method clickWebElementAndSaveDateTimeOneByOne
	* Added By Rashmi Mittal on 9 Nov,2016
	* @param object : Xpath of Element coresponding to which will match the key 
	* @param data   : Not required
	*/
	
	public String verifySavedDateTimeOneByOne(String object, String data) {
		logger.debug("Clicking on element");
		WebElement ele=null;

		
		try {
			ele = explictWaitForElementUsingFluent(object);
			String ActualKey=ele.getText();

			for (Map.Entry<String, String> entry : dateTime.entrySet())
			{
				String key=entry.getKey();
				logger.debug(key);
				String value=entry.getValue();
				logger.debug(value);

			   	         
            	if(key.trim().contains(ActualKey.trim()))
				{     
           							 
            	   WebElement ele1=driver.findElement(By.xpath("//tr[contains(.,'"+key+"')]//a"));
	               ele1.click();
	               if(key.equalsIgnoreCase("FieldExperience"))
	               {
	            	   WebElement iframe =driver.findElement(By.xpath("//iframe[@id='fieldexperience']"));
	   			       driver.switchTo().frame(iframe);
	               }
	                String ele2=driver.findElement(By.xpath("//div[label[contains(.,'Date Created:')]]/following-sibling::div//p")).getText();
	                if(ele2.trim().equals(value));
	                {
	                	 driver.switchTo().defaultContent();
                        		                	
	                	return Constants.KEYWORD_PASS +"Matched with saved date and time";
	                
	                }
	               
				} }
            	
			
        	return Constants.KEYWORD_FAIL +"Not Matched with saved date and time";
       
		}
		
			
		catch(TimeoutException ex)
		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch (Exception e) {
		
			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}
	}

	
	
	
	
}
