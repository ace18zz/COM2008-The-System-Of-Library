import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Authors extends Stakeholders {
    private String title;
    private String username;
    private String forename;
    private String surname;
    private String affiliation;
    private String email;
    private String password;
    private int teamID;
    
	 public Authors (String titleA,String forenameA, String surnameA, String affiliationA, String emailA,String passwordA,int teamIdA) {
	    	super(titleA,forenameA,surnameA,affiliationA,emailA,passwordA,teamIdA);
	}   
    
    public void getProperties() {
		   System.out.println(title + " " + forename + " " + surname + " " + affiliation + " " + email + " " + password + " " + teamID);
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
    public static void AuthorsTable () {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String sql = "CREATE TABLE IF NOT EXISTS authorsTable (\n"
				    + "    title text NOT NULL,\n"
	                + "    forename text NOT NULL,\n"
	                + "    surname text NOT NULL,\n"
	                + "    affiliation text NOT NULL,\n"
	                + "    email text PRIMARY KEY,\n"
	                + "    password text NOT NULL,\n"
	                + "    teamID text NOT NULL,\n"
                + ");";
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("table was not created");
	    }
	}
}
