import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import HashPasswordT2.HashClass;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class ChiefEditorGUI extends Database {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 *//*
		 * public static void main(String[] args) { EventQueue.invokeLater(new
		 * Runnable() { public void run() { try { ChiefEditorGUI frame = new
		 * ChiefEditorGUI(); frame.setVisible(true); } catch (Exception e) {
		 * e.printStackTrace(); } } }); }
		 */

	/**
	 * Create the frame.
	 */
	public ChiefEditorGUI(Editor editor) {
		setTitle("Chief Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font("Calibri", Font.PLAIN, 25));
			textArea.setEditable(false);
			textArea.setBounds(792, 165, 354, 269);
			contentPane.add(textArea);
	
			textField = new JTextField();
			textField.setFont(new Font("Calibri", Font.PLAIN, 30));
		textField.setBounds(847, 579, 315, 69);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnManageTeam = new JButton("Manage Team");
		btnManageTeam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnManageTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * check if the text field has code injection if yes => display error if no =>
				 * test if the issn that the user has inputed exists in the data base if yes =>
				 * continue if no => display error
				 */

				if (!checkInjection(textField.getText()) && !textField.getText().isEmpty()) { //check if the field is null
					if (getEditorStatus(editor.getId(), textField.getText())) { // check if the reviewer is chief in that team
						int teamIssn = Integer.valueOf(textField.getText());
						List<Integer> checkIssn = getIssn(editor.getId());

						if (checkIssn.contains(teamIssn)) {// check if issn exists
							ManageTeam manage = new ManageTeam(String.valueOf(teamIssn), editor.getId());
							manage.setVisible(true);
						} else
							JOptionPane.showMessageDialog(null, "ERROR ISSN WAS NOT RECOGNIZED");
					} else {
						JOptionPane.showMessageDialog(null, "Sorry, You are not a Chief Editor In this Team");
					}
				} else {
					JOptionPane.showMessageDialog(null, "ERROR - SPECIAL CHARACTERS DETECTED");
				}

			}
		});
		btnManageTeam.setBounds(12, 557, 680, 106);
		contentPane.add(btnManageTeam);

		JButton btnManageJournal = new JButton("Manage Journal");
		btnManageJournal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnManageJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnManageJournal.setBounds(12, 420, 680, 106);
		contentPane.add(btnManageJournal);

		JLabel lblHelloMrmrs = new JLabel(
				"Chief Editor: " + editor.getTitle() + "." + " " + editor.getForename() + " " + editor.getSurname());
		lblHelloMrmrs.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHelloMrmrs.setBounds(12, 105, 805, 69);
		contentPane.add(lblHelloMrmrs);

		JButton btnBack = new JButton("?Back?");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(12, 13, 131, 55);
		contentPane.add(btnBack);

		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();

				JFrame frmWelcomePage;
				WelcomePage window = new WelcomePage();
				window.frmWelcomePage.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton.setBounds(1039, 13, 131, 55);
		contentPane.add(btnNewButton);

		JButton button = new JButton("Change Details");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeEditorDetails change = new ChangeEditorDetails();
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(12, 282, 447, 88);
		contentPane.add(button);

		JLabel lblAffiliation = new JLabel("Affiliation:" + editor.getAffiliation());
		lblAffiliation.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAffiliation.setBounds(12, 187, 805, 69);
		contentPane.add(lblAffiliation);

		JLabel lblTeamInWhich = new JLabel("Team in which you are a Chief Editor:");
		lblTeamInWhich.setFont(new Font("Calibri", Font.BOLD, 23));
		lblTeamInWhich.setBounds(792, 119, 354, 55);
		contentPane.add(lblTeamInWhich);

		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				int id = editor.getId();
				List<Integer> issns = getIssn(id);

				for (int i = 0; i < issns.size(); i++)
					textArea.append(i + 1 + ") " + issns.get(i) + "\n");
			}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 23));
		btnNewButton_1.setBounds(925, 447, 131, 57);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Team Number:");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 22));
		lblNewLabel.setBounds(704, 578, 166, 27);
		contentPane.add(lblNewLabel);

		JLabel lblissn = new JLabel("        (ISSN)");
		lblissn.setFont(new Font("Calibri", Font.BOLD, 22));
		lblissn.setBounds(704, 605, 131, 27);
		contentPane.add(lblissn);

	}

	public static boolean checkInjection(String expression) {
		Pattern p = Pattern.compile("[^a-z^A-Z0-9@. ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(expression);
		boolean b = m.find();
		return b;
	}
}
