import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class FinalReviewGUI extends Database{
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ReviewForm formx = new ReviewForm(1,1,"a","b");
					FinalReviewGUI window = new FinalReviewGUI(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FinalReviewGUI(Review review) {
		
		ReviewForm reviewForm = getReviewForm(review); 

		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Final Review");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(386, 5, 230, 72);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblYour = new JLabel("Authors Responses");
		lblYour.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblYour.setBounds(513, 284, 453, 40);
		frame.getContentPane().add(lblYour);
		
		JLabel lblQuestionFromReviewer = new JLabel("Question from reviewer:");
		lblQuestionFromReviewer.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblQuestionFromReviewer.setBounds(28, 287, 294, 34);
		frame.getContentPane().add(lblQuestionFromReviewer);
		
		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSummary.setBounds(28, 74, 210, 40);
		frame.getContentPane().add(lblSummary);
		
		JTextArea label_Summary = new JTextArea(reviewForm.getSummary());
		label_Summary.setBounds(28, 112, 413, 162);
		frame.getContentPane().add(label_Summary);
		
		JLabel lblTypos = new JLabel("Typos:");
		lblTypos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTypos.setBounds(513, 71, 368, 40);
		frame.getContentPane().add(lblTypos);
		
		JTextArea label_Typos = new JTextArea(reviewForm.getTypos());
		label_Typos.setBounds(513, 124, 453, 117);
		frame.getContentPane().add(label_Typos);
		
		List<String> questions = getQuestions(reviewForm.getId(),review.getReviewerX());
		DefaultListModel<String> allQues = new DefaultListModel<String>();
		int index = 0;
		for (String que: questions) {
			String queId = "Q"+(index+1)+") "+que;
			allQues.add(index, queId);
			index++;
		}
		JList<String> questionList = new JList<String>();
		questionList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		questionList.setBounds(24, 322, 432, 201);
		questionList.setModel(allQues);
		frame.getContentPane().add(questionList);
        
        
		List<String> answers = getAnswers(reviewForm.getId(),review.getReviewerX());
		DefaultListModel<String> allAns = new DefaultListModel<String>();
		int index1 = 0;
		for (String ans: answers) {
			String queId = "A"+(index1+1)+") "+ans;
			allAns.add(index1, queId);
			index1++;
		}
        JList<String> responses = new JList<String>();
        responses.setFont(new Font("Tahoma", Font.PLAIN, 30));
        responses.setBounds(507, 322, 441, 199);
        responses.setModel(allAns);
        frame.getContentPane().add(responses);
        
        JButton btnShowQue = new JButton("Show All Questions");
        btnShowQue.setBounds(26, 542, 185, 49);
        frame.getContentPane().add(btnShowQue);
        
        JButton btnShowAns = new JButton("Show All Answers");
        btnShowAns.setBounds(237, 542, 185, 49);
        frame.getContentPane().add(btnShowAns);
        
        JButton btnFinalVerdict = new JButton("Give Final Verdict ->");
        btnFinalVerdict.setBounds(761, 541, 185, 49);
        frame.getContentPane().add(btnFinalVerdict);
        
        JButton btnDownload = new JButton("Download Revised Article");
        btnDownload.setBounds(447, 542, 185, 49);
        frame.getContentPane().add(btnDownload);
		
	}
	
	public static List<String> getAnswers(int subID, int revX){
		String find = "SELECT * FROM ReviewFormTable WHERE submissionID = '"+subID+"' AND "
				+ "reviewerX ='"+revX+"';";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			ResultSet set = stmt.executeQuery(find);
			List<String> answers = new ArrayList<String>();
			while(set.next()) {
				String ans = set.getString("answer");
				answers.add(ans);
			}
			return answers;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldn't find review form");
		}
		return null;
	}
	
	public static ReviewForm getReviewForm(Review review) {
		String find = "SELECT * FROM ReviewFormTable WHERE submissionID = '"+review.getSubID()+"' AND "
				+ "reviewerX ='"+review.getReviewerX()+"';";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			ResultSet set = stmt.executeQuery(find);
			List<ReviewForm> revForms = new ArrayList<ReviewForm>();
			while(set.next()) {
				int subId = set.getInt("submissionID");
				int revX = set.getInt("reviewerX");
				String summary = set.getString("summary");
				String typos = set.getString("typos");
				revForms.add(new ReviewForm(subId, revX, summary, typos));
			}
			return revForms.get(0);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldn't find review form");
		}
		return null;
	}
}
