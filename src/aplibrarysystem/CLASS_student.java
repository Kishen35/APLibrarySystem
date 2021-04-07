/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplibrarysystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author K15H3N
 */
public class CLASS_student extends CLASS_users {
    
    public static int id;
    public static String name, email, phoneNumber; //static because parent constructor not yet executed
    public String role;
    
    public CLASS_student(){
     // empty constructor to call the delete function later
    }
    
    public CLASS_student(int id, String name, String email, String phoneNumber){
        super(id, name, email, phoneNumber);
        role = "student";
        
            try{
                //Open the file
                String filename = "src/apls_DBTXT/borrower_t.txt";
                FileWriter fw = new FileWriter(filename, true); //append
                PrintWriter outputFile = new PrintWriter(fw);

                //Write Data
                outputFile.print(id +
                    ":" + name +
                    ":" + role +
                    ":" + email +
                    ":" + phoneNumber
                        + "\r\n");

                outputFile.close();
            } catch (IOException ex){}
    }
    
    APLS_users updateUser = new APLS_users(); 
    public CLASS_student(int id, String name, String email, String phoneNumber, int rowCount, int columnCount, int selectedRow){
        super(id, name, email, phoneNumber);
        role = "student";
        
        DefaultTableModel model = (DefaultTableModel)updateUser.borrowerList.getModel(); //model from JTable

        model.setValueAt(id, selectedRow, 0);
        model.setValueAt(name, selectedRow, 1);
        model.setValueAt(role, selectedRow, 2);
        model.setValueAt(email, selectedRow, 3);
        model.setValueAt(phoneNumber, selectedRow, 4);               

            //update from JTable to textfile
            try {
                File borrowerTXTF = new File("src/apls_DBTXT/borrower_t.txt");
                FileWriter fw = new FileWriter(borrowerTXTF);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0; i < rowCount; i++){ //rows in borrowing List table
                    for(int j = 0; j < columnCount; j++){//columns in borrowing list table
                        bw.write(updateUser.borrowerList.getValueAt(i, j).toString()+":"); //use delimiter ":" to seperate
                    }
                    bw.newLine();
                }

                bw.close();
                fw.close();
            
            } catch (IOException ex) {}
    }
}
