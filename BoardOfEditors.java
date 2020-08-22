import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// not needed
public class BoardOfEditors extends BoardOfEditorsTable {
	public int TeamId;
	
	 public BoardOfEditors(int TeamId) {
	    	this.TeamId = TeamId;
	    }
	 public void getProperties() {
		   System.out.println(TeamId);
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
	 
	 public static void main(String[] args) throws Exception {
		 exceptionCheck();
	 }
}

