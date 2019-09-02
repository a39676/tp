package utils.sql_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MySqlTmpTool {
	
	/**
	 * 临时工具方法
	 * 往指定的表填入批量数据
	 * 编写中
	 * @param conn
	 */
	public void dateCreater(Connection conn) {
		
		try {

			System.out.println("get tables list: \n");
			
			String sql = "insert into tmp_table (colchar, intCol) values (?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			for (int i = 0; i < 10000; i++) {
				ps.setString(1, "something" + i);
				ps.setInt(2, i);
				ps.addBatch();
			}
			
			ps.executeBatch();
			conn.commit();
			
			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
