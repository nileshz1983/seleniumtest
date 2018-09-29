

package com.tk20.seleniumuiflipassessment.test.accreditationManagement;

import static com.tk20.seleniumuiflipassessment.base.DataProvider.CONFIG;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.OR;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.logger;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.driver;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explicitWaitForAlert;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explicitWaitForElement;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explictWaitForElementList;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explictWaitForElementSize;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.explictWaitForElementUsingFluent;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.wait;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.waitForPageLoad;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfAnnotation.PdfImportedLink;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.tk20.seleniumuiflipassessment.base.Constants;
import com.tk20.seleniumuiflipassessment.util.ApplicationSpecificKeywordEventsUtil;
import com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill;


public class AM_KeywordEvents {
	String randomUsername = null;
	String	firstUsername="";
	String postTime=null;

	String dirName=System.getProperty("user.dir")+File.separator+ "externalFiles" + File.separator + "downloadFiles";
	KeywordEventsUtill keyUtill = (KeywordEventsUtill) KeywordEventsUtill.keyUtillFactory();
	List<String> resultList=new ArrayList<String>();
	String Folder_File=null;
	int Hcount=0;
	private static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";


	/**
	 * This Method used to move on the specific window .
	 * 
	 * @author Pankaj Sharma
	 * @since 2 April, 2014
	 * 
	 * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            : It accepts the no of window that you want to move.
	 * 
	 * @return <b>PASS</b> if successfully moved to specified window.
	 *         <b>FAIL</b> otherwise.
	 */
	public String moveToSpecificWindow(String object, String data) throws InterruptedException {
		logger.debug("Move to Specific Windows......");
		try {
			// validate the parameters
			if (data == null) {
				return Constants.KEYWORD_FAIL + "Specific Window number is empty";
			}
			int switchToWindow = Integer.parseInt(data);
			String newWindow = "";
			Set<String> windowids = driver.getWindowHandles();
			Iterator<String> ite = windowids.iterator();
			for (int i = 0; i < switchToWindow; i++) {
				newWindow = ite.next();
			}
			driver.switchTo().window(newWindow);
			return Constants.KEYWORD_PASS;
		} catch (NoSuchElementException nse) {

			return Constants.KEYWORD_FAIL + nse.getLocalizedMessage();
		}
	}

	/**
	 * This Method used to verify current window URL
	 * 
	 * @author Pankaj Sharma
	 * @since 2 April, 2014
	 * 
	 * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            : Expected URL.
	 * 
	 * @return <b>PASS</b> if successfully moved to specified window.
	 *         <b>FAIL</b> otherwise.
	 */
	public String verifyCurrentWindowURL(String object, String data) throws InterruptedException {
		logger.debug("Move to Specific Windows......");
		try {
			// validate the parameters
			if (data == null) {
				return Constants.KEYWORD_FAIL + " Data is Emplty";
			}
			String currentWindowUrl = driver.getCurrentUrl();
			logger.debug("Actual URL : " + currentWindowUrl.trim());
			logger.debug("Expected URL : " + data.trim());
			driver.close();
			if (currentWindowUrl.contains(data.trim())) {
				return Constants.KEYWORD_PASS + "Actual URL contains the Expected URL";
			} else {
				return Constants.KEYWORD_FAIL + "Actual URL do not contains the Expected URL";
			}

		} catch (Exception nse) {

			return Constants.KEYWORD_FAIL + nse.getLocalizedMessage();
		}
	}

	/**
	 * This Method is used to verify count of windows opened.
	 * 
	 * @author Surender
	 * @since 04/04/2014
	 * 
	 * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            : It accepts the no of windows opened count.
	 * 
	 * @return <b>PASS</b> if count matched successfully. <b>FAIL</b> otherwise.
	 */
	public String verifyCountOfWindow(String object, String data) throws InterruptedException {
		logger.debug("entered into verifyCountOfWindow()");
		try {
			// validate the parameters
			if (data == null) {
				return Constants.KEYWORD_FAIL + " count of windows is empty";
			}
			int windowCount = Integer.parseInt(data);
			int count = driver.getWindowHandles().size();
			logger.debug("count=" + count);
			if (count == windowCount)
				return Constants.KEYWORD_PASS;
			else {
				return Constants.KEYWORD_FAIL;
			}
		} catch (Exception e) {

			return Constants.KEYWORD_FAIL + e.getLocalizedMessage();
		}
	}
	/**
	 * Added By Mayank Saini This Method is used to accept the ssl certificates.
	 * 
	 */
	public void SSLcetificate() {
		TrustManager[] trustAllCertificates = new TrustManager[] { new X509TrustManager() {
			 
			public X509Certificate[] getAcceptedIssuers() {

				return null;
			}
			 
			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

			}
			 
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}
		}
		};
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCertificates, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (GeneralSecurityException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 11/04/2014
	 * 
	 * Added By Mayank Saini This Method is used to extract text from PDF file
	 * 
	 * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            :It accepts two parameter with comma separated. 1)Particular
	 *            Page number that you want to access 2)Contents of Particular
	 *            page
	 * @throws IOException
	 */
	public String verifyTextInPDF(String object, String data) throws IOException {
		logger.debug("Verifying PDF text File");
		PDDocument pd = null;
		try {
			String temp[] = data.split(",");
			int page = Integer.parseInt(temp[0]);
			URL url = new URL(driver.getCurrentUrl());
			// to accept the ssl certificate as url class works on http protocol
			// only
			SSLcetificate();

			// open the connection with specified url ang get the raw data from
			// inputstream
			InputStream in = url.openStream();
			BufferedInputStream filetoParse = new BufferedInputStream(in);
			// pddocument is just like a pdf file and load method is to assign a
			// PDF file to the PDDocument class
			pd = PDDocument.load(filetoParse);
			// pdftextstripper is used to read and write the pdf file
			// This class will take a pdf document and strip out all of the text
			// and ignore the formatting and such.

			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(page);
			stripper.setEndPage(page);
			stripper.getPageEnd();
			String actualText = stripper.getText(pd);

			if (page > pd.getNumberOfPages()) {
				throw new ArrayIndexOutOfBoundsException();
			}

			if (actualText.contains(temp[1]))
				return Constants.KEYWORD_PASS + "PDF file contains text on mentioned" + page;
			else
				return Constants.KEYWORD_FAIL + "PDF file doesn't contains text on mentioned" + page;
		} catch (ArrayIndexOutOfBoundsException ex) {
			return Constants.KEYWORD_FAIL + "Page Number is not valid";
		} catch (Exception ex) {
			return Constants.KEYWORD_FAIL + ex.getMessage();
		} finally {
			pd.close();
		}

	}

	/**
	 * @since 08/April/14
	 * @author Mayank Saini This method will Pass even if there is no Text box
	 *         displaying to write data in a 2 operation scenario like in 1st
	 *         scenario textbox is displayed and in 2nd scenario textbox is not
	 *         displaying but is present there
	 */

	public String enterInputIfPresent(String object, String data) {
		logger.debug("Writing in text box");
		logger.debug("Data: " + data);
		try {

			boolean textBoxPresent = false;
			WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			textBoxPresent = element.isDisplayed();
			if (textBoxPresent == true) {

				element.clear();
				element.sendKeys(data);
				return Constants.KEYWORD_PASS + "--" + "Textbox Found" + data;
			} else {
				return Constants.KEYWORD_PASS + " " + "Input Textbox not found becasue user has selected some other operation";
			}

		} catch (NoSuchElementException e) {

			return Constants.KEYWORD_PASS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
		}
	}
	/**
	 * @since 08/April/14
	 * @author Mayank Saini This method will Pass even if there is no Text box
	 *         displaying to write data in a 2 operation scenario like in 1st
	 *         scenario textbox is displayed and in 2nd scenario textbox is not
	 *         displaying but is present there
	 */

	public String clickIfPrsesent(String object, String data) {
		logger.debug("Writing in text box");
		logger.debug("Data: " + data);
		try {
			boolean elementPresent = false;
			WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			elementPresent = element.isDisplayed();
			if (elementPresent == true) {
				driver.findElement(By.xpath(OR.getProperty(object))).click();
				return Constants.KEYWORD_PASS + " " + "Clicking button displaying";
			} else {
				return Constants.KEYWORD_PASS + " " + "button not displaying because user has selected other scenario ";
			}
		} catch (NoSuchElementException e) {

			return Constants.KEYWORD_PASS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
		}
	}
	/**
	 * @since 08/April/2014
	 * @author Mayank Saini 
	 * This method will click on uploaded file until the file gets deleted
	 */

	public String clickFile(String object, String data) {
		logger.debug("Deleting File if exists");
		try {
			String temp[] = object.split(",");
			while (true) {
				String result = new KeywordEventsUtill().isWebElementPresent(temp[0], data);
				if (result.startsWith(Constants.KEYWORD_PASS)) {
					new KeywordEventsUtill().clickLink(temp[0], data);
					new KeywordEventsUtill().clickButton(temp[1], data);
					new KeywordEventsUtill().isInConsistentAlert(object, data);
				} else
					return Constants.KEYWORD_PASS + "All Files are deleted";
			}

		} 
		catch (TimeoutException ex) {

			return Constants.KEYWORD_FAIL + ex.getCause();
		}
		catch (Exception ex) {

			return Constants.KEYWORD_FAIL + ex.getMessage();
		}

	}

	/**
	 * This Method used to create random data,if alert opens. Use only at the
	 * place where alert opens, due to similar data existance.
	 * 
	 * @author Sanjay Sharma
	 * @since 2 April, 2014
	 * @param object
	 *            :a)part of alert message (static) b)xpath of input box, where
	 *            you want to enter data. c)xpath of create site button
	 * @param data
	 *            : existing user name for input.
	 * @return <b>PASS</b> if random data created <b>FAIL</b> otherwise.
	 */
	public String renameIfAlertOpen(String object, String data) throws InterruptedException {
		try {
			String[] objArr = object.split(Constants.DATA_SPLIT);
			String EXPECTED_ALERT_MSG_TEXT = OR.getProperty(objArr[0]);
			String INPUT_FIELD_XPATH = OR.getProperty(objArr[1]);
			String BUTTON_XPATH = OR.getProperty(objArr[2]);

			// validating the arguments

			if (EXPECTED_ALERT_MSG_TEXT == null || EXPECTED_ALERT_MSG_TEXT.equals("")) {
				return Constants.KEYWORD_FAIL + "--\"" + objArr[0] + "\" is not a valid value Please check in property file";
			}
			if (INPUT_FIELD_XPATH == null || INPUT_FIELD_XPATH.equals("")) {
				return Constants.KEYWORD_FAIL + "--\"" + objArr[1] + "\" is not a valid xpath Please check in property file";
			}
			if (BUTTON_XPATH == null || BUTTON_XPATH.equals("")) {
				return Constants.KEYWORD_FAIL + "--\"" + objArr[2] + "\" is not a valid xpath please check in property file";
			}

			String randomNum = KeywordEventsUtill.createRandomNum();

			Alert alert = explicitWaitForAlert();
			String actual = alert.getText().trim();

			if (actual.contains(EXPECTED_ALERT_MSG_TEXT)) {
				alert.accept();
				randomUsername = data.concat(randomNum);

				WebElement ele = wait.until(explicitWaitForElement(By.xpath(INPUT_FIELD_XPATH)));
				ele.clear();
				((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", ele, randomUsername);
				logger.debug("Entered " + randomUsername + " into the text field");
				WebElement ele2 = wait.until(explicitWaitForElement(By.xpath(BUTTON_XPATH)));
				ele2.click();
				logger.debug("Clicked on " + objArr[2] + "");

				return Constants.KEYWORD_PASS + "--Entered \"" + randomUsername + "\" to the input field";

			} else {
				return Constants.KEYWORD_FAIL;
			}
		} catch (TimeoutException ex) {
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} catch (NoSuchElementException nse) {

			return Constants.KEYWORD_FAIL + nse.getLocalizedMessage();// .printStackTrace();
		}
	}

	/**
	 * This Method used to write randomly created data from method
	 * "renameIfAlertOpen".
	 * 
	 * @author Sanjay Sharma
	 * @since 2 April, 2014
	 * @param object
	 *            : xpath of input textbox.
	 * @param data
	 *            : none
	 * @return PASS FAIL
	 */
	public String writeRecentCreatedData(String object, String data) {
		logger.debug("Writing in input box");
		logger.debug("Recent created Data: " + randomUsername);
		try {
			String browser = CONFIG.getProperty("browserType");
			WebElement ele = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object))));
			ele.clear();

			((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", ele, randomUsername);

			if (browser.equals("IE")) {

				Thread.sleep(3000);
			}

		} catch (TimeoutException ex) {
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} catch (Exception e) {

			return Constants.KEYWORD_FAIL + " Unable to write " + e.getMessage();
		}
		return Constants.KEYWORD_PASS + "--" + randomUsername;

	}

	/**
	 * 6 March,2014 Sandeep Dhamija. This method is used to verify partial name
	 * in instant search
	 */

	public String verifyPartialNameInstantSearch(String object, String data) {
		logger.debug("inside verifyPartialNameInstantSearch");
		try {

			List<WebElement> l1 = explictWaitForElementList(object);
			String input = data;
			for (int i = 0; i < l1.size(); i++) {
				String result = l1.get(i).getText();
				if (result.toLowerCase().contains(input.toLowerCase())) {
					continue;
				} else {
					return Constants.KEYWORD_FAIL + "--elements in auto search do not contains the input";
				}
			}

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + e.getLocalizedMessage();
		}
		return Constants.KEYWORD_PASS + "--All elements in auto search contain the input";
	}

	/**
	 * open browser window
	 * 
	 * @return
	 */
	public static String openBrowserAfterClosing(String object, String data) {
		logger.debug("open browser");

		try {
			new KeywordEventsUtill().openBrowser(object, data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.KEYWORD_PASS;
	}
	/**
	 * Make driver instance null
	 * 
	 * @return
	 */
	public static String nullDriver(String object, String data) {
		logger.debug("make driver's instance null");

		try {
			driver = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.KEYWORD_PASS;
	}
	/**
	 * Sandeep Dhamija. 14 August, 2013 Move to a newly opened window
	 * 
	 * @param object
	 * @param data
	 * @return
	 */
	public String moveToPreviousWindow(String object, String data) throws InterruptedException {
		logger.debug("move to new window....");
		try {
			String mainwindow = "";
			String newwindow = "";
			Set<String> windowids = driver.getWindowHandles();
			Iterator<String> ite = windowids.iterator();
			mainwindow = ite.next();
			driver.switchTo().window(mainwindow);
		} catch (NoSuchElementException nse) {
			return Constants.KEYWORD_FAIL + nse.getLocalizedMessage();
		}
		return Constants.KEYWORD_PASS;
	}
	/**
	 * This Method used to Verify Scroll bar position in HTML Page
	 * 
	 * @author Pankaj Sharma
	 * @since 18 April, 2014
	 * 
	 * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            : It Accepts two values that are separated by "|".First is of
	 *            page no to verify and total no of pages in the file.
	 * 
	 * @return <b>PASS</b> if the scroll bar is on the specified page
	 *         <b>FAIL</b> otherwise.
	 */
	public String verifyScrollbarPosition(String object, String data) {
		try {
			driver.manage().window().maximize();
			// validates parameter
			if (data.split(Constants.DATA_SPLIT)[0].equals(null) & data.split(Constants.DATA_SPLIT)[1].equals(null)) {
				return Constants.KEYWORD_FAIL + " You must have not enter either page to no verify or total no of pages ";
			}
			long actualScrollPositionY = (Long) ((JavascriptExecutor) driver).executeScript("var y = window.pageYOffset; return y; ");
			long lastPosition;
			long latestPosition;
			while (true) {
				lastPosition = (Long) ((JavascriptExecutor) driver).executeScript("var y = window.pageYOffset; return y; ");
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)", "");
				latestPosition = (Long) ((JavascriptExecutor) driver).executeScript("var y = window.pageYOffset; return y; ");
				if (lastPosition == latestPosition)
					break;
			}
			long maximumScrollableHeight = lastPosition;
			int verifyPage = Integer.parseInt(data.split(Constants.DATA_SPLIT)[0]) - 1;
			int totalPages = Integer.parseInt(data.split(Constants.DATA_SPLIT)[1]);
			long startRange = 0;
			long endRange = 0;
			for (int i = 0; i < totalPages; i++) {
				if (i == verifyPage) {
					startRange = (maximumScrollableHeight / totalPages) * i;
					endRange = (maximumScrollableHeight / totalPages) * (i + 1);
					if (actualScrollPositionY >= startRange & actualScrollPositionY <= endRange)
						return Constants.KEYWORD_PASS + "The Scroll Bar is at " + (i + 1) + "Page";
					else
						return Constants.KEYWORD_FAIL + "The Scroll Bar is not at " + (i + 1) + "Page";
				}
			}
			return Constants.KEYWORD_FAIL + " You must have not enter wrong  page no to verify or total no of pages ";
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + e.getMessage();
		}
	}

	/**
	 * Created by Pallavi Singla Method verifies the file is opened in pdf
	 * format.
	 * 
	 * @param object
	 * @param data
	 * @return
	 */

	public String verifyPDFformat(String object, String data) {
		logger.debug("Verify pdf format of file");
		try {

			String url = driver.getTitle();
			if (url.contains("pdf")) {
				return Constants.KEYWORD_PASS + "--" + "File is in pdf format.";
			}
		} catch (Exception e) {

			return Constants.KEYWORD_FAIL + e.getLocalizedMessage();
		}
		return Constants.KEYWORD_FAIL + "--" + data;
	}

	/**
	 * This method is used to press TAB key from keyboard
	 * 
	 * @author Sumit Aggarwal
	 * 
	 * @param object
	 *            : Not Reqiured
	 * 
	 * @param data
	 *            : Not Reqiured
	 *            
	 * @since 10 March, 2014
	 */
	public String pressTabFromKeyboard(String object, String data) {
		logger.debug("entered into pressKeyFromKeyboard");
		try {
			Actions act = new Actions(driver);
			Action pressTabKey = act.sendKeys(Keys.TAB).build();
			pressTabKey.perform();
			return Constants.KEYWORD_PASS + "--" + "Tab key pressed.";
		} 
		catch (Exception e) {
			return Constants.KEYWORD_FAIL + e.getMessage();
		}
	}

	/*
	 * Created By Sumit Aggarwal 10/10/2013 This method is used to verify the
	 * focus in input field.
	 */
	public String verifyFocusOnEditableField(String object, String data) {
		logger.debug("Writing in text box");

		try {
			WebElement element = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object))));
			if (element.equals(driver.switchTo().activeElement())) {
				return Constants.KEYWORD_PASS+ "--" + "focus on required field";
			} else
				return Constants.KEYWORD_FAIL+ "--" + "focus is not on required field";
		} 
		catch (TimeoutException ex) {
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} 
		catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
		}
	}

	/**
	 * This Method used to Verify that focus in not on element
	 * 
	 * @author Sumit Aggarwal
	 * @since 01 May, 2014
	 * 
	 * @param object
	 *            : Element xpath to be passed
	 * 
	 * @param data
	 *            : Not Reqiured
	 * 
	 * @return <b>PASS</b> if the focus is not on element
	 *         <b>FAIL</b> otherwise.
	 */
	public String verifyFocusNotOnElement(String object, String data) {
		logger.debug("Writing in text box");

		try {
			WebElement element = wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object))));
			if (!(element.equals(driver.switchTo().activeElement()))) {
				return Constants.KEYWORD_PASS+ "--" + "focus not on element";
			} else
				return Constants.KEYWORD_FAIL+ "--" + "focus is on element";
		}
		catch (TimeoutException ex) {
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} 
		catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
		}
	}

	/**
	 * 09/05/2014
	 * 
	 * Added By Pooja Sharma This Method is used to extract text from PDF file 
	 * and verifies that particulat content is not present in the PDF file.
	 * 
	 * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            :It accepts two parameter with comma separated. 1)Particular
	 *            Page number that you want to access 2)Contents on Particular
	 *            page that is to be verified as not present.
	 * @throws IOException
	 */


	public String verifyTextNotInPDF(String object, String data) throws IOException {
		logger.debug("Verifying PDF text File");
		PDDocument pd = null;
		try {
			String temp[] = data.split(",");
			int page = Integer.parseInt(temp[0]);
			URL url = new URL(driver.getCurrentUrl());
			// to accept the ssl certificate as url class works on http protocol
			// only
			SSLcetificate();

			// open the connection with specified url ang get the raw data from
			// inputstream
			InputStream in = url.openStream();
			BufferedInputStream filetoParse = new BufferedInputStream(in);
			// pddocument is just like a pdf file and load method is to assign a
			// PDF file to the PDDocument class
			pd = PDDocument.load(filetoParse);
			// pdftextstripper is used to read and write the pdf file
			// This class will take a pdf document and strip out all of the text
			// and ignore the formatting and such.

			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(page);
			stripper.setEndPage(page);
			stripper.getPageEnd();
			String actualText = stripper.getText(pd);

			if (page > pd.getNumberOfPages()) {
				throw new ArrayIndexOutOfBoundsException();
			}

			if (actualText.contains(temp[1]))
				return Constants.KEYWORD_FAIL + "PDF file contains text on mentioned page:-" + page;

			else
				return Constants.KEYWORD_PASS + "PDF file doesn't contains text on mentioned page:-" + page;
		} catch (ArrayIndexOutOfBoundsException ex) {
			return Constants.KEYWORD_FAIL + "Page Number is not valid";
		} catch (Exception ex) {
			return Constants.KEYWORD_FAIL + ex.getMessage();
		} finally {
			pd.close();
		}

	}

	/**
	 * Added by Vikas Bhadwal On 05/05/2014 
	 * This method is used to get date and time of Post(Comment/Reply) in
	 * (MM/dd/yyyy hh:mm a) Format in US/Central Time Zone.
	 * 
	 * @param object:None
	 *            
	 * @param data:None
	 *            
	 * 
	 */

	public String getPostDateTime(String object, String data) {
		logger.debug("Getting the past date and time");
		try {

			DateFormat DtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date date = new Date();
			DtFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
			postTime= DtFormat.format(date).toString().trim();
			logger.debug("post time  " + postTime);
			return Constants.KEYWORD_PASS + "--post time  " + postTime;
		} 

		catch (TimeoutException ex) {
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} 
		catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}
	}

	/**
	 * Added by Vikas Bhadwal On 05/05/2014 
	 * This method is used to verify Post(Comment/Reply) date and time in
	 * (MM/dd/yyyy hh:mm a) Format according to US/Central Time Zone.
	 * 
	 * @param object::This method accepts One xpath,i.e location where the date and time is
	 *            displayed.
	 *            
	 * @param data:None
	 *            
	 * 
	 */
	public String verifyPostDateTime(String object, String data) {
		logger.debug("Verifying the Post date and time");
		try {
			String actual =wait.until(explicitWaitForElement(By.xpath(OR.getProperty(object)))).getText().trim();
			logger.debug("expected" + postTime);
			logger.debug("act" + actual);
			if (actual.trim().equals(postTime))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " -- actual:  " + actual + " -- post date and time  = " + postTime + " ";
		}
		catch (TimeoutException ex) {
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		}  


		catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}
	}


	/**
	 * Added by Vikas Bhadwal On 06/05/2014 
	 * This method Get the first user name in instant searched list.
	 *
	 * @param object::This method accepts One xpath,i.e xapth of instant search list
	 *            
	 * @param data:None
	 *            
	 * 
	 */

	public String saveFirstInstantSearchName(String object, String data)throws Exception {
		logger.debug("Entered into saveFirstInstantSearchName()");
		try {

			if(firstUsername.length()!=0){
				firstUsername="";
			}
			List<WebElement> allNames= explictWaitForElementList(object);
			for (WebElement webElement : allNames) {
				firstUsername=webElement.getText().trim();
				logger.debug("firstUsername:-"+firstUsername);
				break;
			}
			return Constants.KEYWORD_PASS + " -- "+firstUsername+" has been saved";
		} 

		catch (TimeoutException ex) {
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} 

		catch (Exception e) {
			return Constants.KEYWORD_FAIL + e.getLocalizedMessage();
		}

	}



	/**
	 * Added by Vikas Bhadwal On 06/05/2014 
	 * This method verify that first user name in instant search get selected.
	 *
	 * @param object:: It accept one xpath that is divided into two parts and both
	 *            parts<Br>
	 *            are given as object parameter that are splitted by ","
	 *            
	 * @param data:None
	 *            
	 * 
	 */
	public String verifyFirstSearchedUsername(String object, String data)throws Exception {
		logger.debug("Entered into verifyFirstSearchedUsername()");
		try {
			String arrayString[] = firstUsername.split(" ");
			String displayedName=arrayString[0]+ " " + arrayString[1];

			String result=new ApplicationSpecificKeywordEventsUtil().isWebElementPresentUsingData(object, displayedName);   

			if(result.contains(Constants.KEYWORD_PASS)){
				return Constants.KEYWORD_PASS + " -- "+displayedName+" present";
			}
			else{
				return Constants.KEYWORD_FAIL + " -- "+displayedName+" not present";
			}
		} 
		catch (TimeoutException ex) {
			return Constants.KEYWORD_FAIL + "Cause: " + ex.getCause();
		} 

		catch (Exception e) {
			return Constants.KEYWORD_FAIL + e.getLocalizedMessage();
		}
	}


	/**
	 * Added by Karan Sood On 07/05/2014 
	 * This method enters Ck Editor frame, finds the element there, and moves out to default frame.
	 *
	 * @param object:: It accept two xpaths splitted by "," first xpath is of the Ck editor frame and second for the 
	 *                 element 
	 *            
	 * @param data:None
	 *            
	 * 
	 */    
	public String isWebElementPresentInFCKeditor(String object, String data) throws InterruptedException

	{
		try {

			String[] element = object.split(Constants.Object_SPLIT);
			String frame = element[0];
			String elementFind = element[1];

			WebElement ckEditor = wait.until(explicitWaitForElement((By.xpath(OR.getProperty(frame)))));
			driver.switchTo().frame(ckEditor);
			logger.debug("Moved to Ck Editor Frame");
	
				int ele = explictWaitForElementSize(elementFind);
				if (ele == 0) {
					logger.debug("webElement NOT present.." + ele);
					return Constants.KEYWORD_FAIL + " -- No webElement present";
				} else
					logger.debug("webElement Present.." + ele);
					driver.switchTo().defaultContent();
					logger.debug("Moved to Default Frame");
				    Thread.sleep(3000);
					return Constants.KEYWORD_PASS + " -- webElement present -- ";
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
	 * This Method used to select complete text in active element
	 * 
	 * @author Sumit Aggarwal
	 * @since 23 May, 2014
	 * 
	 * @param object
	 *            : Not Reqiured
	 * 
	 * @param data
	 *            : Not Reqiured
	 * 
	 * @return <b>PASS</b> if text is selected
	 *         <b>FAIL</b> otherwise.
	 */
 
 	public String selectCompleteText(String object, String data)

	{
		try {
			WebElement editable = driver.switchTo().activeElement();
			editable.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		}
		catch(TimeoutException ex)
		{
			return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
		}
		catch (Exception e) 
		{
			return Constants.KEYWORD_FAIL + " Object not found " + e.getMessage();
		}
		return Constants.KEYWORD_PASS + " Text is selected";
	}
 	

 	/**
	 * This Method used to verify only single file is present in folder
	 * 
	 * @author Sumit Aggarwal
	 * @since 26 May, 2014
	 * 
	 * @param object
	 *            : Reqiured (path where file exists after downloadFiles 
	 *              i.e "Evidence_New Report\\Level2.1")
	 * 
	 * @param data
	 *            : Required (file name to be verified i.e "New Report.pdf")
	 * 
	 * @return <b>PASS</b> if single file exist
	 *         <b>FAIL</b> otherwise.
	 */
 	
 	public String checkSingleFileExistance(String object,String data){
	
 		try
 		{		
 			int temp;
 			if(object.equals(""))
 				object=dirName;
 			else
 				object=dirName+File.separator+object;	
 			File folderDir=new File(object);
 			File[] allFiles=folderDir.listFiles();
 			temp=allFiles.length;
 			for(int i=0;i<allFiles.length;i++)
 			{
				if(allFiles[i].getName().contains(".zip"));
				{
					temp=temp-1;
					break;
				}
 			}

 			if(new File(object+File.separator+data).exists() && temp==1)
 			{
 				return Constants.KEYWORD_PASS+"Single File Found in Parent Folder";
 			}
 			else
 			{
 				return Constants.KEYWORD_FAIL+" Multiple files and folder present";
 			}
 		}

 		catch(Exception ex)
 		{
 			return Constants.KEYWORD_FAIL+ex.getMessage();
 		}
 	}

 	
 	
 	/**Added By Sohal Bansal 
 	  * 27/05/2014
 	  * This method will check the text and verify that file is not in html format
 	  * @throws IOException 
 	  * */
 	 public String verifyHTMLTagNotPresentInDoc(String object,String data) throws IOException{
 	 	FileInputStream fis=null;
 	 	try{
 	 		Boolean flag=false;
 	 		String temp[]=data.split(",");
 	 		String path=dirName+File.separator+temp[0];
 	 		File file=new File(path);
 	 		fis=new FileInputStream(file.getAbsolutePath());
 	 		 ArrayList<String> s=new ArrayList<String>();
 	         XWPFDocument doc = new XWPFDocument(fis);
 	         XWPFParagraph[] p=  doc.getParagraphs();
 	         int i= p.length;
 	         int x=1;
 	         for(int j=0;j<i;j++){
 	        	  String st= p[j].getParagraphText();
 	        	  s.add(st);
 	        	  logger.debug("actual:"+st);
 	         }
 	         for(int j=0;j<i;j++){
 	      	  String st= p[j].getParagraphText();
 	      	  if(x<temp.length && st.trim().equals(temp[x]))
 	      	  {
 	      		  x++;
 	      		 logger.debug("text Found");	
 	  			if(st.matches(HTML_A_TAG_PATTERN))
 	  			{
 	  				flag=false;
 	  				break;
 	  			}
 	  			else
 	  				flag=true;
 	      	  }
 	         }
 	      	  if(flag)
 	      		  return Constants.KEYWORD_PASS+"--text is not in HTML format";
 	      	  else
 	      		  return Constants.KEYWORD_FAIL+"--text is in HTML format";
 	         }
 	 	
 	 	catch(Exception ex){
 	 		return Constants.KEYWORD_FAIL+ex.getMessage();
 	 	}
 	 	finally{
 	     	fis.close();
 	     }  
 	 }
 	 		
 	 /**Added By Sohal Bansal 
 	  * 02/05/2014
 	  * This method will check that the particular text is not present in doc file
 	  * @throws IOException 
 	  * */
 	 public String verifyTextNotPresentInDoc(String object,String data) throws IOException{
 	 	FileInputStream fis=null;
 	 	try{
 	 		Boolean flag=false;
 	 		String temp[]=data.split(",");
 	 		String path=dirName+File.separator+temp[0];
 	 		File file=new File(path);
 	 		fis=new FileInputStream(file.getAbsolutePath());
 	 		 
 	         XWPFDocument doc = new XWPFDocument(fis);
 	         XWPFParagraph[] p=  doc.getParagraphs();
 	         int i= p.length;
 	         //int x=1;
 	         for(int j=0;j<i;j++){
 	        	  String st= p[j].getParagraphText();
 	        	  logger.debug("actual:"+st);
 	         }
 	         for(int j=0;j<i;j++){
 	      	  String st= p[j].getParagraphText();
 	      	  if(st.trim().equals(temp[1]))
 	      	  {
 	      		 flag=false;    
 	      		 break;
 	      	}
 	      	  else
 	      		  flag=true;
 	         }
 	      	  if(flag)
 	      		  return Constants.KEYWORD_PASS+"--text is not present";
 	      	  else
 	      		  return Constants.KEYWORD_FAIL+"--text is in present";
 	         }
 	 	
 	 	catch(Exception ex){
 	 		return Constants.KEYWORD_FAIL+ex.getMessage();
 	 	}
 	 	finally{
 	     	fis.close();
 	     }  
 	 }
 	 /**Added By Sohal Bansal 
 	  * 27/05/2014
 	  * This method will check whether passed text is present in doc file 
 	  * @throws IOException 
 	  * */
 	 public String verifyTextPresentInDoc(String object,String data) throws IOException{
 	 	FileInputStream fis=null;
 	 	try{
 	 		Boolean flag=false;
 	 		String temp[]=data.split(",");
 	 		String path=dirName+File.separator+temp[0];
 	 		File file=new File(path);
 	 		fis=new FileInputStream(file.getAbsolutePath());
 	 		 
 	         XWPFDocument doc = new XWPFDocument(fis);
 	         XWPFParagraph[] p=  doc.getParagraphs();
 	         int i= p.length;
 	         int x=1;
 	         for(int j=0;j<i;j++){
 	        	  String st= p[j].getParagraphText();
 	        	  logger.debug("actual:"+st);
 	         }
 	         for(int j=0;j<i;j++){
 	      	  String st= p[j].getParagraphText();
 	      	  if(x<temp.length && st.trim().equals(temp[x]))
 	      	  {
 	      		  x++;
 	      		 logger.debug("text Found");	
 	      		 flag=true;
 	         }
 	         }
 	      	  if(flag)
 	      		  return Constants.KEYWORD_PASS+"--text found";
 	      	  else
 	      		  return Constants.KEYWORD_FAIL+"--text not found";
 	         }
 	 	
 	 	catch(Exception ex){
 	 		return Constants.KEYWORD_FAIL+ex.getMessage();
 	 	}
 	 	finally{
 	     	fis.close();
 	     }  
 	 }
 	/**
  	 * This Method used to check specific file or folder is not present
  	 * 
  	 * @author Kritika
  	 * @since 28 May, 2014
  	 * 
  	 * @param object
  	 *            : Not required
  	 * 
  	 * @param data
  	 *            : Required (path of file or folder after downloadFiles 
  	 *              i.e "Evidence_New Report\Level2.1" Or "Evidence_New Report\Level2.1\New Report.pdf") 
  	 * 
  	 * @return <b>PASS</b> if file/ folder does not exists
  	 *         <b>FAIL</b> otherwise.
  	 */

  	 public String checkSpecificFolderNotExist(String object,String data){

  	         try
  	         {                
  	                if(object.equals(""))
  	                object=dirName+File.separator+data;
  	                File folderDir=new File(object);

  	                if(folderDir.exists())
  	                {
  	                        return Constants.KEYWORD_FAIL+ data+ " File/Folder Found";
  	                }
  	                else
  	                {
  	                	 return Constants.KEYWORD_PASS+ data+ " File/Folder not Found";
  	                }
  	         }
  	         
  	         catch(Exception ex)
  	         {
  	                         return Constants.KEYWORD_FAIL+ex.getMessage();
  	         }

  	 }
  	 
  	/**
 	 * 22/05/2014
 	 * 
 	 * Added By Nitin Gupta This Method is used to verify Link present in PDF
 	 * 
 	 * @param object
 	 *            : not require.
 	 * 
 	 * @param data
 	 *            :It accepts three parameter with comma separated. 1)Particular
 	 *            Page number that you want to access 2)URL to which the link will navigate
 	 *            3)Total count of links
 	 * @throws IOException
 	 */
 	public String verifyLinkInPDF(String object, String data) throws IOException {
 	    int link_count=0;
 	    logger.debug("Verifying PDF text File");
 	     try {
 	        String temp[] = data.split(",");
 	         int page = Integer.parseInt(temp[0]);
 	        String link_name=temp[1];
 	        int count=Integer.parseInt(temp[2]);
 	        String ghf=null;
 	        URL url = new URL(driver.getCurrentUrl());
 	        SSLcetificate();

 	        InputStream in = url.openStream();
 	        BufferedInputStream filetoParse = new BufferedInputStream(in);
 	       
 	  PdfReader pdfreader = new PdfReader(filetoParse);
 	   PdfDictionary PageDictionary = pdfreader.getPageN(page);

 	  //Get all of the annotations for the current page
 	  PdfArray Annots = PageDictionary.getAsArray(PdfName.ANNOTS);

 	  for(PdfObject A : Annots){
 	 //Convert the itext-specific object as a generic PDF object
 	 //skip all annotations that aren't of the /Subtype /Link or don't have an /A key
 	  PdfDictionary AnnotationDictionary = (PdfDictionary)PdfReader.getPdfObject(A);

 	 //Make sure this annotation has a link
 	if (!AnnotationDictionary.get(PdfName.SUBTYPE).equals(PdfName.LINK))
 	 continue;
 	//Make sure this annotation has an ACTION
 	if (AnnotationDictionary.get(PdfName.A) == null)
 	continue;
 	//Get the ACTION for the current annotation
 	 PdfObject a = AnnotationDictionary.get(PdfName.A);
 	if(a.isDictionary()){
 	PdfDictionary AnnotationAction = (PdfDictionary)a;
 	if (AnnotationAction.get(PdfName.S).equals(PdfName.URI))
 	 {
 	    
 	ghf=AnnotationAction.get(PdfName.URI).toString();
 	link_count++;
 	System.out.println(ghf);
 	}}}
 	 
 	 
 	  if(ghf.contains(link_name) && link_count==count)
 	        return Constants.KEYWORD_PASS + "PDF file contains mentioned link name" + link_count;
 	     else
 	        return Constants.KEYWORD_FAIL + "PDF file doesn't contains mentioned link name" + link_count;
 	 }      
 	catch (NoSuchElementException e) {

 	return Constants.KEYWORD_FAIL;
 	} catch (Exception e) {
 	e.printStackTrace();
 	return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
 	}
 	  }
 	
 	
 	/**
 	 * 22/05/2014
 	 * 
 	 * Added By Nitin Gupta This Method is used to verify Text present in PDF and the count of text
 	 * 
 	 * @param object
 	 *            : not require.
 	 * 
 	 * @param data
 	 *            :It accepts three parameter with comma separated. 1)Particular
 	 *            Page number that you want to access 2)Contents of Particular
 	 *            page 3)Number of times text present on page
 	 * @throws IOException
 	 */
 	
 	public String verifyTextCountInPDF(String object, String data) throws IOException {
 		logger.debug("Verifying PDF text File");
 		PDDocument pd = null;
 		try {
 			String temp[] = data.split(",");
 			int page = Integer.parseInt(temp[0]);
 			URL url = new URL(driver.getCurrentUrl());
 			// to accept the ssl certificate as url class works on http protocol
 			// only
 			SSLcetificate();

 			// open the connection with specified url ang get the raw data from
 			// inputstream
 			InputStream in = url.openStream();
 			BufferedInputStream filetoParse = new BufferedInputStream(in);
 			// pddocument is just like a pdf file and load method is to assign a
 			// PDF file to the PDDocument class
 			pd = PDDocument.load(filetoParse);
 			// pdftextstripper is used to read and write the pdf file
 			// This class will take a pdf document and strip out all of the text
 			// and ignore the formatting and such.

 			PDFTextStripper stripper = new PDFTextStripper();
 			stripper.setStartPage(page);
 			stripper.setEndPage(page);
 			stripper.getPageEnd();
 			String actualText = stripper.getText(pd);
 			int j=StringUtils.countMatches(actualText,temp[1]); 
 			
 			if (page > pd.getNumberOfPages()) {
 				throw new ArrayIndexOutOfBoundsException();
 			}
 			
 			if (actualText.contains(temp[1])&& j==(Integer.parseInt(temp[2])))
 			{	 
 				return Constants.KEYWORD_PASS + "PDF file contains text on mentioned page no:-" + page+ "and text count is "+ j;
 				}
 			else
 				return Constants.KEYWORD_FAIL + "PDF file doesn't contains text on mentioned" + page;
 					
 		} catch (ArrayIndexOutOfBoundsException ex) {
 			return Constants.KEYWORD_FAIL + "Page Number is not valid";
 		} catch (Exception ex) {
 			return Constants.KEYWORD_FAIL + ex.getMessage();
 		} finally {
 			pd.close();
 		}

 	}
 	/**
	 *  Sanjay Sharma: 27/05/2014
	 *   This method is used to open the given file from system directory
	 *   data: File name
	 * */
	public String openbrowserWindow(String object,String data)
	{

		try{

			// validate the parameters
			if (data == null) {
				return Constants.KEYWORD_FAIL + " Data is Empty";
			}
			String sep = System.getProperty("file.separator");
			driver.get(System.getProperty("user.dir")+ sep + "externalFiles" + sep + "downloadFiles"+ sep + data);

			return Constants.KEYWORD_PASS;
		}

		catch(TimeoutException ex)
		{
			return Constants.KEYWORD_FAIL+ex.getCause();
		}
		catch(Exception ex)
		{
			return Constants.KEYWORD_FAIL + " Object not found " + ex.getMessage();
		}
	}

	/**
	 * This Method used to verify current window URL and does not close driver instance.
	 * @author Sanjay Sharma
	 * @since 28/05/2014
	 * @param object
	 *            : not require.
	 * @param data
	 *            : Expected URL.
	 * @return <b>PASS</b> if successfully moved to specified window.
	 *         <b>FAIL</b> otherwise.
	 */
	public String verifyWindowURL(String object, String data) throws InterruptedException {
		logger.debug("Move to Specific Windows......");
		try {
			// validate the parameters
			if (data == null) {
				return Constants.KEYWORD_FAIL + " Data is Empty";
			}
			String currentWindowUrl = driver.getCurrentUrl();
			logger.debug("Actual URL : " + currentWindowUrl.trim());
			logger.debug("Expected URL : " + data.trim());

			if (currentWindowUrl.contains(data.trim())) {
				return Constants.KEYWORD_PASS + "Actual URL contains the Expected URL";
			} else {
				return Constants.KEYWORD_FAIL + "Actual URL do not contains the Expected URL";
			}

		} catch (Exception nse) {

			return Constants.KEYWORD_FAIL + nse.getLocalizedMessage();
		}
	}
	
	/**This method(verifyPdfTextUnderHeader) will check that Text is present under Header in pdf file
	 * 
	 * Added By Sumit Aggarwal 
	 * 29/05/2014
	 * 
	 *  * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            :It accepts 4 parameter with comma separated.1) File path after download Files 2)Particular
	 *            Page number that you want to access 3)Header Text 4)Text below header to be verified
	 * 
	 * 
	 * @return <b>PASS</b> if text is present under header
     *         <b>FAIL</b> otherwise.
	 */
	 
	public String verifyPdfTextUnderHeader (String object, String data) throws IOException {
        logger.debug("Verifying PDF text File");
        PdfReader pdfreader=null;
        try {
            String temp[] = data.split(",");
            int page = Integer.parseInt(temp[1]);
            final String header=temp[2];
            final String text=temp[3];
            String path=dirName+File.separator+temp[0];
            pdfreader = new PdfReader(path);
            String finalResult= PdfTextExtractor.getTextFromPage(pdfreader, page, new TextExtractionStrategy() {
            String result;
            boolean flag=false;
             
            public void renderText(TextRenderInfo renderInfo) {
            	String str=renderInfo.getText();
            	if(flag){
            		if(str.equals(text))
            		{
		      result="Pass";
		      flag=false;
            		}
            }
            	if(str.equals(header)){
            		flag=true;
            	}	
            }
             
            public void renderImage(ImageRenderInfo arg0) {
			// TODO Auto-generated method stub
			
            }
		
             
            public void endTextBlock() {
			// TODO Auto-generated method stub
			
            }
		
             
            public void beginTextBlock() {
			// TODO Auto-generated method stub
			
            }
		
             
            public String getResultantText() {
			return result;
            }
	   });
       
        if(finalResult.equals("Pass")){
    	   return Constants.KEYWORD_PASS+"Text found and verified";
    	   
       }
       else{
    	   return Constants.KEYWORD_PASS+"Text found and verified";
       }
     } 
        catch (FileNotFoundException e) {

        	return Constants.KEYWORD_FAIL+"File not found";
        } catch (Exception e) {
        	e.printStackTrace();
        	return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
        }
        finally{
        	pdfreader.close();
        }
        
      }
	
	/** 
	 * Added By Mayank Saini 
	 * 29/05/2014
	 * 
	 *  * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            :It accepts 3 parameter with comma separated.1) File path after download Files 2)Particular
	 *            Page number that you want to access 3)String of the text of whose color you want to access
	 * 
	 * 
	 * @return <b>PASS</b> if text is present under header
     *         <b>FAIL</b> otherwise.
	 */
	 
	public String verifyPdfTextColor (String object, String data) throws IOException {
        logger.debug("Verifying PDF text File");
        PdfReader pdfreader=null;
        try {
            String temp[] = data.split(",");
            int page = Integer.parseInt(temp[1]);
          
            final String text=temp[2];
            String path=dirName+File.separator+temp[0];
            pdfreader = new PdfReader(path);
            String finalResult= PdfTextExtractor.getTextFromPage(pdfreader, page, new TextExtractionStrategy() {
            String result;
         
             
            public void renderText(TextRenderInfo renderInfo) {
            	String str=renderInfo.getText();
            	
            		if(str.equals(text))
            		{
            			BaseColor greyColor=new BaseColor(192, 192, 192);
            			BaseColor textColor =renderInfo.getFillColor();
            			if(textColor.getRGB()==greyColor.getRGB())
            				result="Pass";
            		}
            
            	
            }
             
            public void renderImage(ImageRenderInfo arg0) {
			// TODO Auto-generated method stub
			
            }
		
             
            public void endTextBlock() {
			// TODO Auto-generated method stub
			
            }
		
             
            public void beginTextBlock() {
			// TODO Auto-generated method stub
			
            }
		
             
            public String getResultantText() {
			return result;
            }
	   });
       
       if(finalResult.equals("Pass")){
    	   return Constants.KEYWORD_PASS+"Color Of Text Verified";
    	   
       }
       else{
    	   return Constants.KEYWORD_PASS+"Color Of Text Not Verified";
       }
     } 
        catch (FileNotFoundException e) {

        	return Constants.KEYWORD_FAIL+"File not found";
        } catch (Exception e) {
        	e.printStackTrace();
        	return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
        }
        finally{
        	pdfreader.close();
        }
        
      }
	/**This method(verifyPdfTextUnderHeaderInUrl) will check that Text is present under Header in pdf file
    *
    * Added By Vikas Bhadwal
    * 29/05/2014
    *
    *  * @param object
    *            : not require.
    *
    * @param data
    *            :It accepts 3 parameter with comma separated.1)Particular
    *            Page number that you want to access 2)Header Text 3)Text below header to be verified
    *
    *
    * @return <b>PASS</b> if text is present under header
    *         <b>FAIL</b> otherwise.
    */
    
   public String verifyPdfTextUnderHeaderInUrl (String object, String data) throws IOException {
       logger.debug("Verifying PDF text File");
       PdfReader pdfreader=null;
       try {
           String temp[] = data.split(",");
           int page = Integer.parseInt(temp[0]);
           final String header=temp[1];
           final String text=temp[2];
           URL url = new URL(driver.getCurrentUrl());
            // to accept the ssl certificate as url class works on http protocol
            // only
            SSLcetificate();

            // open the connection with specified url ang get the raw data from
            // inputstream
            InputStream in = url.openStream();
            BufferedInputStream filetoParse = new BufferedInputStream(in);
           pdfreader = new PdfReader(filetoParse);
           String finalResult= PdfTextExtractor.getTextFromPage(pdfreader, page, new TextExtractionStrategy() {
           String result;
           boolean flag=false;
            
           public void renderText(TextRenderInfo renderInfo) {
               String str=renderInfo.getText();
               if(flag){
                   if(str.equals(text))
                   {
             result="Pass";
             flag=false;
                   }
           }
               if(str.equals(header)){
                   flag=true;
               }   
           }
            
           public void renderImage(ImageRenderInfo arg0) {
           // TODO Auto-generated method stub
          
           }
      
            
           public void endTextBlock() {
           // TODO Auto-generated method stub
          
           }
      
            
           public void beginTextBlock() {
           // TODO Auto-generated method stub
          
           }
      
            
           public String getResultantText() {
           return result;
           }
      });
     
      if(finalResult.equals("Pass")){
          return Constants.KEYWORD_PASS+"Text found and verified";
         
      }
      else{
          return Constants.KEYWORD_PASS+"Text found and verified";
      }
    }
       catch (FileNotFoundException e) {

           return Constants.KEYWORD_FAIL+"File not found";
       } catch (Exception e) {
           e.printStackTrace();
           return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
       }
       finally{
           pdfreader.close();
       }
      
     }

	/**
	 * This method(verifyTextIsNotLinkInPdf) will check that specified Text is
	 * not present in the pdf as a link
	 * 
	 * @author Pankaj Sharma
	 * @since 26 May, 2014
	 * 
	 * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            : It accepts Three values as data parameter ,First is page no ,Second is Text that you want to verify .<br>
	 *            And Third is specified text is File or Link. All this values are splits by "|" symbol.<br>
	 *            For Example : 1|test|File
	 * 
	 * @return <b>PASS</b> if Specified text is not link in PDF
	 *         <b>FAIL</b> otherwise.
	 */
	public String verifyTextIsNotLinkInPdf(String object, String data) throws IOException {
		logger.debug("Verifying PDF text File");
		try {
			
			String temp[] = data.split(Constants.DATA_SPLIT);
			int page = Integer.parseInt(temp[0]);
			String link_name = temp[1];
			String type = temp[2];
			ArrayList<String> allLinks = new ArrayList<String>();
			ArrayList<String> allFiles = new ArrayList<String>();
			// validate the parameters
			if (data==null ||temp.length<3) {
				return Constants.KEYWORD_FAIL + "Data is not passed properly";
			}
			
			String ghf = null;
			URL url = new URL(driver.getCurrentUrl());
			SSLcetificate();

			InputStream in = url.openStream();
			BufferedInputStream filetoParse = new BufferedInputStream(in);

			PdfReader pdfreader = new PdfReader(filetoParse);
			PdfDictionary PageDictionary = pdfreader.getPageN(page);

			// Get all of the annotations for the current page
			PdfArray Annots = PageDictionary.getAsArray(PdfName.ANNOTS);
			if(Annots==null)
				return Constants.KEYWORD_PASS + "PDF file does not contains mentioned Text as Link on Page  " + page + " of " + type;

			for (PdfObject A : Annots) {
				// Convert the itext-specific object as a generic PDF object
				// skip all annotations that aren't of the /Subtype /Link or
				// don't have an /A key
				PdfDictionary AnnotationDictionary = (PdfDictionary) PdfReader.getPdfObject(A);

				// Make sure this annotation has a link
				if (!AnnotationDictionary.get(PdfName.SUBTYPE).equals(PdfName.LINK))
					continue;
				// Make sure this annotation has an ACTION
				if (AnnotationDictionary.get(PdfName.A) == null)
					continue;
				// Get the ACTION for the current annotation
				PdfObject a = AnnotationDictionary.get(PdfName.A);
				if (a.isDictionary()) {
					PdfDictionary AnnotationAction = (PdfDictionary) a;
					if (AnnotationAction.get(PdfName.S).equals(PdfName.URI)) {
						ghf = AnnotationAction.get(PdfName.URI).toString();
						System.out.println("This is Link" + ghf);
						allLinks.add(ghf);
					} else if (AnnotationAction.get(PdfName.S).equals(PdfName.F)) {
						ghf = AnnotationAction.get(PdfName.F).toString();
						allFiles.add(ghf);
						System.out.println("This is File" + ghf);
					}
				}
			}
			if (type.equals("File"))
				if (allFiles.contains(link_name))
					return Constants.KEYWORD_FAIL + "PDF file contains mentioned Text as Link on Page  " + page + " of " + type;
				else
					return Constants.KEYWORD_PASS + "PDF file does not contains mentioned Text as Link on Page  " + page + " of " + type;
			else if (type.equals("Link"))
				if (allFiles.contains(link_name))
					return Constants.KEYWORD_FAIL + "PDF file contains mentioned Text as Link on Page  " + page + " of " + type;
				else
					return Constants.KEYWORD_PASS + "PDF file does not contains mentioned Text as Link on Page  " + page + " of " + type;
			else
				return Constants.KEYWORD_FAIL + "Enter Link or File";
		} catch (NoSuchElementException e) {
			return Constants.KEYWORD_FAIL + e;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
		}
	}
	/**This method(verifyTextInLocalSystemPdf) will check that Text is present in pdf file that is exist in our local system
     *
     * @author Pankaj Sharma
	 * @since 04 June, 2014
	 * 
	 * @param object
	 *            : not require.
	 * @param data
	 *            : It accepts Three values as data parameter, First one is Path to pdf file from the download files folder.<br>
	 *              Second is page no, And Third is Text that you want to verify. All this values are splits by "|" symbol.<br>
	 *              For Example :Evidence_Level 1.1\testone.pdf|1|TK20
	 * 
	 * @return <b>PASS</b> if Specified Text is Present in the PDF
	 *         <b>FAIL</b> otherwise.
	 */
		public String verifyTextInLocalSystemPdf (String object, String data) throws IOException {
	        logger.debug("Verifying PDF text File");
	        PdfReader pdfreader=null;
	        try {
	            String temp[] = data.split(Constants.DATA_SPLIT);
	        	// validate the parameters
				if (data==null ||temp.length<3) {
					return Constants.KEYWORD_FAIL + "Data is not passed properly";
				}
	            int page = Integer.parseInt(temp[1]);
	             String text=temp[2];
	            String path=dirName+File.separator+temp[0];
	            pdfreader = new PdfReader(path);
	            String actualText=PdfTextExtractor.getTextFromPage(pdfreader, page);
	            if(actualText.contains(text))
	            	return Constants.KEYWORD_PASS + " Pdf file Contains specified text";
	            else
	            	return Constants.KEYWORD_FAIL+" Pdf File do not Contains specified text, Actual text is : " +actualText;
	        	}
			 catch (NoSuchElementException e) {
				return Constants.KEYWORD_FAIL;
			} catch (Exception e) {
				e.printStackTrace();
				return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
			}
			finally{
				pdfreader.close();
			}  
		}
		/**This method(verifyImageTypeInPDF) will check that Image is present in pdf file that is exist in our local system.
	     *
	     * @author Pankaj Sharma
		 * @since 05 June, 2014
		 * 
		 * @param object
		 *            : not require.
		 * @param data
		 *            : It accepts Three values as data parameter, First one is Path to pdf file from the download files folder.<br>
		 *              Second is page no, And Third is Type of the Image File that you want to verify. All this values are splits by "|" symbol.<br>
		 *              For Example :Evidence_Level 1.1\testone.pdf|1|jpg
		 * 
		 * @return <b>PASS</b> if Specified Image is Present in the PDF
		 *         <b>FAIL</b> otherwise.
		 */
		public String verifyImageTypeInPDF (String object, String data) throws IOException {
	        logger.debug("Verifying PDF text File");
	        PdfReader pdfreader=null;
	        try {
	            String temp[] = data.split(Constants.DATA_SPLIT);
	            int page = Integer.parseInt(temp[1]);
	            final String expectedImgType = temp[2].trim();
	            String path=dirName+File.separator+temp[0];
	            pdfreader = new PdfReader(path);
	            String finalResult= PdfTextExtractor.getTextFromPage(pdfreader, page, new TextExtractionStrategy() {
				String result;

				 
				public void renderText(TextRenderInfo renderInfo) {
				}

				 
				public void renderImage(ImageRenderInfo renderImgInfo) {
					// TODO Auto-generated method stub
					try {
						String actualImageType = renderImgInfo.getImage().getFileType();
						if(expectedImgType.equals(actualImageType)){
							 result=Constants.KEYWORD_PASS;
						}
						else {
							result=Constants.KEYWORD_FAIL;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				 
				public void endTextBlock() {
					// TODO Auto-generated method stub

				}

				 
				public void beginTextBlock() {
					// TODO Auto-generated method stub

				}

				 
				public String getResultantText() {
					return result;
				}
		   });
	       
			if (finalResult.equals(Constants.KEYWORD_PASS))
				return Constants.KEYWORD_PASS + " Expected Image type is present in the PDF";
			else 
				return Constants.KEYWORD_PASS + " Expected Image type is not present in the PDF";
	     } 
	        catch (FileNotFoundException e) {

	        	return Constants.KEYWORD_FAIL+"File not found";
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
	        }
	        finally{
	        	pdfreader.close();
	        }
	        
	      }
		/**This method(verifyLinkInPDFInURL) will check that Link is present in pdf file that is exist in Open in URL.
	     *
	     * @author Pankaj Sharma
		 * @since 04 June, 2014
		 * 
		 * @param object
		 *            : not require.
		 * @param data
		 *            : It accepts Three values as data parameter, First one is page no<br>
		 *              Second is Link No, And Third is Action behind the Link that you want to verify. All this values are splits by "|" symbol.<br>
		 *              For Example :1|1|Google
		 * 
		 * @return <b>PASS</b> if Specified Link is Present in the PDF
		 *         <b>FAIL</b> otherwise.
		 */
		public String verifyLinkInPDFInURL(String object, String data) throws IOException {
		logger.debug("Verifying PDF text File");
		PdfReader pdfreader=null;
		try {
			String temp[] = data.split(Constants.DATA_SPLIT);
            int page = Integer.parseInt(temp[0]);
            int lnkNo=Integer.parseInt(temp[1]);
            String expectedAction=temp[2].trim();
            String ghf="";

			URL url = new URL(driver.getCurrentUrl());
			// to accept the ssl certificate as url class works on http protocol
			// only
			SSLcetificate();

			// open the connection with specified url ang get the raw data from
			// inputstream
			InputStream in = url.openStream();
			BufferedInputStream filetoParse = new BufferedInputStream(in);
			pdfreader = new PdfReader(filetoParse);
			PdfDictionary PageDictionary = pdfreader.getPageN(page);
			// Get all of the annotations for the current page
			PdfArray Annots = PageDictionary.getAsArray(PdfName.ANNOTS);
			PdfObject a=Annots.getDirectObject(lnkNo-1);
			// Convert the itext-specific object as a generic PDF object
			// skip all annotations that aren't of the /Subtype /Link or
				// don't have an /A key
			PdfDictionary AnnotationDictionary = (PdfDictionary) PdfReader.getPdfObject(a);
		
			// Make sure this annotation has a link
			if (!AnnotationDictionary.get(PdfName.SUBTYPE).equals(PdfName.LINK))
				return Constants.KEYWORD_FAIL;
			
			if (AnnotationDictionary.get(PdfName.A) == null)
				return Constants.KEYWORD_FAIL;
			
			PdfObject  am= AnnotationDictionary.get(PdfName.A);
				if (am.isDictionary()) {
					PdfDictionary AnnotationAction = (PdfDictionary) am;
					if (AnnotationDictionary.get(PdfName.SUBTYPE).equals(PdfName.LINK))	
					ghf = AnnotationAction.get(PdfName.URI).toString();
					if (AnnotationDictionary.get(PdfName.SUBTYPE).equals(PdfName.F))	
					ghf = AnnotationAction.get(PdfName.F).toString();
					}
				if (ghf.contains(expectedAction))
					return Constants.KEYWORD_PASS + "PDF file contains specified link " +expectedAction +" at position "+lnkNo +" on Page no. "+page;
				else
					return Constants.KEYWORD_FAIL + "PDF file not contains specified link " +expectedAction +" at position "+lnkNo +" on Page no. "+page;
		} catch (NoSuchElementException e) {
			return Constants.KEYWORD_FAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
		}
		 finally{
         	pdfreader.close();
         }  
	}

	/**
	 * This method(verifyLinkInSystemPdf) will check that Link is present in pdf file that is exist in our local system.
	 * 
	 * @author Pankaj Sharma
	 * @since 04 June, 2014
	 * 
	 * @param object
	 *            : not require.
	 * @param data
	 *            It accepts Four values as data parameter, First one is Path to pdf file from the download files folder.<br>
	 *            Second is page no,Third is Link no ,And Fourth is Action behind the Link.All this values are splits by "|" symbol.<br>
	 *            For Example :Evidence_Level 1.1\testone.pdf|1|1|Google
	 * 
	 * @return <b>PASS</b> if Specified Link is Present in the PDF. <b>FAIL</b>
	 *         otherwise.
	 */
	public String verifyLinkInSystemPdf(String object, String data) throws IOException {
		logger.debug("Verifying PDF text File");
		PdfReader pdfreader = null;
		try {
			String temp[] = data.split(Constants.DATA_SPLIT);
			int page = Integer.parseInt(temp[1]);
			int lnkNo = Integer.parseInt(temp[2]);
			String expectedAction = temp[3].trim();
			String path = dirName + File.separator + temp[0];
			pdfreader = new PdfReader(path);
			String ghf = "";
			PdfDictionary PageDictionary = pdfreader.getPageN(page);

			// Get all of the annotations for the current page
			PdfArray Annots = PageDictionary.getAsArray(PdfName.ANNOTS);
			PdfObject a = Annots.getDirectObject(lnkNo - 1);
			// Convert the itext-specific object as a generic PDF object
			// skip all annotations that aren't of the /Subtype /Link or
			// don't have an /A key
			PdfDictionary AnnotationDictionary = (PdfDictionary) PdfReader.getPdfObject(a);

			// Make sure this annotation has a link
			if (!AnnotationDictionary.get(PdfName.SUBTYPE).equals(PdfName.LINK))
				return Constants.KEYWORD_FAIL;

			if (AnnotationDictionary.get(PdfName.A) == null)
				return Constants.KEYWORD_FAIL;

			// Make sure this annotation has a link
			PdfObject am = AnnotationDictionary.get(PdfName.A);
			if (am.isDictionary()) {
				PdfDictionary AnnotationAction = (PdfDictionary) am;
				if (AnnotationAction.get(PdfName.S).equals(PdfName.URI))
					ghf = AnnotationAction.get(PdfName.URI).toString();
				if (AnnotationAction.get(PdfName.S).equals(PdfName.GOTOR))
					ghf = AnnotationAction.get(PdfName.F).toString();
			}
			if (ghf.contains(expectedAction))
				return Constants.KEYWORD_PASS + "PDF file contains specified link " + expectedAction + " at position " + lnkNo + " on Page no. " + page;
			else
				return Constants.KEYWORD_FAIL + "PDF file not contains specified link " + expectedAction + " at position " + lnkNo + " on Page no. " + page;
		} catch (NoSuchElementException e) {
			return Constants.KEYWORD_FAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
		} finally {
			pdfreader.close();
		}

	}
	/**Added By Mayank Saini 
	 * 02/05/2014
	 * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            :It accepts Three parameter with comma separated.
	 *             1) File path after download Files
	 *             2) Evidence
	 *             3) File name that you have uploaded
	 *         

	 * @throws IOException
	 * */
	public String VerifyTextNotPresentUnderHeader(String object,String data) throws IOException
	{
		  FileInputStream fis=null;
		try{
	          String temp[]=data.split(",");
	        String path=dirName+File.separator+temp[0];

	        File file=new File(path);
	         fis=new FileInputStream(file.getAbsolutePath());
	         
	        XWPFDocument doc = new XWPFDocument(fis);
	        
	        XWPFHyperlink[] links=doc.getHyperlinks();
	        if(links.length>0)
	        	  return Constants.KEYWORD_FAIL+"Links Are Present in File";
	        
	        XWPFParagraph[] p=  doc.getParagraphs();
	        int i= p.length;
	        for(int j=0;j<i;j++){
	           String st= p[j].getParagraphText();
	           if(st.trim().equals(temp[1].trim())){
	        	   for(int k=j+1;k<i;k++){
	              String s1t= p[k].getParagraphText();
	       	   if(p[k].getCTP().getHyperlinkArray().length<1)
	              if(s1t.trim().equals(temp[2].trim())){
	                logger.debug("text Found");    
	                	{
	         		  return Constants.KEYWORD_FAIL+"Text is Present ";
	                	}
	                
	                }
	        	   }
	        }
	       }
	    
	   
	        return Constants.KEYWORD_PASS+"Text is not present";
	    }
	    catch(Exception ex){
	        return Constants.KEYWORD_FAIL;
	    }
		finally{
			fis.close();
		}
	}
	
	
	/**This method(pdfHyperLinkUnderSection) will verify Link is present under Evidence pane or Narrative section 
	 * and Page no. opened corresponding to link is correct.
	 * 
	 * Added By Sumit Aggarwal 
	 * 19/06/2014
	 * 
	 *  * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            :It accepts 5 parameter with comma separated. 1) File path after download Files 2)Particular
	 *            Page number that you want to access 
	 *            3)Location of link for narrative section "1" and for evidence pane "2"
	 *            4) Page that will open from this link
	 *            5)File name behind the link
	 * 
	 * 
	 * @return <b>PASS</b> if Link is Present and File opened on correct
     *         <b>FAIL</b> otherwise.
	 */
	 
	public String pdfHyperLinkUnderSection (String object, String data) throws IOException {
		{
            logger.debug("Verifying PDF text File");
            PdfReader pdfreader=null;
            try {
            	int count=0;
            	int openPage=0;
            	Boolean flag=false;
                String temp[] = data.split(",");
                int page = Integer.parseInt(temp[1]);
                int linkLoc=Integer.parseInt(temp[2]);
             
                String path=dirName+File.separator+temp[0];
                pdfreader = new PdfReader(path);
                PdfDictionary PageDictionary = pdfreader.getPageN(page);

                //Get all of the annotations for the current page 
                PdfArray Annots = PageDictionary.getAsArray(PdfName.ANNOTS);
    

                for(PdfObject A : Annots)
                {
                	//Convert the itext-specific object as a generic PDF object
                	//skip all annotations that aren't of the /Subtype /Link or don't have an /A key
                	PdfDictionary AnnotationDictionary = (PdfDictionary)PdfReader.getPdfObject(A);
 
                	
                	//Make sure this annotation has a link
                	if (!AnnotationDictionary.get(PdfName.SUBTYPE).equals(PdfName.LINK))
                		continue;
                	//Make sure this annotation has an ACTION
                	if (AnnotationDictionary.get(PdfName.A) == null)
                		continue;
                	//Get the ACTION for the current annotation
                	PdfObject a = AnnotationDictionary.get(PdfName.A);
                	if(a.isDictionary())
                	{
                		PdfDictionary AnnotationAction = (PdfDictionary)a;
                		if (AnnotationAction.get(PdfName.S).equals(PdfName.GOTOR))
                		{
                			String destination_file=AnnotationAction.get(PdfName.F).toString();
                			String objArr[] = destination_file.split("/");
                			int arrSize = objArr.length;
                			String fileName = objArr[arrSize - 1];
                			if (fileName.equals(temp[4]))
                			{
                				if(linkLoc==1)
                				{
                					count=count+2;
                				}
                				else
                					count++;
      					
                			}
                			if(fileName.equals(temp[4]) && count==2)
                			{
                				Desktop.getDesktop().open(new File(dirName+File.separator+destination_file));
                				new KeywordEventsUtill().browserSpecificPause(object, data);
                				if(AnnotationAction.contains(PdfName.D))
                				{
                					PdfObject obj=AnnotationAction.get(PdfName.D);
                					String destinationPage=obj.toString().substring(1, 2);
                					openPage=Integer.parseInt(destinationPage)+1;
                					if(openPage==Integer.parseInt(temp[3]))
                					{
                						flag=true; 
                						break;
                					}
                				}
                			}
                		}
                	}
                }           
                if(flag)          
                	return Constants.KEYWORD_PASS + " " + "Link is Present and File opened on"+openPage;
                else
                	return Constants.KEYWORD_FAIL + " " + "Link is not Present";
            } 
            catch (FileNotFoundException e){
            	return Constants.KEYWORD_FAIL+"File not found";
            } 
            catch (Exception e){
            	e.printStackTrace();
            	return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
            }
            finally{
        	pdfreader.close();
            }  
		}
	}	
	/**
	 * This method(verifyTotalLinkInPDF) will check Total Number of links present in the PDF file that present In our local system
	 * 
	 * @author Pankaj Sharma
	 * @since 09 June, 2014
	 * 
	 * @param object
	 *            : not require.
	 * @param data
	 *            : It accepts Three values as data parameter, First one is Path to pdf file from the download files folder.<br>
	 *            Second is page no,Third is total number of Links, All this values are splits by "|" symbol.<br>
	 *            For Example :Evidence_Level 1.1\testone.pdf|1|5
	 * 
	 * @return <b>PASS</b> if Specified count of Links Present in the PDF. <b>FAIL</b>
	 *         otherwise.
	 */
	public String verifyTotalLinkInPDF(String object, String data) throws IOException {
        logger.debug("Verifying PDF text File");
       PdfReader pdfreader = null;
        try {
            String temp[] = data.split(Constants.DATA_SPLIT);
           int page = Integer.parseInt(temp[1]);
           int totalLinkCount = Integer.parseInt(temp[2]);
           String path = dirName + File.separator + temp[0];
           pdfreader = new PdfReader(path);
           PdfDictionary PageDictionary = pdfreader.getPageN(page);

           // Get all of the annotations for the current page
           PdfArray Annots = PageDictionary.getAsArray(PdfName.ANNOTS);
          
           if(totalLinkCount==0)
               if(Annots==null)
                   return Constants.KEYWORD_PASS;
               else
                   return Constants.KEYWORD_FAIL;
           else
               if(Annots.size()==totalLinkCount)
                   return Constants.KEYWORD_PASS;
               else
                   return Constants.KEYWORD_FAIL;
       } catch (NoSuchElementException e) {
           return Constants.KEYWORD_FAIL;
       } catch (Exception e) {
           e.printStackTrace();
           return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
       } finally {
           pdfreader.close();
       }
   }
	/**
	 * This method(verifyTotalLinkInPDFInUrl) will check Total Number of links present in the PDF file that Open in URL
	 * 
	 * @author Pankaj Sharma
	 * @since 10 June, 2014
	 * 
	 * @param object
	 *            : not require.
	 * @param data
	 *            : It accepts Two values as data parameter, First is page no<br>
	 *              ,Second is total number of Links, All this values are splits by "|" symbol.<br>
	 *               For Example :1|5
	 * 
	 * @return <b>PASS</b> if Specified count of Links Present in the PDF. <b>FAIL</b>
	 *         otherwise.
	 */
   public String verifyTotalLinkInPDFInUrl(String object, String data) throws IOException {
        logger.debug("Verifying PDF text File");
       PdfReader pdfreader = null;
        try {
           
            String temp[] = data.split(Constants.DATA_SPLIT);
           int page = Integer.parseInt(temp[0]);
           int totalLinkCount = Integer.parseInt(temp[1]);
      
           URL url = new URL(driver.getCurrentUrl());
           // to accept the ssl certificate as url class works on http protocol
           // only
           SSLcetificate();

           // open the connection with specified url ang get the raw data from
           // inputstream
           InputStream in = url.openStream();
           BufferedInputStream filetoParse = new BufferedInputStream(in);
           pdfreader = new PdfReader(filetoParse);
           PdfDictionary PageDictionary = pdfreader.getPageN(page);

           // Get all of the annotations for the current page
           PdfArray Annots = PageDictionary.getAsArray(PdfName.ANNOTS);
          
           if(totalLinkCount==0)
               if(Annots==null)
                   return Constants.KEYWORD_PASS;
               else
                   return Constants.KEYWORD_FAIL;
           else
               if(Annots.size()==totalLinkCount)
                   return Constants.KEYWORD_PASS;
               else
                   return Constants.KEYWORD_FAIL;
       } catch (NoSuchElementException e) {
           return Constants.KEYWORD_FAIL;
       } catch (Exception e) {
           e.printStackTrace();
           return Constants.KEYWORD_FAIL + " Unable to write " + e.getLocalizedMessage();
       } finally {
           pdfreader.close();
       }
   }
   /** 25/06/2014
	 * Added By Mayank Saini 
	 * This method is used to create Accreditaion report 
	 *  @param data
	 *            :It accepts 3 parameter with comma separated.
	 *            1) Create New...
	 *            2) Title of the Report
	 *            3) Template for report
	 *  @object  It accepts 6 parameter given below       
	 *             am_select_report_dd,am_report_title_input,am_select_report_template,Next_Button,am_create_report_btn,createNew_AccreRprtLink
	 **/
   
     /*
	 * Updated By:- Karan Sood Date:- 10 July 2014 
	 * Added "if" statement to check whether "Create a new accreditation report"
	 * present in link form.
	 * Now, it Accepts 6 object parameters , added 6th parameter "createNew_AccreRprtLink"
	 */
   
	public String createAccreditaionReport(String object,String data)
	{
		try{
			
			String temp[]=data.split(",");
			String xpath[]=object.split(",");
			
			int size = driver.findElements(By.xpath(OR.getProperty(xpath[5]))).size();
			
			if(size>0)
			{
				keyUtill.clickLink(xpath[5], data);
			}
			else
			{
			    keyUtill.selectList(xpath[0], temp[0]);
			}
			keyUtill.writeInInput(xpath[1], temp[1]);
			keyUtill.selectList(xpath[2], temp[2]);
			keyUtill.clickButton(xpath[3], data);
			keyUtill.clickButton(xpath[4], data);
			return Constants.KEYWORD_PASS;
		}
		
		catch(Exception ex){
			
			return Constants.KEYWORD_FAIL  + ex.getLocalizedMessage();
		}
		
	}	
	


	/** 25/06/2014
	 * 	Added By Mayank Saini 
	 *	 This method is used to create Accreditaion report 
	 *  @param no data
	 *  
	 *  @object It accepts 4 parameter given below 
	 *  am_edit_report_btn,am_frame_xpath,am_unlock_btn,am_delete_report_btn
	 **/
	/* Updated By:- Karan Sood Date:- 15 July 2014 
	 * Added  isInConsistentAlert
	 */
	
	public String deleteAccreditationReport(String object,String data)
	{
		try{
			String xpath[]=object.split(",");
			keyUtill.clickImage(xpath[0], data);
			keyUtill.moveToFrameByXpath(xpath[1] ,data);
			keyUtill.clickButton(xpath[2], data);
			keyUtill.clickButton(xpath[3], data);
			keyUtill.isInConsistentAlert(object, data);
			keyUtill.isInConsistentAlert(object, data);
			return Constants.KEYWORD_PASS;
		}
		
		catch(Exception ex){
			
			return Constants.KEYWORD_FAIL  + ex.getLocalizedMessage();
		}
		
	}	
	/**Added By Mayank Saini 
	 * 02/05/2014
	 * This method will check the pdfHyperLinks on the pdf file
	 * 
	 *  * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            :It accepts 3 parameter with comma separated.1) Particular
	 *            Page number that you want to access 2)Position of link 3)Text Of the Link
	 *            e.g-1,1,File1
	 * @throws IOException
	 */


	public String getHyperLinkTextInURL(String object,String data){
		PdfReader pdfreader=null;
		ArrayList<Float> l5=new ArrayList<Float>();
		List<String> links=new ArrayList<String>();
		try{
			String temp[] = data.split(",");
            int page = Integer.parseInt(temp[0]);
           			URL url = new URL(driver.getCurrentUrl());
			// to accept the ssl certificate as url class works on http protocol
			// only
			SSLcetificate();

			// open the connection with specified url ang get the raw data from
			// inputstream
			InputStream in = url.openStream();
			BufferedInputStream filetoParse = new BufferedInputStream(in);
			pdfreader = new PdfReader(filetoParse);
			ArrayList<PdfImportedLink> l1=pdfreader.getLinks(page);

			int total_link=l1.size();
			while(total_link>0){
				Boolean flag=false;
				int xposn=0;
				int yposn=0;
				String str=l1.get(total_link-1).toString();
				for(int i=0; i<str.length();i++)
				{
					char c=str.charAt(i);
					if(c=='['){
						xposn=i+1;
						for(int j=i;j<str.length();j++){
							char m=str.charAt(j);
							if(m==']')
							{
								yposn=j;
								flag=true;
								break;
							}
						}
					}
					if(flag){
						break;
					}		}
				logger.debug("1st subsplit: "+xposn+"//"+"2nd subsplit: "+yposn);
				str=str.substring(xposn, yposn);

				String all_cordinates[]=str.split(" ");
				for(int m=0;m<all_cordinates.length;m++){
					String j=all_cordinates[m];
					float coordinates=Float.parseFloat(j);
					l5.add(coordinates);

				}

				Rectangle rect =new Rectangle(l5.get(0), l5.get(1), l5.get(2), l5.get(3));
				l5=new ArrayList<Float>();
				RenderFilter filter = new RegionTextRenderFilter(rect);
				TextExtractionStrategy strategy;

				strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
				String text=PdfTextExtractor.getTextFromPage(pdfreader, page, strategy);
				links.add(text);
				total_link--;
			}

			Iterator<String> ite = links.iterator();
			while(ite.hasNext()){
				String text=ite.next();
				logger.debug("Actual text"+text);

				if(text.trim().equals(temp[2]))
				{
					logger.debug("Expected text"+temp[2]);
					return Constants.KEYWORD_PASS+"Links Text Matched";
				}
			}


			return Constants.KEYWORD_FAIL+"Links Text Not Matched";


		}
		catch(Exception ex){
			return Constants.KEYWORD_FAIL+" -- Not able to Find Link"+ex.getMessage();
		}
		finally{
			pdfreader.close(); 
		}
	}	
	/**Added By Kritika 
	 * 01/09/2014
	 * This method will check the pdfHyperLinks on the pdf file
	 * 
	 *  * @param object
	 *            : not require.
	 * 
	 * @param data
	 *            :It accepts 3 parameter with comma separated.1) Particular
	 *            Page number that you want to access 2)Position of link 3)Text Of the Link
	 *            e.g-1,1,File1
	 * @throws IOException
	 */


	public String getHyperLinkTextInURLContains(String object,String data){
		PdfReader pdfreader=null;
		ArrayList<Float> l5=new ArrayList<Float>();
		List<String> links=new ArrayList<String>();
		try{
			String temp[] = data.split(",");
            int page = Integer.parseInt(temp[0]);
           			URL url = new URL(driver.getCurrentUrl());
			// to accept the ssl certificate as url class works on http protocol
			// only
			SSLcetificate();

			// open the connection with specified url ang get the raw data from
			// inputstream
			InputStream in = url.openStream();
			BufferedInputStream filetoParse = new BufferedInputStream(in);
			pdfreader = new PdfReader(filetoParse);
			ArrayList<PdfImportedLink> l1=pdfreader.getLinks(page);

			int total_link=l1.size();
			while(total_link>0){
				Boolean flag=false;
				int xposn=0;
				int yposn=0;
				String str=l1.get(total_link-1).toString();
				for(int i=0; i<str.length();i++)
				{
					char c=str.charAt(i);
					if(c=='['){
						xposn=i+1;
						for(int j=i;j<str.length();j++){
							char m=str.charAt(j);
							if(m==']')
							{
								yposn=j;
								flag=true;
								break;
							}
						}
					}
					if(flag){
						break;
					}		}
				logger.debug("1st subsplit: "+xposn+"//"+"2nd subsplit: "+yposn);
				str=str.substring(xposn, yposn);

				String all_cordinates[]=str.split(" ");
				for(int m=0;m<all_cordinates.length;m++){
					String j=all_cordinates[m];
					float coordinates=Float.parseFloat(j);
					l5.add(coordinates);

				}

				Rectangle rect =new Rectangle(l5.get(0), l5.get(1), l5.get(2), l5.get(3));
				l5=new ArrayList<Float>();
				RenderFilter filter = new RegionTextRenderFilter(rect);
				TextExtractionStrategy strategy;

				strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
				String text=PdfTextExtractor.getTextFromPage(pdfreader, page, strategy);
				links.add(text);
				total_link--;
			}

			Iterator<String> ite = links.iterator();
			while(ite.hasNext()){
				String text=ite.next();
				logger.debug("Actual text"+text);

				if(text.trim().contains(temp[2]))
				{
					logger.debug("Expected text"+temp[2]);
					return Constants.KEYWORD_PASS+"Links Text Matched";
				}
			}


			return Constants.KEYWORD_FAIL+"Links Text Not Matched";


		}
		catch(Exception ex){
			return Constants.KEYWORD_FAIL+" -- Not able to Find Link"+ex.getMessage();
		}
		finally{
			pdfreader.close(); 
		}
	}
	
 	/**
   	 * Added by Sanjay Sharma on 08/11/2016
   	* this method moves the mouse over the element specified in the object
   	* @param object
   	* @param data
   	* @return
   	*/
   	
	public String mouseOverUntilView(String object, String data) {
       	
	    
	       		try {
	       	
	       	waitForPageLoad(driver);  	   
	       	WebElement ele=null;
	       	ele = explictWaitForElementUsingFluent(object);
	      String javaScript = "var evObj = document.createEvent('MouseEvents');" +
	            "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
	            "arguments[0].dispatchEvent(evObj);";
     ((JavascriptExecutor)driver).executeScript(javaScript, ele);
	       	
	       	List<WebElement> arrowIcon =  driver.findElements(By.xpath(OR.getProperty("am_com_arrow_icon_downward")));
	       	if(arrowIcon.size()>0)
	       	{
	      
	       	return Constants.KEYWORD_PASS;
	       	}
	       	else
	       	{
	       		if(Hcount<3)
	       		{
	       		WebElement ele1=driver.findElement(By.xpath(OR.getProperty("am_com_placeholder_input")));
	       		ele1.click();
	       		Thread.sleep(3000);
	       		Hcount++;
	       	logger.debug(" Inside recursion ");
	        mouseOverUntilView(object,data);
	       	}
	       	}
	       	return Constants.KEYWORD_FAIL;
	       	} 
	       	catch(TimeoutException ex)
	       	{
	       	return Constants.KEYWORD_FAIL +"Cause: "+ ex.getCause();
	       	}
	       	catch (Exception e) {
	       	
	       	return Constants.KEYWORD_FAIL + " Object not found";
	       	}
	       	}
}

