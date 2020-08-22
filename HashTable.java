package HashPasswordT2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class HashTable {
	
	public static void UPtableCreator() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String drop = "DROP TABLE userPassTable;";
		String sql = "CREATE TABLE IF NOT EXISTS userPassTable (\n"
                + "    username text NOT NULL,\n"
                + "    password text NOT NULL,\n"
                + "    teamId INTEGER NOT NULL\n"
                + ");";
		//stmt.execute(drop);
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("table was not created");
	    }
	}
	
	public static void addData(String user, String pass, int teamId) throws NoSuchAlgorithmException {
		//System.out.println(salty);
		//System.out.println(hashPass);
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
			String newPass = encryptThisString(pass);
		String test1 = "INSERT INTO userPassTable(username, password, teamId)\r\n" + 
				"VALUES ('" + user + "','"+ newPass + "','"+ teamId +"');";
		stmt.execute(test1);
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
	
	public static List<HashClass> getData() {
		String extract = "SELECT * FROM userPassTable";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet result = stmt.executeQuery(extract);
    		List<HashClass> userPass = new ArrayList<HashClass>(); 
    		while (result.next()) {  
             	String user = result.getString("username");
             	String pass = result.getString("password");
             	int id = result.getInt("teamId");
				//with those we create a object of class
             	userPass.add(new HashClass(user, pass,id));
             }
    		return userPass;
    	}
	     catch (SQLException e) {
	        System.out.println("table display had an error");
	    }
		return null;
	}
	
	//====================hashing
	public static String encryptThisString(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-224 
            MessageDigest md = MessageDigest.getInstance("SHA-224"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
