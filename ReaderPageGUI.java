import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ReaderPageGUI extends Database{

	private JTextField txtChooseTheJournal;
	private JFrame frameJournal;
	private JPanel contentPane;
	private JTextField txtRegistration;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderPageGUI window = new ReaderPageGUI();
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReaderPageGUI() {
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
		txtRegistration.setText("Choose the Journal");
		txtRegistration.setBounds(0, 0, 982, 120);
		contentPane.add(txtRegistration);
		txtRegistration.setColumns(10);
		

		List<Journals> journalsList = getJournals();
		String[] journalTitles = new String[journalsList.size()];
		for (int i=0; i<journalsList.size();i++) {
			String jTitle = journalsList.get(i).getTitle();
			journalTitles[i] = jTitle;
			System.out.println(journalTitles[i]);
		}
		
		List<Volume> volumelist = getVolumes();
		int[] volumetitles = new int[volumelist.size()];
		for (int i=0; i<volumelist.size();i++) {
			int vyear = volumelist.get(i).getYear();
			volumetitles[i] = vyear;
			System.out.println(volumetitles[i]);
		}
		
//		List<Journals> journalsList = getJournals();
//		String[] journalTitles = new String[journalsList.size()];
//		for (int i=0; i<journalsList.size();i++) {
//			String jTitle = journalsList.get(i).getTitle();
//			journalTitles[i] = jTitle;
//			System.out.println(journalTitles[i]);
//		}
		
		JComboBox comboJournal = new JComboBox();
		comboJournal.setModel(new DefaultComboBoxModel(journalTitles));
		comboJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboJournal.setBounds(294, 156, 218, 32);
		contentPane.add(comboJournal);
		
		
		JLabel lblFileChosen = new JLabel("");
		lblFileChosen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileChosen.setBounds(421, 545, 508, 36);
		contentPane.add(lblFileChosen);
		
		JLabel lblJournal = new JLabel("Journal :");
		lblJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJournal.setBounds(133, 146, 135, 53);
		contentPane.add(lblJournal);
		
		JLabel lblvolume= new JLabel("Volume :");
		lblvolume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblvolume.setBounds(133, 214, 102, 36);
		contentPane.add(lblvolume);
	
		
		JButton btnBack = new JButton("<- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				
			    JFrame frmWelcomePage;
				WelcomePage window = new WelcomePage();
				window.frmWelcomePage.setVisible(true);
			}
		});
		btnBack.setBounds(15, 532, 134, 43);
		contentPane.add(btnBack);
		
		JButton btnNewButton = new JButton("Choose this Edition");
		btnNewButton.setBounds(604, 281, 218, 32);
		contentPane.add(btnNewButton);
		
		JComboBox comboVolume = new JComboBox();
		comboVolume.setModel(new DefaultComboBoxModel(volumetitles));
		comboVolume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboVolume.setBounds(294, 156, 218, 32);
		contentPane.add(comboJournal);
		
		JButton button = new JButton("Choose this Journal");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Volume> volumelist =(comboJournal.getSelectedIndex()).getVolumes();
				int[] volumetitles = new int[volumelist.size()];
				for (int i=0; i<volumelist.size();i++) {
					int vyear = volumelist.get(i). getYear();
					volumetitles[i] = vyear;
					System.out.println(volumetitles[i]);
				}
				
			}
		});
		button.setBounds(604, 156, 218, 32);
		contentPane.add(button);
		
		JButton btnChooseThisVolume = new JButton("Choose this Volume");
		btnChooseThisVolume.setBounds(604, 219, 218, 32);
		contentPane.add(btnChooseThisVolume);
		
		JLabel lblEdition = new JLabel("Edition:");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEdition.setBounds(133, 277, 102, 36);
		contentPane.add(lblEdition);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(294, 284, 218, 32);
		contentPane.add(comboBox_1);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBackground(Color.GREEN);
		btnConfirm.setBounds(794, 529, 135, 50);
		contentPane.add(btnConfirm);

	}
}
