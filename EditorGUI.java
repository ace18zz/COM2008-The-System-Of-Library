import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorGUI frame = new EditorGUI();
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
	public EditorGUI(Editor editor) {
		setTitle("Chief Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManageJournal = new JButton("Manage Journal");
		btnManageJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnManageJournal.setBounds(12, 551, 1158, 106);
		contentPane.add(btnManageJournal);
		
		JLabel lblHelloMrmrs = new JLabel("Editor: " + editor.getTitle() + ". " + editor.getForename() + " " + editor.getSurname());
		lblHelloMrmrs.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHelloMrmrs.setBounds(12, 129, 1158, 69);
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
		
		JButton button = new JButton("?Change Details?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(12, 379, 545, 141);
		contentPane.add(button);
		
		JLabel label = new JLabel("Affiliation: " + editor.getAffiliation());
		label.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label.setBounds(12, 248, 1158, 69);
		contentPane.add(label);
	}
}
