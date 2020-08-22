import java.sql.*;
import java.util.*;
import java.util.*;
public class JournalsTable {
	public static void JournalsTable2() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			//DELETE OLD TABLE NOT DONE YET
		String sql = "CREATE TABLE IF NOT EXISTS journalsTable (\n"
                + "    title text NOT NULL,\n"
                + "    issn integer PRIMARY KEY,\n"
                + ");";
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("table was not created");
	    }
	}

	
	public static void addData(String title, int issn, int teamId) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String test1 = "INSERT INTO journalsTable(title, issn, teamId)\r\n" + 
				"VALUES ('" + title + "','" + issn + "','" +  teamId + "');";
				//"VALUES ('A.L.I.C.E','213', '100');";
		stmt.execute(test1);
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
	
	public static List<Journals> getData() {
		String extract = "SELECT * FROM journalsTable";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet result = stmt.executeQuery(extract);
    		List<Journals> journalsCreated = new ArrayList<Journals>(); 
    		while (result.next()) {
             	String title = result.getString("title");    // access by col name   String name = res.getString(“name”); int office = res.getInt(“office”);
             	int issn = result.getInt("issn");
				//with those we create a object of class
             	journalsCreated.add(new Journals(title, issn));
             }
    		return journalsCreated;
    	}
	     catch (SQLException e) {
	        System.out.println("table display had an error");
	    }
		return null;
	}
}
