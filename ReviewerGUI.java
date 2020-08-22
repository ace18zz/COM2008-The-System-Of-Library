import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReviewerGUI extends Database {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewerGUI frame = new ReviewerGUI(selectReviewerEmail("morty123@gmail.com"));
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
	public ReviewerGUI(Reviewer reviewer) {
		setTitle("Reviewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null);
		
		JButton btnChooseSubmissions = new JButton("Choose Submissions");
		btnChooseSubmissions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnChooseSubmissions.setBounds(12, 470, 1158, 106);
		btnChooseSubmissions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("yey");
				int teamRevs = getTotalReviews(reviewer.getTeamId());
				System.out.println("no");
				if (teamRevs < 3) {
					ReviewerSubmissionGUI chooseSubs = new ReviewerSubmissionGUI(reviewer, teamRevs);
					chooseSubs.setVisible(true);
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Your review team has already selected 3 submissions.");
				}
			}
		});
		contentPane.add(btnChooseSubmissions);
		
		JLabel lblHelloMrmrs = new JLabel("Hello " + reviewer.getTitle() + " " + reviewer.getForename() + " " + reviewer.getSurname());
		lblHelloMrmrs.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHelloMrmrs.setBounds(12, 49, 788, 69);
		contentPane.add(lblHelloMrmrs);
		
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
				ChangeReviewersDetails change = new ChangeReviewersDetails(reviewer);
				change.frame.setVisible(true);
				
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(12, 302, 554, 155);
		contentPane.add(button);
		
		JButton btnReview = new JButton("Review Submissions");
		btnReview.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReview.setBounds(12, 584, 1158, 106);
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();

				ChooseReviewGUI reviewSub = new ChooseReviewGUI(reviewer);
				reviewSub.setVisible(true);
			}
		});
		contentPane.add(btnReview);
		
		JLabel lblAffiliation = new JLabel("Affiliation: " + reviewer.getAffiliation());
		lblAffiliation.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAffiliation.setBounds(12, 144, 788, 69);
		contentPane.add(lblAffiliation);
		
		JButton btnGiveFinalReview = new JButton("Give Final Review");
		btnGiveFinalReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseFinalReviewGUI reviewSub = new ChooseFinalReviewGUI(reviewer);
				reviewSub.setVisible(true);
				
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnGiveFinalReview.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGiveFinalReview.setBounds(616, 302, 554, 155);
		contentPane.add(btnGiveFinalReview);
	}
}
