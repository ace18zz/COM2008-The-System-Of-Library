import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReviewFormGUI extends Database {

	private JPanel contentPane;
	String summary;
	String typos;
	String questions;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					//Review review1 = new Review(1,2,3);
//					ReviewFormGUI frame = new ReviewFormGUI(review1);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ReviewFormGUI(Review review) {
		setTitle("Review Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReviewForm = new JLabel("Review Form");
		lblReviewForm.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblReviewForm.setBounds(370, 13, 326, 38);
		contentPane.add(lblReviewForm);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSummary.setBounds(30, 75, 150, 60);
		contentPane.add(lblSummary);
		
		JLabel lblTypos = new JLabel("Typos:");
		lblTypos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTypos.setBounds(30, 195, 150, 60);
		contentPane.add(lblTypos);
		
		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblQuestion.setBounds(30, 315, 150, 60);
		contentPane.add(lblQuestion);
		
		JTextArea textSummary = new JTextArea();
		textSummary.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textSummary.setBounds(171, 75, 775, 97);
		contentPane.add(textSummary);
		textSummary.setLineWrap(true);       
		textSummary.setWrapStyleWord(true);
		summary =textSummary.getText();
		
		JTextArea textTypos = new JTextArea();
		textTypos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textTypos.setBounds(171, 212, 779, 90);
		contentPane.add(textTypos);
		textTypos.setLineWrap(true);       
		textTypos.setWrapStyleWord(true);
		typos = textTypos.getText();
		
		JTextArea textQuestions = new JTextArea();
		textQuestions.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textQuestions.setBounds(171, 325, 779, 131);
		contentPane.add(textQuestions);
		textQuestions.setLineWrap(true);       
		textQuestions.setWrapStyleWord(true);		
		

		DefaultListModel<String> allQues = new DefaultListModel<>();
		List<String> questions = new ArrayList<String>();
		JList questionList = new JList<>(allQues);
		
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.setBounds(302, 479, 150, 27);
		contentPane.add(btnAddQuestion);
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String question = textQuestions.getText();
				if(!question.trim().isEmpty()) {
					questions.add(question);
				}
				textQuestions.setText("");
			}
		});
		
		JButton btnShowQuestion = new JButton("Show Question");
		btnShowQuestion.setBounds(566, 479, 150, 27);
		contentPane.add(btnShowQuestion);
		btnShowQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayQuestion frame = new DisplayQuestion(questions);
				frame.setVisible(true);
			}
		});

		JButton btnSubmitForm = new JButton("Submit Form");
		btnSubmitForm.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSubmitForm.setBounds(750, 550, 200, 40);
		contentPane.add(btnSubmitForm);
		btnSubmitForm.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			addReviewFormTable(review.getTeamID(),review.getSubID(),review.getReviewerX(),summary,typos);
			addResponseTable(review, questions);
			updateReviewTable(review.getTeamID(),review.getSubID());
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
			}
		});
		
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(171, 75, 775, 97);
        contentPane.add(scrollPane);                
        scrollPane.setViewportView(textSummary);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(171, 210, 779, 82);
        contentPane.add(scrollPane_1);                
        scrollPane_1.setViewportView(textTypos);

        JScrollPane scrollPane_2 = new JScrollPane(); 
        scrollPane_2.setBounds(171, 325, 779, 131);
        contentPane.add(scrollPane_2);                
        scrollPane_2.setViewportView(textQuestions);
	}
}
