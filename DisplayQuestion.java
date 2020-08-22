import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class DisplayQuestion extends Database {

	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<String> empty = new ArrayList<>();
					DisplayQuestion frame = new DisplayQuestion(empty);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param review1 
	 */
	public DisplayQuestion(List<String> questions) {

		setTitle("Your question list");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourQuestionList = new JLabel("Your question list");
		lblYourQuestionList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblYourQuestionList.setBounds(354, 13, 249, 78);
		contentPane.add(lblYourQuestionList);

		DefaultListModel<String> allQues = new DefaultListModel<>();
		int index = 0;
		for (String que: questions) {
			String queId = "Q"+(index+1)+") "+que;
			allQues.add(index, queId);
			index++;
		}
		JList questionList = new JList<>(allQues);
		questionList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		questionList.setBounds(40, 102, 902, 470);
		contentPane.add(questionList);

		
		
	}
}
