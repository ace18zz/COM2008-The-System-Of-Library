import java.sql.*;
import java.util.*;

public class Review {

	//instance fields
	private int teamID;
	private int submissionID;
	private int reviewerX;
	private boolean reviewed;

	public Review(int teamID, int submissionID, int reviewerX, boolean status) {
		this.teamID = teamID;
		this.submissionID = submissionID;
		this.reviewerX = reviewerX;
		this.reviewed = status;
		}
	
	//get methods
	public int getTeamID() {
		return this.teamID;
	}
	
	public int getSubID() {
		return this.submissionID;
	}
	
	public int getReviewerX() {
		return this.reviewerX;
	}
	
	public boolean isReviewed() {
		return this.reviewed;
	}
	//set methods
}
