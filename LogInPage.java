import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HashPasswordT2.HashClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class LogInPage extends Database {

	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInPage frame = new LogInPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInPage() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginEditorAccount = new JLabel("Login Editor Account");
		lblLoginEditorAccount.setFont(new Font("Calibri", Font.BOLD, 40));
		lblLoginEditorAccount.setBounds(12, 60, 359, 45);
		contentPane.add(lblLoginEditorAccount);
		
		JLabel label_1 = new JLabel("email:");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 31));
		label_1.setBounds(12, 144, 113, 29);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("password:");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 31));
		label_2.setBounds(12, 229, 134, 29);
		contentPane.add(label_2);
		
		JButton btnSubmitAnArticle = new JButton("Submit an Article");
		btnSubmitAnArticle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSubmitAnArticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnSubmitAnArticle.setBounds(181, 385, 337, 43);
		contentPane.add(btnSubmitAnArticle);
		
		JButton button_1 = new JButton("Log In");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					exceptionCheck();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String user = textField_1.getText();
				String pass = textField.getText();
				//System.out.println(user + " " + pass);
				//valentin 1val
				
				if(userLogIn(user,pass) == 1) {
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					
					ChiefEditorGUI chief = new ChiefEditorGUI();
					chief.setVisible(true);
				}

			}
		});
		button_1.setBounds(438, 314, 134, 45);
		contentPane.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 31));
		textField_1.setColumns(10);
		textField_1.setBounds(150, 144, 425, 40);
		contentPane.add(textField_1);
		
		JButton btnBack = new JButton("<- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				
			    JFrame frmWelcomePage;
				WelcomePage window = new WelcomePage();
				window.frmWelcomePage.setVisible(true);
			}
		});
		btnBack.setBounds(542, 27, 134, 43);
		contentPane.add(btnBack);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()) {
					textField.setEchoChar((char)0);
				}
				else {
					textField.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox.setBounds(150, 319, 241, 35);
		contentPane.add(chckbxNewCheckBox);
		
		textField = new JPasswordField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 31));
		textField.setBounds(150, 227, 425, 45);
		contentPane.add(textField);
	}
	
	
	//==========================Data Base Functions =====================================
	//database check connection
	public static void exceptionCheck() throws SQLException {
    	Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
			System.out.println("Got Connection!");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (con != null) {
				con.close();
			}
		}
    }
	
	//authentication
	public static int userLogIn(String username,String password) {
		String user = username;
    	String pass = password;
    	
    	List<Editor> persInfo = new ArrayList<Editor>(); 
    	persInfo = getEditor();
    	pass = encryptThisString(pass);
    	
    	
    	int success = 0; // to display error message
    	for(Editor i : persInfo) {
    		if(user.equals(i.getEmail()) && pass.equals(i.getPassword())) {
    			System.out.println("Access Granted");
    			success = 1;
    		}
    		
    		System.out.println(i.getPassword());
    	}
    	
    	if(success == 0) {
    			JOptionPane.showMessageDialog(null, "Access Denied"); 
    	}
    	
    		
    	return success;
	}
	
	
	//hashing
	//====================hashing
		public static String encryptThisString(String input) { 
	        try { 
	            // getInstance() method is called with algorithm SHA-224 
	            MessageDigest md = MessageDigest.getInstance("SHA-224"); 
	  
	            // digest() method is called 
	            // to calculate message digest of the input string 
	            // returned as array of byte 
	            byte[] messageDigest = md.digest(input.getBytes()); 
	  
	            // Convert byte array into signum representation 
	            BigInteger no = new BigInteger(1, messageDigest); 
	  
	            // Convert message digest into hex value 
	            String hashtext = no.toString(16); 
	  
	            // Add preceding 0s to make it 32 bit 
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	  
	            // return the HashText 
	            return hashtext; 
	        } 
	  
	        // For specifying wrong message digest algorithms 
	        catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 
	    } 
}
