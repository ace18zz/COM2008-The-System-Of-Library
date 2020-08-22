import java.sql.*;
import java.util.*;

public class Response {
	
	//instance fields
	private int submissionID;
	private String reviewerX;
	private List<String> responses;
	
	public Response(int submissionID, String reviewerX, List<String> responses) {
		this.submissionID = submissionID;
		this.reviewerX = reviewerX;
		this.responses = responses;
	}
	
	//get methods
	
	//set methods
}
