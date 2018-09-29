package com.tk20.seleniumuiflipassessment.testLink;

import java.awt.Color;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tk20.seleniumuiflipassessment.base.Constants;

/**
*
* @author Ameet Kumar on April 17,2017
* 
* This class recieves the internal id and updates result of
* test cases in testlink.
* It return the status back to GetTestCaseId.java class where it 
* is transferred tp signature file.
*/
public class UpdateTestCase {
	
	public static String URL="https://tlink.tk20.com/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
	
	public static String container=null;
	
	 public static String sendNewResult(String notes, String status,int num,String DEVKEY) {
			try {
				
				XmlRpcClient rpcClient;
				XmlRpcClientConfigImpl config;

				config = new XmlRpcClientConfigImpl();
				config.setServerURL(new java.net.URL(URL));
				rpcClient = new XmlRpcClient();
				rpcClient.setConfig(config);
			
				ArrayList<Object> params = new ArrayList<Object>();
				Hashtable<String, Object> executionData = new Hashtable<String, Object>();
				executionData.put("devKey", DEVKEY);
				executionData.put("testplanid",Constants.AUTOMATION_PLAN_ID);
				executionData.put("testcaseid",num);
				executionData.put("guess", true	);
				executionData.put("notes", notes);
				executionData.put("status", status);
				params.add(executionData);

				Object[] result = (Object[]) rpcClient.execute("tl.reportTCResult",
						params);

				//System.out.println("Result was:\n");
				for (int i = 0; i < result.length; i++) {
					container="Fail";
					Map item = (Map) result[i];
					
					TestLink.status.setBackground(Color.GREEN);
					   TestLink.status.setText("Connection Established");
					/*System.out.println("Keys: " + item.keySet().toString()
							+ " values: " + item.values().toString());*/
					
					if(!item.values().toString().contains("Success!"))
					{
						System.out.println("Result was:\n");
						System.out.println("Keys: " + item.keySet().toString()
								+ " values: " + item.values().toString());
					GetTestcaseId.flag=true;
					}
					
					container=item.values().toString();
				}
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
				TestLink.stat.setBackground(Color.red);
				TestLink.stat.setText("       Error exists");
			} catch (XmlRpcException e) {
				e.printStackTrace();
				TestLink.stat.setBackground(Color.red);
				TestLink.stat.setText("       Error exists");
				
				if(e.toString().contains("The xml-rpc call to TestLink API method tl.getProjects failed."))
				{
					TestLink.status.setBackground(Color.red);
					TestLink.status.setText("No Connection found");
					
				}
				
			}

			 return container;
	 }
	
	

}
