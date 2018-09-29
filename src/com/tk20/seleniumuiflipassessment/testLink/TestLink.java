package com.tk20.seleniumuiflipassessment.testLink;
import com.tk20.seleniumuiflipassessment.base.DataProvider;









import com.tk20.seleniumuiflipassessment.util.ReportUtil;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.commons.io.FileUtils;

import com.ibm.icu.text.SimpleDateFormat;
import com.seaglasslookandfeel.*;



/**
*
* @author Ameet Kumar on April 17,2017
* 
* This is the main GUI testlink class That connect all the classes
* in testlink package and plays a central role in all the operations.
* Note:In order to make changes to this file,you need to be familiar with 
* java swing API.
*/
public class TestLink extends JFrame implements ActionListener{
	
	
	JFrame frame;
	static JButton result;
	JButton showresult;
	
	JButton signature;
	JRadioButton updateFail;
	JComboBox suites;
	JComboBox sub_suites;
	JLabel sub_suite_name;
	  public static  javax.swing.JScrollPane jScrollPane1;
	    public static javax.swing.JTable jTable1;
	    
	    TestCaseOperations o=new TestCaseOperations();
		JButton ico=new JButton();
	

	Font largeFontPLAIN = new Font("New Times Roman", Font.PLAIN,22);
	 ButtonGroup group = new ButtonGroup();
	public static JLabel title;
	 JLabel suite_name;
	
	 JLabel key_value;
	 JTextField key;
	 JRadioButton updateAll;
	 JRadioButton update;
	 String path="";
	 JRadioButton custom;
	 final JTextField custom_values;
	 final JLabel customize;
	public static JLabel status;
	public static JLabel stat;
	JLabel total_cases;
	
		String currDir = System.getProperty("user.dir");
		String sep = System.getProperty("file.separator");
	 
	 List<String> custom_cases=new ArrayList<String>();
	 List<String> cases_range=new ArrayList<String>();
	 List<String> PassCases=new ArrayList<String>();
	 List<String> FailCases=new ArrayList<String>();
	 List<String> SkipCases=new ArrayList<String>();
	
	 
	 List<String> failed_l=new ArrayList<String>();
	 List<String> passed_l=new ArrayList<String>();
	 List<String> skipped_l=new ArrayList<String>();
	 
	 TreeSet<String> failed=new TreeSet<String>();  
	 TreeSet<String> passed=new TreeSet<String>();  
	 TreeSet<String> skipped=new TreeSet<String>();  
	 
	 static List<String> a=new ArrayList<String>();
	 static List<String> b=new ArrayList<String>();
	 static List<String> c=new ArrayList<String>();
	 
	 Set<String> set = new HashSet<String>();
	 
	//List<String> fail, List<String> pass, List<String> skip
	 static long[] t={0,2};
	 
	public TestLink(List<String> fail, List<String> pass, List<String> skip) throws NoSuchMethodException, SecurityException, IOException{
		
		
		frame = new JFrame();
		 String path = currDir + sep + "externalFiles" + sep + "uploadFiles" + sep + "testlink.png";
		 ImageIcon img = new ImageIcon(path);
		 frame.setIconImage(img.getImage());
		 frame.setTitle("TestLink Selenium Integration");
		
	
		failed.addAll(fail);
		passed.addAll(pass);
		skipped.addAll(skip);
		
		failed_l.addAll(failed);
		passed_l.addAll(passed);
		skipped_l.addAll(skipped);
		
		
         frame.setSize(780,455);
		 frame.setLocationRelativeTo(null);
		 frame.setAlwaysOnTop(true);
		 frame.setLayout(null);
		
		 int stack=o.readLines().size();
			
		 Container content = frame.getContentPane();
		// GetTestcaseId.progressBar = new JProgressBar();
		
		 
		 GetTestcaseId.progressBar =  new JProgressBar();
		 GetTestcaseId.progressBar .setValue(0);
		 GetTestcaseId.progressBar.setStringPainted(true);
		
		 GetTestcaseId.progressBar.setForeground(Color.BLACK);
		
		 GetTestcaseId.progressBar.setBounds(15,245,150,20);
		 content.add(GetTestcaseId.progressBar);
		 
		 GetTestcaseId.counter=new JLabel();
		 GetTestcaseId.counter.setBounds(15,193,170,20);
		 Font font = new Font("Courier", Font.BOLD,16);
		 GetTestcaseId.counter.setFont(font);
		 frame.add(GetTestcaseId.counter);
		 
		 JLabel progress=new JLabel("<html><i>Progress<i><html>");
		 progress.setBounds(65,220,100,18);
		 frame.add(progress);
		
		 JButton enabler = new JButton("E");
		 enabler.setBounds(420,60, 40, 30);
		 enabler.setToolTipText("Update Enabler");
		        frame.add(enabler);
		        enabler.addActionListener(this);
		        enabler.addActionListener(new ActionListener(){
	        	    public void actionPerformed(ActionEvent e) {
	        	    
	        	    	result.setEnabled(true);
						
	        	    	 
	        	    	
	        	      }
	        	  });
		        
		        
		        JButton get_sheet = new JButton("S");
		        get_sheet.setBounds(470,60, 40, 30);
		        get_sheet.setToolTipText("Enter Sheet name");
				        frame.add(get_sheet);
				        get_sheet.addActionListener(this);
				        get_sheet.addActionListener(new ActionListener(){
			        	    public void actionPerformed(ActionEvent e) {
			        	    
			        	    	//String name = JOptionPane.showInputDialog(frame, "Enter the Suite name");
			        	    
			        	    	String name =JOptionPane.showInputDialog(
			        	    	        frame, 
			        	    	        "Enter the suite name\nNote:Make sure you put the same suite name in Suite.xls file", 
			        	    	        "Suite name input", 
			        	    	        JOptionPane.WARNING_MESSAGE
			        	    	    );
			        	    	
			        	    	if(!name.equals(""))
			        	    	{
			        	    		passed_l.clear();
									failed_l.clear();
									skipped_l.clear();
			        	    	ReportUtil report = new ReportUtil();
			       		    	
			       		    	try{
			       		    		frame.dispose();
			       		    	report.generateHTMLReport(t,"Build",name);
			       		    //	Thread.sleep(1000);
			       		    
			       		   	//FileUtils.deleteDirectory(new File(ReportUtil.result_FolderName));
			       		    	}
			      		    	catch(Exception ex)
			      		    	{
			      		    		System.out.println(ex);
			      		    	}
			        	    	 
			        	    	}
			        	    	else
			        	    	{
			        	    		JOptionPane.showMessageDialog(frame,"Please provide the sheet name!","Error message",JOptionPane.ERROR_MESSAGE);
			        	    	}
			        	      }
			        	  });
				        
		 
		
			ico.setBounds(10,10,50,50);
			ico.setToolTipText("You got Test cases in your reserve file!");
			ico.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			
			frame.add(ico);
			
			if(stack>0)
			{
				ico.setVisible(true);
			}
			else
			{
				ico.setVisible(false);
			}
			
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(currDir + sep + "externalFiles" + sep + "uploadFiles" + sep + "notification.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			 ico.setIcon(imageIcon);
		 
		 
		 
		 FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/externalFiles/config/TestLinkconfig.properties");
			final Properties CONFIG= new Properties();
			CONFIG.load(fs);
			
		
		title=new JLabel("TestLink Test Cases Update");
		title.setBounds(190,0,300,50);
		title.setFont(largeFontPLAIN);
		 frame.add(title);
		 
		 suite_name=new JLabel("Suite Name :");
			suite_name.setBounds(50,60,100,30);
			
			 frame.add(suite_name);
			 
			
			
			 final String[] Home={"Home suite","4003","4004","4005"};
			 final String[] Surveys={"3002","3003","3004","3005"};
			 final String[] Artifacts={"4002 (Sharing)","2003","2004","2005"};
			 final String[] Administartion={"Sub Admin","1003","1004","1005"};
			 
			 final String[] All_suites={"Accreditation_Management","Administration","Administration_Form_Builder",
					 "Automated_Scheduling","Admission_Application","Advisement","Applications","Artifacts",
					 "Autosave","CK_Editor","Course_Evaluation","Course_Registration","Courses_Course_Binder",
					 "Courses_Assignment/Project","Courses_Libraries","Courses_Observation","Courses_Quiz/Exam",
					 "Document_Room","EdTPA","EDVA_&_S3","Faculty_Qualification","FE_Bulk_Placement","Field_Experience",
					 "Home","i18N_Accreditation_Management","i18N_Administration","i18N_Login","Legacy_Planning_Assessment_Planning",
					 "Legacy_Planning_Curriculum_maps","Legacy_Planning_Dashboard","Legacy_Planning_Juried_Assessment",
					 "Legacy_Planning_Strategic_Planning","Login","LTI","Mobile_Onsite","New_Planning","Portfolios",
					 "Security","Surveys","Timelogs_Template"};
			 
			
			 
			 sub_suite_name=new JLabel("Sub Suite Name :");
			 sub_suite_name.setBounds(50,100,100,30);
				
				 frame.add(sub_suite_name);
				 
				 sub_suites=new JComboBox();
				 sub_suites.setBounds(180,100,190,30);
				 
				 frame.add(sub_suites);
			 
			 suites=new JComboBox();
			 suites.setBounds(180,60,190,30);
			 
			 for(int i=0;i<All_suites.length;i++)
			 {
			 
			 suites.addItem(All_suites[i]);
			 }
			
			 frame.add(suites);
			 suites.addActionListener(new ActionListener(){
	        	    public void actionPerformed(ActionEvent e) {
	        	    	JComboBox combo = (JComboBox)e.getSource();
	                    String suite_name = (String)combo.getSelectedItem();
	                    
	                    sub_suites.removeAllItems();
	                  
	                    int i=0;
	                    
	                    while(CONFIG.containsKey(suite_name+"["+i+"]"))
	                    {
	                    	sub_suites.addItem(CONFIG.getProperty(suite_name+"["+i+"]"));
	                    	i++;
	                    	
	                    }
	            	
	        	      }
	        	  });
			 
			
			
			 
			 key_value=new JLabel("Enter your key :");
			 key_value.setBounds(50,150,100,30);
			 frame.add(key_value);
			 
			 
	         key=new JTextField();
	         key.setBounds(180,150,350,25);
	         frame.add(key);
	         
	         custom_values=new JTextField();
	         custom_values.setBounds(310,310,300,25);
	         custom_values.setVisible(false);
	         frame.add(custom_values);
	         
	         customize=new JLabel("Enter Testcases seprated by commas or enter a range");
	         customize.setBounds(310,280,340,20);
	         customize.setVisible(false);
	         frame.add(customize);
	         
	         updateAll=new JRadioButton("Update All Test Cases (this will include skipped cases also)");
	         updateAll.setBounds(200,190,400,30);
	         frame.add(updateAll);
	         updateAll.addActionListener(new ActionListener(){
	        	    public void actionPerformed(ActionEvent e) {
	        	    	custom_values.setVisible(false);
	        	    	 customize.setVisible(false);
	        	      }
	        	  });
	         
	         update=new JRadioButton("Update Pass only");
	         update.setBounds(200,220,200,30);
	         frame.add(update);
	         update.addActionListener(new ActionListener(){
	        	    public void actionPerformed(ActionEvent e) {
	        	    	custom_values.setVisible(false);
	        	    	 customize.setVisible(false);
	        	      }
	        	  });
	         
	         updateFail=new JRadioButton("Update Pass and Fail only");
	         updateFail.setBounds(200,250,200,30);
	         frame.add(updateFail);
	         updateFail.addActionListener(new ActionListener(){
	        	    public void actionPerformed(ActionEvent e) {
	        	    	custom_values.setVisible(false);
	        	    	 customize.setVisible(false);
	        	      }
	        	  });
	         
	        
	         
	         custom=new JRadioButton("Custom");
	         custom.setBounds(200,280,100,30);
	         frame.add(custom);
	         custom.addActionListener(new ActionListener(){
	        	    public void actionPerformed(ActionEvent e) {
	        	    	custom_values.setVisible(true);
	        	    	 customize.setVisible(true);
	        	    	 
	        	    	
	        	      }
	        	  });
			 
	         group.add(update);
	         group.add(updateAll);
	         group.add(updateFail);
	         group.add(custom);
	         
	       result = new JButton("Update");
	       result.setBounds(170, 345, 100, 30);
	        frame.add(result);
	        result.addActionListener(this);
	        result.addActionListener(new ActionListener(){
        	    public void actionPerformed(ActionEvent e) {
        	    	resultactionPerformed(e);
        	    	
        	    	
        	    	
        	      }
        	  });
	        
	        JButton open_sheet = new JButton("Save Cases");
	        open_sheet.setBounds(290, 345, 100, 30);
		        frame.add(open_sheet);
		        open_sheet.addActionListener(this);
		        open_sheet.addActionListener(new ActionListener(){
	        	    public void actionPerformed(ActionEvent e) {
	        	    
							OpenSheetactionPerformed(e);
						
	        	    	
	        
	        	      
	        	    }
	        	  });
		        
		        signature = new JButton("Signature");
		        signature.setBounds(410, 345, 100, 30);
			        frame.add( signature);
			        signature.addActionListener(this);
			        signature.addActionListener(new ActionListener(){
		        	    public void actionPerformed(ActionEvent e) {
		        	    
							try {
								new SignatureRead().setVisible(true);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
		        	    	 
		        	    	
		        	      }
		        	  });
		        
		        
		        
		        ico.addActionListener(this);
		        ico.addActionListener(new ActionListener(){
	        	    public void actionPerformed(ActionEvent e) {
	        	    
							updateTestCasesinFile(e);
						
	        	    	 
	        	    	
	        	      }
	        	  });
		        
		        
		        
		        
	        
		        int fail_num=failed_l.size();
		        int pass_num=passed_l.size();
		        int skip_num=skipped_l.size();
		        
		      int total_tests=fail_num+pass_num+skip_num;
		     
		        
		       int chec[]={fail_num,pass_num,skip_num}; 
		       
		       int check_len=chec.length;
		       

		       for(int i=0;i<chec.length;i++)
		       {
		   while(i>0){
		if(chec[i-1]>chec[i])
		{
			int x=chec[i-1];
			chec[i-1]=chec[i];
			chec[i]=x;
		
		}
		break;
		       }
		  
		}
		       
		  
		     
		       JLabel conn=new JLabel("<html><p style='margin-left:17px'>Connection</p></html>"); 
			     conn.setBounds(35,300,100, 10);
			     conn.setFont(new Font("Helvetica", Font.PLAIN, 9));
			     frame.add(conn);
			     
			     
			     JLabel con=new JLabel("<html><p style='margin-left:14px'>Cases Status</p></html>"); 
			     con.setBounds(35,340,100, 10);
			     con.setFont(new Font("Helvetica", Font.PLAIN, 9));
			     frame.add(con);
		       
		      status=new JLabel();
		       status.setOpaque(true);
		       status.setBackground(Color.yellow);
		 status.setFont(new Font("Helvetica", Font.PLAIN, 11));
		     //status.setBounds(560, 385,90, 30);
		 status.setBounds(25, 310,110, 30);
		    frame.add(status);
		     
		     stat=new JLabel();
		     stat.setOpaque(true);
		       stat.setBackground(Color.yellow);
		      // stat.setBounds(670, 385,90, 30);
		       stat.setBounds(25, 350,110, 30);
			     frame.add(stat);
		       
			     
			     
		      
		       JLabel n=new JLabel("STATUS");
		       n.setBounds(55, 260, 50, 50);
		       frame.add(n);
		       
			     JPanel   p = new JPanel();
			       p.setBorder(new BevelBorder (BevelBorder.RAISED));
			       p.setBounds(10, 300, 140, 90);
			    frame.add(p);
		
			     
		       
		        jScrollPane1 = new javax.swing.JScrollPane();
		        jTable1 = new javax.swing.JTable();
		        
		        jScrollPane1.setViewportView(jTable1);
		        jScrollPane1.setBounds(560, 0, 210, 380);
		        frame.add(jScrollPane1);
		        
		        total_cases=new JLabel();
		        total_cases.setBounds(560,385,210,40);
		        frame.add(total_cases);
		        
		        JLabel check_warning=new JLabel();
		        check_warning.setBounds(100,400,450,20);
		        check_warning.setOpaque(true);
		        frame.add(check_warning);
		        if(total_tests==ReportUtil.automatedCases)
		        {
		        	//check_warning.setBackground(Color.green);
		        	check_warning.setText("<html><p style='margin-left:80px'>Cases count verified with script.OK</p></html>");

		        }
		        else
		        {
		        	check_warning.setBackground(Color.red);
		        	check_warning.setText("<html><p><font color='white'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Warning:You case count does not match with script,Cases in script = "+ReportUtil.automatedCases+"</font></p><html>");
		        }
		        total_cases.setText("<html>Total cases = "+total_tests+"<br>Passed = "+pass_num+",Failed = "+fail_num+",Skipped = "+skip_num+"</html>");
		        
		        String[] col={"Passed","Failed","Skipped"};
		        
		        
		        Object case_table[][]=new Object[chec[check_len-1]][3];
		        
		      
		        
		        for(int y=0;y<pass_num;y++)
		        {
		        	case_table[y][0]=passed_l.get(y);
		        }
		        	   
		        for(int y=0;y<fail_num;y++)
		        {
		        	case_table[y][1]=failed_l.get(y);
		        }
		        for(int y=0;y<skip_num;y++)
		        {
		        	case_table[y][2]=skipped_l.get(y);
		        }
		        
		  
		        
		        JTableHeader theader=jTable1.getTableHeader();
		        theader.setBackground(Color.green);
		        DefaultTableModel dm=new DefaultTableModel(case_table,col);
		        jTable1.setModel(dm);	
	             
	       
		 frame.setVisible(true);
		
	
		

	}
	

public static void main(String[] args)
{
	
	 try {
 
			//for windows look
		     //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		
		 //for swing look

		 //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	
		
		UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		   new TestLink(a,b,c);
		 }
		  catch (Exception e) {
		      e.printStackTrace();
		    }
}




public void resultactionPerformed(ActionEvent e) {
	
	
	
	int suite_value1=sub_suites.getItemCount();
	//String sub_suite_value=sub_suites.getSelectedItem().toString();
	  String user_key=key.getText().toString();
	//String sub_suite_value="";
	
	
	if(user_key.equals("") && suite_value1!=0)
	{
		JOptionPane.showMessageDialog(frame,"Please Enter your key!\nKey is required in order to update results to TestLink","Error message",JOptionPane.ERROR_MESSAGE);
	}
	else if(suite_value1==0 && !user_key.equals(""))
	{
		JOptionPane.showMessageDialog(frame,"Please select the sub suite\nNo Sub suite is selected!","Error message",JOptionPane.ERROR_MESSAGE);
	}
	else if(suite_value1==0 || user_key.equals(""))
	{
		JOptionPane.showMessageDialog(frame,"No Sub suite is selected\nNo Key is provided","Error message",JOptionPane.ERROR_MESSAGE);
	}
	
	
	else
	{
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			
			
			   @Override
			   protected Void doInBackground() throws Exception {
				
				   int suite_value1=sub_suites.getItemCount();
					//String sub_suite_value=sub_suites.getSelectedItem().toString();
					  String user_key=key.getText().toString();
					String sub_suite_value="";
					
					
					if(user_key.equals("") && suite_value1!=0)
					{
						JOptionPane.showMessageDialog(frame,"Please Enter your key!\nKey is required in order to update results to TestLink","Error message",JOptionPane.ERROR_MESSAGE);
					}
					else if(suite_value1==0 && !user_key.equals(""))
					{
						JOptionPane.showMessageDialog(frame,"Please select the sub suite\nNo Sub suite is selected!","Error message",JOptionPane.ERROR_MESSAGE);
					}
					else if(suite_value1==0 || user_key.equals(""))
					{
						JOptionPane.showMessageDialog(frame,"No Sub suite is selected\nNo Key is provided","Error message",JOptionPane.ERROR_MESSAGE);
					}
					
					
					else
					{
				if(updateAll.isSelected())
					{
						sub_suite_value=sub_suites.getSelectedItem().toString();
						
						int n=JOptionPane.showConfirmDialog(frame,"Are you Sure you want to update all the test results to TestLink\nThis will include Pass,Fail and Skipped cases","Conformation message",JOptionPane.YES_NO_CANCEL_OPTION);
						 
						if(n==JOptionPane.YES_OPTION)
						{
							
				
							/* status.setBackground(Color.green);
							status.setText("     Updating finished");*/
							GetTestcaseId.updateTestlink(failed_l, passed_l, skipped_l,sub_suite_value,user_key);
							passed_l.clear();
							failed_l.clear();
							skipped_l.clear();	
						
						}
						
					}
					
					
					else if(update.isSelected())
					{
						sub_suite_value=sub_suites.getSelectedItem().toString();
						
						int n=JOptionPane.showConfirmDialog(frame,"Are you Sure you want to update only Passed cases to TestLink","Conformation message",JOptionPane.YES_NO_CANCEL_OPTION);
						 
						if(n==JOptionPane.YES_OPTION)
						{
							failed_l.clear();
							skipped_l.clear();

							/* status.setBackground(Color.green);
							status.setText("     Updating finished");*/
							GetTestcaseId.updateTestlink(failed_l, passed_l, skipped_l,sub_suite_value,user_key);
							
							
						}
					}
					
					
					else if(updateFail.isSelected())
					{
						sub_suite_value=sub_suites.getSelectedItem().toString();
						int n=JOptionPane.showConfirmDialog(frame,"Are you Sure you want to update only Pass and Fail cases to TestLink","Conformation message",JOptionPane.YES_NO_CANCEL_OPTION);
						 
						if(n==JOptionPane.YES_OPTION)
						{
							//passed.clear();
							skipped_l.clear();

							/* status.setBackground(Color.green);
							status.setText("     Updating finished");*/
							GetTestcaseId.updateTestlink(failed_l, passed_l, skipped_l,sub_suite_value,user_key);
							
							
						
						}
						
						
					}
					
					else if(custom.isSelected())
					{
						sub_suite_value=sub_suites.getSelectedItem().toString();
					String cases=custom_values.getText().toString();
					if(cases.equals(""))
					{
						JOptionPane.showMessageDialog(frame,"Custom cases field cannot be empty!\nPlease Enter Case Numbers","Error message",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
					String[] case_values=cases.split(",");
					for(int i=0;i<=case_values.length-1;i++)
					{
						custom_cases.add(case_values[i].trim());
					}
					for(String h:custom_cases)
					{
						if(h.contains("-"))
						{
							String[] num=h.split("-");
							int first_range=Integer.parseInt(num[0]);
							int last_range=Integer.parseInt(num[1]);
						
							for(int i=first_range;i<=last_range;i++)
							{
								cases_range.add("TC-"+i);
							}
							
						}
						else{
							cases_range.add("TC-"+h);
						}
					
					}
					
					set.addAll(cases_range);
					
					for(String i:set)
					{
						if(failed_l.contains(i))
						{
							FailCases.add(i);
							
						}
						else if(passed_l.contains(i))
						{
							PassCases.add(i);
							
						}
						else if(skipped_l.contains(i))
						{
							SkipCases.add(i);
							
						}
						System.out.println("All cases "+i);
						
					}
					int n=JOptionPane.showConfirmDialog(frame,"Are you Sure you want to update provided test cases\n"+cases,"Conformation message",JOptionPane.YES_NO_CANCEL_OPTION);
					 
					if(n==JOptionPane.YES_OPTION)
					{
						
//						 status.setBackground(Color.green);
//						status.setText("     Connection OK");
						GetTestcaseId.updateTestlink(FailCases, PassCases, SkipCases,sub_suite_value,user_key);
					
					
					}
					
					
					
					}
					
					}	 

					}		 
					

			    return null;
			   }
			  }; 
		
		worker.execute();
	}
	/*}
	if(updateAll.isSelected())
	{
		//sub_suite_value=sub_suites.getSelectedItem().toString();
		
		int n=JOptionPane.showConfirmDialog(frame,"Are you Sure you want to update all the test results to TestLink\nThis will include Pass,Fail and Skipped cases","Conformation message",JOptionPane.YES_NO_CANCEL_OPTION);
		 
		if(n==JOptionPane.YES_OPTION)
		{
			
//worker.execute();
			 status.setBackground(Color.green);
			status.setText("     Updating finished");
			GetTestcaseId.updateTestlink(failed_l, passed_l, skipped_l,sub_suite_value,user_key);
			passed_l.clear();
			failed_l.clear();
			skipped_l.clear();	
		
		}
		
	}
	
	
	else if(update.isSelected())
	{
		sub_suite_value=sub_suites.getSelectedItem().toString();
		
		int n=JOptionPane.showConfirmDialog(frame,"Are you Sure you want to update only Passed cases to TestLink","Conformation message",JOptionPane.YES_NO_CANCEL_OPTION);
		 
		if(n==JOptionPane.YES_OPTION)
		{
			failed_l.clear();
			skipped_l.clear();

			 status.setBackground(Color.green);
			status.setText("     Updating finished");
			GetTestcaseId.updateTestlink(failed_l, passed_l, skipped_l,sub_suite_value,user_key);
			
			
		}
	}
	
	
	else if(updateFail.isSelected())
	{
		sub_suite_value=sub_suites.getSelectedItem().toString();
		int n=JOptionPane.showConfirmDialog(frame,"Are you Sure you want to update only Pass and Fail cases to TestLink","Conformation message",JOptionPane.YES_NO_CANCEL_OPTION);
		 
		if(n==JOptionPane.YES_OPTION)
		{
			//passed.clear();
			skipped_l.clear();

			 status.setBackground(Color.green);
			status.setText("     Updating finished");
			GetTestcaseId.updateTestlink(failed_l, passed_l, skipped_l,sub_suite_value,user_key);
			
			
		
		}
		
		
	}
	
	else if(custom.isSelected())
	{
		sub_suite_value=sub_suites.getSelectedItem().toString();
	String cases=custom_values.getText().toString();
	if(cases.equals(""))
	{
		JOptionPane.showMessageDialog(frame,"Custom cases field cannot be empty!\nPlease Enter Case Numbers","Error message",JOptionPane.ERROR_MESSAGE);
	}
	else
	{
	String[] case_values=cases.split(",");
	for(int i=0;i<=case_values.length-1;i++)
	{
		custom_cases.add(case_values[i].trim());
	}
	for(String h:custom_cases)
	{
		if(h.contains("-"))
		{
			String[] num=h.split("-");
			int first_range=Integer.parseInt(num[0]);
			int last_range=Integer.parseInt(num[1]);
		
			for(int i=first_range;i<=last_range;i++)
			{
				cases_range.add("TC-"+i);
			}
			
		}
		else{
			cases_range.add("TC-"+h);
		}
	
	}
	
	set.addAll(cases_range);
	
	for(String i:set)
	{
		if(failed_l.contains(i))
		{
			FailCases.add(i);
			
		}
		else if(passed_l.contains(i))
		{
			PassCases.add(i);
			
		}
		else if(skipped_l.contains(i))
		{
			SkipCases.add(i);
			
		}
		System.out.println("All cases "+i);
		
	}
	int n=JOptionPane.showConfirmDialog(frame,"Are you Sure you want to update provided test cases\n"+cases,"Conformation message",JOptionPane.YES_NO_CANCEL_OPTION);
	 
	if(n==JOptionPane.YES_OPTION)
	{
		
		 status.setBackground(Color.green);
		status.setText("     Updating finished");
		GetTestcaseId.updateTestlink(FailCases, PassCases, SkipCases,sub_suite_value,user_key);
	
	
	}
	
	
	
	}
	
	}	 

	}		 
	*/

}




	

public static void disableButton()
{
	result.setEnabled(false);
	
	
	
}

public void clearLists()
{
	passed_l.clear();
	failed_l.clear();
	skipped_l.clear();
}


public void OpenSheetactionPerformed(ActionEvent e) 
{
	 if(passed_l.size()==0 && failed_l.size()==0 && skipped_l.size()==0)
		{
			JOptionPane.showMessageDialog(frame,"You don't have any Test cases to save","Error message",JOptionPane.ERROR_MESSAGE);
		}
	 else
	 {
	 
	int suite_value1=sub_suites.getItemCount();
	
	if(suite_value1==0)
	{
		JOptionPane.showMessageDialog(frame,"Sub suite field cannot be blank","Error message",JOptionPane.ERROR_MESSAGE);
	}
	else
	{
	
	Date d=new Date();
	SimpleDateFormat f=new SimpleDateFormat();
	String date=f.format(d);
	
	String suite=suites.getSelectedItem().toString();
	String sub_suite=sub_suites.getSelectedItem().toString();
	
	//DataProvider.OpenSheet();
	for(String pass:passed_l)
	{
		o.writeTestCases(suite+"|"+sub_suite+"| "+pass+" | PASS |"+date);
	}
	for(String fail:failed_l)
	{
		o.writeTestCases(suite+"|"+sub_suite+"| "+fail+" | FAIL |"+date);
	}
	for(String skip:skipped_l)
	{
		o.writeTestCases(suite+"|"+sub_suite+"| "+skip+" | SKIP |"+date);
	}
	
	int total=passed_l.size()+failed_l.size()+skipped_l.size();
	if(total>0)
	{
		ico.setVisible(true);
	}
	
	String finish="\n\n\n*****[END]*****----"+sub_suite+" Total Cases Saved = "+total+" at "+date+"-----*****[END]*****\n\n\n";
	o.writeTestCases(finish);
	
	
	passed.clear();
	failed.clear();
	skipped.clear();
	
	
	passed_l.clear();
	failed_l.clear();
	skipped_l.clear();
	
	   String[] col={"Passed","Failed","Skipped"};
	updateTable(failed_l,passed_l,skipped_l,col);
	
	
	JOptionPane.showMessageDialog(frame,"Cases saved successfully!");
	
	int fail_num=failed_l.size();
     int pass_num=passed_l.size();
     int skip_num=skipped_l.size();
     
   int total_tests=fail_num+pass_num+skip_num;
   total_cases.setText("<html>Total cases = "+total_tests+"<br>Passed = "+pass_num+",Failed = "+fail_num+",Skipped = "+skip_num+"</html>");	 
	
	}	
}
}
	
	public void updateTestCasesinFile(ActionEvent e)
	{
					
			int suite_value1=sub_suites.getItemCount();
		
	
		
		if(suite_value1==0)
		{
			JOptionPane.showMessageDialog(frame,"Sub suite field cannot be blank","Error message",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
		
			
			
	 if(passed.size()>0 || failed.size()>0 || skipped.size()>0)
		{
			JOptionPane.showMessageDialog(frame,"Operation Prohibited\nYou cannot use this feature after running the sheet\nYou already got cases in Table either save them or update them first","Error message",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			
			
			pass_set.clear();
			fail_set.clear();
			skip_set.clear();
			theList.clear();
			
			   String[] col={"Passed","Failed","Skipped"};
			 updateTable(failed_l,passed_l,skipped_l,col);
			
			String sub_suite=sub_suites.getSelectedItem().toString();
			boolean y=false;
				try {
					
					theList= o.readLines();
					 for(String tc:theList)
					 {
						 if(tc.contains("TC"))
						 {
							 String[] parts=tc.split("\\|");
							 
							 String suite=parts[1].trim();
							 String testcase=parts[2].trim();
							 String result=parts[3].trim();
							 
							 if(suite.equals(sub_suite))
							 {
								
								 y=true;
								 if(result.equals("PASS"))
								{
									pass_set.add(testcase);
								}
								else if(result.equals("FAIL"))
								{
									fail_set.add(testcase);
								}
								else if(result.equals("SKIP"))
								{
									skip_set.add(testcase);
								}
								 
								
								 
							 }
							/* else
							 {
								JOptionPane.showMessageDialog(frame,"Oops No data found for the requested suite");
							break;
							 }*/
							
							 
							
							 
						 }
					 }
					 passed_l.clear();
						failed_l.clear();
						skipped_l.clear();
					 
					 passed_l.addAll(pass_set);
					 failed_l.addAll(fail_set);
					 skipped_l.addAll(skip_set);
					 
					 int fail_num=failed_l.size();
				        int pass_num=passed_l.size();
				        int skip_num=skipped_l.size();
				        
				      int total_tests=fail_num+pass_num+skip_num;
				      total_cases.setText("<html>Total cases = "+total_tests+"<br>Passed = "+pass_num+",Failed = "+fail_num+",Skipped = "+skip_num+"</html>");	 
				      String[] column={"Passed","Failed","Skipped"};
				      updateTable(failed_l,passed_l,skipped_l,column);
				if(!y)
				{
					JOptionPane.showMessageDialog(frame,"Oops No data found for the requested suite");
				}
					
					
					 if(theList.size()==0)
					 {
						 ico.setVisible(false);
					 }
					 
				} catch (IOException ex) {
					
					ex.printStackTrace();
				}
		}
		}
	
	}
	
	
	public static void updateTable(List<String> fail,List<String> pass,List<String> skip,String[] col)
	{
		int fail_num=fail.size();
	    int pass_num=pass.size();
	    int skip_num=skip.size();
	    
	  int total_tests=fail_num+pass_num+skip_num;
	 
	    
	   int chec[]={fail_num,pass_num,skip_num}; 
	   
	   int check_len=chec.length;
	   

	   for(int i=0;i<chec.length;i++)
	   {
	while(i>0){
	if(chec[i-1]>chec[i])
	{
	int x=chec[i-1];
	chec[i-1]=chec[i];
	chec[i]=x;

	}
	break;
	   }

	}
	  
	   
	   
	   Object case_table[][]=new Object[chec[check_len-1]][3];
	   
	 
	   
	   for(int y=0;y<pass_num;y++)
	   {
	   	case_table[y][0]=pass.get(y);
	   }
	   	   
	   for(int y=0;y<fail_num;y++)
	   {
	   	case_table[y][1]=fail.get(y);
	   }
	   for(int y=0;y<skip_num;y++)
	   {
	   	case_table[y][2]=skip.get(y);
	   }
	   

	   
	   JTableHeader theader=jTable1.getTableHeader();
	   theader.setBackground(Color.green);
	   DefaultTableModel dm=new DefaultTableModel(case_table,col);
	   jTable1.setModel(dm);	
	    
	}

@Override
public void actionPerformed(ActionEvent e) {
	
	
}


	
List<String> theList = new ArrayList<String>();
List<String> updatedResults = new ArrayList<String>();
Set<String> pass_set = new TreeSet<String>();
Set<String> fail_set = new TreeSet<String>();
Set<String> skip_set = new TreeSet<String>();

}
