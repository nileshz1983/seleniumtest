/*
 * Signature Reader 
 */
package com.tk20.seleniumuiflipassessment.testLink;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Ameet Kumar on April 17,2017
 * 
 * This class reads the signature file and writes the 
 * results in the table.
 */
public class SignatureRead extends javax.swing.JFrame {

   
	   String path=System.getProperty("user.dir")+ "/src/com/tk20/seleniumuiflipassessment/Testlink/signature.txt";
   
	   public SignatureRead() throws IOException {
        initComponents();
        setTitle("Signature Reader");
        setLocationRelativeTo(null);
        jTable1.setRowHeight(23);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
   int rows=readLines();
       String[] col={"Suite Name","Test Case","Result","TestLink Status","Date updated","Reason"};
       
        Object case_table[][]=new Object[rows][6];    
        int i=0;
      
      try{
    	  FileInputStream fis=new FileInputStream(path);
    	 
     while(true)
      {
    	
    	 ObjectInputStream in=new ObjectInputStream(fis);  
    	 Signature s=(Signature)in.readObject();
    	
  
    case_table[i][0]=s.suitename;
	case_table[i][1]=s.id;
	case_table[i][2]=s.status;
	case_table[i][3]=s.result;
	case_table[i][4]=s.d1;
	case_table[i][5]=s.reason;
     
      i++;
     
      if(s==null)
      {
    	 
    	  break;
      }
      }
      }
      catch(Exception ex)
      {
    	  if(ex.toString().contains("java.io.EOFException"))
		     {
    		 
		    	 System.out.print("Reading finished");
		     }
    	  else
    	  {
    		  JOptionPane.showMessageDialog(null,"Data Corrupted in file" );
    	  }   
    	 //System.out.println(ex);
      }
      finally
      {
    	
         //  fis.close();   
           // in.close();   
      }
      
      JTableHeader theader=jTable1.getTableHeader();
	   theader.setBackground(Color.green);
	   DefaultTableModel dm=new DefaultTableModel(case_table,col);
	   jTable1.setModel(dm);	
	   jTable1.getColumnModel().getColumn(0).setPreferredWidth(180);
	   jTable1.getColumnModel().getColumn(1).setPreferredWidth(20);
	   jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
	   jTable1.getColumnModel().getColumn(3).setPreferredWidth(35);
	   jTable1.getColumnModel().getColumn(4).setPreferredWidth(160);
	   jTable1.getColumnModel().getColumn(5).setPreferredWidth(170);
    
    }

    //This method returns the size of signature.txt file
	   
    public int readLines() throws IOException
	  {
    	
    	 List<String> myList = new ArrayList<String>();
	
		  
		  try {   
			  
			  FileInputStream fis=new FileInputStream(path);
		    	 
		    	 
			    //while((s=(Signature)in.readObject())!=null)
			     while(true)
			      {
			    	
			    	 ObjectInputStream in=new ObjectInputStream(fis);  
			    	 Signature s=(Signature)in.readObject();
		    	
		          myList.add(s.toString());
			      }
		  }
	   
		   catch (Exception e) {
		     if(e.toString().contains("java.io.EOFException"))
		     {
		    	// System.out.print("Reading finished");
		     }
		     else
	    	  {
	    		  JOptionPane.showMessageDialog(null,"Data Corrupted in file" );
	    	  }   
			  // e.printStackTrace();
		  } 
		  
		  return myList.size();
	  
}
    /**
     * WARNING: Do NOT modify this code.-Ameet Kumar
     * 
     * This method initializes the Table that carries the 
     * signature data.
     * It is called in the constructor of signatureRead class.
     */
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Signature Reader");

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
               
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Developed By -Ameet Kumar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 1004, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(475, 475, 475))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignatureRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignatureRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignatureRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignatureRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new SignatureRead().setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }*/


    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
  
}
