package io.zhpooer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ConnManager {
	private static String url;
	private static String user;
	private static String password;
	private static ConnManager instance;

	private ConnManager() {
	}
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("jdbccfg");
		try {
	        Class.forName(rb.getString("driverClass"));
        } catch (ClassNotFoundException e) {
        	throw new ExceptionInInitializerError(e);
        }
		url = rb.getString("url");
		user = rb.getString("user");
		password = rb.getString("password");
	}

	public static ConnManager getInstance() {
		if (instance == null) {
			synchronized (ConnManager.class) {
				if (instance == null)
					instance = new ConnManager();
			}
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public void release(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			} finally {
				rs = null;
			}

		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			} finally {
				stmt = null;
			}

		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			} finally {
				conn = null;
			}
	}

}
