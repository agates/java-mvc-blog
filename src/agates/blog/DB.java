package agates.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DB {
	private static Connection conn = null;
	
	public static synchronized Connection getConn() {
		if (!DB.isConnected())
			DB.connect();
		return DB.conn;
	}
	
	public static void connect() {
		Properties connectionProps = new Properties();
		connectionProps.put("user", Constants.DB_USER);
		connectionProps.put("password", Constants.DB_PASS);
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String connStr = String.format("jdbc:sqlserver://%s:%d;databaseName=%s",
					Constants.DB_SERVER, Constants.DB_PORT, Constants.DB_NAME);
			DB.conn = DriverManager.getConnection(connStr, connectionProps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			DB.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Statement getStatement() {
		Statement s = null;
		try {
			s = DB.getConn().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return s;
	}
	
	public static Boolean isConnected() {
		ResultSet rs = null;
		Statement stmt = null;
		
		if (DB.conn != null) {
			try {
				stmt = DB.conn.createStatement();
				rs = stmt.executeQuery("SELECT 1;");
				if (rs.next())
					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// TODO log exception
				return false;
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// TODO log exception
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
