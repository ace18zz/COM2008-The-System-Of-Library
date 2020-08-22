import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class ChiefEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiefEditor frame = new ChiefEditor();
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
	public ChiefEditor() {
		setTitle("Chief Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManageTeam = new JButton("Manage Team");
		btnManageTeam.setBounds(114, 92, 200, 55);
		contentPane.add(btnManageTeam);
		
		JButton btnManageJournal = new JButton("Manage Journal");
		btnManageJournal.setBounds(114, 160, 200, 55);
		contentPane.add(btnManageJournal);
		
		JLabel lblHelloMrmrs = new JLabel("Hello Chief Editor");
		lblHelloMrmrs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHelloMrmrs.setBounds(12, 13, 149, 55);
		contentPane.add(lblHelloMrmrs);
		
		
		JLabel label = new JLabel();
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(136, 13, 112, 55);
		contentPane.add(label);
	}
}
