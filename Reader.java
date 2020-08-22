import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reader extends Stakeholder {
	
	 public Reader(String titleR,String forenameR, String surnameR, String affiliationR, String emailR,String passwordR) {
	    	super(titleR,forenameR,surnameR,affiliationR,emailR,passwordR);
	    }
	 
	  public void getProperties() {
		   System.out.println(title + " " + forename + " " + surname + " " + affiliation + " " + email + " " + password);
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
}
