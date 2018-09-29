package com.tk20.seleniumuiflipassessment.test.survey;

import static com.tk20.seleniumuiflipassessment.base.DataProvider.OR;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.logger;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.driver;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explicitWaitForElement;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.wait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.tk20.seleniumuiflipassessment.base.Constants;
import com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill;


public class Srvy_Create_KeywordEvents {

	/**
	 * Added By Pooja 27/03/2015
	 * This Method verifies the Expected Next Year.
	 * @param object
	 *            :xpath of the icon(>>).
	 * @param data
	 *            :Number of years to be added in the current Year.
	 * 
	 */

	public String verifyCalNextYear(String object, String data) {
		logger.debug("Verifying the Next Year");
		try {

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format_year = new SimpleDateFormat("yyyy");
			format_year.setTimeZone(TimeZone.getTimeZone("US/Central"));
			String year=format_year.format(calendar.getTime());
			int current_year=Integer.parseInt(year);
			int add=Integer.parseInt(data.trim());
			int next_year=current_year+add;
			String expected=String.valueOf(next_year);
			String actual =wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText().trim();

			logger.debug("Actual=" + actual);
			logger.debug("Next Year="+next_year);
			if((actual.trim()).equals(expected.trim())){
				return Constants.KEYWORD_PASS+"Current Year="+current_year + "Next Year="+next_year;
			}
			else
				return Constants.KEYWORD_FAIL + " -- text not verified--" + actual + " -- " + expected;

		} 
		catch(TimeoutException ex)

		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch (Exception e) {

			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}

	}
	/**
	 * Added By Pooja 27/03/2015
	 * This Method verifies the Expected Previous Year.
	 * @param object
	 *            :xpath of the icon(<<).
	 * @param data
	 *            :Number of years to be deducted in the current Year.
	 * 
	 */
	public String verifyCalPreviousYear(String object, String data) {
		logger.debug("Verifying the Previous Year");
		try {

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format_year = new SimpleDateFormat("yyyy");
			format_year.setTimeZone(TimeZone.getTimeZone("US/Central"));
			String year=format_year.format(calendar.getTime());
			int current_year=Integer.parseInt(year);
			int minus=Integer.parseInt(data.trim());
			int prev_year=current_year-minus;
			String expected=String.valueOf(prev_year);
			String actual =wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText().trim();

			logger.debug("Actual=" + actual);
			logger.debug("Previous Year="+prev_year);
			if((actual.trim()).equals(expected.trim())){
				return Constants.KEYWORD_PASS+"Current Year="+current_year + "Previous Year="+prev_year;
			}
			else
				return Constants.KEYWORD_FAIL + " -- text not verified--" + actual + " -- " + expected;

		} 
		catch(TimeoutException ex)

		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch (Exception e) {

			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}

	}

	/**
	 * Added By Pooja 27/03/2015
	 * This Method verifies the Expected Next Month.
	 * @param object
	 *            :xpath of the icon(>>).
	 * @param data
	 *            :Number of years to be added in the current Month.
	 * 
	 */

/*	public String verifyCalNextMonth(String object, String data) {
		logger.debug("Verifying the Next Month");
		try {

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format_month = new SimpleDateFormat("MMMM");
			format_month.setTimeZone(TimeZone.getTimeZone("US/Central"));
			java.util.Date date = new java.util.Date(calendar.getTimeInMillis());
			String current_month=format_month.format(date);
			int add=Integer.parseInt(data.trim());
			calendar.add(calendar.MONTH,+add);
			String expected=format_month.format(calendar.getTime());
			String months =wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText().trim();
			String actual[]=months.split(Constants.v);
			logger.debug("Actual=" + actual[0]);
			logger.debug("Next Month="+expected);
			if((actual[0].trim()).equals(expected.trim())){
				return Constants.KEYWORD_PASS+"Current Month="+actual[0] + "Next Month="+expected;
			}
			else
				return Constants.KEYWORD_FAIL + " -- text not verified--" + actual[0] + " -- " + expected;

		} 
		catch(TimeoutException ex)

		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch (Exception e) {

			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}

	}

	*//**
	 * Added By Pooja 27/03/2015
	 * This Method verifies the Expected Previous Month.
	 * @param object
	 *            :xpath of the icon(<<).
	 * @param data
	 *            :Number of years to be deducted from the current Month.
	 * 
	 *//*

	public String verifyCalPreviousMonth(String object, String data) {
		logger.debug("Verifying the Previous Month");
		try {

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format_month = new SimpleDateFormat("MMMM");
			format_month.setTimeZone(TimeZone.getTimeZone("US/Central"));
			java.util.Date date = new java.util.Date(calendar.getTimeInMillis());
			String current_month=format_month.format(date);
			int minus=Integer.parseInt(data.trim());
			calendar.add(calendar.MONTH,-minus);
			String expected=format_month.format(calendar.getTime());
			String month =wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText().trim();
			String actual[]=month.split(Constants.VALUE);
			logger.debug("Actual=" + actual[0]);
			logger.debug("Previous Month="+expected);
			if((actual[0].trim()).equals(expected.trim())){
				return Constants.KEYWORD_PASS+"Current Month="+actual[0] + "Previous Month="+expected;
			}
			else
				return Constants.KEYWORD_FAIL + " -- text not verified--" + actual[0] + " -- " + expected;

		} 
		catch(TimeoutException ex)

		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch (Exception e) {

			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}

	}


	/**
	 * Added By Naincy 27/03/2015
	 * This Method verifies the Current Month present.
	 * @param object
	 *            :xpath of the Location which is to be verified.
	 * @param data: NOne.
	 *            
	 * 
	 */

	public String verifyMonth(String object, String data) {
		logger.debug("Verifying the Current Month");
		try {

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format_month = new SimpleDateFormat("MMMM");
			format_month.setTimeZone(TimeZone.getTimeZone("US/Central"));
			java.util.Date date = new java.util.Date(calendar.getTimeInMillis());
			String current_month=format_month.format(date);
					
			String expected =wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText().trim();
			String expected_month[]=expected.split(Constants.VALUE);
			logger.debug("Actual=" + current_month);
			logger.debug("Expected="+expected_month[0]);
			if((expected_month[0].trim()).equals(current_month.trim())){
				return Constants.KEYWORD_PASS+"Current Month="+current_month + "Expected="+expected_month[0];
			}
			else
				return Constants.KEYWORD_FAIL + " -- text not verified--" + current_month + " -- " + expected_month[0];

		} 
		catch(TimeoutException ex)

		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch (Exception e) {

			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}

	}


	/**
	 * Added By Naincy Saini Date: 11/03/2016
	 * 		 * 
	 * This method is used to click Next link by counting all entries in a form on the basis of one question per page from 1st page to last page.
	 * 
	 * @param object
	 *            : Requires two xpaths
	 *            1st xpath of Page link in the bottom [i.e.Page 1 of 14].
	 *            2nd xpath of next_lnk.
	 *            
	 * 
	 * @param data
	 *            : No required.
	 * */

	 public String clickNextPage(String object, String data) {
         logger.debug("entered into clickNextPage");
         String objArr[]=object.split(Constants.Object_SPLIT);
         String actual_count = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(objArr[0])))).getText();
         String values[] = actual_count.split("of ");
         int actualValue = Integer.parseInt(values[1]);
         
        // boolean flag = false;
         try {
             
                 for(int i=0;i<actualValue-1;i++){
                         driver.findElement(By.xpath(OR.getProperty(objArr[1]))).click();
                         new KeywordEventsUtill().browserSpecificPause(object, data);
                         }
                 return Constants.KEYWORD_PASS + " -- navigates to next page";
              
         } 
         catch (TimeoutException ex)

         {
                 return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
         } catch (Exception e) {
                 e.printStackTrace();
                 return Constants.KEYWORD_FAIL + " -- link not clickable";
         }
       }

	 
	 /**
		 * Added By Naincy Saini Date: 11/03/2016
		 * 		 * 
		 * This method is used to click Previous link by counting all entries in a form on the basis of one question per page from last page to 1st page
		 * 
		 * @param object
		 *            : Requires two xpaths
		 *            1st xpath of Page link in the bottom [i.e.Page 14 of 14].
		 *            2nd xpath of prev_lnk.
		 *            
		 * 
		 * @param data
		 *            : No required.
		 * */
	 
	 
	 public String clickPreviousPage(String object, String data) {
         logger.debug("entered into clickPreviousPage");
         String objArr[]=object.split(Constants.Object_SPLIT);
         String actual_count = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(objArr[0])))).getText();
         String values[] = actual_count.split("of ");
         int actualValue = Integer.parseInt(values[1]);
         
        // boolean flag = false;
         try {
             
                 for(int i=actualValue;i>1;i--){
                         driver.findElement(By.xpath(OR.getProperty(objArr[1]))).click();
                         new KeywordEventsUtill().browserSpecificPause(object, data);
                         }
                 return Constants.KEYWORD_PASS + " -- navigates to previous page";
              
         } 
         catch (TimeoutException ex)

         {
                 return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
         } catch (Exception e) {
                 e.printStackTrace();
                 return Constants.KEYWORD_FAIL + " -- link not clickable";
         }
       }



}
