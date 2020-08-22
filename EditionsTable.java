import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditionsTable {
	
	
	
	public static void CreateTableEditions() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		//String drop = "DROP TABLE EditionsTable;";
		String sql = "CREATE TABLE IF NOT EXISTS EditionsTable (\n"
                + "    title text NOT NULL,\n"
                + "    date integer,\n"
                + "    editionId integer PRIMARY KEY,\n"
                + "    volume VARCHAR(5),\n"
                + "    FOREIGN KEY (volume) REFERENCES VolumesTable(volume)\n"
                + ");";
		//stmt.execute(drop);
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("table was not created");
	    }
	}

	
	public static void addData(String title, int date, int edationId, String volX) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String test1 = "INSERT INTO EditionsTable(title, date, editionId, volume)\r\n" + 
				"VALUES ('" + title + "','" + date + "','" +  edationId + "', '" + volX + "');";
				//"VALUES ('A.L.I.C.E','213', '100');";
		stmt.execute(test1);
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
	
	public static void getData() {
		String extract = "SELECT * FROM EditionsTable";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet result = stmt.executeQuery(extract);
    		
    		while (result.next()) {
             	String title = result.getString("title");    // access by col name   String name = res.getString(name); int office = res.getInt(office);
             	int date = result.getInt("date");
             	int EId = result.getInt("editionId");
             	String volume = result.getString("volume");
				//with those we create a object of class; but now we are just print
             	System.out.println(title + " " + date + " " + EId + " " + volume);
             }
    	}
	     catch (SQLException e) {
	        System.out.println("table display had an error");
	    }
	}
}
