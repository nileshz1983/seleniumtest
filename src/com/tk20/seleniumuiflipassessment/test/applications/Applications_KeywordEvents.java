package com.tk20.seleniumuiflipassessment.test.applications;

import static com.tk20.seleniumuiflipassessment.base.DataProvider.OR;
import static com.tk20.seleniumuiflipassessment.base.DataProvider.logger;
import static com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill.driver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tk20.seleniumuiflipassessment.base.Constants;
import com.tk20.seleniumuiflipassessment.util.KeywordEventsUtill;

public class Applications_KeywordEvents {
	
	KeywordEventsUtill keyUtil = (KeywordEventsUtill) KeywordEventsUtill.keyUtillFactory();
	int app=1;
	/**
	 * Added By Pankaj Dogra Date: 17 May 2016
	 * This method is used to click on Copied Application where application
	 * copied application can only view by Time Difference
	 * @param object
	 *            : It accept Two xpath that is divided into Four parts and both
	 *            parts<Br> are given as object parameter that are splitted by ","
	 *            First Two Xpath will conclude the link with Date column and Next
	 *            Two Xpaths will be donate the link
	 * 
	 * @param data
	 *            : Link Name on which you want to click
	 * */
	public String checkCopyApplicationAndClick(String object, String data)
	{
		try {
			int hour,value=0;
			keyUtil.browserSpecificPause(object, data);
			String objArr[] = object.split(Constants.Object_SPLIT);
			String date_xpathStart = OR.getProperty(objArr[0]);
			String date_xpathEnd = OR.getProperty(objArr[1]);
			String link_xpathStart = OR.getProperty(objArr[2]);
			String link_xpathEnd = OR.getProperty(objArr[3]);
			if (date_xpathStart == null || date_xpathEnd == null|| link_xpathEnd==null||link_xpathStart==null) {
				return Constants.KEYWORD_FAIL + "Object is not passed properly";
			}
			logger.debug(date_xpathStart + data.trim() + date_xpathEnd);
			int element = driver.findElements(By.xpath(date_xpathStart +data.trim()+ date_xpathEnd)).size();
		     for(app=1;app<=element;app++)
		        {
		    	 WebElement aplication=driver.findElement(By.xpath("("+date_xpathStart + data.trim() + date_xpathEnd + ")"+"[position()="+app+"]"));
		          String[] text=aplication.getText().split(" ");
		          for(int k=0;k<text.length-1;k++)
		             {
		        	   hour=Integer.parseInt(text[1].split(":")[k+1]);
		        	  if(app==1)
		        	  {
		        	    value=hour;
		        	    hour=0;
		        	    break;
		        	  }
		        	  
		   if(value>hour)
		   {
		driver.findElement(By.xpath("("+link_xpathStart + data.trim() + link_xpathEnd + ")"+"[position()="+(app-1)+"]")).click();
		keyUtil.browserSpecificPause(object, data);
		   return Constants.KEYWORD_PASS+"--Click Successfully on Copied Application";   
		   }
		   else if(hour<value) {
			   driver.findElement(By.xpath("("+link_xpathStart + data.trim() + link_xpathEnd + ")"+"[position()="+app+"]")).click();
				keyUtil.browserSpecificPause(object, data);
				   return Constants.KEYWORD_PASS+"--Click Successfully on Copied Application Link"; 
		   }
		  }
	       }		    	 
}
		catch(Exception e){
			return Constants.KEYWORD_FAIL + e.getLocalizedMessage();
		}
		
		return Constants.KEYWORD_FAIL;
}
	/**
	 * Added By Pankaj Dogra Date: 17 May 2016
	 * This method is used to click on Initial Application where application
	 * copied application can only view by Time Difference
	 * @param object
	 *            : It accept Two xpath that is divided into Four parts and both
	 *            parts<Br> are given as object parameter that are splitted by ","
	 *            First Two Xpath will conclude the link with Date column and Next
	 *            Two Xpaths will be donate the link
	 * 
	 * @param data
	 *            : Link Name on which you want to click
	 * */
	public String checkInitialApplicationAndClick(String object, String data)
	{
		try {
			int hour,value=0;
			keyUtil.browserSpecificPause(object, data);
			String objArr[] = object.split(Constants.Object_SPLIT);
			String date_xpathStart = OR.getProperty(objArr[0]);
			String date_xpathEnd = OR.getProperty(objArr[1]);
			String link_xpathStart = OR.getProperty(objArr[2]);
			String link_xpathEnd = OR.getProperty(objArr[3]);
			if (date_xpathStart == null || date_xpathEnd == null|| link_xpathEnd==null||link_xpathStart==null) {
				return Constants.KEYWORD_FAIL + "Object is not passed properly";
			}
			logger.debug(date_xpathStart + data.trim() + date_xpathEnd);
			int element = driver.findElements(By.xpath(date_xpathStart +data.trim()+ date_xpathEnd)).size();
		     for(app=1;app<=element;app++)
		        {
		    	 WebElement aplication=driver.findElement(By.xpath("("+date_xpathStart + data.trim() + date_xpathEnd + ")"+"[position()="+app+"]"));
		          String[] text=aplication.getText().split(" ");
		          for(int k=0;k<text.length-1;)
		             {
		        	   hour=Integer.parseInt(text[1].split(":")[k+1]);
		        	  if(app==1)
		        	  {
		        	    value=hour;
		        	    hour=0;
		        	    break;
		        	  }
		        	  
		   if(value<hour)
		   {
		driver.findElement(By.xpath("("+link_xpathStart + data.trim() + link_xpathEnd + ")"+"[position()="+(app-1)+"]")).click();
		keyUtil.browserSpecificPause(object, data);
		   return Constants.KEYWORD_PASS+"--Click Successfully on Initial Created Application";   
		   }
		   else if(hour<value) {
			   driver.findElement(By.xpath("("+link_xpathStart + data.trim() + link_xpathEnd + ")"+"[position()="+app+"]")).click();
				keyUtil.browserSpecificPause(object, data);
				   return Constants.KEYWORD_PASS+"--Click Successfully on Initail Created Application Link"; 
		   }
		  }
	       }		    	 
}
		catch(Exception e){
			return Constants.KEYWORD_FAIL + e.getLocalizedMessage();
		}
		
		return Constants.KEYWORD_FAIL;
}


}
