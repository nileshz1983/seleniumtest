package com.tk20.seleniumuiflipassessment.testLink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Ameet Kumar on April 17,2017
* 
* This class contains methods to read and write data 
* to reserve.txt file.
*/

public class TestCaseOperations {

	   String path=System.getProperty("user.dir")+ "/src/com/tk20/seleniumuiflipassessment/Testlink/reserve.txt";
	
	/*public static void main(String[] args) {
		TestCaseOperations o=new TestCaseOperations();
	
		 List<String> theList = new ArrayList<String>();
		 
		try {
			
			theList= o.readLines();
			 for(String m:theList)
			 {
				 if(m.contains("TC"))
				 System.out.println(m);
			 }
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		 

	}*/

	public void writeTestCases(String data)
	{
		   try{    
			
			   
			   File fout = new File(path);
			   
			 
				FileOutputStream fos = new FileOutputStream(fout,true);
			 
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	
					bw.write(data);
					bw.newLine();
		           bw.close();
	          }catch(Exception e){System.out.println(e);}    
	        
	}
	
	
	  public List readLines() throws IOException
	  {
		  BufferedReader in = null;
		  
		  try {
			  
			  in = new BufferedReader(new FileReader(path));
		     
		
		      String str;
		      while ((str = in.readLine()) != null) {
		    	
		          myList.add(str);
		      }
		  } catch (FileNotFoundException e) {
		      e.printStackTrace();
		  } catch (IOException e) {
		      e.printStackTrace();
		  } finally {
		      if (in != null) {
		          in.close();
		      }
		  }
		  return myList;
	  }
	  List<String> myList = new ArrayList<String>();
	  
	
	
}
