import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Edition extends EditionsTable{
	private String title; //in the class diagram change from name
    private int date;
    private int editionId;
    private String volume;
    
    public Edition(String Edutionname, int MofPublication, int id, String volX) {
    	this.title = Edutionname;
    	this.date = MofPublication;
    	this.editionId = id;
    	volume = volX;
    }

   
    
    //check if the connection work
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
		CreateTableEditions();
		addData("TitleTest",13,15,"vol.4");
		getData();	
		
	}
}
