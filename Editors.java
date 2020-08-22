import java.sql.*;
import java.util.*;

public class Editors extends Stakeholder{
	public int editorID;
	private String title;
	private String forename;
	private String surname;
	private String affiliation;
	private String email;
	private String password;
	
	public Editors(int editorsId, String title, String forename, String surname, String affiliation, String email, String password) {
    	super(title, forename, surname, affiliation, email, password);
    	this.editorID = editorID; 
    }
	
	public void getProperties() {
		   System.out.println(editorID + " " + title + " " + forename + " " + surname + " " + affiliation + " " + 
	  email + " " + password);
	   }
	
	public static void exceptionCheck() throws SQLException {
    	Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
			System.out.println("Got Connection!");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (con != null) {
				con.close();
			}
		}
    }
	
	public static void main(String[] args) throws Exception {
		exceptionCheck();
		createEditorsTable();
		addEditor(2,"saradomin@yahoo.com");
		List<Editors> editors = new ArrayList <Editors>();
		editors = getEditor();
		for(Editors i: editors) {
			i.getProperties();
		}
	}
}
