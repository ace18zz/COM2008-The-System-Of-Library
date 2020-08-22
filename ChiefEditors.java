import java.sql.*;
import java.util.*;

public class ChiefEditors extends ChiefEditorsTable{

	public int teamId;
	private String title;
	private String forename;
	private String surname;
	private String affiliation;
	private String email;
	private String password;
	
	 public ChiefEditors(int teamId, String title, String forename, String surname, String affiliation, String email, String password) {
	    	this.teamId = teamId;
	    	this.title = title;
	    	this.forename = forename;
	    	this.surname = surname;
	    	this.affiliation = affiliation;
	    	this.email = email;
	    	this.password = password;
	    }
	 
	 public void getProperties() {
		   System.out.println(teamId + " " + title + " " + forename + " " + surname + " " + affiliation + " " + 
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
			ChiefEditorsTable();
			addData(2,"Mr.", "Legendary", "Craft", "C.S.", "saradomin@yahoo.com", "minecraft");
			List<ChiefEditors> chefEditors = new ArrayList <ChiefEditors>();
			chefEditors = getData();
			for(ChiefEditors i: chefEditors) {
				i.getProperties();
			}
		}
}
