package useTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ioHandle.FileUtilCustom;
import utils.constant.LocalEnvironmentConstant;
import utils.sql_utils.MySQLDBUtility;
import utils.sql_utils.MySqlTmpTool;
import utils.sql_utils.MySqlTools;

public class SqlTool {
	
	private static MySqlTools sqlTools = new MySqlTools();
	private static String propertiesFilePath = null;
	public static String getPropertiesFilePath() {
		return propertiesFilePath;
	}
	public static void setPropertiesFilePath(String propertiesFilePath) {
		SqlTool.propertiesFilePath = propertiesFilePath;
	}

	/**
	 * 根据输入的excl 生成创建数据表的语句
	 * (编写中)
	 */
	public void createTable() {
		Workbook workbook = null;
		
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(LocalEnvironmentConstant.tablesExclPath)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (workbook != null) {
			sqlTools.createTableFromXLSX(workbook, 3);
		}
	}
	
	/**
	 * 根据properties文件 
	 * 获取其中所有表的表结构
	 */
	public void getTableInfo() {
		
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
			List<String> tableNames = sqlTools.getTableNameListByShowTables(connect);
			System.out.println(tableNames.toString());
			sqlTools.getTableAllInfoToTxtInOne(connect, tableNames, LocalEnvironmentConstant.outputPath);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getTableCreatorSql(List<String> tableNames) {
		Connection connect = null;
		
		String sqlTemplate = "show create table ?";
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
			if(tableNames == null || tableNames.size() <= 0) {
				tableNames = sqlTools.getTableNameListByShowTables(connect);
			}
			
			if(tableNames == null || tableNames.size() <= 0) {
				System.out.println("无法获取该数据库表名,或该数据库尚未建表.");
				throw new Exception();
			}
			
			System.out.println(tableNames.toString());
			for(String tableName : tableNames) {
				sqlTools.getResultToTxt(connect, sqlTemplate.replace("?", tableName), "tmpResult", true, false);				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 根据输入的关键字(可多个), 模糊查找全库表结构, 
	 * 返回所在位置
	 * (表名, 字段名, 备注)
	 * @param keywordList
	 */
	public void getTableWithAim(List<String> keywordList) {
		getTableWithAim(keywordList.toArray(new String[keywordList.size()]));
	}
	
	/**
	 * 根据输入的关键字(可多个), 模糊查找全库表结构, 
	 * 返回所在位置
	 * (表名, 字段名, 备注)
	 * @param keywords
	 */
	public void getTableWithAim(String... keywords) {
		
		Connection connect = null;
		
		List<String> aimWordList = new ArrayList<String>();
		for(String keyword : keywords) {
			aimWordList.add(keyword);
		}
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
			List<String> tableNames = sqlTools.getTableNameListByShowTables(connect);
			System.out.println(tableNames.toString());
			sqlTools.getTableAllInfoWithAim(connect, tableNames, aimWordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tmpInsert() {
		MySqlTmpTool tmpTool = new MySqlTmpTool();
		
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tmpTool.dateCreater(connect);
		
	}
	
	public void keepAliveAndExecuteOrder() throws InterruptedException {
//		TODO
		MySqlTools mySqlTool01 = new MySqlTools();
		FileUtilCustom ioTool = new FileUtilCustom();
		
		Connection connect = null;
		String sql = null;
		String result = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(true) {
			mySqlTool01.selectOne(connect);
			sql = ioTool.getStringFromFile(LocalEnvironmentConstant.inputPath + "input.txt");
			result = mySqlTool01.executeSql(connect, sql);
			ioTool.byteToFile(result.getBytes(), LocalEnvironmentConstant.outputPath + "result.txt");
			System.out.println(result);
			TimeUnit.SECONDS.sleep(3);
		}
	}
	
	/**
	 * appendFlag = true ---> append at end
	 * appendFlag = false ---> rewrite
	 * @param sql
	 * @param resultName
	 * @param appendFlag
	 */
	public void getResultToTxt(String sql, String resultName, boolean appendFlag) {
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlTools.getResultToTxt(connect, sql, resultName, appendFlag);
	}
	
	public void getResultToCsv(String sql, String resultName, boolean appendFlag, String codeType) {
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlTools.getResultToCsv(connect, sql, resultName, appendFlag, codeType);
	}
	
	public void backupInfoToExcl(String outputPath) {
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlTools.backupInfoToExcl(connect, outputPath);
	}
	
	public void backupInfoToTxt(String outputPath) {
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlTools.backupInfoToTxt(connect, outputPath);
	}
	
	public void createBackupSqlFromTxt(String sourceFilePath, String outputFilePath) {
		File f = new File(sourceFilePath);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		
		String tableName = f.getName().replaceAll("\\.txt", "");
		System.out.println(tableName);
		FileUtilCustom io = new FileUtilCustom();
		String oldStr = io.getStringFromFile(sourceFilePath);
		
		List<String> lines = Arrays.asList(oldStr.split("\n"));
		
//		List<String> columnNameList = Arrays.asList(lines.get(0).split("\t"));
		String columnNameStr = lines.get(0).replaceAll("\t", ",");
		columnNameStr = columnNameStr.substring(4, columnNameStr.length() - 1);
		System.out.println(columnNameStr);
		
		List<String> values = null;
		StringBuffer sqlBuilder = new StringBuffer();
		for(int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
			sqlBuilder.append("insert into " + tableName + " (" + columnNameStr + " ) values"
					+ "(");
			values = Arrays.asList(lines.get(lineIndex).split("\t"));
			for(int i = 1; i < values.size(); i++) {
				if("null".equals(values.get(i))) {
					sqlBuilder.append("null,");
				} else {
					sqlBuilder.append("'" + values.get(i) + "',");
				}
			}
			sqlBuilder.setLength(sqlBuilder.length() - 1);
			sqlBuilder.append(" ); \n");
			System.out.println(sqlBuilder.toString());
			io.byteToFileAppendAtEnd(sqlBuilder.toString().getBytes(StandardCharsets.UTF_8), outputFilePath);
			sqlBuilder.setLength(0);
		}
		
	}
	
	public static void main(String[] args) throws Exception  {
		SqlTool tool = new SqlTool();
		SqlTool.setPropertiesFilePath(LocalEnvironmentConstant.ws);
//		IOtools iot = new IOtools();
		
//		 搜索数据库
//		tool.getTableWithAim("审核");
		
		// 索取数据
//		String sql = iot.getStringFromFile("d:/auxiliary/tmp/tmpSql.txt");
//		tool.getResultToTxt(sql, "tmpResult", false);
		
		// 获取数据库概况
//		tool.getTableInfo();
		
//		tool.getTableCreatorSql(null);
		
		// 拉取数据
//		String templateSql = "select * from t_member where create_time < \'2018-01-01\' order by mem_id limit ";
//		String tmpSql = null;
//		String resultName = "member3";
//		int step = 1000;
//		for(int i = 4600; i < 60000; i += step) {
//			tmpSql = templateSql + i + ", " + step;
//			System.out.println(tmpSql);
//			tool.getResultToTxt(tmpSql, resultName, true);
//		}
		
		String mainFolderPath = "D:/auxiliary/tmp";
		String tableName = "s_complaint_title_item";
		String backSuffix = "Backup";
		String fileNameSuffix = ".txt";
		String templateSql = "select * from " + tableName;
		tool.getResultToTxt(templateSql, tableName, true);
		
		tool.createBackupSqlFromTxt(mainFolderPath + "/" + tableName + fileNameSuffix
				, mainFolderPath + "/" + tableName + backSuffix + fileNameSuffix);
		
	}


}
