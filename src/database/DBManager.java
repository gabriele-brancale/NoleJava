package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	private static Connection conn = null;
	
	private DBManager() {}
	
	public static Connection getConnection() throws SQLException {
			
			if(conn == null || conn.isClosed()) {

				try {

					Class.forName("com.mysql.cj.jdbc.Driver");

				} catch (ClassNotFoundException e) {
					
					throw new SQLException();

				}

				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			}
			
			return conn;
		
	}
	
	
	public static void closeConnection() throws SQLException {
		
			if(conn != null) {

				conn.close();
				
			}
	}

}
