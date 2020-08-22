package HashPasswordT2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.*;

public class HashClass extends HashTable{
	private String username;
	private String password;
	private int teamId;
	public HashClass(String userN, String pass, int id) {
    	username = userN;
    	password = pass;
    	teamId = id;
    }
	
	public String getUser() {
		return username;
	}
	public String getPass() {
		return password;
	}
	
	public int getTeamId() {
		return teamId;
	}
	
	public void getProperties() {
		   System.out.println(username + " "+ password + " " + teamId);
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
    	UPtableCreator();
    	String user = "valentin";
    	String pass = "1val";
    	/*addData("valentin","1val",1);
        addData("Ana","ani",1);
    	addData("Ali","Ali",1);
    	addData("user1","password",2);
    	addData("Secret", "Agent",2);
    	addData("a","abc",2);*/
    	
    	//addData("fakeUser1","123",1); 
    	//addData("fakeUser2","456",1);
    	//addData("fakeUser3","678",1);
    	 
    	
    	List<HashClass> persInfo = new ArrayList<HashClass>(); 
    	persInfo = getData();
    	pass = encryptThisString(pass);
    	for(HashClass i : persInfo) {
    		i.getProperties();
    		if(user.equals(i.getUser()) && pass.equals(i.getPass())) {
    			
    			System.out.println("Access Granted");
    		}else {
    			System.out.println("Access Denied");
    		}
    
    	}

    }
}
