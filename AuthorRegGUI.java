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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JList;

public class AuthorRegGUI extends JFrame {

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
					List<Author> emptyList = new ArrayList<Author>();
					AuthorRegGUI frame = new AuthorRegGUI(emptyList);
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
	public AuthorRegGUI(List<Author> authors) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1062, 735);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtRegistration = new JTextField();
		txtRegistration.setForeground(new Color(255, 255, 255));
		txtRegistration.setBackground(new Color(255, 165, 0));
		txtRegistration.setFont(new Font("Tahoma", Font.BOLD, 40));
		txtRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistration.setText("Register Co-author");
		txtRegistration.setBounds(0, 13, 1044, 120);
		contentPane.add(txtRegistration);
		txtRegistration.setColumns(10);
		
		JLabel lblName = new JLabel("Forname :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(28, 287, 102, 36);
		contentPane.add(lblName);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea_1.setBounds(236, 290, 472, 36);
		contentPane.add(textArea_1);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSurname.setBounds(28, 336, 102, 36);
		contentPane.add(lblSurname);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBounds(236, 339, 472, 36);
		contentPane.add(textArea);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea_2.setBounds(236, 385, 472, 36);
		contentPane.add(textArea_2);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(28, 385, 102, 36);
		contentPane.add(lblEmail);
		
		JLabel lblAffiliation = new JLabel("Affiliation :");
		lblAffiliation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAffiliation.setBounds(28, 434, 102, 36);
		contentPane.add(lblAffiliation);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea_3.setBounds(236, 434, 472, 36);
		contentPane.add(textArea_3);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(28, 237, 102, 36);
		contentPane.add(lblTitle);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mr.", "Mrs.", "Ms.", "Dr.", "Prof."}));
		comboBox.setBounds(236, 239, 140, 32);
		contentPane.add(comboBox);
		
		JLabel lblPassword = new JLabel("Temporary Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(28, 500, 209, 36);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(236, 503, 472, 36);
		contentPane.add(passwordField);
		
		JLabel lblRetypePassword = new JLabel("Retype Password :");
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRetypePassword.setBounds(28, 548, 175, 36);
		contentPane.add(lblRetypePassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(236, 549, 472, 36);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("Add another co-author");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(236, 625, 220, 49);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Complete Submission");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(466, 625, 210, 49);
		contentPane.add(btnCancel);
		
		JButton button = new JButton("Cancel");
		button.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button.setBounds(687, 626, 139, 49);
		contentPane.add(button);
		
		DefaultListModel<Author> list = new DefaultListModel();
		int index = 0;
		for (Author i : authors) {
			list.add(index, i);
			index++;
		}
		JList showAuthor = new JList<>();
		showAuthor.setModel(list);;
		showAuthor.setBounds(28, 146, 945, 82);
		contentPane.add(showAuthor);
	}
}
