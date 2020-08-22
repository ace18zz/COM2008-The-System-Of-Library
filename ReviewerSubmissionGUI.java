import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Window;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ReviewerSubmissionGUI extends Database {

	private JPanel contentPane;
	private JTextField addSub;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reviewer rev = selectReviewerEmail("morty123@gmail.com"); 
					int revs = getTotalReviews(rev.getTeamId());
					ReviewerSubmissionGUI frame = new ReviewerSubmissionGUI(rev,revs);
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
	public ReviewerSubmissionGUI(Reviewer reviewer, int teamRevs) {
		setTitle("Add Submissions To Review");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final int TEAMREVS = 3 - teamRevs;
		
		/*DefaultListModel emptyModel = new DefaultListModel<Integer>();
		JList addedSubList = new JList(emptyModel);
		addedSubList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addedSubList.setBounds(12, 413, 958, 110);
		contentPane.add(addedSubList);*/
		
		JList addedSubList = new JList<>();
					
		List<Submission> subs = getValidSubs(reviewer);
		DefaultListModel subModel = new DefaultListModel<Submission>();
		List<Integer> availSubs = new ArrayList<Integer>();
		int i =0;
		for(Submission sub: subs) {
			availSubs.add(i,sub.getId());
			subModel.add(i, sub);
			i++;
		}
		
		JList subList = new JList(subModel);
		subList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subList.setBounds(12, 75, 958, 263);
		contentPane.add(subList);
		
		JLabel lblSubmissionId = new JLabel("Submission ID:");
		lblSubmissionId.setFont(new Font("Calibri", Font.PLAIN, 26));
		lblSubmissionId.setBounds(22, 357, 158, 39);
		contentPane.add(lblSubmissionId);
		
		addSub = new JTextField();
		addSub.setFont(new Font("Tahoma", Font.PLAIN, 23));
		addSub.setColumns(10);
		addSub.setBounds(201, 359, 189, 39);
		contentPane.add(addSub);
		
		List<Integer> addedSubs = new ArrayList<Integer>();
		DefaultListModel model = new DefaultListModel<>();
		JButton addSubBtn = new JButton("Add Submission");
		addSubBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
		addSubBtn.setBounds(428, 356, 189, 42);
		addSubBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sub = addSub.getText();
				if(addedSubs.size() < TEAMREVS && !sub.trim().isEmpty()) {
						try{
							int subId = Integer.parseInt(sub);
							if (validSub(subId, addedSubs, availSubs)) {
								addedSubs.add(subId);
								model.add(addedSubs.size()-1, subId);
								System.out.println(subId);
								JList addedSubList = new JList<>(model);
								addedSubList.setFont(new Font("Tahoma", Font.PLAIN, 15));
								addedSubList.setBounds(12, 413, 958, 110);
								contentPane.add(addedSubList);
								contentPane.repaint();
							}else {
								JOptionPane.showMessageDialog(null, "This is an invalid choice.");
								}
						}catch(NumberFormatException e1) {
							System.out.println(e1.getMessage());
							JOptionPane.showMessageDialog(null, "Input is not a number. Please enter a valid submission ID.");
							}
				}
			}
		});
		contentPane.add(addSubBtn);
		
		
		JButton finishBtn = new JButton("Finish");
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
		                Statement stmt = conn.createStatement()){
					int teamId = reviewer.getTeamId();
					updateTeamReviews(teamId, addedSubs.size(),stmt);
					for (int sub: addedSubs) {
						addReviewTable(teamId, sub);
						updateSubmissionReviews(sub);
					}
				}catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
				ReviewerGUI revGui = new ReviewerGUI(reviewer);
				revGui.setVisible(true);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		finishBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
		finishBtn.setBounds(630, 536, 189, 42);
		contentPane.add(finishBtn);
		
		JButton restartBtn = new JButton("Reselect All");
		restartBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
		restartBtn.setBounds(677, 356, 189, 42);
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				ReviewerSubmissionGUI restart = new ReviewerSubmissionGUI(reviewer, teamRevs);
				restart.setVisible(true);				
			}
		});
		contentPane.add(restartBtn);
	
		JButton backBtn = new JButton("Back");
		backBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
		backBtn.setBounds(117, 536, 189, 42);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				ReviewerGUI reviewerPg = new ReviewerGUI(reviewer);
				reviewerPg.setVisible(true);				
			}
		});
		contentPane.add(backBtn);
		
		JLabel lblChooseReviews = new JLabel("Choose Submissions");
		lblChooseReviews.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseReviews.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblChooseReviews.setBounds(171, 0, 622, 42);
		contentPane.add(lblChooseReviews);
		
		JLabel lblInfo = new JLabel("*If there is no submissions to choose from, please come back later.");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInfo.setBounds(12, 42, 854, 29);
		contentPane.add(lblInfo);
		
		
	}
	
	/*public void showAddedSubs(List<Integer> subs) {
		DefaultListModel subModel = new DefaultListModel<Integer>();
		int i =0;
		for(int subid: subs) {
			subModel.add(i, subid);
			i++;
		}
		JList addedSubList = new JList(subModel);
		addedSubList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addedSubList.setBounds(12, 413, 958, 110);
		contentPane.add(addedSubList);
	}*/	
	
	public static boolean validSub(int subId, List<Integer> addedSubs, List<Integer> availSubs) {
		for (int sub: addedSubs) {
			System.out.println(sub == subId);
			if (sub == subId) {
				return false;
			}
		}
		for (int sub: availSubs) {
			//System.out.println(sub == subId);
			if (sub == subId) {
				return true;
			}
		}
		return false;
	}
	
	
}
