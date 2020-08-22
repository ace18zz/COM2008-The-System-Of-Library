import java.sql.*;
import java.util.*;

public class Reviewer extends Stakeholder {
	
	//instance fields
	public int reviewerID;
	private String title;
	private String forename;
	private String surname;
	private String affiliation;
	private String email;
	private String password;
	private int teamID;
	
	public Reviewer(int reviewerId, String title, String forename, String surname, String affiliation, 
			String email, String password) {
    	super(title, forename, surname, affiliation, email, password);
    	this.reviewerID = reviewerId; 
    }
	
	public Reviewer(String title, String forename, String surname, String affiliation,
			String email, String password) {
    	super(title, forename, surname, affiliation, email, password);
    }

	public int getId() {
		return this.reviewerID;
	}
	
	public int getTeamId() {
		return this.teamID;
	}
	
	//get methods
	
	//set methods
	public void setTeamId(int teamId) {
		teamID = teamId;
	}
	
	public static void main(String[] args) {
	}
}
