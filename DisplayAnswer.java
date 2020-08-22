import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DisplayAnswer extends Database{

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
					DisplayAnswer frame = new DisplayAnswer(empty);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DisplayAnswer(List<String> answers) {

		setTitle("Your answer list");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		DefaultListModel<String> allAnswer = new DefaultListModel<>();

		int index = 0;
		for (String ans: answers) {
			String ansId = "A"+(index+1)+") "+ans;
			allAnswer.add(index, ansId);
			index++;
		}
		JList<String> answerList = new JList<>(allAnswer);
		answerList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		answerList.setBounds(30, 100, 940, 450);
		contentPane.add(answerList);
		
		JLabel lblNewLabel = new JLabel("Answer list");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(371, 23, 170, 73);
		contentPane.add(lblNewLabel);
	}
}
