import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface AuthorsTable {
	public static void AuthorsTable () {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String sql = "CREATE TABLE IF NOT EXISTS authorsTable (\n"
                + "    title text NOT NULL,\n"
                + "    username text NOT NULL,\n"
                + "    affiliation text NOT NULL,\n"
                + "    email text NOT NULL,\n"
                + "    password text NOT NULL,\n"
                + "    teamid integer PRIMARY KEY,\n"
                + ");";
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("table was not created");
	    }
	}

	
	public static void addData(String titleA, String usernameA, String affiliationA , String emailA,String passwordA,int teamidA) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String test1 = "INSERT INTO journalsTable(title, username,affilation,email,password, teamId)\r\n" + 
				"VALUES ('" + titleA + "','" + usernameA  + "','" +  affiliationA +"','" + emailA +"','"+ passwordA +"','"+  teamidA + "');";
				//"VALUES ('Blessing','acd18zf','Student','dsfsfs@gmail.com','sfs', 100);";
		stmt.execute(test1);
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
	
	public static ResultSet getData() {
		String extract = "SELECT * FROM Authors";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
            Statement stmt = conn.createStatement()) {
    		ResultSet result = stmt.executeQuery(extract);
    		List<Authors> authors = new ArrayList<Authors>(); 
    		while (result.next()) {
             	String titleA = result.getString("title");
             	String surnameA = result.getString("surname");
             	String forenameA = result.getString("forename");
             	String affiliationA = result.getString("affiliation");
             	String emailA = result.getString("email");
				String passwordA = result.getString("password");
				int teamIdA = result.getInt("teamID");
				authors.add(new Authors(titleA,forenameA,surnameA,affiliationA,emailA,passwordA,teamIdA));
    		}
    		return result;
    }
	     catch (SQLException e) {
	        System.out.println("table display had an error");
	    }
		return null;
	}
}