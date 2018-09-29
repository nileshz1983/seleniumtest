package com.tk20.seleniumuiflipassessment.testLink;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


//import org.apache.commons.collections.bag.SynchronizedBag;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tk20.seleniumuiflipassessment.base.Constants;

import testlink.api.java.client.*;

/**
*
* @author Ameet Kumar on April 17,2017
* function to update testlink
*/
public class GetTestcaseId{
	
	 public static boolean flag=false;
	
	
	public static String URL="https://tlink.tk20.com/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
	
	public static  JProgressBar progressBar =  null;
	public static JLabel counter=null;

	
	
	
	
	

/**
*
*This method recieves the list of pass,fail and skipped cases and 
*fetches internal id and send it to UpdateTestCase.java class for 
*case updations.
*It also generates a signature of those test cases whose Internal id
*is successfully fethched.
*/
	
	public static void updateTestlink(List<String> fail,List<String> pass,List<String> skip,String testsuitename,String DEVKEY)//throws Exception
	
	{
		
	
		
		
		int total_size=fail.size()+pass.size()+skip.size();
		 counter.setText("Cases left:"+total_size+"");
		//System.out.println(total_size+"");
		 float factor=100/total_size;
		 
		 progressBar.setMaximum((int) (total_size*factor));
		 int multi=0;
		
		 List<String> theList = new ArrayList<String>();
		 Set<String> updated = new TreeSet<String>();
		 List<String> theResult = new ArrayList<String>();
		 
		 Set<String> cur_updated = new TreeSet<String>();
		 List<String> cur_Result = new ArrayList<String>();
		 
		 TestCaseOperations o=new TestCaseOperations();
		 FileUtil util = new FileUtil();
		 Date d1=new Date();
		 
		 
	String notes="passed";
	
	String result="";
	
	Scanner sc=new Scanner(System.in);
	
Calendar now = Calendar.getInstance();
System.out.println("\n"+now.getTime());
	
	try{
		
		TestLinkAPIClient api=new TestLinkAPIClient(DEVKEY, URL);
		
		if(fail.isEmpty())
		{
			System.out.println("No Fail cases");
		}
		else
		{
		for(String testcasename : fail)
		{
			
		
		try{
		
	TestLinkAPIResults cx=api.getTestCaseIDByName(testcasename,Constants.TESTLINK_PROJECT,testsuitename);
//System.out.println(cx);
      String[] testcase=cx.toString().split(",");
      String testcase_id=testcase[3];
      
      String[] only_id=testcase_id.split("=");
      String id_number=only_id[1];
      int id_num=Integer.parseInt(id_number);
      
      System.out.println("--------------Testcase "+testcasename+" with Internal Id: "+id_num+" Updated-------------");
      
  
    
   String container= UpdateTestCase.sendNewResult(notes,"f",id_num,DEVKEY);
  
   
   if(container.contains("Success!")) 
 {
	   TestLink.status.setBackground(Color.GREEN);
	   TestLink.status.setText("Connection Established");
    theList= o.readLines();
	 for(String m:theList)
	 {
		  if(m.contains("TC"))
		   {
		 String[] parts=m.split("\\|");
		 String suite=parts[1].trim();
		 String test=parts[2].trim();
		 
		 if(suite.equals(testsuitename) && test.equals(testcasename))
			 util.removeLineFromFile(m);
		   } 
		  
		  
	
	
 }
	 Signature sign=new Signature(testsuitename,testcasename,"Fail","updated",d1,"Success");

	 Signature.writeSignanture(sign);
	 cur_updated.add(testcasename);
	  String[]col={"Updated","Not-Updated"};
	  cur_Result.clear();
		 cur_Result.addAll(cur_updated);
		 TestLink.updateTable(theResult,cur_Result,theResult,col);
    
 }
   else
	 {
		 updated.add(testcasename);
		 String[]col={"Updated","Not-Updated"};
		 theResult.clear();
		 theResult.addAll(updated);
		 TestLink.updateTable(theResult,cur_Result,theResult,col);
		 
		 Signature sign=new Signature(testsuitename,testcasename,"FAIL","Not-updated",d1,"Case not aligned with plan");
 		
		 Signature.writeSignanture(sign);
		 
	 }
 
 
		}
		catch(Exception ex)
		{
			System.out.println("***************************\nCould not update "+testcasename+"\n"+ex+"\n***********************************");
			Signature sign=new Signature(testsuitename,testcasename,"Fail","Not-updated",d1,"Testcase not found in TestLink");
			
			 Signature.writeSignanture(sign);
		    
			 updated.add(testcasename);
			 String[]col={"Updated","Not-Updated"};
			 theResult.clear();
			 theResult.addAll(updated);
			 TestLink.updateTable(theResult,cur_Result,theResult,col);
			
			
			TestLink.stat.setBackground(Color.red);
			
		
		}
		 multi++;
		 counter.setText("Cases left:"+(total_size-multi)+"");
		 int roundOffValue=Math.round((float)multi*factor);
		 progressBar.setValue(roundOffValue);
    	}
		}
		
		
		
		if(pass.isEmpty())
		{
			System.out.println("No Pass cases");
		}
		else
		{
			
		for(String testcasename : pass)
		{
			
			
try{
	
	
	TestLinkAPIResults cx=api.getTestCaseIDByName(testcasename,Constants.TESTLINK_PROJECT,testsuitename);
	
	String[] testcase=cx.toString().split(",");
      String testcase_id=testcase[3];
      String[] only_id=testcase_id.split("=");
      String id_number=only_id[1];
      int id_num=Integer.parseInt(id_number);
      System.out.println("--------------Testcase "+testcasename+" with Internal Id: "+id_num+" Updated-------------");
      
      int a=id_num;
   
  
   String container= UpdateTestCase.sendNewResult(notes,"p",a,DEVKEY);
 
   

    if(container.contains("Success!")) 
    {
    	  TestLink.status.setBackground(Color.GREEN);
    	   TestLink.status.setText("Connection Established");
    theList= o.readLines();
 
	 for(String m:theList)
	 {
		   if(m.contains("TC"))
		   {
		 String[] parts=m.split("\\|");
		 String suite=parts[1].trim();
		 String test=parts[2].trim();
		 
		 if(suite.equals(testsuitename) && test.equals(testcasename))
			 util.removeLineFromFile(m);
		   } 
	 }
	 
	 Signature sign=new Signature(testsuitename,testcasename,"PASS","updated",d1,"Success");
	
	 Signature.writeSignanture(sign);
	 
	 cur_updated.add(testcasename);
	  String[]col={"Updated","Not-Updated"};
	  cur_Result.clear();
		 cur_Result.addAll(cur_updated);
		 TestLink.updateTable(theResult,cur_Result,theResult,col);
		
    }
    else
    {
    	// updated.add(testcasename+" - Not updated");
    	 updated.add(testcasename);
		 String[]col={"Updated","Not-Updated"};
		 theResult.clear();
		 theResult.addAll(updated);
		 TestLink.updateTable(theResult,cur_Result,theResult,col);
    	 
    	 Signature sign=new Signature(testsuitename,testcasename,"PASS","Not-updated",d1,"Case not aligned with plan");
    		
    		 Signature.writeSignanture(sign);
    }
    
    
    
}
catch(Exception ex)
{
	System.out.println("***************************\nCould not update "+testcasename+"\n"+ex+"\n***********************************");
	 Signature sign=new Signature(testsuitename,testcasename,"PASS","Not-updated",d1,"Testcase not found in TestLink");
	
	 Signature.writeSignanture(sign);
   
		 //updated.add(testcasename+" - Not updated");
	 updated.add(testcasename);
	 String[]col={"Updated","Not-Updated"};
	 theResult.clear();
	 theResult.addAll(updated);
	 TestLink.updateTable(theResult,cur_Result,theResult,col);
		
		
	TestLink.stat.setBackground(Color.red);
	
}
multi++;
counter.setText("Cases left:"+(total_size-multi)+"");
int roundOffValue=Math.round((float)multi*factor);
progressBar.setValue(roundOffValue);
   }
		}
      
		
		
		
		if(skip.isEmpty())
		{
			System.out.println("No Skip cases");
		}
		else
		{
		
	for(String testcasename : skip)
		{
		
		
		try{
	TestLinkAPIResults cx=api.getTestCaseIDByName(testcasename,Constants.TESTLINK_PROJECT,testsuitename);
	

	 String[] testcase=cx.toString().split(",");
      String testcase_id=testcase[3];
      String[] only_id=testcase_id.split("=");
      String id_number=only_id[1];
      int id_num=Integer.parseInt(id_number);
      System.out.println("--------------Testcase "+testcasename+" with Internal Id: "+id_num+" Updated-------------");
    
      
      
   int a=id_num;
  
  
  String container=  UpdateTestCase.sendNewResult(notes,"e",a,DEVKEY);
  
 if(container.contains("Success!"))
 { 
	 TestLink.status.setBackground(Color.GREEN);
	   TestLink.status.setText("Connection Established");
	   
    theList= o.readLines();
	 for(String m:theList)
	 {
		  if(m.contains("TC"))
		   {
		 String[] parts=m.split("\\|");
		 String suite=parts[1].trim();
		 String test=parts[2].trim();
		 
		 if(suite.equals(testsuitename) && test.equals(testcasename))
			 util.removeLineFromFile(m);
		   } 
		
	 }
	 
	 Signature sign=new Signature(testsuitename,testcasename,"Skip","updated",d1,"Success");
	
	 Signature.writeSignanture(sign);
	 
	 cur_updated.add(testcasename);
	  String[]col={"Updated","Not-Updated"};
	  cur_Result.clear();
		 cur_Result.addAll(cur_updated);
		 TestLink.updateTable(theResult,cur_Result,theResult,col);
    
 }
 else
 {
	 //updated.add(testcasename+" - Not updated");
	 updated.add(testcasename);
	 String[]col={"Updated","Not-Updated"};
	 theResult.clear();
	 theResult.addAll(updated);
	 TestLink.updateTable(theResult,cur_Result,theResult,col);
	 
	 Signature sign=new Signature(testsuitename,testcasename,"SKIP","Not-updated",d1,"Case not aligned with plan");
		
	 Signature.writeSignanture(sign);
 }

   
		}
		catch(Exception ex)
		{
			System.out.println("***************************\nCould not update "+testcasename+"\n"+ex+"\n***********************************");
			
			 Signature sign=new Signature(testsuitename,testcasename,"Skip","Not-updated",d1,"Testcase not found in TestLink");
			 Signature.writeSignanture(sign);
		    
				// updated.add(testcasename+" - Not updated");
			 updated.add(testcasename);
			 String[]col={"Updated","Not-Updated"};
			 theResult.clear();
			 theResult.addAll(updated);
			 TestLink.updateTable(theResult,cur_Result,theResult,col);
				
				
				TestLink.stat.setBackground(Color.red);	
			
			
		}
    	
		 multi++;
		 counter.setText("Cases left:"+(total_size-multi)+"");
		 int roundOffValue=Math.round((float)multi*factor);
		 progressBar.setValue(roundOffValue);
      
		}
		}
      
		
		theList= o.readLines();
		 for(String m:theList)
		 {
			 if(m.contains(testsuitename+" Total Cases Saved"))
				 util.removeLineFromFile(m);
		 }
		
		 
	/* String[]col={"Not Updated Cases"};
		 theResult.addAll(updated);
		 TestLink.updateTable(theResult,theResult,theResult,col);
		*/
	
		
		/* String[]col={"Updated","Not-Updated"};
		 theResult.addAll(updated);
		 cur_Result.addAll(cur_updated);
		 TestLink.updateTable(theResult,cur_Result,theResult,col);
		 */
		TestLinkAPIResults plans_of_project=api.getProjectTestPlans("Core Application");
	    //System.out.print("\nThe plans in the project are\n"+plans_of_project.toString());   
		TestLinkAPIResults suites_in_plan=api.getTestSuitesForTestPlan("Core Application","9.1.0 - Automation Test Plan");
				//System.out.print("\nThe suites in the plan are\n"+suites_in_plan.toString());   
      
      
	
          Calendar wow = Calendar.getInstance();
  
 		System.out.println("\n"+wow.getTime());
 		  System.out.print("\nTotal time taken to update= "+(wow.getTimeInMillis()-now.getTimeInMillis())/1000+" Seconds");
	
 		  
 		  String time=""+(wow.getTimeInMillis()-now.getTimeInMillis())/1000;
 		 
 		  if(flag)
 		  {
 			 TestLink.stat.setBackground(Color.red);	  
 			TestLink.stat.setText("      Error exists");
 		  }
 		  else{
 		 TestLink.stat.setBackground(Color.green);
 		 TestLink.stat.setText("        Success");
 		
 		  }
	}
	catch(Exception e){
 
		
	result=TestLinkAPIResults.TEST_FAILED;

	System.out.print("Execution failed--"+e);
	TestLink.stat.setBackground(Color.red);
	
	if(e.toString().contains("The call to the xml-rpc client failed.") || e.toString().contains("Can not authenticate client: invalid developer key"))
	{
		TestLink.status.setBackground(Color.red);
		TestLink.status.setText(" No Connection found");
		
	}
	
	TestLink.stat.setText("        Error exists");
	 
	

	}

	TestLink.disableButton();
		 }
	
	
	

}
