import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Window;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JScrollBar;
import java.awt.SystemColor;

public class ChooseFinalReviewGUI extends Database {

	private JPanel contentPane;
	private JTextField addSub;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public ChooseFinalReviewGUI(Reviewer reviewer) {
		setTitle("Add Submissions To Review");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/*DefaultListModel emptyModel = new DefaultListModel<Integer>();
		JList addedSubList = new JList(emptyModel);
		addedSubList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addedSubList.setBounds(12, 413, 958, 110);
		contentPane.add(addedSubList);*/
		
		JList addedSubList = new JList<>();
					
		List<Submission> subms = getReviewedList(reviewer.getTeamId());
		DefaultListModel subModel = new DefaultListModel<Submission>();
		List<Integer> availSubs = new ArrayList<Integer>();
		int i =0;
		if(subms != null) {
			for(Submission sub: subms) {
				availSubs.add(i,sub.getId());
				subModel.add(i, sub);
				i++;
			}
		}
		JList subList = new JList(subModel);
		subList.setFont(new Font("Tahoma", Font.PLAIN, 25));
		subList.setBounds(12, 88, 958, 291);
		contentPane.add(subList);
		
		JLabel lblSubmissionId = new JLabel("Submission ID:");
		lblSubmissionId.setFont(new Font("Calibri", Font.PLAIN, 26));
		lblSubmissionId.setBounds(25, 494, 158, 39);
		contentPane.add(lblSubmissionId);
		
		addSub = new JTextField();
		addSub.setFont(new Font("Tahoma", Font.PLAIN, 23));
		addSub.setColumns(10);
		addSub.setBounds(204, 492, 189, 39);
		contentPane.add(addSub);
		
		DefaultListModel model = new DefaultListModel<>();
		JButton addSubBtn = new JButton("Review Submission");
		addSubBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
		addSubBtn.setBounds(689, 493, 227, 42);
		addSubBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sub = addSub.getText();
				if(!sub.trim().isEmpty()) {
					try{
						int subId = Integer.parseInt(sub);
						if (validSub(subId, availSubs)) {
							Submission submission = findSub(subms, subId);
							Review review = getReview(reviewer.getTeamId(), submission.getId());
							FinalReviewGUI finalReview = new FinalReviewGUI(review);
							finalReview.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "This is an invalid choice.");
							}
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "Input is not a number. Please enter a valid submission ID.");
					}
				}
			}
		});
		contentPane.add(addSubBtn);
		
		
		JButton finishBtn = new JButton("Save Revised File...");
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sub = addSub.getText();
				if(!sub.trim().isEmpty()) {
						try{
							int subId = Integer.parseInt(sub);
							if (validSub(subId, availSubs)) {
								Submission submission = findSub(subms, subId);
								File pdFile = submission.getOrgFile();
								JFileChooser fc = new JFileChooser();
								fc.setDialogTitle("Save File...");
								fc.setFileFilter(new FileExtFilter(".pdf", "PDF File"));
								int result = fc.showSaveDialog(null);
								if (result == fc.APPROVE_OPTION) {
									byte[] pdfData = new byte[(int) pdFile.length()];
							        DataInputStream dis = new DataInputStream(new FileInputStream(pdFile));
							        dis.readFully(pdfData); 
							        dis.close();
									File file = fc.getSelectedFile();
									OutputStream out = new FileOutputStream(file.getPath());
									out.write(pdfData);
									out.close();
								}
							}else {
								JOptionPane.showMessageDialog(null, "This is an invalid choice.");
								}
						}catch(Exception e1) {
							System.out.println(e1.getMessage());
							JOptionPane.showMessageDialog(null, "Input is not a number. Please enter a valid submission ID.");
							}
				}
			}
		});

		finishBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
		finishBtn.setBounds(445, 493, 207, 42);
		contentPane.add(finishBtn);
	
		JButton backBtn = new JButton("<- Back");
		backBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
		backBtn.setBounds(781, 548, 189, 42);
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
		
		JLabel lblChooseReviews = new JLabel("Review Submission");
		lblChooseReviews.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseReviews.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblChooseReviews.setBounds(171, 13, 622, 42);
		contentPane.add(lblChooseReviews);
		
		JLabel lblInfo = new JLabel("*If there is no submissions to choose from, please add submissions to review.");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInfo.setBounds(12, 57, 854, 29);
		contentPane.add(lblInfo);
		
		JTextArea lblSaveInfo = new JTextArea("Remember to add the \".pdf\" extension to your file name when saving a submission, for convenience.");
		lblSaveInfo.setBackground(SystemColor.control);
		lblSaveInfo.setLineWrap(true);
		lblSaveInfo.getWrapStyleWord();
		lblSaveInfo.setEditable(false);
		lblSaveInfo.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblSaveInfo.setBounds(22, 398, 948, 83);
		contentPane.add(lblSaveInfo);
	}
	
	private List<Submission> getReviewedList(int teamId) {
		String findSubs = "SELECT * FROM SubmissionsTable INNER JOIN ReviewTable ON teamID ='"+teamId+"' AND reviewed="+true+";";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			ResultSet set = stmt.executeQuery(findSubs);
			List<Submission> subms = new ArrayList<Submission>();
			while (set.next()) {
				int subId = set.getInt("submissionID");
				String subTitle = set.getString("title");
				String abstractTxt = set.getString("abstract");
				String issn = set.getString("issn");
				int totRevs = set.getInt("totalReviews");
				Blob blob = set.getBlob("revisedPDF");
				byte[] data = blob.getBytes(1, (int) blob.length());
				File pdFile = File.createTempFile(subTitle, ".pdf");
				FileOutputStream fos = new FileOutputStream(pdFile);
				fos.write(data);
				subms.add(new Submission(subId, subTitle, abstractTxt, issn, pdFile, totRevs));
				}
			return subms;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	//returns submission for given subId;
	public static Submission findSub(List<Submission> subs, int subId) {
		for(Submission sub : subs) {
			if(sub.getId() == subId)
				return sub;
		}
		return null;
	}
	
	//checks if a subId is in the available list of submissions
	public static boolean validSub(int subId, List<Integer> availSubs) {
		for (int sub: availSubs) {
			//System.out.println(sub == subId);
			if (sub == subId) {
				return true;
			}
		}
		return false;
	}
}