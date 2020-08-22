import java.sql.*;
import java.util.*;

public class ReviewSystem {
	
	
	public static void CreateReviewTables() {
		
		String reviewTable = "CREATE TABLE IF NOT EXISTS ReviewTable (\n"
				+ "    reviewerId VARCHAR PRIMARY KEY,\n"
                + "    submissionId VARCHAR(5),\n"
                + "    reviewerX integer NOT NULL,\n"
                //+ "    FOREIGN KEY (submissionId) REFERENCES SubmissionTable(submissionId)\n"
                + ");";
		
		String responseTable = "CREATE TABLE IF NOT EXISTS ResponseTable (\n"
                + "    submissionId VARCHAR(5) PRIMARY KEY,\n"
                + "    reviewerX integer NOT NULL,\n"
                + "    responses text,\n"
                //+ "    FOREIGN KEY (issn) REFERENCES journalsTable(issn)\n"
                + ");";
		
		String reviewerTable ="CREATE TABLE IF NOT EXISTS ReviewerTable (\n"
                + "    reviewerId VARCHAR NOT NULL PRIMARY KEY,\n"
                + "    authorId VARCHAR,\n"
                //+ "    FOREIGN KEY (authorId) REFERENCES AuthorsTable(authorId)\n"
                + ");";
		
		String reviewFormTable = "CREATE TABLE IF NOT EXISTS ReviewFormTable (\n"
                + "    submissionId VARCHAR(5) PRIMARY KEY,\n"
                + "    reviewerX integer NOT NULL,\n"
                + "    summary text,\n"
                + "    typos text,\n"
                + "    criticismId text,\n"
                + "    FOREIGN KEY (criticismId) REFERENCES CriticismTable(criticismId)\n"
                + ");";
		
		String criticismTable = "CREATE TABLE IF NOT EXISTS CriticismTable (\n"
                + "    criticismId VARCHAR PRIMARY KEY,\n"
                + "    questionNum integer NOT NULL,\n"
                + "    question text NOT NULL,\n"
                + "    answer text,\n"
                //+ "    FOREIGN KEY () REFERENCES xTable()\n"
                + ");";
				
	}
}
