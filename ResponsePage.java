import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ResponsePage extends Database{
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReviewForm formx = new ReviewForm(1,1,"a","b");
//					ResponsePage window = new ResponsePage(formx);
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ResponsePage(ReviewForm form1) {

		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Response Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(336, 13, 319, 117);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblYour = new JLabel("Your reply for these questions:\r\n");
		lblYour.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblYour.setBounds(515, 357, 453, 40);
		frame.getContentPane().add(lblYour);
		
		JLabel lblQuestionFromReviewer = new JLabel("Question from reviewer:");
		lblQuestionFromReviewer.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblQuestionFromReviewer.setBounds(33, 359, 294, 34);
		frame.getContentPane().add(lblQuestionFromReviewer);
		
		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSummary.setBounds(33, 127, 210, 40);
		frame.getContentPane().add(lblSummary);
		
		JLabel label_Summary = new JLabel(form1.getSummary());
		label_Summary.setBounds(33, 165, 413, 188);
		frame.getContentPane().add(label_Summary);
		
		JLabel lblTypos = new JLabel("Typos:");
		lblTypos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTypos.setBounds(515, 127, 368, 40);
		frame.getContentPane().add(lblTypos);
		
		JLabel label_Typos = new JLabel(form1.getTypos());
		label_Typos.setBounds(515, 180, 453, 117);
		frame.getContentPane().add(label_Typos);
		
		List<String> questions = getQuestions(form1.getId(),form1.getReviewerX());
		DefaultListModel<String> allQues = new DefaultListModel<String>();
		int index = 0;
		for (String que: questions) {
			String queId = "Q"+(index+1)+") "+que;
			allQues.add(index, queId);
		}
		JList<String> questionList = new JList<String>();
		questionList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		questionList.setBounds(33, 406, 432, 164);
		questionList.setModel(allQues);
		frame.getContentPane().add(questionList);
		
		
		JTextArea answerArea = new JTextArea();
		answerArea.setFont(new Font("Tahoma", Font.PLAIN, 30));
		answerArea.setBounds(525, 402, 443, 67);
		frame.getContentPane().add(answerArea);
		answerArea.setLineWrap(true);       
		answerArea.setWrapStyleWord(true);
        JScrollPane scrollAnswer = new JScrollPane();
        scrollAnswer.setBounds(525, 402, 443, 67);
        frame.getContentPane().add(scrollAnswer);                
        scrollAnswer.setViewportView(answerArea);
        
        

		DefaultListModel<String> allAnswer = new DefaultListModel<>();
		List<String> answers = new ArrayList<String>();
		JList<String> answerList = new JList<>(allAnswer);
		
		JButton btnAddAnswer = new JButton("Add answer");
		btnAddAnswer.setBounds(720, 482, 113, 50);
		frame.getContentPane().add(btnAddAnswer);
		btnAddAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = answerArea.getText();
				if(!answer.trim().isEmpty()) {
					answers.add(answer);
				}
				answerArea.setText("");
			}
		});
		
		JButton btnShowAnswer = new JButton("Show answer");
		btnShowAnswer.setBounds(847, 482, 121, 50);
		frame.getContentPane().add(btnShowAnswer);
		btnShowAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayAnswer frame = new DisplayAnswer(answers);
				frame.setVisible(true);
			}
		});
		
		JButton btnConfirmMySubmission = new JButton("Confirm my submission");
		btnConfirmMySubmission.setBounds(735, 545, 233, 54);
		frame.getContentPane().add(btnConfirmMySubmission);
		btnConfirmMySubmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAnswer(form1.getId(), form1.getReviewerX(), answers);
			}
		});

		
		JLabel lblNewLabel_1 = new JLabel("*Please answer all question in order.");
		lblNewLabel_1.setBounds(515, 310, 443, 50);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		}
	}