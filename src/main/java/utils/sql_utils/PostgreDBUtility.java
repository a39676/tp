package utils.sql_utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class PostgreDBUtility {

	
	static private BasicDataSource createDataSource(String propertiesFileLocation) {
		BasicDataSource dataSouce01 = null;
		try {
			Properties property = new Properties();
			property.load(new FileInputStream(propertiesFileLocation));

			String driver = property.getProperty("DB_DRIVER_CLASS");
			String url = property.getProperty("DB_URL");
			String username = property.getProperty("DB_USERNAME");
			String password = property.getProperty("DB_PASSWORD");

			System.out.print("connect with: \n" + "driver: " + driver + "\n" + "url: " + url + "\n");

			dataSouce01 = new BasicDataSource();
			dataSouce01.setDriverClassName(driver);
			dataSouce01.setUrl(url);
			dataSouce01.setUsername(username);
			dataSouce01.setPassword(password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSouce01;
	}

	
	public static Connection getConnection(String propertiesFile) throws ClassNotFoundException, SQLException {
		Connection conn = createDataSource(propertiesFile).getConnection();
		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Connection closed");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
		
}
