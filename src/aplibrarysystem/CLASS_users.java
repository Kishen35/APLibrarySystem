/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplibrarysystem;

import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author K15H3N
 */
abstract class CLASS_users {
    
    public int id;
    public String name, email, phoneNumber;
    
    public CLASS_users(){
     // empty constructor to call the delete function later
    }
    
    public CLASS_users(int id, String name, String email, String phoneNumber){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    //delete user
    APLS_users updateUser = new APLS_users();
    public void deleteUser(int rowCount, int columnCount, int selectedRow){
        DefaultTableModel model = (DefaultTableModel)updateUser.borrowerList.getModel(); //model from JTable
        model.removeRow(selectedRow);
        
            //update from JTable to textfile
            try {
                File borrowerTXTF = new File("src/apls_DBTXT/borrower_t.txt");
                FileWriter fw = new FileWriter(borrowerTXTF);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0; i < rowCount - 1; i++){ //rows in borrowing List table //row -1 because has been removed
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
