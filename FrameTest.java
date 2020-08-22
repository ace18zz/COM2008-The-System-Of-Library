import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JRadioButton;

public class FrameTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTest frame = new FrameTest();
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
	public FrameTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Press to Submit");
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(254, 215, 166, 25);
		contentPane.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(258, 177, 142, 25);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(254, 124, 142, 25);
		contentPane.add(textArea_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(254, 102, 151, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassowrd = new JLabel("Passowrd");
		lblPassowrd.setBounds(254, 162, 56, 16);
		contentPane.add(lblPassowrd);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(12, 83, 119, 26);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("My new menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnTestmore = new JMenu("test1More");
		mnNewMenu.add(mnTestmore);
		
		JMenuItem mntmHello = new JMenuItem("hello");
		mnTestmore.add(mntmHello);
		
		JMenuItem mntmItWorks = new JMenuItem("it works");
		mnTestmore.add(mntmItWorks);
		
		JMenuItem menuItem = new JMenuItem("123");
		mnNewMenu.add(menuItem);
		
		JMenuItem mntmAbc = new JMenuItem("abc");
		mnNewMenu.add(mntmAbc);
		
		JMenuItem mntmYey = new JMenuItem("yey");
		mnNewMenu.add(mntmYey);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Click 1");
		rdbtnNewRadioButton.setBounds(254, 9, 127, 25);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Click2");
		rdbtnNewRadioButton_1.setBounds(254, 47, 127, 25);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Click 3");
		rdbtnNewRadioButton_2.setBounds(254, 77, 127, 25);
		contentPane.add(rdbtnNewRadioButton_2);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
