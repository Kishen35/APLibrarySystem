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
public class CLASS_librarian extends CLASS_users {
    
    public static int id;
    public static String name, email, phoneNumber; //static because parent constructor not yet executed
    public String username, password, role;
    
    public CLASS_librarian(){
    // empty constructor to call the delete function later
    }
    
    //ADD
    public CLASS_librarian(int id, String name, String email, String phoneNumber, String username, String password){
        super(id, name, email, phoneNumber);
        role = "librarian";
        
        try{
        //Open the file
        String filename = "src/apls_DBTXT/librarian_t.txt";
        FileWriter fw = new FileWriter(filename, true); //append
        PrintWriter outputFile = new PrintWriter(fw);

        //Write Data
        outputFile.print(id +
            ":" + name +
            ":" + username +
            ":" + password +
            ":" + email +
            ":" + phoneNumber
            + "\r\n");

        outputFile.close();
        
        updateUser.setPassword(password);
        } catch (IOException ex){}
    }
    
    //UPDATE
    APLS_users updateUser = new APLS_users(); 
    public CLASS_librarian(int id, String name, String email, String phoneNumber, String username, String password, int rowCount, int columnCount, int selectedRow){
        super(id, name, email, phoneNumber);
        role = "librarian";
        
        DefaultTableModel model = (DefaultTableModel)updateUser.librarianList.getModel(); //model from JTable

        model.setValueAt(id, selectedRow, 0);
        model.setValueAt(name, selectedRow, 1);
        model.setValueAt(username, selectedRow, 2);
        
        for(int i = 0; i< rowCount; i++){ //load passwords first
            model.setValueAt(updateUser.getPassword(i), i, 3);
        }
        
        model.setValueAt(password, selectedRow, 3);
        model.setValueAt(email, selectedRow, 4);
        model.setValueAt(phoneNumber, selectedRow, 5);               

            //update from JTable to textfile
            try {
                File librarianTXTF = new File("src/apls_DBTXT/librarian_t.txt");
                FileWriter fw = new FileWriter(librarianTXTF);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0; i < rowCount; i++){ //rows in borrowing List table
                    for(int j = 0; j < columnCount; j++){//columns in borrowing list table
                        bw.write(updateUser.librarianList.getValueAt(i, j).toString()+":"); //use delimiter ":" to seperate
                    }
                    bw.newLine();
                }

                bw.close();
                fw.close();
            
            } catch (IOException ex) {}
    }
    
    //DELETE
    @Override //because librarian uses different textfile than other users
    public void deleteUser(int rowCount, int columnCount, int selectedRow){
        DefaultTableModel model = (DefaultTableModel)updateUser.librarianList.getModel(); //model from JTable
        
        for(int i = 0; i< rowCount; i++){ //load passwords first
            model.setValueAt(updateUser.getPassword(i), i, 3);
        }
        
        model.removeRow(selectedRow);
        
        //update from JTable to textfile
        try {
            File librarianTXTF = new File("src/apls_DBTXT/librarian_t.txt");
            FileWriter fw = new FileWriter(librarianTXTF);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < rowCount - 1; i++){ //rows in borrowing List table //row -1 because has been removed
                for(int j = 0; j < columnCount; j++){//columns in borrowing list table
                    bw.write(updateUser.librarianList.getValueAt(i, j).toString()+":"); //use delimiter ":" to seperate
                }
                bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (IOException ex) {}
    }
}
