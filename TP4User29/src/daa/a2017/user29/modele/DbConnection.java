package daa.a2017.user29.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to make database connection
 *
 */
public class DbConnection {
	
	public static final String DB_USER = "dbUser";
	public static final String DB_PASSWORD = "dbPassword";
	public static final String DB_URL = "dbUrl";
	
	public static Connection createConection(String connectionUrl, String username, String password){
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = (Connection) DriverManager.getConnection(connectionUrl, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
