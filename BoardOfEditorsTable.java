import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//not needed 
public class BoardOfEditorsTable {
	public static void BoardOfEditorsTable2() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String sql = "CREATE TABLE IF NOT EXISTS boardOfEditors (\n"
                + "    teamId int,\n"
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
		
			// to be added
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
}
