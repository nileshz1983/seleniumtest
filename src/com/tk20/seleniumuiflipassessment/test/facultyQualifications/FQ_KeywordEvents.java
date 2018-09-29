package com.tk20.seleniumuiflipassessment.test.facultyQualifications;

import static com.tk20.seleniumuiflipassessment.base.DataProvider.OR;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.logger;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.driver;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explicitWaitForElement;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.wait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.tk20.seleniumuiflipassessment.base.Constants;

public class FQ_KeywordEvents {

	/**
	 * This method Select all the text in the FCK Editer and clicks B,I,U from
	 * the rich text field <Br>
	 * 
	 * @author Surender
	 * @since 22 July, 2016
	 * 
	 * @param object
	 *            : It Accepts three xpaths. First Xpath is of FCk iframe,
	 *            Second is of Rich Text Formatting link <Br>
	 *            and third of B/I/U button. For Example :
	 *            fckeditor_iframe|fq_rich_txt_form_link|fq_bold_link
	 * 
	 * @param data
	 *            : Do not Required any Data.
	 * 
	 * @return <b>PASS</b> If Method select the text any click on the any Button
	 *         among B/I/U <br>
	 *         <b>FAIL</b> otherwise.
	 */

		public String selectAllAndClickFormatText(String object, String data)
			throws InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String objArr[] = object.split(Constants.DATA_SPLIT);
			String FRAME_XPATH = objArr[0];
			String FORMAT_TEXT_XPATH = objArr[1];
			String FONT_XPATH = objArr[2];
			WebElement frame = driver.findElement(By.xpath((OR
					.getProperty(FRAME_XPATH))));
			driver.switchTo().frame(frame);
			WebElement editable = driver.switchTo().activeElement();
			editable.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.switchTo().defaultContent();
			
			driver.switchTo().frame(wait.until(explicitWaitForElement((By.xpath(OR.getProperty("fq_iframe"))))));
			
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty(FORMAT_TEXT_XPATH)))
			.click();
			driver.findElement(By.xpath(OR.getProperty(FONT_XPATH))).click();
			Thread.sleep(1000);
			Set<String> availableWindows = driver.getWindowHandles();
			logger.debug("Handle Size:" + availableWindows.size());
			// Retreive all the window handles into variables
			String WindowIDparent = null;
			int counter = 1;
			for (String windowId : availableWindows) {
				if (counter == 1) {
					logger.debug(Integer.toString(counter) + " " + windowId);
					WindowIDparent = windowId;
				}
				counter++;
			}
			// Navigate to Parent window
			driver.switchTo().window(WindowIDparent);
			logger.debug("In the Parent");

		} catch (WebDriverException e) {
			return Constants.KEYWORD_FAIL + " - Could not enter the message "
					+ e.getMessage();
		}

		return Constants.KEYWORD_PASS;

	}	

}
