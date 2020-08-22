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
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class MainAuthorRegGUI extends Database {

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
					MainAuthorRegGUI frame = new MainAuthorRegGUI();
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
	public MainAuthorRegGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1062, 735);
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
		txtRegistration.setBounds(0, 13, 1044, 120);
		contentPane.add(txtRegistration);
		txtRegistration.setColumns(10);
		
		JLabel lblName = new JLabel("Forname :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(28, 235, 102, 36);
		contentPane.add(lblName);
		
		JTextField forename = new JTextField();
		forename.setFont(new Font("Monospaced", Font.PLAIN, 20));
		forename.setBounds(220, 238, 341, 36);
		contentPane.add(forename);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSurname.setBounds(28, 284, 102, 36);
		contentPane.add(lblSurname);
		
		JTextField surname = new JTextField();
		surname.setFont(new Font("Monospaced", Font.PLAIN, 20));
		surname.setBounds(220, 287, 341, 36);
		contentPane.add(surname);
		
		JTextField email = new JTextField();
		email.setFont(new Font("Monospaced", Font.PLAIN, 20));
		email.setBounds(220, 333, 341, 36);
		contentPane.add(email);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(28, 333, 102, 36);
		contentPane.add(lblEmail);
		
		JLabel lblAffiliation = new JLabel("Affiliation :");
		lblAffiliation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAffiliation.setBounds(28, 382, 102, 36);
		contentPane.add(lblAffiliation);
		
		JTextField affiliation = new JTextField();
		affiliation.setFont(new Font("Monospaced", Font.PLAIN, 20));
		affiliation.setBounds(220, 382, 341, 36);
		contentPane.add(affiliation);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(28, 185, 102, 36);
		contentPane.add(lblTitle);
		
		String[] titles = new String[] {"Mr.", "Mrs.", "Ms.", "Dr.", "Prof."};
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(titles));
		comboBox.setBounds(220, 187, 140, 32);
		contentPane.add(comboBox);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(28, 447, 102, 36);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 450, 341, 36);
		contentPane.add(passwordField);
		
		JLabel lblRetypePassword = new JLabel("Retype Password :");
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRetypePassword.setBounds(28, 496, 175, 36);
		contentPane.add(lblRetypePassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(220, 496, 341, 36);
		contentPane.add(passwordField_1);
		
		List<Journals> journalsList = getJournals();
		String[] journalTitles = new String[journalsList.size()];
		for (int i=0; i<journalsList.size();i++) {
			String jTitle = journalsList.get(i).getTitle();
			journalTitles[i] = jTitle;
			System.out.println(journalTitles[i]);
		}
		JComboBox comboJournal = new JComboBox();
		comboJournal.setModel(new DefaultComboBoxModel(journalTitles));
		comboJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboJournal.setBounds(689, 187, 218, 32);
		contentPane.add(comboJournal);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(758, 612, 241, 49);
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
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView());
		JLabel lblUploadSubmission = new JLabel("Upload submission :");
		lblUploadSubmission.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUploadSubmission.setBounds(28, 545, 185, 36);
		contentPane.add(lblUploadSubmission);
		
		JLabel lblFileChosen = new JLabel("");
		lblFileChosen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileChosen.setBounds(421, 545, 508, 36);
		contentPane.add(lblFileChosen);
		
		JLabel lblJournal = new JLabel("Journal :");
		lblJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJournal.setBounds(599, 185, 102, 36);
		contentPane.add(lblJournal);
		
		JLabel lblSubmissionTitle = new JLabel("Submission Title:");
		lblSubmissionTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubmissionTitle.setBounds(599, 235, 163, 36);
		contentPane.add(lblSubmissionTitle);
		
		JTextField subTitle = new JTextField();
		subTitle.setFont(new Font("Monospaced", Font.PLAIN, 20));
		subTitle.setBounds(758, 235, 274, 36);
		contentPane.add(subTitle);
		
		JLabel lblSubmissionAbstract = new JLabel("Submission Abstract:");
		lblSubmissionAbstract.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubmissionAbstract.setBounds(599, 284, 199, 36);
		contentPane.add(lblSubmissionAbstract);
		
		JTextArea abstractTxt = new JTextArea();
		abstractTxt.setFont(new Font("Monospaced", Font.PLAIN, 18));
		abstractTxt.setBounds(599, 329, 433, 204);
		contentPane.add(abstractTxt);
		
		JLabel authorT = new JLabel("About Main Author");
		authorT.setHorizontalAlignment(SwingConstants.CENTER);
		authorT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		authorT.setBounds(155, 138, 175, 36);
		contentPane.add(authorT);
		
		JLabel submissionT = new JLabel("About Submission");
		submissionT.setHorizontalAlignment(SwingConstants.CENTER);
		submissionT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submissionT.setBounds(702, 138, 168, 36);
		contentPane.add(submissionT);
				
		
		
		JButton btnChooseAFile = new JButton("Choose File...");
		btnChooseAFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChooseAFile.setBounds(220, 549, 199, 32);
		btnChooseAFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					//change button text 
					btnChooseAFile.setText("Choose another file..");
					//show name of selected file next to button
					lblFileChosen.setText("File Name: " + jfc.getSelectedFile().getName());
				}
			}
		});
		contentPane.add(btnChooseAFile);
	
		//File pdfFile = jfc.getSelectedFile();
		
		List<JComponent> allComponents = new ArrayList<JComponent>();
		allComponents.add(passwordField_1);
		allComponents.add(passwordField);
		allComponents.add(forename);
		allComponents.add(surname);
		allComponents.add(email);
		allComponents.add(affiliation);
		allComponents.add(subTitle);
		allComponents.add(abstractTxt);
		
		List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		JButton btnAddCoauthor = new JButton("Add co-author");
		btnAddCoauthor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddCoauthor.setBounds(220, 612, 241, 49);
		btnAddCoauthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anyEmptyFields(allComponents)) {
					JOptionPane.showMessageDialog(null, "There are empty fields. Please fill them in."); 
				} else if (injections(allComponents)) {
					JOptionPane.showMessageDialog(null, "ERROR - SPECIAL CHARACTERS DETECTED");
				} else if (!validatePassword(passwordField_1,passwordField)){
					JOptionPane.showMessageDialog(null, "Invalid Password. Must contain atleast 8 characters"
							+ " including uppercase, lowercase, numbers and a special character(e.g.!#$%&*?@\")");
				} else if (jfc.getSelectedFile() == null) {
					JOptionPane.showMessageDialog(null, "Please select a file for submission.");
				}
				else {
					String jTitle = journalTitles[comboJournal.getSelectedIndex()];
					String jIssn = null;
					for (Journals j : journalsList) {
						if (j.getTitle().equals(jTitle)) {
							jIssn = j.getIssn();
						}
					}
					Submission newSubmission = new Submission(subTitle.getText(), abstractTxt.getText(), 
							jIssn, jfc.getSelectedFile(),0);
					//System.out.println(pdfFile.);
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
		btnCompleteSubmission.setBounds(487, 612, 241, 49);
		btnCompleteSubmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//validation checks
				if (anyEmptyFields(allComponents)) {
					JOptionPane.showMessageDialog(null, "There are empty fields. Please fill them in."); 
				} else if (injections(allComponents)) {
					JOptionPane.showMessageDialog(null, "ERROR - SPECIAL CHARACTERS DETECTED");
				} 
				else if (!validatePassword(passwordField_1,passwordField)){
					JOptionPane.showMessageDialog(null, "Invalid Password. Must contain atleast 8 characters"
							+ " including uppercase, lowercase, numbers and a special character(e.g.!#$%&*?@");
				}else if (jfc.getSelectedFile() == null) {
					JOptionPane.showMessageDialog(null, "Please select a file for submission.");
				}
				//try adding all information to database 
				else {
					String jTitle = journalTitles[comboJournal.getSelectedIndex()];
					String jIssn = null;
					for (Journals j : journalsList) {
						if (j.getTitle().equals(jTitle)) {
							jIssn = j.getIssn();
						}
					}
					Submission newSubmission = new Submission(subTitle.getText(), abstractTxt.getText(), 
							jIssn, jfc.getSelectedFile(),0);
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
			}else if (i.getClass().getSimpleName().equals("JTextArea")) {
				if (((JTextArea) i).getText().trim().isEmpty()) {
					return true;
				}
			}else if (((JTextField) i).getText().trim().isEmpty()) {
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
