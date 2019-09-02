package utils.sql_utils;

import ioHandle.FileUtilCustom;
import utils.constant.LocalEnvironmentConstant;

public class CommonSqlTools {
	
	/**
	 * 从文件中获取sql
	 * @param path
	 * @return
	 */
	public String getSqlFromFile(String path) {
		FileUtilCustom iotool = new FileUtilCustom(); 
		String sql = null;
		byte[] data = null;
		try {
			data = iotool.getByteFromFile(path);
			sql = new String(data, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}
	
	public static void main(String[] args) {
		CommonSqlTools tool = new CommonSqlTools();
		System.out.println(tool.getSqlFromFile(LocalEnvironmentConstant.inputPath));
	}

}
