import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import HashPasswordT2.HashClass;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class Database extends JFrame{
	
	//--------------JOURNALS
	public static void createJournalsTable() {
	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	//DELETE OLD TABLE NOT DONE YET
	//String delete = "DROP TABLE JournalsTable;";
	//String delete2 = "DROP TABLE BoardOfJournals;";
	String sql = "CREATE TABLE IF NOT EXISTS JournalsTable (\n"
	                + "    title TEXT NOT NULL,\n"
	                + "    issn VARCHAR(8) PRIMARY KEY \n"
	                + ");";
	//stmt.execute(delete2);
	//stmt.execute(delete);
	stmt.execute(sql);
	}
	catch (SQLException e) {
	System.out.println(e.getMessage());
	       System.out.println("Journals table was not created");
	   }
	}

	public static void addJournal(String title, String issn) {
	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	String test1 = "INSERT INTO JournalsTable(title, issn)\r\n" + 
	"VALUES ('" + title + "','" + issn + "');";
	//"VALUES ('A.L.I.C.E','213');";
	stmt.execute(test1);
	}
	   catch (SQLException e) {
	      System.out.println(e.getMessage());
	      System.out.println("Journal was not added");
	  }
	}

	public static List<Journals> getJournals() {
	String extract = "SELECT * FROM JournalsTable";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	    ResultSet result = stmt.executeQuery(extract);
	    List<Journals> journalsCreated = new ArrayList<Journals>(); 
	    while (result.next()) {
	              String title = result.getString("title");    
	              String issn = result.getString("issn");
	//with those we create a object of class
	              journalsCreated.add(new Journals(title, issn));
	             }
	    return journalsCreated;
	    }
	    catch (SQLException e) {
	       System.out.println("Journal table display had an error");
	   }
	return null;
	}
	//--------------JOURNALS 


	//--------------VOLUMES
	public static void CreateVolumesTable() {

	String table = "CREATE TABLE IF NOT EXISTS VolumesTable (\n"
	                + "    volume INTEGER AUTO_INCREMENT,\n"
	                + "    yearOfPublication integer NOT NULL,\n"
	                + "    numOfPages real,\n"
	                + "    issn VARCHAR(8),\n"
	                + "    FOREIGN KEY (issn) REFERENCES JournalsTable(issn),\n"
	                + "    PRIMARY KEY (volume, issn)\n"
	                + ");";
	String drop = "DROP TABLE IF EXISTS VolumesTable;";

	try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	            Statement stmt = conn.createStatement()){ 
	//stmt.execute(drop);
	stmt.execute(table);
	System.out.println("Success!");
	} 
	catch (SQLException e) {
	System.out.println(e.getMessage());
	System.out.print("Volumes table not created!");
	}
	}

	public static void addVolume(String volX, int year, int pages, String issn) {
	String insert = "INSERT INTO VolumesTable(volume, yearOfPublication, numOfPages, issn)";
	String data = " VALUES ('" + volX + "', '" + year + "', '" + pages + "', '" + issn + "')"; 
	String sql = insert + data;

	try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	           Statement stmt = conn.createStatement()){
	stmt.executeUpdate(sql);
	}
	catch (SQLException e) {
	System.out.println(e.getMessage()); 
	}
	}

	public static List<Volume> getVolumes() {
	String allVolumes = "Select * FROM VolumesTable";
	try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	           Statement stmt = conn.createStatement()){
	List<Volume> volumesExtracted = new ArrayList<Volume>();
	ResultSet result = stmt.executeQuery(allVolumes);
	while(result.next()) {
	String vol = result.getString("volume");
	int year = result.getInt("yearOfPublication");
	int pages = result.getInt("numOfPages");
	String issn = result.getString("issn");
	volumesExtracted.add(new Volume(vol,year,pages,issn));
	}
	return volumesExtracted;
	}
	catch (SQLException e) {
	System.out.println(e.getMessage()); 
	}
	return null;
	}

	public static Volume selectVolume(String volX) {
	String select = "Select * FROM VolumesTable ";
	String data = " WHERE volume = '" + volX + "'"; 
	String sql = select + data;
	String vol = "";
	int year = 0, pages = 0;
	String issn = null;
	Volume volume = null;

	try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	           Statement stmt = conn.createStatement()){

	ResultSet result = stmt.executeQuery(sql);
	while(result.next()) {
	vol = result.getString("volume");
	year = result.getInt("yearOfPublication");
	pages = result.getInt("numOfPages");
	issn = result.getString("issn");
	}
	volume = new Volume(vol,year,pages,issn);
	}
	catch (SQLException e) {
	System.out.println(e.getMessage()); 
	}
	return volume;
	}

	public static List<Volume> checkVolume(String issn) {
	List<Volume> listvolume = new ArrayList<Volume>();
	String select = "Select * FROM VolumesTable";
	String data = " WHERE issn = '" + issn + "'"; 
	String sql = select + data;
	String vol = "";
	int year = 0, pages = 0;
	Volume volume = null;
	try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	           Statement stmt = conn.createStatement()){

	ResultSet result = stmt.executeQuery(sql);

	while(result.next()) {
	vol = result.getString("volume");
	year = result.getInt("yearOfPublication");
	pages = result.getInt("numOfPages");
	issn = result.getString("issn");
	listvolume.add(new Volume(vol,year,pages,issn));
	} 

	}
	catch (SQLException e) {
	System.out.println(e.getMessage()); 
	}

	return listvolume;
	}
	//--------------VOLUMES


	//--------------EDITIONS
	public static void CreateEditionsTable() {
	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	//String drop = "DROP TABLE EditionsTable;";
	String sql = "CREATE TABLE IF NOT EXISTS EditionsTable (\n"
	                + "    title text NOT NULL,\n"
	                + "    date integer,\n"
	                + "    editionId integer AUTO_INCREMENT,\n"
	                + "    volume integer,\n"
	                + "    issn VARCHAR(8),\n" 
	                + "    FOREIGN KEY (volume, issn) REFERENCES VolumesTable(volume, issn),\n"
	                + "    PRIMARY KEY (editionId, volume, issn)\n"
	                + ");";
	//stmt.execute(drop);
	stmt.execute(sql);
	}
	catch (SQLException e) {
	System.out.println(e.getMessage());
	       System.out.println("Edition table was not created");
	   }
	}

	public static void addEdition(String title, int date, int edationId, String volX) {
	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	String test1 = "INSERT INTO EditionsTable(title, date, editionId, volume)\r\n" + 
	"VALUES ('" + title + "','" + date + "','" +  edationId + "', '" + volX + "');";
	//"VALUES ('A.L.I.C.E','213', '100');";
	stmt.execute(test1);
	}
	   catch (SQLException e) {
	      System.out.println(e.getMessage());
	      System.out.println("Edition was not added");
	  }
	}

	public static List<Edition> getEdition() {
	String extract = "SELECT * FROM EditionsTable";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	    ResultSet result = stmt.executeQuery(extract);
	    
	    while (result.next()) {
	              String title = result.getString("title");    // access by col name   String name = res.getString(name); int office = res.getInt(office);
	              int date = result.getInt("date");
	              int EId = result.getInt("editionId");
	              String volume = result.getString("volume");
	//with those we create a object of class; but now we are just print
	              System.out.println(title + " " + date + " " + EId + " " + volume);
	             }
	    }
	    catch (SQLException e) {
	       System.out.println("Edition's table display had an error");
	   }
	return null;
	}
	public static List<Edition> checkEdition(String volume) {
	List<Edition> listedition = new ArrayList<Edition>();
	String select = "Select * FROM EditionsTable";
	String data = " WHERE volume = '" + volume + "'"; 
	String sql = select + data;
	String title = "";
	int date = 0, editionId = 0;
	String volx = null;
	try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	           Statement stmt = conn.createStatement()){

	ResultSet result = stmt.executeQuery(sql);

	while(result.next()) {
	title = result.getString("title");
	date = result.getInt("date");
	editionId = result.getInt("editionId");
	volx = result.getString("volume");
	listedition.add(new Edition(title,date,editionId,volx));
	} 

	}
	catch (SQLException e) {
	System.out.println(e.getMessage()); 
	}

	return listedition;
	}
	//--------------EDITIONS


	//--------------ARTICLES
	public static void createArticlesTable () {
	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	String sql = "CREATE TABLE ArticlesTable (\r\n" + 
	" ArticleID INTEGER,\r\n" + 
	" issn varchar(8) NOT NULL,\r\n" + 
	" volume integer NOT NULL,\r\n" + 
	" editionId integer NOT NULL,\r\n" + 
	" PageRange integer AUTO_INCREMENT NOT NULL,\r\n" + 
	" Title varchar(255),\r\n" + 
	" Abstract varchar(255),\r\n" + 
	" Link varchar(255),\r\n" + 
	" FOREIGN KEY (editionId,volume,issn) REFERENCES EditionsTable(editionId,volume,issn),\n"+
	" PRIMARY KEY (PageRange,issn,volume,editionId));";
	stmt.execute(sql);
	}
	catch (SQLException e) {
	System.out.println(e.getMessage());
	       System.out.println("Article table was not created");
	   }
	} 
	public static void addArticle(int articleId,String title, String issn,int volume, int edition,String pageRange,String abstracts,String link) {
	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	String test1 = "INSERT INTO ArticlesTable(articleId,title,issn,volume,edition,pageRange,abstracts, link)\r\n" + 
	"VALUES ('"+ articleId + "','" + title + "','" + issn+ "','" + volume + "','" + edition + "','" + pageRange + "','" + abstracts + "','" +  link+ "');";
	//"VALUES ('A.L.I.C.E','ASDS', 'SAFS',100);";
	stmt.execute(test1);
	}
	   catch (SQLException e) {
	      System.out.println(e.getMessage());
	      System.out.println("Article was not added");
	  }
	}

	public static List<Article> getArticles() {
	String extract = "SELECT * FROM ArticlesTable";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	    ResultSet result = stmt.executeQuery(extract);
	    List<Article> articles = new ArrayList<Article>(); 
	    while (result.next()) {
	    String issn = result.getString("ISSN");
	    int edition = result.getInt("Edition");
	    String pageRange = result.getString("PageRange");
	    int volume = result.getInt("Volume");
	              String title = result.getString("title");    // access by col name   String name = res.getString(name); int office = res.getInt(office);
	              String abstracts = result.getString("abstracts");
	              String link = result.getString(" link");
	              int AId = result.getInt("articleId");
	  
	//with those we create a object of class; but now we are just print
	articles.add(new Article(AId,title,issn,volume,edition,pageRange,abstracts, link));
	             }
	    return articles;
	    }
	    catch (SQLException e) {
	       System.out.println("Article table had an error");
	   }
	return null;
	}
	public static List<Article> checkArticle(int id) {
	List<Article> listarticle = new ArrayList<Article>();
	String select = "Select * FROM ArticlesTable";
	String data = " WHERE editionid = '" + id + "'"; 
	String sql = select + data;
	String title = "";
	int volume = 0, editionId = 0, articleId = 0;
	String Pagerange = null;
	        String Abstract = null ; 
	        String link = null;
	        String issn = null;

	try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	           Statement stmt = conn.createStatement()){

	ResultSet result = stmt.executeQuery(sql);

	while(result.next()) {
	title = result.getString("title");
	articleId = result.getInt("articleId");
	editionId = result.getInt("editionId");
	volume = result.getInt("volume");
	link = result.getString("Link");
	issn = result.getString("issn");
	Pagerange = result.getString("Pagerange");
	Abstract = result.getString("Abstract");

	listarticle.add(new Article(articleId,title,issn,volume,editionId,Pagerange,Abstract,link));
	} 

	}
	catch (SQLException e) {
	System.out.println(e.getMessage()); 
	}

	return listarticle;
	}
    //-------------Articles

	
	
	//--------------STAKEHOLDERS
	public static void createStakeholdersTable() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String sql = "CREATE TABLE IF NOT EXISTS StakeholdersTable (\n"
                + "    title TEXT NOT NULL,\n"
                + "    forename TEXT NOT NULL,\n"
                + "    surname TEXT NOT NULL,\n"
                + "    affiliation TEXT NOT NULL,\n"
                + "    email VARCHAR(320) PRIMARY KEY,\n"
                + "    password TEXT NOT NULL \n" //????????????????            
                + ");";
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("Stakeholder table was not created");
	    }
	}
	
	public static void addStakeholder(Stakeholder stkholder,Statement stmt) throws SQLException {
			//hashing the password
			String passwordS = encryptThisString(stkholder.getPassword());
			String test1 = "INSERT INTO StakeholdersTable(title, forename, surname, affiliation,"
				+ "email, password)\r\n" + 
				"VALUES ('" + stkholder.getTitle() + "','" +  stkholder.getForename() + "','" +  stkholder.getSurname() + "','" + 
				stkholder.getAffiliation() + "','" +  stkholder.getEmail() + "','" +  passwordS + "');";
			try{
				stmt.execute(test1);
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	}

	public static void addStakeholder( String titleS, String forenameS, String surnameS, String affiliationS, String emailS, String password) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			//hashing the password
			String passwordS = encryptThisString(password);
			String test1 = "INSERT INTO StakeholdersTable(title, forename, surname, affiliation,"
				+ "email, password)\r\n" + 
				"VALUES ('" + titleS + "','" +  forenameS + "','" +  surnameS + "','" +  affiliationS + "','" +  emailS + "','" +  passwordS + "');";
				//"VALUES ('Mr.', 'Legendary', 'Craft', 'C.S.', 'saradomin@yahoo.com', 'minecraft');";
			//if(isNewRecord("StakeholdersTable", "email", emailS, stmt)) {
				stmt.execute(test1);
			//}else {
				//throw new SQLException();
			//}
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("Stakeholder was not added");
	   }
	}
	
	public static List<Stakeholder> getStakeholders() {
		String extract = "SELECT * FROM StakeholdersTable";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet result = stmt.executeQuery(extract);
    		List<Stakeholder> allStakeholders = new ArrayList<Stakeholder>(); 
    		while (result.next()) {
             	String title2 = result.getString("title");
                String forename2 = result.getString("forename");
                String surname2 = result.getString("surname");
                String affiliation2 = result.getString("affiliation");
                String email2 = result.getString("email");
                String password2 = result.getString("password");
				//with those we create a object of class
             	allStakeholders.add(new Stakeholder(title2, forename2, surname2,
             			affiliation2,email2, password2));
             }
    		return allStakeholders;
    	}
	     catch (SQLException e) {
	        System.out.println("Stakeholder table display had an error");
	    }
		return null;
	}
	//--------------STAKEHOLDERS
	
	
	//--------------AUTHORS
	public static void createAuthorsTable () {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			String sql = "CREATE TABLE IF NOT EXISTS AuthorsTable (\n"
					+ "    authorID INTEGER AUTO_INCREMENT,\n"
					+ "    email VARCHAR(320) NOT NULL,\n"
					+ "    forename TEXT NOT NULL,\n"
					+ "    surname TEXT NOT NULL,\n"
					+ "    affiliation TEXT NOT NULL,\n"
					+ "    PRIMARY KEY (authorID),\n"
					+ "    FOREIGN KEY (email) REFERENCES StakeholdersTable(email)"
					+ ");";
			stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("Author table was not created");
	    }
	}
	
	public static void addAuthor(String forename, String surname, String affiliation, String email) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String insert = "INSERT INTO AuthorsTable(email, forename, surname, affiliation)\r\n" + 
				"VALUES ('" + email  + "','" +forename  + "','" + surname +"','" + affiliation +"');";
				//"VALUES ('Blessing','acd18zf','Student','dsfsfs@gmail.com','NFS', 100);";
		if(isNewRecord("AuthorsTable", "email", email, stmt)) {
			stmt.execute(insert);
		}else {
			throw new SQLException("Duplicate Author Entry in Table");
		}
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
	
	public static void addAuthor(Author author, Statement stmt) throws SQLException {
		String insert = "INSERT INTO AuthorsTable(email, forename, surname, affiliation)\r\n" + 
				"VALUES ('" + author.getEmail()  + "','" +author.getForename()+ "','" + 
				author.getSurname() +"','" + author.getAffiliation() +"');";
		try{
			if(isNewRecord("AuthorsTable", "email", author.getEmail(), stmt)) {
			stmt.execute(insert);
			}
		}catch (SQLException e) {
			System.out.println(e); 
		}
	}

	public static List<Author> getAuthors() {
		String extract = "SELECT * FROM AuthorsTable";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement(); Statement stmt2 = conn.createStatement()) {
    		ResultSet allEmails = stmt.executeQuery(extract);
    		List<Author> authors = new ArrayList<Author>(); 
    		String title = null, forename = null, surname = null, affiliation = null, email = null, password = null;
    		int id = 0;
    		String authorEmail = null;
    		while (allEmails.next()) {
    			authorEmail = allEmails.getString("email");
    			int authorID = allEmails.getInt("authorID");
    			id = authorID;
    			String getAuthor = "SELECT * FROM StakeholdersTable WHERE email = '" + authorEmail + "';";
    			System.out.println(getAuthor);
    			ResultSet result = stmt2.executeQuery(getAuthor);
    			while (result.next()) {
    				title = result.getString("title");  
    				forename = result.getString("forename");
    				surname = result.getString("surname");
    				affiliation = result.getString("affiliation");
    				email = result.getString("email");
    				password = result.getString("password");
    			}
    			authors.add(new Author(id, title, forename, surname,
             			affiliation,email, password));
    		}    		
    		return authors;
    	}catch (SQLException e) {
	        System.out.println("table display had an error");
	    }
		return null;
	}
	
	public static Author selectAuthorID(String authorId) {
		String extractEmail = "SELECT * FROM AuthorsTable WHERE editorID ='" +authorId+ "';";
		String extract = "SELECT * FROM StakeholdersTable ";
		String emailAddress = null;
		int authorID = Integer.valueOf(authorId); 
		List<Author> authors = new ArrayList<Author>(); 
		String title = null, forename = null, surname = null, affiliation = null, email = null, password = null;
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet emailSet = stmt.executeQuery(extractEmail);
    		int size = 0;
    		if (emailSet != null) {
            	emailSet.last();
            	size = emailSet.getRow();
            }
            if (size == 1) {
            	emailAddress = emailSet.getString("email");
            	System.out.println(emailAddress);
            }
            String data = "WHERE email = '" + emailAddress +"';";
            System.out.println(extract+data);
            ResultSet result = stmt.executeQuery(extract+data);
    		while (result.next()) {
             	title = result.getString("title");
                forename = result.getString("forename");
                surname = result.getString("surname");
                affiliation = result.getString("affiliation");
                email = result.getString("email");
                password = result.getString("password");
				//with those we create a object of class
             }
    		authors.add(new Author(authorID, title, forename, surname,
         			affiliation, email, password));
    		return authors.get(0);
    	}
	     catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        System.out.println("Author table display had an error");
	    }
		return null;
	}
	
	public static Author selectAuthorEmail(String emailAdd) {
		String extractID = "SELECT * FROM AuthorsTable WHERE email ='" +emailAdd+ "';";
		String extract = "SELECT * FROM StakeholdersTable WHERE email ='" +emailAdd+ "';";
		String emailAddress = null; 
		List<Author> authors = new ArrayList<Author>(); 
		String title = null, forename = null, surname = null, affiliation = null, email = null, password = null;
    	int id = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement(); Statement stmt2 = conn.createStatement()) {
    		ResultSet stakeholderSet = stmt.executeQuery(extract);
            ResultSet authorSet = stmt2.executeQuery(extractID);
    		while (stakeholderSet.next() && authorSet.next()) {
             	title = stakeholderSet.getString("title");
                forename = stakeholderSet.getString("forename");
                surname = stakeholderSet.getString("surname");
                affiliation = stakeholderSet.getString("affiliation");
                email = stakeholderSet.getString("email");
                password = stakeholderSet.getString("password");
                id = authorSet.getInt("authorID");
				//with those we create a object of class
             }
    		authors.add(new Author(id, title, forename, surname,
         			affiliation, email, password));
    		return authors.get(0);
    	}
	     catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        System.out.println("Author table display had an error");
	    }
		return null;
	}
	//--------------AUTHORS
	
	//--------------AuthorsOfSubmission
	public static void createAuthorsOfSubmission() {
		String create = "CREATE TABLE IF NOT EXISTS AuthorsOfSubmission(\n"
					+ "   authorID INTEGER NOT NULL,\n"
					+ "   submissionID INTEGER NOT NULL,\n"
					+ "   mainAuthor BOOLEAN NOT NULL,\n"
					+ "   FOREIGN KEY (authorID) REFERENCES AuthorsTable(authorID),\n"
					+ "   FOREIGN KEY (submissionID) REFERENCES SubmissionsTable(submissionID),\n"
					+ "   PRIMARY KEY (authorID, submissionID)\n"
					+ ");";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			stmt.execute(create);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("AoS table was not created");
		}
	}	
	
	public static void addAuthorWithSubmission(int authorId, int subId, boolean mainA, Statement stmt) throws SQLException {
		String insert = "INSERT INTO AuthorsOfSubmission(authorID, submissionID, mainAuthor)"
				+ " VALUES('"+authorId+"', '"+subId+"', "+mainA+");";
		try {
				stmt.execute(insert);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//--------------AuthorsOfSubmission
	
	//---------------EDITORS
	public static void createEditorsTable() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			//DELETE OLD TABLE NOT DONE YET
			String sql = "CREATE TABLE IF NOT EXISTS EditorsTable(\n"
	                + "    editorID INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
	                + "    email VARCHAR(320) NOT NULL,\n"
	                + "    FOREIGN KEY (email) REFERENCES StakeholdersTable(email)"
	                + ");";
			stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("Editors table was not created");
	    }
	}
	
	public static void addEditor(String email, Statement stmt) {
		String insert = "INSERT INTO EditorsTable(email)\r\n" + 
				"VALUES ('" + email +"');";
		try {
			if(isNewRecord("EditorsTable", "email", email, stmt)) {
				stmt.execute(insert);
			}
		}catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("editor data was not added");
	   }
	}
	
	public static void addEditor(String email) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String insert = "INSERT INTO EditorsTable(email)\r\n" + 
				"VALUES ('" +  email +"');";
		if(isNewRecord("EditorsTable", "email", email, stmt)) {
			stmt.execute(insert);
		}else {
			throw new SQLException("Duplicate Editor Entry in Table");
		}
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("editor data was not added");
	   }
	}
	
	public static List<Editor> getEditor() {
		String extract = "SELECT * FROM EditorsTable";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement(); Statement stmt2 = conn.createStatement()) {
    		ResultSet allEmails = stmt.executeQuery(extract);
    		List<Editor> editors = new ArrayList<Editor>();
			String title = null, forename = null, surname = null, affiliation = null, email = null, password = null;
    		int id = 0;
    		String editorEmail = null;
			while (allEmails.next()) {
    			editorEmail = allEmails.getString("email");
    			int editorID = allEmails.getInt("editorID");
    			id = editorID;
    			String getEditor = "SELECT * FROM StakeholdersTable WHERE email = '" + editorEmail + "';";
    			System.out.println(getEditor);
    			ResultSet result = stmt2.executeQuery(getEditor);
    			while (result.next()) {
    				title = result.getString("title");  
    				forename = result.getString("forename");
    				surname = result.getString("surname");
    				affiliation = result.getString("affiliation");
    				email = result.getString("email");
    				password = result.getString("password");
    			}
    			//with those we create a object of class
    		editors.add(new Editor(id, title, forename, surname,
         			affiliation,email, password));
			} return editors;
		}catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        System.out.println("editor table display had an error");
	    }
		return null;
	}
	
	public static List<Editor> selectEditorID(String editorId) {
		String extractEmail = "SELECT * FROM EditorsTable WHERE editorID ='" +editorId+ "';";
		String extract = "SELECT * FROM StakeholdersTable ";
		String emailAddress = null;
		int editorID = Integer.valueOf(editorId); 
		List<Editor> editors = new ArrayList<Editor>(); 
		String title = null, forename = null, surname = null, affiliation = null, email = null, password = null;
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet emailSet = stmt.executeQuery(extractEmail);
    		int size = 0;
    		if (emailSet != null) {
            	emailSet.last();
            	size = emailSet.getRow();
            }
            if (size == 1) {
            	emailAddress = emailSet.getString("email");
            	System.out.println(emailAddress);
            }
            String data = "WHERE email = '" + emailAddress +"';";
            System.out.println(extract+data);
            ResultSet result = stmt.executeQuery(extract+data);
    		while (result.next()) {
             	title = result.getString("title");
                forename = result.getString("forename");
                surname = result.getString("surname");
                affiliation = result.getString("affiliation");
                email = result.getString("email");
                password = result.getString("password");
				//with those we create a object of class
             }
    		editors.add(new Editor(editorID, title, forename, surname,
         			affiliation, email, password));
    		return editors;
    	}
	     catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        System.out.println("editor table display had an error");
	    }
		return null;
	}
	
	public static Editor selectEditorEmail(String emailAdd) {
		String extractID = "SELECT * FROM EditorsTable WHERE email ='" +emailAdd+ "';";
		String extract = "SELECT * FROM StakeholdersTable WHERE email ='" +emailAdd+ "';";
		String emailAddress = null; 
		List<Editor> editors = new ArrayList<Editor>(); 
		String title = null, forename = null, surname = null, affiliation = null, email = null, password = null;
    	int id = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement(); Statement stmt2 = conn.createStatement()) {
    		ResultSet stakeholderSet = stmt.executeQuery(extract);
            ResultSet editorSet = stmt2.executeQuery(extractID);
    		while (stakeholderSet.next() && editorSet.next()) {
             	title = stakeholderSet.getString("title");
                forename = stakeholderSet.getString("forename");
                surname = stakeholderSet.getString("surname");
                affiliation = stakeholderSet.getString("affiliation");
                email = stakeholderSet.getString("email");
                password = stakeholderSet.getString("password");
                id = editorSet.getInt("editorID");
				//with those we create a object of class
             }
    		editors.add(new Editor(id, title, forename, surname,
         			affiliation, email, password));
    		return editors.get(0);
    	}
	     catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        System.out.println("editor table display had an error");
	    }
		return null;
	}
	//---------------EDITORS
	
	//---------------BOARD OF JOURNALS
	public static void createBoardOfJournals() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String sql = "CREATE TABLE IF NOT EXISTS BoardOfJournals (\n"
                + "    editorID INTEGER NOT NULL,\n"
                + "    issn VARCHAR(8) NOT NULL,\n"
                + "    chief_editor BOOLEAN,\n"
                + "    FOREIGN KEY (editorID) REFERENCES EditorsTable(editorID),\n"
                + "    FOREIGN KEY (issn) REFERENCES JournalsTable(issn),\n"
                + "    PRIMARY KEY(editorID, issn)"
                + ");";
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("BoJ table was not created");
	    }
	}
	
	public static void addEditorToJournal(int edd, String issn) {
		addEditorToJournal(edd,issn,false);
	}
	
	public static void addEditorToJournal(Editor edd, String issn, boolean chiefEdd) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			int editorId = edd.getId();
			String insert = "INSERT INTO BoardOfJournals(editorID, issn, chief_editor)\r\n"
					+ " VALUES ('"+editorId+"', '"+issn+"', "+chiefEdd+");";
			stmt.execute(insert);
		}
		catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("Editor was not added to BoJ");
	   }
	}
	
	public static void addEditorToJournal(int eddID, String issn, boolean chiefEdd) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			String insert = "INSERT INTO BoardOfJournals(editorID, issn, chief_editor)\r\n"
					+ " VALUES ('"+eddID+"', '"+issn+"', "+chiefEdd+");";
			stmt.execute(insert);
		}
		catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("Editor was not added to BoJ");
	   }
	}
	
	//chef editor or not
		public static boolean getEditorStatus(int editorID, String issn) {
			String extractStatus = "SELECT * FROM BoardOfJournals WHERE editorID ='" +editorID+ " AND "+ "issn = " + issn +"';";
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
				if(stmt.executeQuery(extractStatus) != null) {
					ResultSet status = stmt.executeQuery(extractStatus);
					while (status.next() ) {
		             	Boolean stat = status.getBoolean("chief_editor");
		             	System.out.println(stat);
		             	return stat;
		             }					
				}	    		
			}
			catch (SQLException e) {
			       System.out.println(e.getMessage());
			       System.out.println("Unabled to find Editor Status");
			   }
			return false;
		}
    //get team id
		public static List getIssn(int editorID){
			
			String extractIssn = "SELECT issn FROM BoardOfJournals WHERE editorID ='" + editorID + "';";
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
				ResultSet issn = stmt.executeQuery(extractIssn);
				
				List<String> listIssn = new ArrayList<>();
				while (issn.next()) {
	             	String stat = issn.getString("issn");
	             	System.out.println(stat);
	             	listIssn.add(stat);
	             }
				return listIssn;
			}
			catch (SQLException e) {
			       System.out.println(e.getMessage());
			       System.out.println("Unable to get ISSN");
			   }
			
			
			return null;
		}
		
		// get the editors id using issn
		public static List getEditorId(String issnNr){
			String extractIds = "SELECT editorID FROM BoardOfJournals WHERE issn ='" + issnNr + "';";
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
				ResultSet editorsIds = stmt.executeQuery(extractIds);
				List<Integer> listEditorsIds = new ArrayList<>();
				
				while (editorsIds.next() ) {
	             	int stat = editorsIds.getInt("editorID");
	             	System.out.println(stat);
	             	listEditorsIds.add(stat);
	             }
				return listEditorsIds;
				
			}
			catch (SQLException e) {
			       System.out.println(e.getMessage());
			       System.out.println("Unable to get editor number using ISSN");
			   }
			
			return null;
		}	
	//---------------BOARD OF JOURNALS
	
	
	//---------------REVIEWERS
	public static void createReviewersTable() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			String createTable ="CREATE TABLE IF NOT EXISTS ReviewersTable (\n"
							+ "    reviewerID INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
							+ "    email VARCHAR(320) NOT NULL,\n"
							+ "    FOREIGN KEY (email) REFERENCES StakeholdersTable(email)\n"
							+ ");";
			stmt.execute(createTable);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("Reviewers table was not created");
	    }
	}
	
	public static void addReviewer(Reviewer reviewer, Statement stmt){
        String insert = "INSERT INTO ReviewersTable(email)\r\n" + 
				"VALUES ('" + reviewer.getEmail() +"');";
        try {
        	if (isNewRecord("ReviewersTable", "email", reviewer.getEmail(), stmt)) {
        		stmt.execute(insert);
        		}
        }catch (SQLException e) {
        	System.out.println(e.getMessage());
        	System.out.println("Reviewer was not added");
        }
	}
	
	public static void addReviewer(String emailAdd) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			String insert = "INSERT INTO ReviewersTable(email)\r\n" + 
					"VALUES ('" +  emailAdd +"');";
			if(isNewRecord("ReviewersTable", "email", emailAdd, stmt)) {
				stmt.execute(insert);
			}else {
				throw new SQLException("Duplicate Reviewer Entry in Table. Entry not added.");
				}
		} catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("Reviewer data was not added");
	   }
	}
	
	public static List<Reviewer> getReviewer() {
		String extract = "SELECT * FROM ReviewersTable";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement(); Statement stmt2 = conn.createStatement()) {
    		ResultSet allEmails = stmt.executeQuery(extract);
    		List<Reviewer> reviewers = new ArrayList<Reviewer>();
			String title = null, forename = null, surname = null, affiliation = null, email = null, password = null;
    		int id = 0;
    		String reviewerEmail = null;
			while (allEmails.next()) {
    			reviewerEmail = allEmails.getString("email");
    			int reviewerID = allEmails.getInt("reviewerID");
    			id = reviewerID;
    			String getReviewer = "SELECT * FROM StakeholdersTable WHERE email = '" + reviewerEmail + "';";
    			System.out.println(getReviewer);
    			ResultSet result = stmt2.executeQuery(getReviewer);
    			while (result.next()) {
    				title = result.getString("title");  
    				forename = result.getString("forename");
    				surname = result.getString("surname");
    				affiliation = result.getString("affiliation");
    				email = result.getString("email");
    				password = result.getString("password");
    			}
    			//with those we create a object of class
    		reviewers.add(new Reviewer(id, title, forename, surname,
         			affiliation,email, password));
			} 
			return reviewers;			
		}catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        System.out.println("Reviewer table had an error");
	    }
		return null;
	}
	
	public static Reviewer selectReviewerEmail(String emailAdd) {
		String extractID = "SELECT * FROM ReviewersTable WHERE email ='" +emailAdd+ "';";
		String extract = "SELECT * FROM StakeholdersTable WHERE email ='" +emailAdd+ "';";
		String emailAddress = null; 
		List<Reviewer> reviewers = new ArrayList<Reviewer>(); 
		String title = null, forename = null, surname = null, affiliation = null, email = null, password = null;
    	int id = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement(); Statement stmt2 = conn.createStatement()) {
    		ResultSet stakeholderSet = stmt.executeQuery(extract);
            ResultSet reviewerSet = stmt2.executeQuery(extractID);
    		while (stakeholderSet.next() && reviewerSet.next()) {
             	title = stakeholderSet.getString("title");
                forename = stakeholderSet.getString("forename");
                surname = stakeholderSet.getString("surname");
                affiliation = stakeholderSet.getString("affiliation");
                email = stakeholderSet.getString("email");
                password = stakeholderSet.getString("password");
                id = reviewerSet.getInt("reviewerID");
				//with those we create a object of class
             }
    		reviewers.add(new Reviewer(id, title, forename, surname,
         			affiliation, email, password));
    		String extractTeam = "SELECT teamID FROM TeamOfReviewers WHERE reviewerID = '"+reviewers.get(0).getId()+"';";
    		ResultSet teams = stmt2.executeQuery(extractTeam);
    		int teamID = 0;
    		while(teams.next()) {
    			teamID = teams.getInt("teamID"); 
    		}
    		reviewers.get(0).setTeamId(teamID);
    		return reviewers.get(0);
    	}
	     catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        System.out.println("Reviewer table had an error");
	    }
		return null;
	}
	
	public static String getTeamAffiliations(Reviewer reviewer) {
		int teamId = reviewer.getTeamId();
		String findRevIDs = "SELECT reviewerID FROM TeamOfReviewers WHERE teamID ='"+teamId+"';";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){	
			//grab all reviewerIDs for a given team
			ResultSet set = stmt.executeQuery(findRevIDs);
			String conflictRevIDs = "";
			boolean first = true;
			while (set.next()) {
				if (first) {
					first = false;
					conflictRevIDs += "'"+set.getInt("reviewerID")+"'";
				}else {
					conflictRevIDs += ", '"+set.getInt("reviewerID")+"'";
				}
			}
			System.out.println(conflictRevIDs);
			String newConflictRevIDs = new String(conflictRevIDs);
			//grab all the emails for the list of reviewerIDs
			String findConflict = "SELECT * FROM ReviewersTable WHERE reviewerID IN ("+newConflictRevIDs+");";
			ResultSet set2 = stmt.executeQuery(findConflict);
			first = true;
			String conflictEmails = "";
			while (set2.next()) {
				if (first) {
					first = false;
					conflictEmails += "'"+set2.getString("email")+"'";
				}else {
					conflictEmails += ", '"+set2.getString("email")+"'";
				}
			}
			System.out.println(conflictEmails);
			String newConflictEmails = new String(conflictEmails);
			//grab all the affiliations for the list of emails 
			String findAffs = "SELECT * FROM AuthorsTable WHERE email IN ("+newConflictEmails+");";
			ResultSet set3 = stmt.executeQuery(findAffs);
			first = true;
			String conflictAffs = "";
			while (set3.next()) {
				if (first) {
					first = false;
					conflictAffs += "'"+set3.getString("affiliation")+"'";
				}else {
					conflictAffs += ", '"+set3.getString("affiliation")+"'";
				}
			}
			System.out.println(conflictAffs);
			return conflictAffs;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	//---------------REVIEWERS
	
	//---------------REVIEWER TEAM TABLES
	public static void createReviewerTeamTable() {
		String createTeam = "CREATE TABLE IF NOT EXISTS ReviewerTeams (\n"
						+ "    teamID INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
						+ "    totalReviews INTEGER NOT NULL\n"
						+ ");";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			stmt.execute(createTeam);			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Reviewer teams table not created");
		}
						
	}
	
	public static int addNewTeam(Statement stmt) {
		String addTeam = "INSERT INTO ReviewerTeams(totalReviews) VALUES ('0');";
		try {
			stmt.execute(addTeam);
			int teamID = 0;
			ResultSet set = stmt.executeQuery("SELECT LAST_INSERT_ID();");
			while(set.next()) {
				teamID = set.getInt(1);
			}
			return teamID;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("New team for reviewers was not added");
		}
		return 0;
	}
	
	public static int getTotalReviews(int teamId) {
		String totRevs = "SELECT * FROM ReviewerTeams WHERE teamID = '"+teamId+"';";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			ResultSet set = stmt.executeQuery(totRevs);
			int total = 0;
			while(set.next()) {
				total = set.getInt("totalReviews");
			}
			return total;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Reviewer teams table not created");
		}
		return 0;
	}
	
	public static void updateTeamReviews(int teamId, int newSubsCount,Statement stmt) {
		int totRevs = getTotalReviews(teamId);
		int totalCount = totRevs + newSubsCount;
		try {
			if (totalCount <= 3) {
				String update = "UPDATE ReviewerTeams SET totalReviews = '"+totalCount+"'"
						+" WHERE teamID = '"+teamId+"';";
				stmt.executeUpdate(update);
			}else {
				throw new SQLException("Total reviews exceeds required amount.");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void createTeamOfReviewersTable() {
		String createTable = "CREATE TABLE IF NOT EXISTS TeamOfReviewers (\n"
						+ "    teamID INTEGER NOT NULL,\n"
						+ "    reviewerID INTEGER NOT NULL,\n"
						+ "    FOREIGN KEY (teamID) REFERENCES ReviewerTeams(teamID),\n"
						+ "    FOREIGN KEY (reviewerID) REFERENCES ReviewersTable(reviewerID),\n"
						+ "    PRIMARY KEY (teamID, reviewerID)"
						+ ");";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			stmt.execute(createTable);			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Team of reviewers table not created");
		}				
	}
	
	public static void addReviewerToTeam(int teamID, int reviewerID, Statement stmt) {
		String addRev = "INSERT INTO TeamOfReviewers(teamID, reviewerID) "
				+ "VALUES ('"+teamID+"', '"+reviewerID+"');";
		try {
			stmt.execute(addRev);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Reviewer was not added to the new team.");
		}
	}
	
	public static List<Integer> getTeamIDs(int revID){
		String getTeams = "SELECT * FROM TeamOfReviewers WHERE reviewerID ='"+revID+"';";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			ResultSet set = stmt.executeQuery(getTeams);
			List<Integer> teamIDs = new ArrayList<Integer>();
			while(set.next()) {
				teamIDs.add(set.getInt("teamID"));
			}
			return teamIDs;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldn't get list of teams");
		}		
		return null;
	}
	//---------------REVIEWER TEAM
	
	//---------------SUBMISSION
	public static void createSubmissionsTable() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
				String create = "CREATE TABLE IF NOT EXISTS SubmissionsTable(\n"
		                + "    submissionID INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
						+ "    title TEXT NOT NULL,\n"
		                + "    abstract TEXT NOT NULL,\n"
						+ "    issn VARCHAR(8),\n"
		                + "    initialPDF MEDIUMBLOB NOT NULL,\n "
		                + "    revisedPDF MEDIUMBLOB,\n"
		                + "    totalReviews INTEGER NOT NULL,\n"
		                + "    FOREIGN KEY (issn) REFERENCES JournalsTable(issn)"
		                + ");";
				stmt.execute(create);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("table was not created");
	    }
	}	
	
	public static int addNewSubmission(Submission submission) {
		String insert = "INSERT INTO SubmissionsTable(title, abstract, issn, initialPDF, totalReviews) "
				+ "VALUES (?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement() ;PreparedStatement ps = conn.prepareStatement(insert)) {
	        // read from file into a byte array to be able to store in BLOB
	        File pdfFile = submission.getOrgFile();
	        byte[] pdfData = new byte[(int) pdfFile.length()];
	        DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
	        dis.readFully(pdfData); 
	        dis.close();
	        
	        //prepare the statement with values
			ps.setString(1, submission.getTitle());
			ps.setString(2, submission.getAbstract());
			ps.setString(3, submission.getIssn());
			ps.setBytes(4, pdfData);
			ps.setInt(5, submission.getTotalReviews());
			ps.execute();
			int subID = 0;
			ResultSet set = ps.executeQuery("SELECT LAST_INSERT_ID();");
			while(set.next()) {
				subID = set.getInt(1);
			}
			return subID;
		}
		 catch (Exception e) {
			System.out.println(e.getMessage());
	        System.out.println("Submission was not added");
	    }
		return 0;
	}	
	
	public static void addRevisedSubmission(int submissionId, File newFile) {
		String insert = "UPDATE SubmissionsTable SET revisedPDF = ? \n"
				+ "WHERE submissionID = '"+submissionId+"';";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement() ;PreparedStatement ps = conn.prepareStatement(insert)) {
			
	        // read from file into a byte array to be able to store in BLOB
	        byte[] pdfData = new byte[(int) newFile.length()];
	        DataInputStream dis = new DataInputStream(new FileInputStream(newFile));
	        dis.readFully(pdfData); 
	        dis.close();
	        
	        //prepare the statement with values
			ps.setBytes(1, pdfData);
			ps.execute(); 
		}
		 catch (Exception e) {
			System.out.println(e.getMessage());
	        System.out.println("Submission was not added");
		 }
	}
	
	//updates total reviews a submission has
	public static void updateSubmissionReviews(int subId) {
		String findTotRevs = "SELECT * FROM SubmissionsTable WHERE submissionID ='"+subId+"';";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
        Statement stmt = conn.createStatement()) {
			ResultSet set = stmt.executeQuery(findTotRevs);
			int total = 0;
			while (set.next()) {
				total = new Integer(set.getInt("totalReviews"));
			}
			int totalCount = total + 1;
			if (totalCount <= 3) {
				String update = "UPDATE SubmissionsTable SET totalReviews = '"+totalCount+"'"
						+" WHERE submissionID = '"+subId+"';";
				stmt.executeUpdate(update);
			}else {
				throw new SQLException("Total reviews exceeds required amount.");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Submission getSubmissionID(int subId, Statement stmt) {
		String getSub = "SELECT * FROM SubmissionsTable WHERE submissionID = '"+subId+"';";
		try {
			ResultSet set = stmt.executeQuery(getSub);
			List<Submission> subms = new ArrayList<Submission>();
			while(set.next()) {
				String subTitle = set.getString("title");
				String abstractTxt = set.getString("abstract");
				String issn = set.getString("issn");
				int totRevs = set.getInt("totalReviews");
				subms.add(new Submission(subId, subTitle, abstractTxt, issn, totRevs));
				}
			return subms.get(0);
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
	
	public static List<Submission> getSubmissions(List<Integer> subIds, Statement stmt){
		String subList = "";
		boolean first = true;
		for(int sub: subIds) {
			if (first) {
				first = false;
				subList += "'"+sub+"'";
			}else {
				subList += ", '"+sub+"'";
			}
		}
		String getSub = "SELECT * FROM SubmissionsTable WHERE submissionID IN ("+subList+");";
		try {
			ResultSet set = stmt.executeQuery(getSub);
			List<Submission> subms = new ArrayList<Submission>();
			while(set.next()) {
				int subId = set.getInt("submissionID");
				String subTitle = set.getString("title");
				String abstractTxt = set.getString("abstract");
				String issn = set.getString("issn");
				int totRevs = set.getInt("totalReviews");
				Blob blob = set.getBlob("initialPDF");
				byte[] data = blob.getBytes(1, (int) blob.length());
				File pdFile = File.createTempFile(subTitle, ".pdf");
				FileOutputStream fos = new FileOutputStream(pdFile);
				fos.write(data);
				subms.add(new Submission(subId, subTitle, abstractTxt, issn, pdFile, totRevs));
				}
			return subms;
		}catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
	
	public static List<Submission> getValidSubs(Reviewer reviewer) {
		String revAffs = getTeamAffiliations(reviewer);
		String findConflict = "SELECT authorID FROM AuthorsTable WHERE affiliation IN ("+revAffs+");"; 
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			//grab all authors that conflict the reviewer teams affilations
			ResultSet set = stmt.executeQuery(findConflict);
			String conflictAuthors = "";
			boolean first = true;
			while (set.next()) {
				if (first) {
					first = false;
					conflictAuthors += "'"+set.getInt("AuthorID")+"'";
				}else {
					conflictAuthors += ", '"+set.getInt("authorID")+"'";
				}
			}
			System.out.println(conflictAuthors);
			
			//grab all submission id for these conflicted authors
			String conflictedSubs = "SELECT * FROM AuthorsOfSubmission WHERE authorID IN ("+conflictAuthors+");";
			ResultSet set2 = stmt.executeQuery(conflictedSubs);
			String conflictSubs = "";
			first = true;
			while (set2.next()) {
				if (first) {
					first = false;
					conflictSubs += "'"+set2.getInt("submissionID")+"'";
				}else {
					conflictSubs += ", '"+set2.getInt("submissionID")+"'";
				}
			}
			System.out.println(conflictSubs);
			List<Submission> subList = getSubList(reviewer.getTeamId());
			String chosenSubs = "";
			first = true;
			if(subList != null) {
				for(Submission sub: subList) {
					if (first) {
						first = false;
						chosenSubs += "'"+sub.getId()+"'";
					}else {
						chosenSubs += ", '"+sub.getId()+"'";
					}
				}
			}
			if (!chosenSubs.trim().isEmpty()) {
				conflictSubs += ", "+chosenSubs;
			}
			//grab all submissions with less than 3 review teams and not equal to conflicted subs
			String validSubs = "SELECT * FROM SubmissionsTable WHERE (totalReviews < '3') AND "
					+ "submissionID NOT IN ("+conflictSubs+");";
			ResultSet set3 = stmt.executeQuery(validSubs);
			int subID = 0;
			List<Submission> subms = new ArrayList<Submission>();
			while (set3.next()) {
				int subId = set3.getInt("submissionID");
				String subTitle = set3.getString("title");
				String abstractTxt = set3.getString("abstract");
				String issn = set3.getString("issn");
				int totRevs = set3.getInt("totalReviews");
				subms.add(new Submission(subId, subTitle, abstractTxt, issn, totRevs));
				}
			return subms;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Could not find any submissions");
		}
		return null;
	}
	//---------------SUBMISSION
	
	//---------------REVIEW SYSTEM ALL TABLES
	public static void createReviewTable() {	
		String reviewTable = "CREATE TABLE IF NOT EXISTS ReviewTable (\n"
				+ "    teamID INTEGER,\n"
				+ "    submissionID INTEGER,\n" 
				+ "    reviewerX INTEGER NOT NULL,\n"
				+ "    reviewed BOOLEAN NOT NULL,\n"
				+ "    PRIMARY KEY (teamID, submissionID),\n"
				+ "    FOREIGN KEY (teamID) REFERENCES ReviewerTeams(teamID),\n"
				+ "    FOREIGN KEY (submissionID) REFERENCES SubmissionsTable(submissionID)"
				+");";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			stmt.execute(reviewTable);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Review table was not created");
		}
	}
	
	public static void addReview(int revId, int subId, Statement stmt) throws SQLException {
		String findLastRevX = "SELECT * FROM ReviewTable WHERE submissionID ='"+subId+"';";
		ResultSet revSet = stmt.executeQuery(findLastRevX);
		int revX = 0;
		if (revSet.next()) {
        	revSet.first();
        	int newID = revSet.getInt("reviewerX");
        	revX = newID +1;
        }else {
        	revX = 1;	        	
        }
        String addChoice = "INSERT INTO ReviewTable(reviewerID, submissionID, reviewerX)"
        	+ " VALUES ('"+revId+"', '"+subId+"', '"+revX+"');";
        stmt.execute(addChoice);
	}

	
	public static Review getReview(int teamId, int subId) {
		String find = "SELECT * FROM ReviewTable WHERE teamID ='"+teamId+"' AND submissionID = '"+subId+"';";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			ResultSet set = stmt.executeQuery(find);
			Review review = null;
			while(set.next()) {
				int revX = set.getInt("reviewerX");
				boolean reviewed = set.getBoolean("reviewed");
				review = new Review(teamId, subId, revX, reviewed);
			}
			return review;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldn't get review");
		}
		return null;
	}
	
	public static void updateReviewTable(int teamId, int subId) {
		String update = "UPDATE ReviewTable SET reviewed = "+true+" WHERE "
				+ "teamID = '"+teamId+"' AND submissionID = '"+subId+"';";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			stmt.executeUpdate(update);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//return list of Submissions for that reviewer team, that isn't yet reviewed
	public static List<Submission> getSubList(int teamId) {
		String find = "SELECT * FROM ReviewTable WHERE teamID ='"+teamId+"' AND reviewed = false;";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			ResultSet set = stmt.executeQuery(find);
			List<Integer> subIds = new ArrayList<Integer>();
			while (set.next()) {
				subIds.add(set.getInt("submissionID"));				
			}
			return getSubmissions(subIds, stmt);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldn't get table");
		}
		return null;
	}
		
	public static void createReviewFormTable() {
		String reviewFormTable = "CREATE TABLE IF NOT EXISTS ReviewFormTable (\n"
				+ "    teamID INTEGER,\n"
				+ "    submissionID INTEGER,\n"
				+ "    reviewerX INTEGER,"
                + "    summary text,\n"
                + "    typos text,\n"
                + "    PRIMARY KEY (submissionID, reviewerX),\n"
                + "    FOREIGN KEY (teamID, submissionID) REFERENCES ReviewTable(teamID, submissionID)\n"
                + ");";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()){
			stmt.execute(reviewFormTable);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Review Form table was not created");
		}
		}
	
	public static void createResponseTable() {
		String responseTable = "CREATE TABLE IF NOT EXISTS ResponseTable (\n"
                + "    teamID INTEGER,\n"
                + "    submissionID INTEGER,\n"
                + "    reviewerX integer NOT NULL,\n"
                + "    questionNum INTEGER,\n"
                + "    question TEXT NOT NULL,\n"
                + "    answer TEXT,\n"
                + "    PRIMARY KEY (submissionID, reviewerX, questionNum),\n"
                + "    FOREIGN KEY (teamID, submissionID) REFERENCES ReviewTable(teamID, submissionID)\n"
                + ");";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
				Statement stmt = conn.createStatement()){
			stmt.execute(responseTable);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Response table was not created");
		}						
	}	
	
	public static List<String> getQuestions(int subId, int revX){
		String getQue = "SELECT * FROM ResponseTable WHERE submissionID ='"+subId+"' AND"
				+ " reviewerX ='"+revX+"';";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
				Statement stmt = conn.createStatement()){
			ResultSet set = stmt.executeQuery(getQue);
			List<String> questions = new ArrayList<String>(); 
			while(set.next()) {
				String que = set.getString("question");
				questions.add(que);
			}
			return questions;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldn't get questions from Response Table");
		}
		return null;
	}
	
	public static void addAnswer(int subId, int revX, List<String> answers) {
		String respond = null;
		String insert; 
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
		           Statement stmt = conn.createStatement()) {

		for(int i = 0; i < answers.size(); i++) {
		respond = answers.get(i);
		insert = "UPDATE ResponseTable SET answer = '" + respond + "'\n" +
		"WHERE submissionID = '"+subId+ "' AND reviewerX = '"+ revX + "' AND questionNum = '" + (i+1) +"';";

		stmt.execute(insert);
		}
		}
		catch (SQLException e) {
		System.out.println(e.getMessage());
		System.out.println("Answer was not added");
		}
	}

	static void addReviewTable(int teamID,int submissionID) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
            Statement stmt = conn.createStatement()) {
			String findReviews = "SELECT reviewerX FROM ReviewTable WHERE submissionID ='"+submissionID+"';";
			ResultSet reviews = stmt.executeQuery(findReviews);
			List<Integer> reviewX = new ArrayList<Integer>();
			while(reviews.next()) {
				reviewX.add(reviews.getInt("reviewerX"));
			}
			int reviewerX;
			if(reviewX.size() == 0) {
				reviewerX=1;
			}else if(reviewX.size() == 1) {
				reviewerX =2;
			}
			else if (reviewX.size() == 2) {
				reviewerX =3;
			} else {
				throw new SQLException("Too many reviewers already");
			}
			String insert = "INSERT INTO ReviewTable(teamID, submissionID, reviewerX, reviewed)\r\n"
				+ " VALUES ('"+teamID+"', '"+submissionID+"', '"+reviewerX+"', "+false+");";
			stmt.execute(insert);
			String findTotRevs = "SELECT totalReviews FROM SubmissionsTable WHERE submissionID ='"+submissionID+"';";
			ResultSet set = stmt.executeQuery(findTotRevs);
			int totRevs = 0;
			while(set.next()) {
				totRevs = set.getInt("totalReviews");
			}
			String updateSubm = "UPDATE SubmissionsTable SET totalReviews ='"+(totRevs+1)+"' "
					+ "WHERE submissionID ='"+submissionID+"';";
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Reviewer did not passed");
		}
	}
	
	public static void addReviewFormTable(int reviewerID,int submissionID,int reviewerX,String summary,String typos) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	        Statement stmt = conn.createStatement()) {
				String insert = "INSERT INTO ReviewFormTable(teamID,submissionID,reviewerX,summary,typos)\r\n"
					+ " VALUES ('"+reviewerID+"', '"+submissionID+"','"+reviewerX+"','"+summary+"', '"+typos+"');";
				stmt.execute(insert);			
				}
				catch (SQLException e) {
				System.out.println(e.getMessage());
				System.out.println("Your summary and typos did not upload");
			}
	}
	
	public static void addResponseTable(Review review, List<String> questions) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	            Statement stmt = conn.createStatement()) {
			String question;
			String insert;
			int teamid = review.getTeamID();
			int subid = review.getSubID();
			int revX = review.getReviewerX();
			for(int i=0;i<questions.size();i++) {
				question = questions.get(i);
				insert = "INSERT INTO ResponseTable(teamID, submissionID, reviewerX, questionNum, question)\r\n"
						+ " VALUES ('"+teamid+"', '"+subid+"', '"+revX+"', '"+(i+1)+"', '"+question+"');";
				stmt.execute(insert);
			}
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Your question did not upload");
		}
	}

	public static void updateInfo(String title,String forname,String surname,String affiliation, String email,String password, String oldEmail) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			String hashPass = encryptThisString(password);
			String delS = "DELETE FROM StakeholdersTable WHERE email ='"+oldEmail+"';";
			String delE = "DELETE FROM EditorsTable WHERE email ='"+oldEmail+"';";
			String delA = "DELETE FROM AuthorsTable WHERE email ='"+oldEmail+"';";
			String delR = "DELETE FROM ReviewersTable WHERE email ='"+oldEmail+"';";
			String updateS = "INSERT INTO StakeholdersTable(title, forename, surname, affilation, email, password) VALUES "
					+ "('"+title+"', '"+forname+"', '"+surname+"', '"+affiliation+"', '"+email+"', '"+hashPass+"');";
			String updateE ="UPDATE EditorsTable SET email ='" +email +"' WHERE email ='"+oldEmail+"';";
			String updateA ="UPDATE AuthorsTable SET surname = '"+surname+"' ,forename = '"+forname+"',affiliation = '"+affiliation+"',email ='"+email +"' WHERE email ='"+oldEmail+"';";
			String updateR ="UPDATE ReviewersTable SET email ='" +email +"' WHERE email ='"+oldEmail+"';";
			stmt.execute(delE);
			stmt.execute(delA);
			stmt.execute(delR);
			stmt.execute(delS);
			stmt.executeUpdate(updateS);
			stmt.executeUpdate(updateE);
			stmt.executeUpdate(updateA);
			stmt.executeUpdate(updateR);
		}catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("Sorry, your updated information did not upload. Please try again.");
	   }
	}
	
	public static void createVerdictsTable() {
		String verdictsTable = "CREATE TABLE IF NOT EXISTS VerdictsTable (\n"
                + "    teamID INTEGER,\n"
                + "    submissionID INTEGER,\n"
                + "    reviewerX integer NOT NULL,\n"
                + "    initialVerdict TEXT NOT NULL,\n"
                + "    finalVerdict TEXT,\n"
                + "    PRIMARY KEY (submissionID, reviewerX),\n"
                + "    FOREIGN KEY (teamID, submissionID) REFERENCES ReviewTable(teamID, submissionID)\n"
                + ");";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
				Statement stmt = conn.createStatement()){
			stmt.execute(verdictsTable);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Verdicts table was not created");
		}						
	}	
	
	public static void addInitialVerdict(int revId, int subId, int revX, String initialVerdict) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	            Statement stmt = conn.createStatement()) {
			String insert = "INSERT INTO VerdictsTable(reviewerID, submissionID, reviewerX, initialVerdict, finalVerdict)\r\n"
					+ " VALUES ('"+revId+"', '"+subId+"', '"+revX+ "', '"+ initialVerdict+ null +");";
			stmt.execute(insert);
		}
		catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("failed to add an initial verdict");
	   }
	}
	
	public static void addFinalVerdict(int revId, int subId, String finalVerdict) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	            Statement stmt = conn.createStatement()) {
			String insert = "UPDATE VerdictsTable SET finalVerdict = " +finalVerdict + "\n"
					+ "WHERE reviewerID = '"+revId+ " AND " + "submissionID = "+ subId +"';";
			stmt.executeUpdate(insert);
		}
		catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("failed to add update the final verdict");
	   }
	}
	
	//---------------REVIEW SYSTEM ALL TABLES
	
	//method to check a given primary key does not already exist in given table
	public static boolean isNewRecord(String tableName, String colName, String pKey, Statement stmt) throws SQLException {
		String findKey = "SELECT * FROM "+tableName+" WHERE "+colName+" ='"+pKey+"';";
		ResultSet set = stmt.executeQuery(findKey);
		return !(set.next());
	}
	
	public static void main(String[] args) throws SQLException {
		//createJournalsTable();
		//createStakeholdersTable();
		//createEditorsTable();
		//createBoardOfJournals();
		//createAuthorsTable();
		//createReviewersTable();
		//createSubmissionsTable();
		//createAuthorsOfSubmission();
		//createReviewerTeamTable();
		//createTeamOfReviewersTable();
		//createReviewTable();
		//createReviewFormTable();
		//createResponseTable();
		//createVerdictsTable();
		//addAuthor("Valentin", "Craciun", "UoS", "val@gmail.com");
		//addStakeholder("Mr", "Tester", "Bug", "UoN", "test@bug.com", "solved");
		//addAuthor("Tester", "Bug", "UoN", "test@bug.com");
		
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!testing if get Status works !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//getEditorStatus(3, 12345678);
		//getEditorStatus(4, 12345678);
		//System.out.println(getIssn(3));
		//System.out.println(getIssn(4));
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		/*/!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! testing Manage Editors Team (deleting) !!!!!!!!!!!!!!!!!!!!!!!!!
		addStakeholder("Mr", "For1", "Sur1", "UoS", "a@gmail.com", "a");
		addEditor("a@gmail.com");
		addEditorToJournal(new Editor(8, "Mr", "For1", "Sur1", "UoS", "a@gmail.com", "a"), 12345678, false);
		
		addStakeholder("Mr", "For2", "Sur2", "UoS", "b@gmail.com", "a");
		addEditor("b@gmail.com");
		addEditorToJournal(new Editor(9, "Mr", "For1", "Sur1", "UoS", "b@gmail.com", "b"), 12345678, false);
		
		addStakeholder("Mr", "For3", "Sur3", "UoS", "c@gmail.com", "a");
		addEditor("c@gmail.com");
		addEditorToJournal(new Editor(810, "Mr", "For1", "Sur1", "UoS", "c@gmail.com", "c"), 12345678, false);
		
		addStakeholder("Mr", "For4", "Sur4", "UoS", "d@gmail.com", "a");
		addEditor("d@gmail.com");
		addEditorToJournal(new Editor(11, "Mr", "For1", "Sur1", "UoS", "d@gmail.com", "d"), 12345678, false);
		
		addStakeholder("Mr", "For5", "Sur5", "UoS", "e@gmail.com", "a");
		addEditor("e@gmail.com");
		addEditorToJournal(new Editor(12, "Mr", "For1", "Sur1", "UoS", "e@gmail.com", "e"), 12345678, false);
		
		addStakeholder("Mr", "For6", "Sur6", "UoS", "f@gmail.com", "a");
		addEditor("f@gmail.com");
		addEditorToJournal(new Editor(13, "Mr", "For1", "Sur1", "UoS", "f@gmail.com", "f"), 12345678, false);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
		
		/*List<Stakeholder> persInfo = new ArrayList<Stakeholder>(); 
		persInfo = getStakeholders();
		for(Stakeholder i : persInfo) {
	    	System.out.println(i);
	    	}
		List<Author> persInfo = new ArrayList<Author>(); 
		persInfo = getAuthors();
		for(Author i : persInfo) {
	    	i.getProperties();
	    	System.out.println(i.getId());
	    	}
		List<Editor> persInfo = new ArrayList<Editor>(); 
		persInfo = getEditor();
		for(Editor i : persInfo) {
	    	i.getProperties();
	    	System.out.println(i.getId());
	    	}
		 List<Editor> test = selectEditorID("4");
		 test.get(0).getProperties();*/
		
		
		
		 //create Journal 
		 //addJournal("Computer Science", 12345678);
		
		 //add editor to Journal
		 //addEditorToJournal(new Editor(3, "Mr", "Valentin", "Craciun", "UoS", "val@gmail.com", "val"), 12345678, true);
		 //addEditorToJournal(new Editor(4, "Mr", "Legendary", "Craft", "UoS", "LastChaos123@gmail.com", "LC"), 12345678, false);
		
		
		////////////////////////////////////////////////LOG IN TESTING
		//create Journal2
		//addJournal("Gameing Science", 12344321);
		//addStakeholder("Ms", "Test1", "a", "UoS", "test@gmail.com", "test");					  //id:5
		//addStakeholder("Mr", "Minecraft", "Diamond", "UoS", "minecraft@gmail.com", "tester");   //id:6
		//addStakeholder("Ms", "Black", "Friday", "UoS", "black@yahoo.com", "black");			  //id:7
		//addEditor("test@gmail.com");
		//addEditor("minecraft@gmail.com");
		//addEditor("black@yahoo.com");
		
		//insertion in first journal
	    //addEditorToJournal(5, 12345678, false); //journal 1
		
		//insertion in second journal
		//addEditorToJournal(6, 12344321, false);
		//addEditorToJournal(5, 12344321, true); // normal in journal1 & chef in journal 2
		//addEditorToJournal(7, 12344321, false);
		
		
		
		/*
		 * Method to see which tables are already in the database
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			ResultSet set2;
				String drop = //"DROP TABLE SubmissionsTable;";
						//"DROP TABLE VerdictsTable;";
						//"DROP TABLE ResponseTable;";
						//"DROP TABLE ReviewFormTable;";
						//"DROP TABLE ReviewTable;"; 
						//"DROP TABLE ReviewersTable;";
						//"DROP TABLE EditionsTable;\n";
						 //"DROP TABLE EditorsTable;\n";
                         //"DROP TABLE chiefEditors;";				
						 //"DROP TABLE JournalsTable;\n";
						 //"DROP TABLE editors;\n";
						 //"DROP TABLE VolumesTable;";
						 //"DROP TABLE journalsTable;";
				//String sql = "SELECT * FROM  information_schema.tables;";
				stmt.execute(drop);
				//set2 = stmt.executeQuery(sql);
				//while(set2.next()) {
					//System.out.println(set2.getString(3));
				//}
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("No show");
	    }
		//*/
	    //addStakeholder("Mr", "Valentin", "Craciun", "UoS", "val@gmail.com", "val");
		//addStakeholder("Mr", "Legendary", "Craft", "UoS", "LastChaos123@gmail.com", "LC");
		//addStakeholder("Mr", "atv", "atv", "UoS", "last@y.com", "test");*/
		/*
		//addEditor("val@gmail.com");
		//addEditor("LastChaos123@gmail.com");
		/*
		List<Editor> editors = selectEditorEmail("mystery123@gmail.com");
		for(Editor i: editors) {
			System.out.println("Happening here");
			i.getProperties();
		}
		
		/*List<Stakeholder> editors = getStakeholder();
		for(Stakeholder i: editors) {
			i.getProperties();
		}		
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			String del = "DELETE FROM AuthorsTable WHERE email='rrai@gmail.com';"; 
			String del4 = "DELETE FROM StakeholdersTable WHERE email='rrai@gmail.com';";
			String del1 = "DELETE FROM AuthorsTable WHERE email='valentin123@gmail.com';";
			String del2 = "DELETE FROM StakeholdersTable WHERE email='valentin123@gmail.com';";
			String del3 = "DELETE FROM ReviewersTable WHERE email='valentin123@gmail.com';";
			//String getAll = "DELETE FROM StakeholdersTable WHERE email='mystery123@gmail.com';";
			stmt.execute(del1);
			stmt.execute(del2);	
			stmt.execute(del3);
			stmt.execute(del);
			stmt.execute(del4);
			//stmt.execute(getAll);
			while(set.next()) {
				int id = set.getInt("editorID");
				String email = set.getString("email");
				System.out.println(id+", "+ email);
				}
			} catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("author data was not deleted");
	   }
	*/
		//Reviewer reviewer = selectReviewerEmail("@gmail.com");
		//getValidSubs(reviewer);
		//System.out.println(getTotalReviews(2));
		//System.out.println(encryptThisString("hao_1234"));
	}
	
	// DATA BASE
	public static String encryptThisString(String input) { 
        try { 
            // getInstance() method is called with algorithm SHA-224 
            MessageDigest md = MessageDigest.getInstance("SHA-224"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
	public static Author stakeholderToAuthor(Stakeholder stkholder, boolean main) {
		Author author = new Author(stkholder.getTitle(),stkholder.getForename(),stkholder.getSurname(),
				stkholder.getAffiliation(),stkholder.getEmail(), stkholder.getPassword(), main);
		return author;
	}
	
	public static Reviewer authorToReviewer(Stakeholder author) {
		Reviewer reviewer = new Reviewer(author.getTitle(),author.getForename(),author.getSurname(),
				author.getAffiliation(),author.getEmail(), author.getPassword());
		return reviewer;
	}
	
	public static boolean checkInjection(String expression) {
		Pattern p = Pattern.compile("[^a-z^A-Z0-9@. ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(expression);
		boolean b = m.find();	
		return b;
	}
}
