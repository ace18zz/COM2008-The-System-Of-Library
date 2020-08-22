import java.sql.*;

public class Volume{
	
	
	//instance fields
	private String volX;
    private int yearOfPublication;
    private int numOfPages;
    private String issn;
    
    //constructor 
    public Volume(String volX, int year, int totalPages, String issn) {
    	this.volX = volX;
    	yearOfPublication = year;
    	numOfPages = totalPages;
    	this.issn = issn;
    }
    
    //get Methods
    String getVolX() {
    	return volX;
    }
    
    int getYear() {
    	return yearOfPublication;
    }
    
    int getTotalPages() {
    	return numOfPages;
    }
    
    String getIssn() {
    	return issn;
    }
    
    //set methods
    
    
    public String toString() {
    	return "Volume: "+ volX + ", Year: " + yearOfPublication + ", Total pages:"
    				+ numOfPages + ", ISSN:" + issn + ".";
    }
    
	public static void main(String[] args) throws Exception {
		
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
}
