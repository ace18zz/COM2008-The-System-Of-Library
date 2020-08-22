 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReviewerTeamGUI extends Database{

	private JPanel contentPane;
	private JTextField txtRegistration;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewerTeamGUI frame = new ReviewerTeamGUI(selectReviewerEmail("mystery@gmail.com"));
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
	public ReviewerTeamGUI(Reviewer reviewer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtRegistration = new JTextField();
		txtRegistration.setEditable(false);
		txtRegistration.setForeground(new Color(255, 255, 255));
		txtRegistration.setBackground(new Color(255, 165, 0));
		txtRegistration.setFont(new Font("Tahoma", Font.BOLD, 40));
		txtRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistration.setText("Choose Team");
		txtRegistration.setBounds(0, 13, 1044, 120);
		contentPane.add(txtRegistration);
		txtRegistration.setColumns(10);
		
		List<Integer> teamIds = getTeamIDs(reviewer.getId());
		String[] model = new String[teamIds.size()];
		int i = 0;
		for(int teamID: teamIds) {
			model[i] = ("Team "+String.valueOf(teamID));
			i++;
		}
		JComboBox teamBox = new JComboBox();
		teamBox.setBounds(294, 224, 375, 59);
		teamBox.setModel(new DefaultComboBoxModel(model));
		contentPane.add(teamBox);
		
		JButton btnNewButton = new JButton("Choose Team");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(490, 463, 179, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int teamId = teamIds.get(teamBox.getSelectedIndex());
				System.out.println(teamId);
				reviewer.setTeamId(teamId);
				ReviewerGUI revGui = new ReviewerGUI(reviewer);
				revGui.setVisible(true);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		button = new JButton("<- Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInPageReviewerGUI logIn = new LogInPageReviewerGUI();
				logIn.setVisible(true);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(294, 463, 179, 50);
		contentPane.add(button);
	}
}
