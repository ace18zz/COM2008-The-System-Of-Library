

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

public class SubmissionsTable {
	public static void SubmissionsTable () {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
					String sql = "CREATE TABLE IF NOT EXISTS submissions (\n"
	                + "    teamId INTEGER NOT NULL,\n"
	                + "    PDFId INTEGER NOT NULL,\n "
	                + "    submissionId INTEGER PRIMARY KEY,\n"
	                + ");";
			stmt.execute(sql);
			}
			 catch (SQLException e) {
				System.out.println(e.getMessage());
		        System.out.println("table was not created");
		    }
		}	
		public static void addData(int teamid, int pdfid ,int submissionid ) {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
			String test1 = 	"VALUES ('" + teamid + "','" + pdfid + "','" +  submissionid+ "');";
					//"VALUES ('1','2', '3');";
			stmt.execute(test1);
			}
		    catch (SQLException e) {
		       System.out.println(e.getMessage());
		       System.out.println("data was not added");
		   }
		}
		public static List<Submission> getData() {
			String extract = "SELECT * FROM SubmissionsTable";
	    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	                Statement stmt = conn.createStatement()) {
	    		ResultSet result = stmt.executeQuery(extract);
	    		List<Submission> submissions = new ArrayList<Submission>(); 
	    		while (result.next()) {
	    			int tId = result.getInt("teamid");
	    			int pId = result.getInt("pdfid");
	             	int sId = result.getInt("submissionid");
					//with those we create a object of class; but now we are just print
	             	submissions.add(new Submission(tId, pId, sId));
	             }
	    		return submissions;
	    	}
		     catch (SQLException e) {
		        System.out.println("table display had an error");
		    }
			return null;
		}
	
		
}


