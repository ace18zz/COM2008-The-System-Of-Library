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

public class MainAuthorGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAuthorGUI frame = new MainAuthorGUI();
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
	public MainAuthorGUI() {
		setTitle("Main Author");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManageJournal = new JButton("Submission Status");
		btnManageJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnManageJournal.setBounds(12, 499, 1158, 106);
		contentPane.add(btnManageJournal);
		
		JLabel lblHelloMrmrs = new JLabel("Hello Main Author<...>");
		lblHelloMrmrs.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHelloMrmrs.setBounds(443, 126, 308, 69);
		contentPane.add(lblHelloMrmrs);
		
		JButton btnBack = new JButton("?Back?");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(12, 13, 131, 55);
		contentPane.add(btnBack);
		
		JButton btnNewButton = new JButton("?Log Out?");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton.setBounds(1039, 13, 131, 55);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("?Change Details?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(616, 302, 554, 155);
		contentPane.add(button);
		
		JButton btnviewAccountDetails = new JButton("?View Account Details?");
		btnviewAccountDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnviewAccountDetails.setBounds(12, 302, 554, 155);
		contentPane.add(btnviewAccountDetails);
	}
}
