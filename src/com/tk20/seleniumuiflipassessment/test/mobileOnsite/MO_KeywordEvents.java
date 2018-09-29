package com.tk20.seleniumuiflipassessment.test.mobileOnsite;

import static com.tk20.seleniumuiflipassessment.base.DataProvider.logger;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.driver;

import com.tk20.seleniumuiflipassessment.base.Constants;

public class MO_KeywordEvents {
	/**Since 03/03/2016 Created By Pooja 
	 * This method is used to delete the cookies of Onsitetesting
	* @param object:None
	* @param data:None
	* @return
	*/



	 public String deleteCookies(String object, String data){
		logger.debug("Deleting Onsite Testing Cookies");
		try {
			driver.manage().deleteCookieNamed(data);
			return Constants.KEYWORD_PASS;
		} 
		catch (Exception e) {
			return Constants.KEYWORD_FAIL;
		}

		
		
	}
}
