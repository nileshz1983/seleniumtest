package com.tk20.seleniumuiflipassessment.test.administration;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.driver;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explicitWaitForElement;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explictWaitForElementList;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explictWaitForElementSize;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.wait;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.CONFIG;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.OR;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.tk20.seleniumuiflipassessment.base.Constants;

public class Admin_KeywordEvents {
	List<String> All_terms=new ArrayList<String>();
	
	/**Since 26/06/2015 Created By Pooja 
	 * Count the Number of Terms Present in the Course Registration> Terms
	 * @param object:common xpath of All the Rows
	 * @param data:
	 * @return
	 * Added code to check if pagination links exists on 08 July, 2015
	 */
	public String countTerms(String object, String data) {

		if(All_terms.size()!=0){
			All_terms=new ArrayList <String>();
		}
		try {
			List <WebElement> terms = explictWaitForElementList(object);
			for(int i=0;i<terms.size();i++){
				String s=terms.get(i).getText();
				All_terms.add(s);
			}
			while (true) {
				int size=explictWaitForElementSize("pagination_next_link");

				if (size == 0) {
					break;
				}

				if (size > 0) {
					Thread.sleep(1000);
					WebElement ele=	wait.until(explicitWaitForElement(By.xpath(OR.getProperty("pagination_next_link"))));    				    
					ele.click();
					Thread.sleep(2000);
					List <WebElement> term_count = explictWaitForElementList(object);
					for(int i=0;i<term_count.size();i++){
						String s=term_count.get(i).getText();
						All_terms.add(s);
					}
					break;
				}
			}

			return Constants.KEYWORD_PASS+"Term Count="+All_terms.size();
		} 
		catch(TimeoutException ex)
		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}

		catch (Exception nse) {

			return Constants.KEYWORD_FAIL + nse.getLocalizedMessage();// .printStackTrace();
		}


	}
	
	/**Since 26/06/2015 Created By Pooja 
	 * Verifies the Terms present excluded the one sent from Data.
	 * @param object:xpath of the drop down till option tag excluding Please Select Text
	 * @param data:Term to be excluded.
	 * @return
	 */
	//*Modified By Karan Sood on 16th December 2015/
	//* Removed Dependency on method countTerms /

	public String VerifyTerms(String object, String data) {
		boolean Flag=false;
		try {

			List<WebElement> Actual_term = explictWaitForElementList(object);
			
			for (WebElement option : Actual_term) {

				String actual = option.getText().trim();
				logger.debug("Actual > " + actual);
				if (actual.equals(data)) {
					Flag=true;
					break;
				}
			}
			
			if(!Flag)
				return Constants.KEYWORD_PASS + " Data Not Contains in Term Dropdown";
			else
				return Constants.KEYWORD_FAIL+" Data Contains in Term Dropdown";

		} 
		catch(TimeoutException ex)
		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}

		catch (Exception nse) {

			return Constants.KEYWORD_FAIL + nse.getLocalizedMessage();
		}


	}
    
	/* Created By Naincy Saini 
	 * Verifies sort of all drop-down elements except First.
	 * @param object:xpath of the drop down.
	 * @param data:not required
	 * @return
	 */
	
	
	public String verifyDropDownValuesSortedExceptFirst(String object, String data) {
		try {

			WebElement select = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object))));
			select.click();
			boolean flag = false;
			List<WebElement> options = select.findElements(By.tagName(OR.getProperty("OPTION_TAG")));
			List<String> actual_list = new ArrayList<String>();
			List<String> sortedlist = new ArrayList<String>();
			for (int i = 1; i < options.size(); i++) {
				String name = options.get(i).getText();
				actual_list.add(name);
				sortedlist.add(name);
			}
			Collections.sort(sortedlist, String.CASE_INSENSITIVE_ORDER);
			for (int i = 1; i < sortedlist.size(); i++) {
				if (sortedlist.get(i).equals(actual_list.get(i))) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
			if (flag)
				return Constants.KEYWORD_PASS + "--elements in drop down are in sorted order";
			else
				return Constants.KEYWORD_FAIL + "--elements are not in sorted order";
		} 
		catch(TimeoutException ex)
		
		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch (Exception e) {
		
			return Constants.KEYWORD_FAIL + e.getLocalizedMessage();
		}
	}
	/**Since 13th July'2015 Created By Kritika 
	 * Verifies the Date
	 * @param object:xpath of the element where date displays
	 * @param data:Date format
	 * @return
	 */

	public String verifyDateAndTimeFormUsingSplit(String object, String data) {
	        logger.debug("entered into verifyDateAndTimeFormatUsingSplit");

	        String actual_pattern = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText();
	        String values[] = actual_pattern.split("on");
	        String actualValue = values[1].trim().substring(0, 11);
	        SimpleDateFormat sdf = new SimpleDateFormat(data.trim());
	        boolean flag = false;
	        try {
	                sdf.setLenient(false);
	                sdf.parse(actualValue);
	                flag = true;
	        } catch (TimeoutException ex)

	        {
	                return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
	        } catch (Exception e) {
	                e.printStackTrace();
	                return Constants.KEYWORD_FAIL + " -- date is not in correct format";
	        }
	        if (flag) {
	                return Constants.KEYWORD_PASS + " -- date is in correct format";
	        } else {
	                return Constants.KEYWORD_FAIL
	                                + " -- date and time is not in correct format";

	        }
	}    
	/**Since 13th July'2015 Created By Kritika
	 * Verifies the Time Format
	 * @param object:xpath of the Element where Time displays
	 * @param data:Time format
	 * @return
	 */

	    public String verifyTimeFormatUsingSplit(String object, String data) {
	        logger.debug("entered into verifyDateAndTimeFormatUsingSplit");

	        String actual_pattern = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText();
	        String values[] = actual_pattern.split("at");
	        String actualValue = values[1].trim();
	        SimpleDateFormat sdf = new SimpleDateFormat(data.trim());
	        boolean flag = false;
	        try {
	                sdf.setLenient(false);
	                sdf.parse(actualValue);
	                flag = true;
	        } catch (TimeoutException ex)

	        {
	                return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
	        } catch (Exception e) {
	                e.printStackTrace();
	                return Constants.KEYWORD_FAIL + " -- date is not in correct format";
	        }
	        if (flag) {
	                return Constants.KEYWORD_PASS + " -- date is in correct format";
	        } else {
	                return Constants.KEYWORD_FAIL
	                                + " -- date and time is not in correct format";

	        }
		

	    }
	
	    /**
	     * This method generate PID Randomly 
	     * 
	     * @author Shweta Gupta
	     * @since 16 March, 2017
	     * @param object
	     *            : It Accepts Four xpath -- admin_pid_lnk_start,admin_pid_lnk_end,admin_com_user_pid,update_btn
	     *            First is --> Start xpath of the link (for e.g admin_pid_lnk_start=//tr[td[text()=')
	     *            Second is---> End xpath of the link (for e.g. admin_pid_lnk_end=']]//a)
	     *            Third is ---> xpath of the input box where pid is to be entered (for e.g. admin_com_user_pid)
	     *            Fourth is --->xpath of the update button (for e.g. update_btn)
	     *            	 
	     * @param data
	     *            : Data which completes the xpath of the name link of the user with the pid.
	     */

	    		public String changePidAndUpdate(String object, String data) {
	    	        logger.debug("changePidAndUpdate");
	    	        logger.debug("Data: " + data);
	    	        try {
	    	    		List<WebElement> element_List=null;

	    	        	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    				String objArr[] = object.split(Constants.Object_SPLIT);
	    				final String link_start = objArr[0];
	    				final String link_end = objArr[1];
	    				final String pid_input = objArr[2];
	    				final String update = objArr[3];
	    			
	    				element_List = driver.findElements(By.xpath(OR.getProperty(link_start) + data + OR.getProperty(link_end)));
	    				if(element_List.size()>0)
	    				{
	    					for(WebElement e : element_List)
		    				{
	    						logger.debug(e.getText());
		    				    e.click();
		    					String number = String.valueOf((long) ((Math.random() * (Constants.upper-Constants.lower)) + Constants.lower));
		  	    				driver.findElement(By.xpath(OR.getProperty(pid_input))).clear();
		  	    				driver.findElement(By.xpath(OR.getProperty(pid_input))).sendKeys(number);
		  	    				driver.findElement(By.xpath(OR.getProperty(update))).click();
		    					  
		    				}
	    	            } 
	    				else
	    				{
	    					return Constants.KEYWORD_PASS + " "+ "element is not present, proceed to next step ";
	    				}
	    	        } 
	    	catch(TimeoutException ex)
	    			
	    			{
	    				return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
	    			}
	    	        catch (Exception e) {
	    	       //     e.printStackTrace();
	    	        
	    	            return Constants.KEYWORD_FAIL + " Unable to write " + e.getMessage();
	    	        }
	    	        return Constants.KEYWORD_PASS + "--" + data;

	    	    }
	    
	    
	    
}
