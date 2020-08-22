import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InitialVerdictGUI extends Database {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialVerdictGUI frame = new InitialVerdictGUI();
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
	public InitialVerdictGUI(Reviewer rev, int subId, int revX) {
		setTitle("Initial Verdict");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Strong Accept");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInitialVerdict(rev.getId(), subId, revX, "StrongAccept");
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 31));
		btnNewButton.setBounds(26, 109, 466, 220);
		contentPane.add(btnNewButton);
		
		JButton btnStrongReject = new JButton("Strong Reject");
		btnStrongReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInitialVerdict(rev.getId(), subId, revX, "StrongReject");
			}
		});
		btnStrongReject.setFont(new Font("Calibri", Font.PLAIN, 31));
		btnStrongReject.setBounds(26, 407, 466, 220);
		contentPane.add(btnStrongReject);
		
		JButton btnWeakAccept = new JButton("Weak Accept");
		btnWeakAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInitialVerdict(rev.getId(), subId, revX, "WeakAccept");
			}
		});
		btnWeakAccept.setFont(new Font("Calibri", Font.PLAIN, 31));
		btnWeakAccept.setBounds(577, 109, 466, 220);
		contentPane.add(btnWeakAccept);
		
		JButton btnWeakReject = new JButton("Weak Reject");
		btnWeakReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInitialVerdict(rev.getId(), subId, revX, "WeakReject");
			}
		});
		btnWeakReject.setFont(new Font("Calibri", Font.PLAIN, 31));
		btnWeakReject.setBounds(577, 407, 466, 220);
		contentPane.add(btnWeakReject);
	}
}
