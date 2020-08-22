import java.sql.*;
import java.util.*;

public class Journals extends JournalsTable {
	
	private String title; //in the class diagram change from name
    private String issn;
    
    public Journals(String journalTitle, String issn2) {
    	title = journalTitle;
    	this.issn = issn2;
    }

   public void getProperties() {
	   System.out.println(title + " " + issn);
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
    public String getTitle() {
 	   return this.title;
    }

    public String getIssn() {
 	   return this.issn;
    }
    
public static void main(String[] args) throws Exception {
		exceptionCheck();
		JournalsTable2();
		addData("TitleTest",13,12);//if data is already there, then will not be added to the table
		List<Journals> journals= new ArrayList<Journals>(); 
		journals = getData();
		for(Journals i : journals) {
		i.getProperties();	
		}		

	}
}
