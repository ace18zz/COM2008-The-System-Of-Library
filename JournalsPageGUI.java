import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class JournalsPageGUI extends Database {

	private JPanel contentPane;
	private JTextField txtRegistration;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JournalsPageGUI frame = new JournalsPageGUI();
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
	public JournalsPageGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		txtRegistration = new JTextField();
		txtRegistration.setForeground(new Color(255, 255, 255));
		txtRegistration.setBackground(new Color(255, 165, 0));
		txtRegistration.setFont(new Font("Tahoma", Font.BOLD, 40));
		txtRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistration.setText("Register New Journal");
		txtRegistration.setBounds(0, 13, 982, 120);
		contentPane.add(txtRegistration);
		txtRegistration.setColumns(10);
		
		JLabel title = new JLabel("Title :");
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		title.setBounds(236, 329, 64, 36);
		contentPane.add(title);
		
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea_1.setBounds(299, 329, 472, 36);
		contentPane.add(textArea_1);
		
		
		List<JComponent> allComponents = new ArrayList<JComponent>();
		allComponents.add(title);
		
		
		String issn = randomStr (8);
		List<Journals> journals = new ArrayList<Journals>();
		JButton btnCompleteSubmission = new JButton("Register Chief Editor");
		btnCompleteSubmission.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCompleteSubmission.setBounds(530, 399, 241, 49);
		btnCompleteSubmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anyEmptyFields(allComponents)) {
					JOptionPane.showMessageDialog(null, "There are empty fields. Please fill them in."); }
				else {
					Journals journal = new Journals(textArea_1.getText(), issn);					
					SFmainEditor sfpage = new SFmainEditor(journal);
					sfpage.setVisible(true);
					
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					} 
				}
				});
		contentPane.add(btnCompleteSubmission);
		
		JButton btnNewButton = new JButton("<- Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frmWelcomePage;
				WelcomePage window = new WelcomePage();
				window.frmWelcomePage.setVisible(true);
					
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(299, 399, 152, 49);
		contentPane.add(btnNewButton);
		
		JLabel lblPleaseGiveThis = new JLabel("Please give this new Journal a nice title:");
		lblPleaseGiveThis.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPleaseGiveThis.setBounds(237, 238, 522, 60);
		contentPane.add(lblPleaseGiveThis);
		
	}
	//returns true if there are fields that are still empty
	public static boolean anyEmptyFields(List<JComponent> list) {
		for (JComponent i : list) {
			if (i.getClass().getSimpleName().equals("JPasswordField")) {
				if (((JTextArea) i).getText().length()==0) {
					return true;
				}
			} else if (((JLabel) i).getText().trim().isEmpty()) {
				return true;
			}
			}
		return false;
	} 

	public static String characters1 = "0123456789";
    public static String randomStr(int factor){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < factor; i++) {
            // nextInt(10) = [0, 10)
            sb.append(characters1.charAt(random.nextInt(9)));
        }
        return sb.toString();
    }


}
