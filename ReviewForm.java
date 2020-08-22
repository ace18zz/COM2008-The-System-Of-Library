import java.sql.*;
import java.util.*;

public class ReviewForm {
	
	//instance fields
	private int submissionID;
	private int reviewerX;
	private String summary;
	private String typos;
	
	//constructor
	public ReviewForm(int submissionID, int reviewerX, String summary, String typos) {
		this.submissionID = submissionID;
		this.reviewerX = reviewerX;
		this.summary = summary;
		this.typos = typos;		
	}
	
	//get methods
	public int getId() {
		return submissionID;
	}	
	public int getReviewerX() {
		return reviewerX;
	}
	public String getSummary() {
		return summary;
	}
	
	public String getTypos() {
		return typos;
	}
	
	//set methods
	
	public String toString() {
		return "Review Form: "+submissionID+", "+reviewerX+", "+summary.substring(0, 20)+".";
	}
	
}
