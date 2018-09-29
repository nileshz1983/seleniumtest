package com.tk20.seleniumuiflipassessment.test.advisement;

import static com.tk20.seleniumuiflipassessment.base.DataProvider.OR;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.logger;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explicitWaitForElement;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.wait;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.tk20.seleniumuiflipassessment.base.Constants;

public class Advisement_KeywordEvents {

    /**
	 * Added By Kritika Date: 06/07/2015
	 * 		 * 
	 * This method is used to verify Date Time Format after splitting the String at "on"
	 * 
	 * @param object
	 *            : xpath of String containing date time.
	 * 
	 * @param data
	 *            : Pass Date-Time format as MM/dd/yyyy HH:mm:ss aaa
	 * */
	
	public String verifyDateTimeFormatByOn(String object, String data) {
		logger.debug("entered into verifyDateAndTimeFormatUsingSplit");

		String actual_pattern = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText();
		String values[] = actual_pattern.split("on");
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

}
