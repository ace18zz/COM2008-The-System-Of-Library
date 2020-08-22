import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage {

	JFrame frmWelcomePage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage window = new WelcomePage();
					window.frmWelcomePage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frmWelcomePage = new JFrame();
		frmWelcomePage.setTitle("Welcome page");
		frmWelcomePage.setBounds(100, 100, 1000, 650);
		frmWelcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomePage.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Welcome");
		label.setFont(new Font("Calibri", Font.BOLD, 40));
		label.setBounds(396, 15, 167, 92);
		frmWelcomePage.getContentPane().add(label);
		
		JButton button = new JButton("Reader");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frmWelcomePage.dispose();
			ReadderPageGUI reader = new ReadderPageGUI();
			reader.setVisible(true);
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 26));
		button.setBounds(396, 289, 118, 68);
		frmWelcomePage.getContentPane().add(button);
		
		JButton btnEditor = new JButton("Editor");
		btnEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmWelcomePage.dispose();
				LogInPageEditorGUI login = new LogInPageEditorGUI();
				login.setVisible(true);
			}
		});
		btnEditor.setFont(new Font("Calibri", Font.PLAIN, 26));
		btnEditor.setBounds(671, 160, 127, 68);
		frmWelcomePage.getContentPane().add(btnEditor);
		
		JButton btnEdito = new JButton("Reviewer");
		btnEdito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmWelcomePage.dispose();
				LogInPageReviewerGUI login = new LogInPageReviewerGUI();
				login.setVisible(true);
			}
		});
		btnEdito.setFont(new Font("Calibri", Font.PLAIN, 26));
		btnEdito.setBounds(144, 160, 138, 68);
		frmWelcomePage.getContentPane().add(btnEdito);
		
		JButton btnAuthor = new JButton("Author");
		btnAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmWelcomePage.dispose();
				LogInPageAuthorsGUI login = new LogInPageAuthorsGUI();
				login.setVisible(true);
			}
		});
		btnAuthor.setFont(new Font("Calibri", Font.PLAIN, 26));
		btnAuthor.setBounds(390, 160, 127, 68);
		frmWelcomePage.getContentPane().add(btnAuthor);
		
		JButton btnNewButton = new JButton("Register New Journal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frmWelcomePage.dispose();
			JournalsPageGUI journal = new JournalsPageGUI();
			journal.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 26));
		btnNewButton.setBounds(329, 419, 269, 66);
		frmWelcomePage.getContentPane().add(btnNewButton);
	}
}