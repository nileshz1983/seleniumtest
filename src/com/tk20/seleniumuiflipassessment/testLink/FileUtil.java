package com.tk20.seleniumuiflipassessment.testLink;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
*
* @author Ameet Kumar on April 17,2017
* 
* The method in this class deletes the line by line from
* reserve.txt file of the successfully updated cases in 
* TestLink
*/
public class FileUtil {

    public void removeLineFromFile(String lineToRemove) {

        try {
        	  String path=System.getProperty("user.dir")+ "/src/com/tk20/seleniumuiflipassessment/Testlink/reserve.txt";
        		        
            File inFile = new File(path);
            
            
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            // Construct the new file that will later be renamed to the original
            // filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            // Read from the original file and write to the new
            // unless content matches data to bbbe removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().contains(lineToRemove)) {
                	//System.out.println(lineToRemove);
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            // Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
   /* public static void main(String[] args) {
        FileUtil util = new FileUtil();
    	TestCaseOperations o=new TestCaseOperations();
    	//	o.writeTestCases();
    		 List<String> theList = new ArrayList<String>();
    		 
    		try {
    			
    			theList= o.readLines();
    			 for(String m:theList)
    			 {
    				 if(m.contains("Accreditation_Management"))
    					 util.removeLineFromFile(m);
    			 }
    			
    		} catch (IOException e) {
    			
    			e.printStackTrace();
    		}
		 
       
    }*/
  
}