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

public class ReadderPageGUI extends Database{

	private JTextField txtChooseTheJournal;
	private JFrame frameJournal;
	private JPanel contentPane;
	private JTextField txtRegistration;
	
	public static List<Volume> volumelist;
	public static List<Edition> editionlist;
	public static List<Article> articlelist;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadderPageGUI window = new ReadderPageGUI();
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
	public ReadderPageGUI() {
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
		
		JLabel lblJournal = new JLabel("Journal :");
		lblJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJournal.setBounds(133, 146, 135, 53);
		contentPane.add(lblJournal);
		
		JLabel lblvolume= new JLabel("Volume :");
		lblvolume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblvolume.setBounds(133, 214, 102, 36);
		contentPane.add(lblvolume);
	
		JLabel lblEdition = new JLabel("Edition:");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEdition.setBounds(133, 277, 102, 36);
		contentPane.add(lblEdition);
		
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
		
		
		JComboBox comboJournal = new JComboBox();
		comboJournal.setModel(new DefaultComboBoxModel(journalTitles));
		comboJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboJournal.setBounds(294, 156, 218, 32);
		contentPane.add(comboJournal);
		
		//backup
		
		JButton button = new JButton("Choose this Journal");
		JComboBox comboBox = new JComboBox();
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volumelist = checkVolume(journalsList.get(comboJournal.getSelectedIndex()).getIssn());
				contentPane.validate();
				contentPane.repaint();	
				
				comboBox.removeAllItems();
				if( volumelist == null) {
					System.out.println("no volume");
//					System.out.println(checkVolume("00641505"));		
				}else {
					
					String[] volumetitles = new String[volumelist.size()];
					System.out.println("size: " + volumelist.size());
					for (int i=0; i<volumelist.size();i++) {
						String vyear = volumelist.get(i).getVolX();
						volumetitles[i] = vyear;
						System.out.println(volumetitles[i]);
						comboBox.addItem(volumetitles[i]);
				}
					
					comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
					comboBox.setBounds(294, 220, 218, 32);
					contentPane.add(comboBox);

				}
			}
		
		});
		button.setBounds(604, 157, 218, 32);
		contentPane.add(button);
		
		JButton btnChooseThisVolume = new JButton("Choose this Volume");	
		JComboBox comboBox_e = new JComboBox();
		
		btnChooseThisVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volumelist = checkVolume(journalsList.get(comboJournal.getSelectedIndex()).getIssn());
				editionlist = checkEdition(volumelist.get(comboBox.getSelectedIndex()).getVolX());	
				contentPane.validate();
				contentPane.repaint();	
				
				comboBox_e.removeAllItems();
			
				if( editionlist == null) {
					System.out.println("no Edition");		
				}else {	
					String[] editionv = new String[editionlist.size()];
					for (int i=0; i<editionlist.size();i++) {
						String etitle = editionlist.get(i).getTitle();
						editionv[i] = etitle;
						System.out.println(editionv[i]);
						comboBox_e.addItem(editionv[i]);
					}
					
					comboBox_e.setFont(new Font("Tahoma", Font.PLAIN, 20));
					comboBox_e.setBounds(297, 279, 218, 32);
					contentPane.add(comboBox_e);

				}
			}
		
		});
		btnChooseThisVolume.setBounds(604, 217, 218, 32);
		contentPane.add(btnChooseThisVolume);
		
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ArticlePageGUI article = new ArticlePageGUI();
				article.setVisible(true);
			}
		});
		btnConfirm.setBackground(Color.GREEN);
		btnConfirm.setBounds(794, 529, 135, 50);
		contentPane.add(btnConfirm);

	
	}
}
