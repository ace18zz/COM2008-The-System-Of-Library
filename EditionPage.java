import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class EditionPage extends JFrame {

	private JPanel contentPane;
	JPanel editionPage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditionPage frame = new EditionPage();
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
	public EditionPage() {
		setTitle("Edition Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		editionPage = new JPanel();
		editionPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(editionPage);
		editionPage.setLayout(null);
		
		JLabel lblLoginEditorAccount = new JLabel("Choose the Edition");
		lblLoginEditorAccount.setFont(new Font("Calibri", Font.BOLD, 40));
		lblLoginEditorAccount.setBounds(12, 60, 359, 45);
		editionPage.add(lblLoginEditorAccount);
		


		JButton btnBack = new JButton("<- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				
			    JFrame VolumePage;
			    VolumePage window = new VolumePage();
				window.volumePage.setVisible(true);
			}
		});
		btnBack.setBounds(809, 25, 134, 43);
		editionPage.add(btnBack);
		
		JList list = new JList();
		list.setBounds(55, 120, 869, 439);
		editionPage.add(list);
		
	}

}
