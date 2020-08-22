import java.sql.*;
import java.util.*;

public class VolumesTable {
	
	private static String insert = "INSERT INTO VolumesTable(volume, yearOfPublication, numOfPages, issn)";
	private static String select = "Select * FROM VolumesTable ";
	
	public static void CreateTableVolumes() {
		
		String table = "CREATE TABLE IF NOT EXISTS VolumesTable (\n"
                + "    volume VARCHAR(5) PRIMARY KEY,\n"
                + "    yearOfPublication integer NOT NULL,\n"
                + "    numOfPages real,\n"
                + "    issn integer,\n"
                + "    FOREIGN KEY (issn) REFERENCES journalsTable(issn)\n"
                + ");";
		String drop = "DROP TABLE IF EXISTS VolumesTable;";
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
            Statement stmt = conn.createStatement()){			
			//stmt.execute(drop);
			stmt.execute(table);
			System.out.println("Success!");
			} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.print("Table not created!");
		}
	}
	
	public static void addVolume(String volX, int year, int pages, int issn) {
		
		String data = " VALUES ('" + volX + "', '" + year + "', '" + pages + "', '" + issn + "')"; 
		
		//System.out.print(insert + data);
		String sql = insert + data;
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	            Statement stmt = conn.createStatement()){
			
			stmt.executeUpdate(sql);
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());			
		}
		
	}
	
	public static Volume selectVolume(String volX) {
		
		String data = " WHERE volume = '" + volX + "'"; 
		
		//System.out.print(insert + data);
		String sql = select + data;
		String vol = "";
		int year = 0, pages = 0;
		Volume volume = null;
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
	            Statement stmt = conn.createStatement()){
			
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				vol = result.getString("volume");
				year = result.getInt("yearOfPublication");
				pages = result.getInt("numOfPages");
				issn
			}
			volume = new Volume(vol,year,pages);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());			
		}
		
		return volume;
		
	}
	public static void main(String[] args) {
		
		CreateTableVolumes();
		addVolume("vol.3", 24, 130, 13);
		
		System.out.print(selectVolume("vol.3"));
		
	
	}
}
