package com.tk20.seleniumuiflipassessment.testLink;

import java.io.*;
import java.util.*;


/**
*
* @author Ameet Kumar on April 17,2017
* This class writes signature to signature.txt file
*/
public class Signature implements Serializable{
	String suitename;
	String id;  
	String status;  
	 String result;
	 Date d1;
	 String reason;
	 
	 public Signature(String suitename,String id,String status,String result,Date d1,String reason) {  
	this.suitename=suitename;
		this.id = id;  
	 this.result=result;
	 this.status = status;  
		  this.d1=d1;
		  this.reason=reason;
		 
		 }  
	 
	public  static void writeSignanture(Object digisign)throws Exception{  
		
		String path=System.getProperty("user.dir")+ "/src/com/tk20/seleniumuiflipassessment/Testlink/signature.txt";
	
		  
		  FileOutputStream fout=new FileOutputStream(path,true);  
		  ObjectOutputStream out=new ObjectOutputStream(fout);  
		  
		  out.writeObject(digisign);
		
		  
		 }  
	
	
	}

