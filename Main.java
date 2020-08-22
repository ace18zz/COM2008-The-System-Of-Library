import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class Main {
 
    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    capacity real\n"
                + ");";
        
        String test1 = "INSERT INTO warehouses(id, name, capacity)\r\n" + 
        		"VALUES ('3022','Alice', '100');";
        
        String extract = "SELECT * FROM warehouses";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql); //create tables
            //stmt.execute(test1); // add in the tables ( execute just once to add data in the database)
            ResultSet result = stmt.executeQuery(extract); // add in the tables
            while (result.next()) {
            	int id = result.getInt("id");    // access by col name   String name = res.getString(“name”); int office = res.getInt(“office”);
            	String name = result.getString("name");
            	int capacity = result.getInt("capacity");
            	//with those we create a object of class; but now we are just print
            	//stmt.execute(id + " " + name + " " + capacity);
            	System.out.println(id +" "+ name+ " " + capacity);
            }
            
            // do something with teacher  }

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();

    }
}
 

