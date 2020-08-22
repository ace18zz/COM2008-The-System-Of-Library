import java.sql.*;
import java.util.*;
import java.util.*;
public class ChiefEditorsTable {
	public static void ChiefEditorsTable() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String sql = "CREATE TABLE IF NOT EXISTS chiefEditors (\n"
                + "    teamID text NOT NULL,\n"
                + "    title text NOT NULL,\n"
                + "    forename text NOT NULL,\n"
                + "    surname text NOT NULL,\n"
                + "    affiliation text NOT NULL,\n"
                + "    email VARCHAR(255) PRIMARY KEY,\n"
                + "    password text NOT NULL\n"
                + ");";
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("table was not created");
	    }
	}
	
	public static void addData(int teamId2, String title2, String forename2, String surname2, String affiliation2, String email, String password) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String test1 = "INSERT INTO chiefEditors(teamId, title, forename, surname, affiliation,"
				+ "email, password)\r\n" + 
				"VALUES ('" + teamId2 + "','" + title2 + "','" +  forename2 + "','" +  surname2 + "','" +  affiliation2 + "','" +  email + "','" +  password  +"');";
				//"VALUES ('2','Mr.', 'Legendary', 'Craft', 'C.S.', 'saradomin@yahoo.com', 'minecraft');";
		stmt.execute(test1);
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
	
	public static List<ChiefEditors> getData() {
		String extract = "SELECT * FROM chiefEditors";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet result = stmt.executeQuery(extract);
    		List<ChiefEditors> chiefEditors = new ArrayList<ChiefEditors>(); 
    		while (result.next()) {
             	int teamId2 = result.getInt("teamId");
             	String title2 = result.getString("title");
                String forename2 = result.getString("surname");
                String surname2 = result.getString("surname");
                String affiliation2 = result.getString("affiliation");
                String email2 = result.getString("email");
                String password2 = result.getString("password");
				//with those we create a object of class
             	chiefEditors.add(new ChiefEditors(teamId2, title2, forename2, surname2,
             			affiliation2,email2, password2));
             }
    		return chiefEditors;
    	}
	     catch (SQLException e) {
	        System.out.println("table display had an error");
	    }
		return null;
	}
}

