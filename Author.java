import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Author extends Stakeholder {
    private String title;
    private String forename;
    private String surname;
    private String affiliation;
    private String email;
    private String password;
    private int authorID;   
    private boolean mainAuthor;
    
    public Author(String titleA,String forenameA, String surnameA, String affiliationA, String emailA,String passwordA) {
    	super(titleA,forenameA,surnameA,affiliationA,emailA,passwordA);
    }
    
    public Author(String titleA,String forenameA, String surnameA, String affiliationA, String emailA,String passwordA,boolean mainAuthor) {
    	super(titleA,forenameA,surnameA,affiliationA,emailA,passwordA);
    	this.mainAuthor = mainAuthor;
    }
	
    public Author(int authorId,String titleA,String forenameA, String surnameA, String affiliationA, String emailA,String passwordA) {
	    	super(titleA,forenameA,surnameA,affiliationA,emailA,passwordA);
	    	this.authorID = authorId;
	}   
	
    public String authorString() {
		return (this.toString()+"--"+this.mainAuthor);
	}
    
	public int getId() {
		return this.authorID;
	}
	
	public boolean isMainAuthor() {
		return this.mainAuthor;
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
