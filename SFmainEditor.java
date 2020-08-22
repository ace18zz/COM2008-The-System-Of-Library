import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.ListModel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class SFmainEditor extends Database {

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
					SFmainEditor frame = new SFmainEditor(null);
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
	public SFmainEditor(Journals journal) {
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
		txtRegistration.setText("Self-Registration Chief Editor");
		txtRegistration.setBounds(0, 13, 1044, 120);
		contentPane.add(txtRegistration);
		txtRegistration.setColumns(10);
		
		JLabel lblName = new JLabel("Forname :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(179, 235, 102, 36);
		contentPane.add(lblName);
		
		JTextField forename = new JTextField();
		forename.setFont(new Font("Monospaced", Font.PLAIN, 20));
		forename.setBounds(393, 237, 341, 36);
		contentPane.add(forename);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSurname.setBounds(179, 286, 102, 36);
		contentPane.add(lblSurname);
		
		JTextField surname = new JTextField();
		surname.setFont(new Font("Monospaced", Font.PLAIN, 20));
		surname.setBounds(393, 288, 341, 36);
		contentPane.add(surname);
		
		JTextField email = new JTextField();
		email.setFont(new Font("Monospaced", Font.PLAIN, 20));
		email.setBounds(393, 335, 341, 36);
		contentPane.add(email);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(179, 337, 102, 36);
		contentPane.add(lblEmail);
		
		JLabel lblAffiliation = new JLabel("Affiliation :");
		lblAffiliation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAffiliation.setBounds(179, 382, 102, 36);
		contentPane.add(lblAffiliation);
		
		JTextField affiliation = new JTextField();
		affiliation.setFont(new Font("Monospaced", Font.PLAIN, 20));
		affiliation.setBounds(393, 384, 341, 36);
		contentPane.add(affiliation);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(179, 187, 102, 36);
		contentPane.add(lblTitle);
		
		String[] titles = new String[] {"Mr.", "Mrs.", "Ms.", "Dr.", "Prof."};
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(titles));
		comboBox.setBounds(393, 187, 140, 32);
		contentPane.add(comboBox);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(179, 433, 102, 36);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(393, 435, 341, 36);
		contentPane.add(passwordField);
		
		JLabel lblRetypePassword = new JLabel("Retype Password :");
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRetypePassword.setBounds(179, 496, 175, 36);
		contentPane.add(lblRetypePassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(393, 498, 341, 36);
		contentPane.add(passwordField_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(758, 612, 241, 49);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				JournalsPageGUI journal = new JournalsPageGUI();
				journal.setVisible(true);
			}
		});
		contentPane.add(btnCancel);
		
		List<JComponent> allComponents = new ArrayList<JComponent>();
		allComponents.add(passwordField_1);
		allComponents.add(passwordField);
		allComponents.add(forename);
		allComponents.add(surname);
		allComponents.add(email);
		allComponents.add(affiliation);

		
		List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		JButton btnAddCEditor = new JButton("Register");
		btnAddCEditor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddCEditor.setBounds(220, 612, 241, 49);
		btnAddCEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anyEmptyFields(allComponents)) {
					JOptionPane.showMessageDialog(null, "There are empty fields. Please fill them in."); 
				} else if (!validatePassword(passwordField_1,passwordField)){
					JOptionPane.showMessageDialog(null, "Invalid Password. Must contain atleast 8 characters"
							+ " including uppercase, lowercase, numbers and a special character(e.g.!#$%&*?@\")");
				} 
				else {
					Stakeholder stkholder =new Stakeholder(titles[comboBox.getSelectedIndex()],forename.getText(),
							surname.getText(), affiliation.getText(), email.getText(), String.copyValueOf(passwordField.getPassword()));
					try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
			                Statement stmt = conn.createStatement()){
						addJournal(journal.getTitle(), journal.getIssn());
						if(isNewRecord("StakeholdersTable", "email", stkholder.getEmail(), stmt)) {
							addStakeholder(stkholder, stmt);
							addEditor(stkholder.getEmail());
						}						
						Editor newEdd = selectEditorEmail(stkholder.getEmail());
						addEditorToJournal(newEdd,journal.getIssn(),true);
						
					}catch(SQLException error) {
						System.out.println(error.getMessage());
						System.out.println("SUBMISSION NOT COMPLETED SUCCESFULLY!");
					}
					WelcomePage welcome = new WelcomePage();
					welcome.frmWelcomePage.setVisible(true);
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
				
				}	
			}
		});
		contentPane.add(btnAddCEditor);
	}
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
	}
