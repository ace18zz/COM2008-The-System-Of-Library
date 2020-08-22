import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;

public class ChooseReviewerGUI extends Database {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnFinish;
	private JTextField txtReviewer1;
	private JTextField txtReviewer2;
	private JTextField txtReviewer3;
	private JLabel lblReviewer_1;
	private JLabel lblReviewer_2;
	private JLabel lblReviewer_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<Stakeholder> emptyList = new ArrayList<Stakeholder>();
					emptyList.add(new Stakeholder("Mr.", "Rahul", "Rai", "UoS", "rairahul19rr@gmail.com", "Rahul_123"));
					emptyList.add(new Stakeholder("Mr.", "Valentin", "Christmas", "UoS", "val@gmail.com", "Valentin_123"));
					Submission emptySub = null;
					ChooseReviewerGUI frame = new ChooseReviewerGUI(emptyList,emptySub);
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
	public ChooseReviewerGUI(List<Stakeholder> stakeholders, Submission newSubmission) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("Choose Reviewer");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Tahoma", Font.BOLD, 40));
		textField.setColumns(10);
		textField.setBackground(new Color(255, 165, 0));
		textField.setBounds(0, 0, 982, 120);
		contentPane.add(textField);
		
		DefaultListModel<Stakeholder> list = new DefaultListModel();
		int index = 0;
		for (Stakeholder i : stakeholders) {
			list.add(index, i);
			index++;
		}
		
		JList showAuthor = new JList<>();
		showAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		showAuthor.setModel(list);;
		showAuthor.setBounds(28, 120, 635, 98);
		contentPane.add(showAuthor);
		
		JTextArea txtInfo = new JTextArea();
		txtInfo.setBackground(SystemColor.control);
		txtInfo.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtInfo.setRows(10);
		txtInfo.setEditable(false);
		txtInfo.setText("Please choose the author(s) that will review 3 other submissions. "
				+ "You are allowed to pick up to 3 different reviewers from yourself and your team of co-authors."
				+ " Please enter their email address below to select them and also specify how many reviews each author will do"
				+ " in the adjacent box. Then click finish to finish submission. *Please fill in Reviewer 1 before any other boxes.");
		txtInfo.setBounds(23, 221, 924, 89);
		txtInfo.setLineWrap(true);
		txtInfo.setWrapStyleWord(true);
		contentPane.add(txtInfo);
		
		txtReviewer1 = new JTextField();
		txtReviewer1.setBounds(230, 336, 389, 39);
		contentPane.add(txtReviewer1);
		txtReviewer1.setColumns(10);
		
		txtReviewer2 = new JTextField();
		txtReviewer2.setColumns(10);
		txtReviewer2.setBounds(230, 391, 389, 39);
		contentPane.add(txtReviewer2);
		
		txtReviewer3 = new JTextField();
		txtReviewer3.setColumns(10);
		txtReviewer3.setBounds(231, 453, 389, 39);
		contentPane.add(txtReviewer3);
		
		lblReviewer_1 = new JLabel("Reviewer 1");
		lblReviewer_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReviewer_1.setBounds(27, 337, 92, 39);
		contentPane.add(lblReviewer_1);
		
		lblReviewer_2 = new JLabel("(OPTIONAL) Reviewer 2");
		lblReviewer_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReviewer_2.setBounds(28, 392, 190, 39);
		contentPane.add(lblReviewer_2);
		
		lblReviewer_3 = new JLabel("(OPTIONAL) Reviewer 3");
		lblReviewer_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReviewer_3.setBounds(28, 453, 185, 39);
		contentPane.add(lblReviewer_3);
		
		List<JTextField> listOfEmails = new ArrayList<JTextField>();
		listOfEmails.add(txtReviewer1);
		listOfEmails.add(txtReviewer2);
		listOfEmails.add(txtReviewer3);
		
		String[] numOfReviews = {"0", "1", "2", "3"};
		
		JComboBox totReviewer_1 = new JComboBox();
		totReviewer_1.setModel(new DefaultComboBoxModel(numOfReviews));
		totReviewer_1.setMaximumRowCount(4);
		totReviewer_1.setBounds(650, 337, 65, 36);
		contentPane.add(totReviewer_1);
		
		JComboBox totReviewer_2 = new JComboBox();
		totReviewer_2.setModel(new DefaultComboBoxModel(numOfReviews));
		totReviewer_2.setMaximumRowCount(4);
		totReviewer_2.setBounds(650, 394, 65, 36);
		contentPane.add(totReviewer_2);
		
		JComboBox totReviewer_3 = new JComboBox();
		totReviewer_3.setModel(new DefaultComboBoxModel(numOfReviews));
		totReviewer_3.setMaximumRowCount(4);
		totReviewer_3.setBounds(650, 454, 65, 36);
		contentPane.add(totReviewer_3);
		
		List<JComboBox> combos = new ArrayList<JComboBox>();
		combos.add(totReviewer_1);
		combos.add(totReviewer_2);
		combos.add(totReviewer_3);
		
		btnFinish = new JButton("Finish");
		btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnFinish.setBounds(301, 525, 155, 49);
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!differentReviewers(listOfEmails)) {
					JOptionPane.showMessageDialog(null, "Please enter different reviewers.");
				}
				else if (!validReviewers(stakeholders, listOfEmails)) {
					JOptionPane.showMessageDialog(null, "Your reviewers aren't in your team of authors."
							+ " Please add only from your team of authors.");
				}else if (!correctReviewSum(combos, numOfReviews)) {
					JOptionPane.showMessageDialog(null, "Your team of reviewers must review a total of 3 submissions. "
							+ "Please adjust this in the combo boxes.");
				}else if (invalidReview(listOfEmails, combos, numOfReviews)) {
					JOptionPane.showMessageDialog(null, "Invalid Review. Please check your selections and try again.");
				}else {
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
								}}
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
						} else {
							JOptionPane.showMessageDialog(null, "No File is chosen. Please restart registration by clicking cancel.");
							throw new SQLException("No File is chosen. Please restart registration.");
						}
						
					}catch(SQLException error) {
						error.printStackTrace();
						System.out.println(error.getMessage());
						System.out.println("SUBMISSION NOT COMPLETED SUCCESFULLY!");
					}
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					LogInPageAuthorsGUI goLogIn = new LogInPageAuthorsGUI();
					goLogIn.setVisible(true);
				}
			}
		} );
		contentPane.add(btnFinish);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(526, 525, 155, 49);
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

	}
	
	public static boolean differentReviewers(List<JTextField> emails) {
		//checks reviewer 1 isn't empty
		if (emails.get(0).getText().trim().isEmpty()) {
			return false;
		}//checks if reviewer 2 and 3 are empty, if yes then only one reviewer entered.
		else if (emails.get(1).getText().trim().isEmpty() && emails.get(2).getText().trim().isEmpty()) {
			return true;
		}
		else if (emails.get(0).getText().equals(emails.get(1).getText())
				|| emails.get(1).getText().equals(emails.get(2).getText())
				|| emails.get(2).getText().equals(emails.get(0).getText()) )
		{
			return false;
		} 
		return true;
	}
	
	public static Boolean validReviewers(List<Stakeholder> authors, List<JTextField> emails) {
		//count1 is number of reviewers entered, 
		//count2 is number of email's that match list of authors
		int count1 = 0, count2 = 0;
		for(JTextField email: emails) {
			if (!email.getText().trim().isEmpty()) {
				count1++;
			for (Stakeholder author: authors) {
				if (author.getEmail().equals(email.getText())) {
					count2++;
					}}}}
		if (count1==count2) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean invalidReview(List<JTextField> emails, List<JComboBox> combos, String[] numberOfReviews) {
		for(int i=0; i<3;i++) {
			if ((emails.get(i).getText().trim().isEmpty() && Integer.valueOf(numberOfReviews[combos.get(i).getSelectedIndex()])>0)
				|| (!emails.get(i).getText().trim().isEmpty() && Integer.valueOf(numberOfReviews[combos.get(i).getSelectedIndex()])==0)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean correctReviewSum(List<JComboBox> combos, String[] numberOfReviews) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += Integer.valueOf(numberOfReviews[combos.get(i).getSelectedIndex()]);
			}
		System.out.println(sum);
		if (sum == 3) {
			return true;
		}
		return false;
	}
	
	
}
