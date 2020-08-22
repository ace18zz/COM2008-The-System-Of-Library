import java.sql.*;
import java.util.*;

public abstract class StakeholdersTable {
	public static void StakeholdersTable() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String sql = "CREATE TABLE IF NOT EXISTS StakeholdersTable (\n"
                + "    title text NOT NULL,\n"
                + "    forename text NOT NULL,\n"
                + "    surname text NOT NULL,\n"
                + "    affiliation text NOT NULL,\n"
                + "    email text PRIMARY KEY,\n"
                + "    password text NOT NULL,\n" //??????????
                + ");";
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("Stakeholder table was not created");
	    }
	}
	
	public static void addData( String titleS, String forenameS, String surnameS, String affiliationS, String emailS, String passwordS,int teamIdS) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String test1 = "INSERT INTO chiefEditors(teamId, title, forename, surname, affiliation,"
				+ "email, password)\r\n" + 
				"VALUES ('" + titleS + "','" +  forenameS + "','" +  surnameS + "','" +  affiliationS + "','" +  emailS + "','" +  passwordS  +"','" + teamIdS + "');";
				//"VALUES ('2','Mr.', 'Legendary', 'Craft', 'C.S.', 'saradomin@yahoo.com', 'minecraft');";
		stmt.execute(test1);
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
}


