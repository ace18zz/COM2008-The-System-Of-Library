import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.ScrollPane;

public class ArticlePage extends JFrame {

	private JPanel contentPane;
	JPanel articlepage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArticlePage frame = new ArticlePage();
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
	public ArticlePage() {
		setTitle("Edition Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		articlepage = new JPanel();
		articlepage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(articlepage);
		articlepage.setLayout(null);
		
		JLabel lblLoginEditorAccount = new JLabel("Article");
		lblLoginEditorAccount.setFont(new Font("Calibri", Font.BOLD, 40));
		lblLoginEditorAccount.setBounds(102, 60, 359, 45);
		articlepage.add(lblLoginEditorAccount);
		


		JButton btnBack = new JButton("<- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				
			    JFrame EditionPage;
			    EditionPage window = new EditionPage();
				window.editionPage.setVisible(true);
			}
		});
		btnBack.setBounds(809, 25, 134, 43);
		articlepage.add(btnBack);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(113, 126, 738, 371);
		articlepage.add(scrollPane);
	}
}
