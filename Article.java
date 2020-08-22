import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Article extends ArticlesTable{
    private String title;
    private String abstracts;
    private String link;
    private int id;
    
    public Article (String articleTitle,String articleAbstract,String PDFlink,int articlesID) {
    	this.title = articleTitle;
    	this.abstracts= articleAbstract;
    	this.link = PDFlink;
    	this.id= articlesID;
    }

    public void getProperties() {
	  System.out.println(title + " " + abstracts + " " + link + " " + id);
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
}