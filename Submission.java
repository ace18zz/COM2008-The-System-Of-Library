import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Submission extends Database {
	private int subId;
	private String subTitle;
	private String abstractTxt;
	private String issn;
	private File orgPdf;
	private File newPdf;
	private int totalReviews;
	
	public Submission (String title, String abst, String issn, File orgFile, int totRevs) {
	    subTitle = title;
	    abstractTxt = abst;
	    this.issn = issn;
	    orgPdf = orgFile;
	    totalReviews = totRevs;
	    }
	
	public Submission (int submissionID, String title, String abst, String issn, int totRevs) {
		subId = submissionID;
		subTitle = title;
	    abstractTxt = abst;
	    this.issn = issn;
	    totalReviews = totRevs;
	    }
	
	public Submission (int submissionID, String title, String abst, String issn, File orgFile, int totRevs) {
		subId = submissionID;
	    subTitle = title;
	    abstractTxt = abst;
	    this.issn = issn;
	    orgPdf = orgFile;
	    totalReviews = totRevs;
	    }

	public String toString() {
		return (this.subId +"    "+subTitle+"    "+abstractTxt+"    "+issn+"    "+totalReviews);
	}
	
	public int getId() {
		return subId;
	}
	
	public String getTitle() {
		return subTitle;
	}
	
	public String getAbstract() {
		return abstractTxt;
	}
	
	public String getIssn() {
		return issn;
	}
	
	public File getOrgFile() {
		return orgPdf;
	}

	public File getNewFile() {
		return newPdf;
	}
	
	public int getTotalReviews() {
		return totalReviews;
	}
	
	//public String toString() {
		//return (subId+"    "+subTitle+"    "+abstractTxt+"    "+issn+"    "+totalReviews);
//	}

	// check if the connection work
	public static void exceptionCheck() throws SQLException {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
			System.out.println("Got Connection!");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
