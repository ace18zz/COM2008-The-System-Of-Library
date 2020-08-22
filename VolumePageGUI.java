import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JList;

public class VolumePageGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField textField;
	JPanel volumePage;
	private String issn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VolumePageGUI frame = new VolumePageGUI(issn);
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
	public VolumePageGUI(String journal) {
		setTitle("Volume Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		volumePage = new JPanel();
		volumePage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(volumePage);
		volumePage.setLayout(null);
		
		JLabel lblLoginEditorAccount = new JLabel("Choose the Volume");
		lblLoginEditorAccount.setFont(new Font("Calibri", Font.BOLD, 40));
		lblLoginEditorAccount.setBounds(12, 60, 359, 45);
		volumePage.add(lblLoginEditorAccount);
		


		JButton btnBack = new JButton("<- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				
			    JFrame ReadderPage;
			    ReadderPage window = new ReadderPage();
				window.setVisible(true);
			}
		});
		btnBack.setBounds(792, 27, 134, 43);
		volumePage.add(btnBack);
		
		JList list = new JList();
		list.setBounds(96, 120, 796, 422);
		volumePage.add(list);
		

	}
}
