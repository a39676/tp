package utils.sql_utils;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDemo {

	public static void main(String[] args) {
		
		Connection connect = null;
		
		String file = "d:" + File.separator + "auxiliary" + File.separator + "Item" + File.separator +
				"MySqlDB01.properties";
		
		try {
			connect = MySQLDBUtility.getConnection(file);
			String testSql = "show tables";
			Statement getTableInfo = connect.prepareStatement(testSql);
			ResultSet rs = getTableInfo.executeQuery(testSql);
			
			while (rs.next()) {
				// System.out.println(rs.getString("field"));
				System.out.println(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
