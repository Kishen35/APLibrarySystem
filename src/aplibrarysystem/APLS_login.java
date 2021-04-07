/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplibrarysystem;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.util.NoSuchElementException;
/**
 *
 * @author K15H3N
 */
public class APLS_login extends JFrame implements ActionListener{
    
    public static void main(String[] args) {
        APLS_login login = new APLS_login();
    }
    
    public APLS_login() {
        setSize(350, 400);
        setLocationRelativeTo(null); //center form
        setTitle("APLS Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Border Layout
        setLayout(new BorderLayout(0,0));
        
        initGUI();
        setVisible(true);
    }
    
    JTextField usernameTxt = new JTextField();
    JPasswordField passwordTxt = new JPasswordField();
        
    
    public void initGUI(){
        //HEADER
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setLayout(new FlowLayout());
        //Title
        JLabel title = new JLabel("APLS");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        title.setForeground(Color.BLACK); //Text Colour
        header.add(title);
        
        add("North", header);
        
        //LOGIN CONTENT
        JPanel loginForm = new JPanel();
        loginForm.setBackground(new Color(255,226,180)); //PEACH
        loginForm.setLayout(new GridLayout(5,0,0,0));
        loginForm.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20)); //right, left and bottom padding
        //Greeting
        JLabel greeting = new JLabel("Login to APLS");
        greeting.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        greeting.setForeground(new Color(226, 38, 88)); //Text Colour PINK
        loginForm.add(greeting);
        
        //Enter Username:
        JLabel usernameLabel = new JLabel("Enter your username :");
        usernameLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        loginForm.add(usernameLabel);
        //Username
        usernameTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        usernameTxt.setForeground(new Color(226, 38, 88)); //Text Colour PINK
        usernameTxt.setBackground(new Color(255,226,180)); //PEACH
        usernameTxt.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(226, 38, 88))); //bottom PINK border only
        loginForm.add(usernameTxt);
        
        //Enter Password:
        JLabel passwordLabel = new JLabel("Enter your password :");
        passwordLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        loginForm.add(passwordLabel);
        //Password
        passwordTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        passwordTxt.setForeground(new Color(226, 38, 88)); //Text Colour PINK
        passwordTxt.setBackground(new Color(255,226,180)); //PEACH
        passwordTxt.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(226, 38, 88))); //bottom PINK border only
        loginForm.add(passwordTxt);
        
        add("Center", loginForm);
        
        //LOGIN BUTTON
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(226, 38, 88)); //PINK
        loginButton.addActionListener(this);
        add("South", loginButton);
    }
    
    public String username;
    private String password;
    public void actionPerformed(ActionEvent e) {     
        try{
            File librarian = new File("src/apls_DBTXT/librarian_t.txt");
            Scanner inputFile = new Scanner(librarian);
            
            do{
                String librarianDetails = inputFile.nextLine(); //Read the next line
                String[] splitter = librarianDetails.split(":"); //Split the line by using the delimiter ":" (semicolon) and store into array
                
                //Credentials
                username = splitter[2].toString();
                password = splitter[3].toString();
            } while((username.equals(usernameTxt.getText()) == false) || (password.equals(String.valueOf(passwordTxt.getPassword())) == false)); //stop only if get the right credentials
            inputFile.close();
            
            /* Create and display the form */
            APLS_home home = new APLS_home();
            home.setUsername(username);
            home.setVisible(true);
            
            //hide login form
            this.setVisible(false);
        } catch (NoSuchElementException ex){ //triggered when the credentials are not found, prompt user and clear textbox
            JOptionPane.showMessageDialog(null, "Please re-enter your username and password", "Invalid Credentials", JOptionPane.ERROR_MESSAGE); //pronpt user
            //clear textbox
            usernameTxt.setText("");
            passwordTxt.setText("");
        } catch (IOException ex) {}
    }
    
}


/*REFERENCES

JavaExample, 2019. JLabel Set Font Example. [Online] 
Available at: https://www.java-examples.com/jlabel-set-font-example
[Accessed 26 February 2021].

JavaTpoint, n.a.. Java String compare. [Online] 
Available at: https://www.javatpoint.com/string-comparison-in-java
[Accessed 6 March 2021].

Stack Overflow, 2013. How to position the form in the center screen?. [Online] 
Available at: https://stackoverflow.com/questions/9543320/how-to-position-the-form-in-the-center-screen
[Accessed 26 February 2021].

*/