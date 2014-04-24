package io.zhpooer.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 通过设施 conf/jdbc.conf, 管理数据库连接, 参数样式如下, debug=true|false,
 * 如果debug为true, 运行h2临时数据库, 临时数据放在/tmp目录下 
 * driverClass=com.mysql.jdbc.Driver
 * url=jdbc:mysql://localhost:3306/test user=root password=root
 * 
 * @author poe
 * 
 */
public class ConnManager {
	private static String url;
	private static String user = "";
	private static String password = "";
	private static ConnManager instance;
	private static String driverClass;

	private ConnManager() {
	}

	static {
		Properties p = new Properties();
		try {
			InputStream conf = ConnManager.class.getClassLoader().getResourceAsStream("jdbc.conf");
			p.load(conf);
			if (p.containsKey("debug")
			        && Boolean.parseBoolean(p.getProperty(("debug")))) {
				url = DbUtil.makeH2TempURL("sample");
				driverClass = "org.h2.Driver";
			} else {
				driverClass = p.getProperty(("driverClass"));
				url = p.getProperty("url");
				user = p.getProperty("user");
				password = p.getProperty("password");
			}
			Class.forName(driverClass);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}

		if (p.containsKey("sqlScript")) {
			String scriptPath = ConnManager.class.getClassLoader()
			        .getResource(p.getProperty("sqlScript")).getPath();
			DbUtil.execSQL(scriptPath, driverClass, url, user, password);
		}
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
