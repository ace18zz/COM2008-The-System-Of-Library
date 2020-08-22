import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticlesTable {
	public static void ArticlesTable () {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
				String sql = "CREATE TABLE IF NOT EXISTS articleTable (\n"
                + "    title text NOT NULL,\n"
                + "    abstracts text NOT NULL,\n"
                + "    link text NOT NULL,\n "
                + "    articleId integer PRIMARY KEY,\n"
                + ");";
		stmt.execute(sql);
		}
		 catch (SQLException e) {
			System.out.println(e.getMessage());
	        System.out.println("table was not created");
	    }
	}	
	public static void addData(String title, String abstracts,String link, int articleId ) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
		String test1 = "INSERT INTO EditionsTable(title, abstracts , link,articleId )\r\n" + 
				"VALUES ('" + title + "','" + abstracts + "','" +  link+ "','" + articleId + "');";
				//"VALUES ('A.L.I.C.E','ASDS', 'SAFS',100);";
		stmt.execute(test1);
		}
	    catch (SQLException e) {
	       System.out.println(e.getMessage());
	       System.out.println("data was not added");
	   }
	}
	public static List<Article> getData() {
		String extract = "SELECT * FROM articlesTable";
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team011", "team011", "731d50a5");
                Statement stmt = conn.createStatement()) {
    		ResultSet result = stmt.executeQuery(extract);
    		List<Article> articles = new ArrayList<Article>(); 
    		while (result.next()) {
             	String title = result.getString("title");    // access by col name   String name = res.getString(name); int office = res.getInt(office);
             	String abstracts = result.getString("abstracts");
             	String link = result.getString(" link");
             	int AId = result.getInt("articleId");
				//with those we create a object of class; but now we are just print
				articles.add(new Article(title, abstracts, link, AId));
             }
    		return articles;
    	}
	     catch (SQLException e) {
	        System.out.println("table display had an error");
	    }
		return null;
	}
}
