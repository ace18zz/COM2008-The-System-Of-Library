import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class ManageJournal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JList list;
	private JTextField textField_1;
	private JButton btnPublish;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageJournal frame = new ManageJournal();
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
	public ManageJournal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 55, 807, 375);
		contentPane.add(panel);
		
		list = new JList();
		panel.add(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 437, 809, 125);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(67, 41, 276, 30);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(420, 41, 276, 30);
		panel_1.add(textField_1);
		
		btnPublish = new JButton("Accept");
		btnPublish.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPublish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPublish.setBounds(262, 82, 101, 35);
		panel_1.add(btnPublish);
		
		JLabel lblSubmission = new JLabel("Submission: ");
		lblSubmission.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubmission.setBounds(67, 12, 153, 25);
		panel_1.add(lblSubmission);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(420, 17, 153, 25);
		panel_1.add(lblDate);
		
		JButton btnReject = new JButton("Reject");
		btnReject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReject.setBounds(402, 82, 101, 35);
		panel_1.add(btnReject);
		
		JLabel lblManageJournals = new JLabel("Manage Journals");
		lblManageJournals.setBounds(350, 20, 105, 16);
		contentPane.add(lblManageJournals);
	}
}
