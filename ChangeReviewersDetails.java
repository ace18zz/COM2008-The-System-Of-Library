import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
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
import javax.swing.JComponent;

import java.awt.Color;

public class ChangeReviewersDetails extends Database{

	JFrame frame;
	private JTextField textTitle;
	private JTextField textForename;
	private JTextField textSurname;
	private JTextField textAffiliation;
	private JTextField textEmail;
	private JPasswordField passwordField;
	private JPasswordField ConfirmpasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reviewer reviewer = new Reviewer(1,"a","b","c","d","e","f");
					ChangeReviewersDetails window = new ChangeReviewersDetails(reviewer);
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
	public ChangeReviewersDetails(Reviewer reviewer) {
	    
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
		
		textTitle = new JTextField();
		textTitle.setBounds(568, 100, 400, 35);
		frame.getContentPane().add(textTitle);
		textTitle.setColumns(10);
		
		textForename = new JTextField();
		textForename.setBounds(568, 170, 400, 35);
		frame.getContentPane().add(textForename);
		textForename.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setBounds(568, 240, 400, 35);
		frame.getContentPane().add(textSurname);
		textSurname.setColumns(10);
		
		textAffiliation = new JTextField();
		textAffiliation.setBounds(568, 310, 400, 35);
		frame.getContentPane().add(textAffiliation);
		textAffiliation.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(568, 380, 400, 35);
		frame.getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblConfirmPassword.setBounds(100, 510, 215, 35);
		frame.getContentPane().add(lblConfirmPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(568, 450, 400, 35);
		frame.getContentPane().add(passwordField);
		String password = new String(passwordField.getPassword());

		ConfirmpasswordField = new JPasswordField();
		ConfirmpasswordField.setBounds(568, 510, 400, 35);
		frame.getContentPane().add(ConfirmpasswordField);
		String confirmedPassword = new String(ConfirmpasswordField.getPassword());

		
		JCheckBox checkTitle = new JCheckBox("");
		checkTitle.setBounds(520, 108, 25, 27);
		frame.getContentPane().add(checkTitle);
		
		
		JCheckBox checkForename = new JCheckBox("");
		checkForename.setBounds(520, 178, 25, 27);
		frame.getContentPane().add(checkForename);
		
		JCheckBox checkSurname = new JCheckBox("");
		checkSurname.setBounds(520, 248, 25, 27);
		frame.getContentPane().add(checkSurname);
		
		JCheckBox checkAffiliation = new JCheckBox("");
		checkAffiliation.setBounds(520, 318, 25, 27);
		frame.getContentPane().add(checkAffiliation);
		
		JCheckBox checkEmail = new JCheckBox("");
		checkEmail.setBounds(520, 388, 25, 27);
		frame.getContentPane().add(checkEmail);
		
		
		JCheckBox checkPassword = new JCheckBox("");
		checkPassword.setBounds(520, 458, 25, 27);
		frame.getContentPane().add(checkPassword);
		
		
		
		JLabel label_title = new JLabel(reviewer.getTitle());
		label_title.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_title.setBackground(new Color(240, 240, 240));
		label_title.setBounds(250, 90, 260, 57);
		frame.getContentPane().add(label_title);
		
		JLabel label_forename = new JLabel(reviewer.getForename());
		label_forename.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_forename.setBounds(250, 168, 258, 49);
		frame.getContentPane().add(label_forename);
		
		JLabel label_surname = new JLabel(reviewer.getSurname());
		label_surname.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_surname.setBounds(250, 245, 255, 42);
		frame.getContentPane().add(label_surname);
		
		JLabel label_affiliation = new JLabel(reviewer.getAffiliation());
		label_affiliation.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_affiliation.setBounds(250, 310, 263, 35);
		frame.getContentPane().add(label_affiliation);
		
		JLabel label_email = new JLabel(reviewer.getEmail());
		label_email.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_email.setBounds(250, 370, 255, 57);
		frame.getContentPane().add(label_email);
		
		JButton btnNewButton = new JButton("Confirm Changes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String updatedTitle;
			    String updatedForename;
			    String updatedSurname;
			    String updatedAffiliation;
			    String updatedEmail;
			    String updatedPassword;
				//check title 
				if (checkTitle.isSelected()==true) {
					updatedTitle = textTitle.getText();
				}else {
					updatedTitle = reviewer.getTitle();
				}
				//check email
				if (checkEmail.isSelected()) {
					updatedEmail = textEmail.getText();
				}else {
					updatedEmail = reviewer.getEmail();
				}
				//check password
				if (checkPassword.isSelected()==true) {
					updatedPassword = password;
				}else {
					updatedPassword = reviewer.getPassword();
				}
				//check affiliation
				if (checkAffiliation.isSelected()==true) {
					updatedAffiliation = textAffiliation.getText();
				}else {
					updatedAffiliation = reviewer.getAffiliation();
				}
				//check surname
				if (checkSurname.isSelected()==true) {
					updatedSurname = textSurname.getText();
				}else {
					updatedSurname = reviewer.getSurname();
				}
				//check forename
				if (checkForename.isSelected()==true) {
					updatedForename = textForename.getText();
				}else {
					updatedForename = reviewer.getForename();
				}
				if(checkPassword.isSelected()) {
					if(password.equals(confirmedPassword)) {
						updateInfo(updatedTitle,updatedForename,updatedSurname,updatedAffiliation,updatedEmail,updatedPassword,reviewer.getEmail());
					}else {
						System.out.println("Sorry,your password and confirmed password is not same");
					}
				} else{
					updateInfo(updatedTitle,updatedForename,updatedSurname,updatedAffiliation,updatedEmail,updatedPassword,reviewer.getEmail());
				}
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				
				LogInPageReviewerGUI logIn = new LogInPageReviewerGUI();
				logIn.setVisible(true);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(324, 560, 361, 35);
		frame.getContentPane().add(btnNewButton);
	}
}