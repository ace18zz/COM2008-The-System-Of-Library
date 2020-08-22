import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Color;

public class ChangeDetails extends Database{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor editor = new Editor(1,"a","b","c","d","e","f");
					ChangeDetails window = new ChangeDetails(editor);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangeDetails(Editor editor) {
			
	    String updatedTitle;
	    String updatedForename;
	    String updatedSurname;
	    String updatedAffiliation;
	    String updatedEmail;
	    String updatedPassword;
	    
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		frame.setBounds(100, 100, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChangeDetails = new JLabel("Change details");
		lblChangeDetails.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblChangeDetails.setBounds(300, 10, 500, 60);
		frame.getContentPane().add(lblChangeDetails);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitle.setBounds(100, 90, 64, 57);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblFurname = new JLabel("Forename:");
		lblFurname.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblFurname.setBounds(100, 160, 119, 57);
		frame.getContentPane().add(lblFurname);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSurname.setBounds(100, 230, 113, 57);
		frame.getContentPane().add(lblSurname);
		
		JLabel lblNewLabel = new JLabel("Affiliation:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(100, 300, 124, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEmail.setBounds(100, 370, 86, 60);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(100, 440, 119, 60);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(568, 100, 400, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(568, 170, 400, 35);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(568, 240, 400, 35);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(568, 310, 400, 35);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(568, 380, 400, 35);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblConfirmPassword.setBounds(100, 510, 215, 35);
		frame.getContentPane().add(lblConfirmPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(568, 450, 400, 35);
		frame.getContentPane().add(passwordField);
		String password = new String(passwordField.getPassword());

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(568, 510, 400, 35);
		frame.getContentPane().add(passwordField_1);
		String confirmedPassword = new String(passwordField_1.getPassword());

		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(520, 108, 25, 27);
		frame.getContentPane().add(checkBox);
		if (checkBox.isSelected()==true) {
			updatedTitle = textField.getText();
		}else {
			updatedTitle = editor.getTitle();
		}
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setBounds(520, 178, 25, 27);
		frame.getContentPane().add(checkBox_1);
		if (checkBox_1.isSelected()==true) {
			updatedForename = textField_1.getText();
		}else {
			updatedForename = editor.getForename();
		}
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setBounds(520, 248, 25, 27);
		frame.getContentPane().add(checkBox_2);
		if (checkBox_2.isSelected()==true) {
			updatedSurname = textField_2.getText();
		}else {
			updatedSurname = editor.getSurname();
		}
		
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setBounds(520, 318, 25, 27);
		frame.getContentPane().add(checkBox_3);
		if (checkBox_3.isSelected()==true) {
			updatedAffiliation = textField_3.getText();
		}else {
			updatedAffiliation = editor.getAffiliation();
		}
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.setBounds(520, 388, 25, 27);
		frame.getContentPane().add(checkBox_4);
		if (checkBox_4.isSelected()==true) {
			updatedEmail = textField_4.getText();
		}else {
			updatedEmail = editor.getEmail();
		}
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.setBounds(520, 458, 25, 27);
		frame.getContentPane().add(checkBox_5);
		if (checkBox_5.isSelected()==true) {
			updatedPassword = password;
		}else {
			updatedPassword = editor.getPassword();
		}
		
		
		JLabel label_title = new JLabel(editor.getName());
		label_title.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_title.setBackground(new Color(240, 240, 240));
		label_title.setBounds(250, 90, 260, 57);
		frame.getContentPane().add(label_title);
		
		JLabel label_forename = new JLabel(editor.getForename());
		label_forename.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_forename.setBounds(250, 168, 258, 49);
		frame.getContentPane().add(label_forename);
		
		JLabel label_surname = new JLabel(editor.getSurname());
		label_surname.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_surname.setBounds(250, 245, 255, 42);
		frame.getContentPane().add(label_surname);
		
		JLabel label_affiliation = new JLabel(editor.getAffiliation());
		label_affiliation.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_affiliation.setBounds(250, 310, 263, 35);
		frame.getContentPane().add(label_affiliation);
		
		JLabel label_email = new JLabel(editor.getEmail());
		label_email.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_email.setBounds(250, 370, 255, 57);
		frame.getContentPane().add(label_email);
		
		JButton btnNewButton = new JButton("Confirm Changes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(password ==confirmedPassword & checkBox_5.isSelected()==true) {
				updateEditor(updatedTitle,updatedForename,updatedSurname,updatedAffiliation,updatedEmail,updatedPassword);
				}
				else {
					System.out.println("Sorry,your password and confirmed password is not same");
				}
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(324, 560, 361, 35);
		frame.getContentPane().add(btnNewButton);
	}
}
