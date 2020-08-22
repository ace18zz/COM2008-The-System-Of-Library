import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HashPasswordT2.HashClass;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class ManageTeam extends Database {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageTeam frame = new ManageTeam();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ManageTeam(String teamId,int myId) {
		setTitle("Manage Team");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField.setBounds(253, 533, 702, 70);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//method to be changed
		//change the method to adapt based on the user connected (team 1,2,3...)
		JButton btnDelete = new JButton("Delete User");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idUser = textField.getText();
				if(checkInjection(idUser)) {
					System.out.println("injection");
					JOptionPane.showMessageDialog(null, "ERROR - SPECIAL CHARACTERS DETECTED");
				}
				else {
					int editorIdNr = Integer.valueOf(idUser);
					if(myId != editorIdNr) {
						deleteUser(teamId, editorIdNr);
					}
					else {
						JOptionPane.showMessageDialog(null, "ERROR - You can not delete yourself");
					}
				}
				
				//System.out.println(username);
			}
		});
		btnDelete.setBounds(12, 533, 229, 70);
		contentPane.add(btnDelete);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnAddUser.setBounds(253, 409, 229, 88);
		contentPane.add(btnAddUser);
		
		JButton btnTransferRole = new JButton("Transfer Role");
		btnTransferRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				String transfer = textField_1.getText();
				
				if(checkInjection(transfer)) {
					System.out.println("injection");
					JOptionPane.showMessageDialog(null, "ERROR - SPECIAL CHARACTERS DETECTED");
				}
				else {
					//check if the editors that we want to transfer the role is part
					//of the team
					List<Integer> editorsId = getEditorId(teamId);
					int transferIntRole = Integer.valueOf(transfer);
					if(editorsId.contains(transferIntRole)) {
						deleteUser(teamId,transferIntRole);
						deleteUser(teamId,myId);
						addEditorToJournal(transferIntRole, teamId,true);
						addEditorToJournal(myId, teamId,false);
						
						JOptionPane.showMessageDialog(null, "Chief Role Has Been Transfered");
						
						Window[] windows = getWindows();

					    for (Window window : windows)
					    {
					        if (window instanceof JFrame)
					        {
					            window.dispose();
					        }
					    }
					    
					    JFrame frmWelcomePage;
						WelcomePage window = new WelcomePage();
						window.frmWelcomePage.setVisible(true);
						
						/*
						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose();*/
					}
					
				}
					
			}
		});
		btnTransferRole.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnTransferRole.setBounds(12, 629, 229, 61);
		contentPane.add(btnTransferRole);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField_1.setBounds(253, 629, 702, 61);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea txtrFdsf = new JTextArea();
		txtrFdsf.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtrFdsf.setEditable(false);
		txtrFdsf.setBounds(12, 13, 943, 360);
		contentPane.add(txtrFdsf);
		
		//refreshing the user area
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrFdsf.setText(null);
				txtrFdsf.append("ID" + "\t" + " Email " + "\n");
				//List<HashClass> users = getData();
				//txtrFdsf.append("Username" + "\t" + " Team ID \n");
				//for(HashClass i : users) {
				//	txtrFdsf.append(i.getUser() + "\t      " + i.getTeamId() + "\n");	
				//}
				List<Integer> ids = getEditorId(teamId);
				
				for(int i = 0; i < ids.size(); i++ ) {
					List<Editor> editorLs = selectEditorID(String.valueOf(ids.get(i)));
					Editor editor = editorLs.get(0);
					txtrFdsf.append(ids.get(i) + "\t" + editor.getEmail() + "\n");
				}
			}
		});
		btnNewButton.setBounds(12, 409, 229, 88);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back <-");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnBack.setBounds(983, 13, 187, 88);
		contentPane.add(btnBack);
	}
	
	//get user data
	//to add a variable to the method in order to be able to take users from other teams
	//depending on the one who is logged on the account
	public static List<HashClass> getData() {
		String extract = "SELECT * FROM userPassTable WHERE teamId = 1";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet result = stmt.executeQuery(extract);
    		List<HashClass> userPass = new ArrayList<HashClass>(); 
    		while (result.next()) {  
             	String user = result.getString("username");
             	String pass = result.getString("password");
             	int id = result.getInt("teamId");
				//with those we create a object of class
             	userPass.add(new HashClass(user, pass,id));
             }
    		return userPass;
    	}
	     catch (SQLException e) {
	        System.out.println("table display had an error");
	    }
		return null;
	}
	
	// delete user where ...
	//to change teamId variable to let users to be deleted from other columns as well
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//should delete from BoardOfJournals!!!!!!!!!!!!!!!!!!!!!
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public static void deleteUser(String issn,int editorId) {
		String delete = "DELETE FROM BoardOfJournals WHERE editorID ='" +editorId+ " AND "+ "issn = " + issn +"';";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(delete);
		    System.out.println("Record deleted successfully");
    		
    	}
	     catch (SQLException e) {
	    	 e.printStackTrace();
	        System.out.println("user was not delted");
	    }
	}
	
	//checking if the string has injections
	public static boolean checkInjection(String expression) {
		Pattern p = Pattern.compile("[^a-z^A-Z0-9@. ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(expression);
		boolean b = m.find();		
		return b;
	}
}
