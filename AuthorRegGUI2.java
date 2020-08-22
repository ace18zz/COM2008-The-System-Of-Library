import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JList;

public class AuthorRegGUI2 extends Database {

	private JPanel contentPane;
	private JTextField txtRegistration;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<Stakeholder> emptyList = new ArrayList<Stakeholder>();
					Submission emptySub = null;
					AuthorRegGUI2 frame = new AuthorRegGUI2(emptyList,emptySub);
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
	public AuthorRegGUI2(List<Stakeholder> stakeholders, Submission newSubmission) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtRegistration = new JTextField();
		txtRegistration.setEditable(false);
		txtRegistration.setForeground(new Color(255, 255, 255));
		txtRegistration.setBackground(new Color(255, 165, 0));
		txtRegistration.setFont(new Font("Tahoma", Font.BOLD, 40));
		txtRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistration.setText("Registration");
		txtRegistration.setBounds(0, 0, 982, 120);
		contentPane.add(txtRegistration);
		txtRegistration.setColumns(10);
		
		JLabel lblName = new JLabel("Forname :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(28, 281, 102, 36);
		contentPane.add(lblName);
		
		JTextField forename = new JTextField();
		forename.setFont(new Font("Monospaced", Font.PLAIN, 20));
		forename.setBounds(220, 284, 472, 36);
		contentPane.add(forename);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSurname.setBounds(28, 330, 102, 36);
		contentPane.add(lblSurname);
		
		JTextField surname = new JTextField();
		surname.setFont(new Font("Monospaced", Font.PLAIN, 20));
		surname.setBounds(220, 333, 472, 36);
		contentPane.add(surname);
		
		JTextField email = new JTextField();
		email.setFont(new Font("Monospaced", Font.PLAIN, 20));
		email.setBounds(220, 379, 472, 36);
		contentPane.add(email);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(28, 379, 102, 36);
		contentPane.add(lblEmail);
		
		JLabel lblAffiliation = new JLabel("Affiliation :");
		lblAffiliation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAffiliation.setBounds(28, 428, 102, 36);
		contentPane.add(lblAffiliation);
		
		JTextField affiliation = new JTextField();
		affiliation.setFont(new Font("Monospaced", Font.PLAIN, 20));
		affiliation.setBounds(220, 428, 472, 36);
		contentPane.add(affiliation);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(28, 231, 102, 36);
		contentPane.add(lblTitle);
		
		String[] titles = new String[] {"Mr.", "Mrs.", "Ms.", "Dr.", "Prof."};
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(titles));
		comboBox.setBounds(220, 233, 140, 32);
		contentPane.add(comboBox);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(28, 493, 102, 36);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 496, 472, 36);
		contentPane.add(passwordField);
		
		JLabel lblRetypePassword = new JLabel("Retype Password :");
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRetypePassword.setBounds(28, 542, 175, 36);
		contentPane.add(lblRetypePassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(220, 542, 472, 36);
		contentPane.add(passwordField_1);
		
		List<JComponent> allComponents = new ArrayList<JComponent>();
		allComponents.add(passwordField_1);
		allComponents.add(passwordField);
		allComponents.add(forename);
		allComponents.add(surname);
		allComponents.add(email);
		allComponents.add(affiliation);
		
		JButton btnAddCoauthor = new JButton("Add co-author");
		btnAddCoauthor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddCoauthor.setBounds(220, 603, 166, 49);
		btnAddCoauthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anyEmptyFields(allComponents)) {
					JOptionPane.showMessageDialog(null, "There are empty fields. Please fill them in."); 
				} else if (injections(allComponents)) {
					JOptionPane.showMessageDialog(null, "ERROR - SPECIAL CHARACTERS DETECTED");
				} else if (!validatePassword(passwordField_1,passwordField)){
					JOptionPane.showMessageDialog(null, "Invalid Password. Must contain atleast 8 characters"
							+ " including uppercase, lowercase, numbers and a special character(e.g.!#$%&*?@\")");
				}
				else {
					stakeholders.add(new Stakeholder(titles[comboBox.getSelectedIndex()],forename.getText(),
							surname.getText(), affiliation.getText(), email.getText(), String.copyValueOf(passwordField.getPassword())));
					AuthorRegGUI2 coauthor = new AuthorRegGUI2(stakeholders, newSubmission);
					coauthor.setVisible(true);
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
				}
			}
		});
		contentPane.add(btnAddCoauthor);
		
		JButton btnCompleteSubmission = new JButton("Complete Submission");
		btnCompleteSubmission.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCompleteSubmission.setBounds(405, 603, 210, 49);
		btnCompleteSubmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//validating all information provided
				if (anyEmptyFields(allComponents)) {
					JOptionPane.showMessageDialog(null, "There are empty fields. Please fill them in."); 
				} else if (injections(allComponents)) {
					JOptionPane.showMessageDialog(null, "ERROR - SPECIAL CHARACTERS DETECTED");
				} else if (!validatePassword(passwordField_1,passwordField)){
					JOptionPane.showMessageDialog(null, "Invalid Password. Must contain atleast 8 characters"
							+ " including uppercase, lowercase, numbers and a special character(e.g.!#$%&*?@");
				} 
				//try adding these data to the database
				else {
					stakeholders.add(new Stakeholder(titles[comboBox.getSelectedIndex()],forename.getText(),
							surname.getText(), affiliation.getText(), email.getText(), String.copyValueOf(passwordField.getPassword())));
					List<Author> allAuthors = new ArrayList<Author>();
					List<Reviewer> allReviewers = new ArrayList<Reviewer>();
					boolean mainAuthor = true;
					for (Stakeholder stakeholder: stakeholders) {
						System.out.println("AUTHORS:");
						allAuthors.add(stakeholderToAuthor(stakeholder, mainAuthor));
						if (mainAuthor) {
							mainAuthor = false;
						}
						Reviewer newReviewer = authorToReviewer(stakeholder);
						allReviewers.add(newReviewer);
						} 
					try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
				               Statement stmt = conn.createStatement()){
						int subID = 0;
						int teamID = 0;
						if (!newSubmission.getOrgFile().getAbsolutePath().trim().isEmpty()) {
							subID = addNewSubmission(newSubmission);
							teamID = addNewTeam(stmt);
							for(Stakeholder stkholder: stakeholders) {
								if (isNewRecord("StakeholdersTable", "email", stkholder.getEmail(), stmt)) {
									addStakeholder(stkholder, stmt);
								}
							}
							for(Author author: allAuthors) {
								if (isNewRecord("AuthorsTable", "email", author.getEmail(), stmt)) {
									addAuthor(author, stmt);
									}
								Author newAuthor = selectAuthorEmail(author.getEmail());
								addAuthorWithSubmission(newAuthor.getId(),subID,author.isMainAuthor(),stmt);
							}
							for(Reviewer reviewer: allReviewers) {
								if (isNewRecord("ReviewersTable", "email", reviewer.getEmail(), stmt)) {
									addReviewer(reviewer, stmt);								
								}
								Reviewer newReviewer = selectReviewerEmail(reviewer.getEmail());
								addReviewerToTeam(teamID, newReviewer.getId(), stmt);
							}
							JComponent comp = (JComponent) e.getSource();
							Window win = SwingUtilities.getWindowAncestor(comp);
							win.dispose();
							LogInPageAuthorsGUI goLogIn = new LogInPageAuthorsGUI();
							goLogIn.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "No File is chosen. Please restart registration by clicking cancel.");
							throw new SQLException("No File is chosen. Please restart registration.");
						}	
					}catch(SQLException error) {
						error.printStackTrace();
						System.out.println(error.getMessage());
						System.out.println("SUBMISSION NOT COMPLETED SUCCESFULLY!");
					}
					} 
				}
			});
		contentPane.add(btnCompleteSubmission);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(635, 603, 155, 49);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				WelcomePage home = new WelcomePage();
				home.frmWelcomePage.setVisible(true);
			}
		});
		contentPane.add(btnCancel);
		
		DefaultListModel<Stakeholder> list = new DefaultListModel();
		int index = 0;
		for (Stakeholder i : stakeholders) {
			list.add(index, i);
			index++;
		}
		
		JList showStakeholder = new JList<>();
		showStakeholder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		showStakeholder.setModel(list);;
		showStakeholder.setBounds(28, 120, 635, 98);
		contentPane.add(showStakeholder);

		
	}
	//return true if the passwords entered are the same
	public static boolean validatePassword(JPasswordField pass1, JPasswordField pass2) {
		System.out.println(pass1.getPassword());
		System.out.println(pass2.getPassword().length);
		System.out.println(Arrays.equals(pass1.getPassword(), pass2.getPassword()));
		System.out.println(String.valueOf(pass1.getPassword()));
		if (Arrays.equals(pass1.getPassword(), pass2.getPassword()) && pass1.getPassword().length > 7) {
			String pass = String.valueOf(pass1.getPassword());
			Pattern uppercase = Pattern.compile("[A-Z]");
			Pattern lowercase = Pattern.compile("[a-z]");
			Pattern numbers = Pattern.compile("\\d");
			Pattern specialChar = Pattern.compile("[^a-zA-Z0-9 ]");
			
	        if (!uppercase.matcher(pass).find()	 
	        		|| !lowercase.matcher(pass).find()	 
	        		|| !numbers.matcher(pass).find()	  
	        		|| specialChar.matcher(pass).find())
	        {	return true;
	        }else {
	        	return false;
	        }
		}else {
			return false;
		}
	}
	
	//returns true if there are fields that are still empty
	public static boolean anyEmptyFields(List<JComponent> list) {
		for (JComponent i : list) {
			if (i.getClass().getSimpleName().equals("JPasswordField")) {
				if (((JPasswordField) i).getPassword().length==0) {
					return true;
				}
			} else if (((JTextField) i).getText().trim().isEmpty()) {
				return true;
			}
			}
		return false;
	}
	public static boolean injections(List<JComponent> list) {
		for (JComponent i : list) {
			if (i.getClass().getSimpleName().equals("JPasswordField")) {
				continue;
			}else if (i.getClass().getSimpleName().equals("JTextArea")) {
				return checkInjection(((JTextArea) i).getText().trim());
			}else if (checkInjection(((JTextField) i).getText().trim())) {
				return true;
			}
		}
		return false;
	}
		
	public static boolean checkInjection(String expression) {
		Pattern p = Pattern.compile("[^a-z^A-Z0-9@. ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(expression);
		boolean b = m.find();	
		return b;
	}
} 

