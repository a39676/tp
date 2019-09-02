package utils.sql_utils;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ioHandle.FileUtilCustom;
import tool_package.io_tools.MysqlIOtools01;

/**
 * mysql工具类
 * 内含各个零件方法
 *
 */
public class MySqlTools {

	public static MysqlIOtools01 tool01;
	
	/**
	 * 从一份excl中 读取多份表设计 输出对应创建语句
	 * @param xlsxWorkbook
	 * @param targetSheet
	 */
	public void createTableFromXLSX(Workbook xlsxWorkbook, int targetSheet) {

		Sheet sheet = xlsxWorkbook.getSheetAt(targetSheet);

		int endRowNum = sheet.getLastRowNum();
		for (int rowCounter = 0; rowCounter < endRowNum;) {

			Row targetRow = sheet.getRow(rowCounter);

			int endCellNum;
			while (targetRow == null) {
				rowCounter += 1;
				targetRow = sheet.getRow(rowCounter);
			}
			endCellNum = targetRow.getLastCellNum();

			Cell targetCell;
			String cellInfo;

			if (endCellNum != 0) {
				targetCell = targetRow.getCell(0);
				cellInfo = targetCell == null ? "" : targetCell.toString().trim();
			} else {
				cellInfo = "";
			}

			if (cellInfo != "") {
				MySqlComponents.isMateData(cellInfo);

				if (MySqlComponents.metaFlag && (MySqlComponents.allList.get(0).size() == 0)) {
					MySqlComponents.mateInfoHandle(targetRow);
					rowCounter += 1;
				} else if (!MySqlComponents.metaFlag && !MySqlComponents.indexRecord.isEmpty()) {
					MySqlComponents.tableInfoHandle(targetRow);
					rowCounter += 1;
				} else if (MySqlComponents.metaFlag && !(MySqlComponents.allList.get(0).size() == 0)
						&& !MySqlComponents.indexRecord.isEmpty()) {
					// 输出语句 清空 容器
					creatTableStatement(MySqlComponents.allList, "D:/auxiliary/tmp/");

					MySqlComponents.cleanupInfoContainer(MySqlComponents.allList, MySqlComponents.indexRecord);

					MySqlComponents.mateInfoHandle(targetRow);
					rowCounter += 1;
				} 
			} else {
				rowCounter += 1;
			}
			
			if (rowCounter == endRowNum) {
				creatTableStatement(MySqlComponents.allList, "D:/auxiliary/tmp/");

				MySqlComponents.cleanupInfoContainer(MySqlComponents.allList, MySqlComponents.indexRecord);
			}

		}
	}

	/**
	 * 提取列表信息(列名, 数据类型, 备注)到json
	 * @param conn
	 * @param tableNames
	 */
	@SuppressWarnings("unchecked")
	public void getTableInfo(Connection conn, List<String> tableNames) {

		Statement getTableInfo = null;
		// 输出的文件路径
		String tmpPath = (File.separator + "auxiliary" + File.separator + "tmp" + File.separator);
		// 获取列表的信息 的sql语句
		String getTableInfoSqlStatement = "show full columns from ";

		try {
			getTableInfo = conn.prepareStatement(getTableInfoSqlStatement);
			for (String tableName : tableNames) {
				ResultSet rs = getTableInfo.executeQuery(getTableInfoSqlStatement + tableName);

				JSONObject json = new JSONObject();
				JSONArray colNameList = new JSONArray();
				JSONArray colTypeList = new JSONArray();
				JSONArray colCommentList = new JSONArray();

				json.put("tableName", tableName);

				try {
					while (rs.next()) {
						// System.out.println(rs.getString("field"));
						colNameList.add(rs.getString("field"));
						colTypeList.add(rs.getString("Type"));
						colCommentList.add(rs.getString("Comment"));

						json.put("colNameList", colNameList);
						json.put("colTypeList", colTypeList);
						json.put("colCommentList", colCommentList);
					}

					FileOutputStream fos2 = new FileOutputStream(tmpPath + tableName + "Info.json", true);
					fos2.write(json.toJSONString().getBytes());
					fos2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提取全部列表信息到json
	 * @param conn
	 * @param tableNames
	 * @param file
	 */
	@SuppressWarnings("unchecked")
	public void getTableAllInfoToJson(Connection conn, List<String> tableNames, String file) {

		Statement getTableInfo = null;

		// 输出 的文件路径
		String outputPath = (file);

		String getTableInfoSqlStatement = "show full columns from ";

		try {
			getTableInfo = conn.prepareStatement(getTableInfoSqlStatement);
			for (String tableName : tableNames) {
				ResultSet rs = getTableInfo.executeQuery(getTableInfoSqlStatement + tableName);
				// ResultSetMetaData rsmd = rs.getMetaData();

				JSONObject json = new JSONObject();
				json.put("tableName", tableName);

				JSONArray COLUMN_NAME = new JSONArray();
				JSONArray COLUMN_TYPE = new JSONArray();
				JSONArray COLLATION_NAME = new JSONArray();
				JSONArray IS_NULLABLE = new JSONArray();
				JSONArray COLUMN_KEY = new JSONArray();
				JSONArray COLUMN_DEFAULT = new JSONArray();
				JSONArray EXTRA = new JSONArray();
				JSONArray PRIVILEGES = new JSONArray();
				JSONArray COLUMN_COMMENT = new JSONArray();

				try {
					while (rs.next()) {
						// System.out.println(rs.getString("field"));
						COLUMN_NAME.add(rs.getString(1));
						COLUMN_TYPE.add(rs.getString(2));
						COLLATION_NAME.add(rs.getString(3));
						IS_NULLABLE.add(rs.getString(4));
						COLUMN_KEY.add(rs.getString(5));
						COLUMN_DEFAULT.add(rs.getString(6));
						EXTRA.add(rs.getString(7));
						PRIVILEGES.add(rs.getString(8));
						COLUMN_COMMENT.add(rs.getString(9));

						json.put("COLUMN_NAME", COLUMN_NAME);
						json.put("COLUMN_TYPE", COLUMN_TYPE);
						json.put("COLLATION_NAME", COLLATION_NAME);
						json.put("IS_NULLABLE", IS_NULLABLE);
						json.put("COLUMN_KEY", COLUMN_KEY);
						json.put("COLUMN_DEFAULT", COLUMN_DEFAULT);
						json.put("EXTRA", EXTRA);
						json.put("PRIVILEGES", PRIVILEGES);
						json.put("COLUMN_COMMENT", COLUMN_COMMENT);

					}

					FileOutputStream fos2 = new FileOutputStream(outputPath + tableName + "Info.json", true);
					fos2.write(json.toJSONString().getBytes());
					fos2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提取全部信息到对应的txt(一张表分拆字段名, 数据类型, 备注等信息到各自的txt)
	 * @param conn
	 * @param tableNames
	 * @param outputPath
	 */
	public void getTableAllInfoToTxt(Connection conn, List<String> tableNames, String outputPath) {

		Statement getTableInfo = null;

		String getTableInfoSqlStatement = "show full columns from ";

		try {
			getTableInfo = conn.prepareStatement(getTableInfoSqlStatement);
			for (String singleTableName : tableNames) {
				ResultSet rs = getTableInfo.executeQuery(getTableInfoSqlStatement + singleTableName);
				// ResultSetMetaData rsmd = rs.getMetaData();

				// JSONObject json = new JSONObject();
				// json.put("tableName", tableName);
				List<List<String>> allList = new ArrayList<List<String>>();

				List<String> COLUMN_NAME = new ArrayList<String>();
				List<String> COLUMN_TYPE = new ArrayList<String>();
				List<String> COLLATION_NAME = new ArrayList<String>();
				List<String> IS_NULLABLE = new ArrayList<String>();
				List<String> COLUMN_KEY = new ArrayList<String>();
				List<String> COLUMN_DEFAULT = new ArrayList<String>();
				List<String> EXTRA = new ArrayList<String>();
				List<String> PRIVILEGES = new ArrayList<String>();
				List<String> COLUMN_COMMENT = new ArrayList<String>();

				List<String> infosList = new ArrayList<String>();
				infosList.add("COLUMN_NAME");
				infosList.add("COLUMN_TYPE");
				infosList.add("COLLATION_NAME");
				infosList.add("IS_NULLABLE");
				infosList.add("COLUMN_KEY");
				infosList.add("COLUMN_DEFAULT");
				infosList.add("EXTRA");
				infosList.add("PRIVILEGES");
				infosList.add("COLUMN_COMMENT");

				try {
					while (rs.next()) {
						COLUMN_NAME.add(rs.getString(1));
						COLUMN_TYPE.add(rs.getString(2));
						COLLATION_NAME.add(rs.getString(3));
						IS_NULLABLE.add(rs.getString(4));
						COLUMN_KEY.add(rs.getString(5));
						COLUMN_DEFAULT.add(rs.getString(6));
						EXTRA.add(rs.getString(7));
						PRIVILEGES.add(rs.getString(8));
						COLUMN_COMMENT.add(rs.getString(9));
					}

					allList.add(COLUMN_NAME);
					allList.add(COLUMN_TYPE);
					allList.add(COLLATION_NAME);
					allList.add(IS_NULLABLE);
					allList.add(COLUMN_KEY);
					allList.add(COLUMN_DEFAULT);
					allList.add(EXTRA);
					allList.add(PRIVILEGES);
					allList.add(COLUMN_COMMENT);

					for (int i = 0; i < allList.size(); i++) {
						String filePath = outputPath + singleTableName + infosList.get(i) + ".txt";
						File tmpFile = new File(filePath);
						tmpFile.createNewFile();
						FileOutputStream fos = new FileOutputStream(tmpFile, false);
						// System.out.println(outputPath + singleTableName +
						// infosList.get(i) +".txt");
						StringBuilder builder = new StringBuilder();
						for (String ele : allList.get(i)) {
							builder.append(ele + "\n");
						}
						fos.write(builder.toString().getBytes());
						fos.close();
						builder = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 提取全部信息到对应的txt(一张表一份txt)
	 * @param conn
	 * @param tableNames
	 * @param outputPath
	 */
	public void getTableAllInfoToTxtInOne(Connection conn, List<String> tableNames, String outputPath) {

		Statement getTableInfo = null;

		String getTableInfoSqlStatement = "show full columns from ";
		StringBuffer sb = null;
		
		try {
			getTableInfo = conn.prepareStatement(getTableInfoSqlStatement);
			for (String singleTableName : tableNames) {
				ResultSet rs = getTableInfo.executeQuery(getTableInfoSqlStatement + singleTableName);

				sb = new StringBuffer();

				try {
					while (rs.next()) {
						sb.append(" ");
						sb.append(rs.getString(1));
						sb.append(" ");
						sb.append(rs.getString(2));
						sb.append(" ");
						sb.append(rs.getString(3));
						sb.append(" ");
						sb.append(rs.getString(4));
						sb.append(" ");
						sb.append(rs.getString(5));
						sb.append(" ");
						sb.append(rs.getString(6));
						sb.append(" ");
						sb.append(rs.getString(7));
						sb.append(" ");
						sb.append(rs.getString(8));
						sb.append(" ");
						sb.append(rs.getString(9));
						sb.append("\n");
					}


					String filePath = outputPath + singleTableName + ".txt";
					File tmpFile = new File(filePath);
					tmpFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(tmpFile, false);
					fos.write(sb.toString().getBytes());
					fos.close();
					sb = null;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	/**
	 * 用基本模糊搜索, 搜出关键字所在的行
	 * @param conn
	 * @param tableNames
	 * @param outputPath
	 * @param aim
	 * @return
	 */
	public List<String> getTableAllInfoWithAim(Connection conn, List<String> tableNames,
			List<String> aim) {

		Statement getTableInfo = null;

		String getTableInfoSqlStatement = "show full columns from ";

		List<String> allList = new ArrayList<String>();
		StringBuffer sb = null;
		
		int matchCount = 0;
		
		try {
			getTableInfo = conn.prepareStatement(getTableInfoSqlStatement);

			for (String singleTableName : tableNames) {
				ResultSet rs = getTableInfo.executeQuery(getTableInfoSqlStatement + singleTableName);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = 0;
				try {
					while (rs.next()) {
						columnCount = 0;
						for (int i = 0; i < aim.size(); i++) {
							
							sb = new StringBuffer();
							columnCount = rsmd.getColumnCount();
							
							for(; columnCount > 0; columnCount--) {
								sb.append(rs.getString(columnCount));
							}
							
							if (sb.toString().contains(aim.get(i))) {
								sb.setLength(0);
								sb.append(singleTableName + " " +
										// colName
										rs.getString(1) + "  " +
										// colType
										rs.getString(2) + "  " +
										// colDefaultVal
										" default " + rs.getString(6) + "  " +
										// colComment
										" comment '" + rs.getString(9) + "'  ");
								System.out.println(sb);
								matchCount ++;
								allList.add(sb.toString());
							}
						}
					}
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("match " + matchCount + " place");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allList;
	}

	/**
	 * select 1
	 * @param conn
	 */
	public void selectOne(Connection conn) {
		String sql = "select 1";
		Statement statement = null;
		try{
			statement = conn.createStatement();
			statement.executeQuery(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行语句,将结果处理成String
	 * (仅用于无参数型sql)
	 * @param conn
	 * @param sql
	 * @return
	 */
	public String executeSql(Connection conn, String sql) {
		
		Statement statement = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			
			int columnCount = rsmd.getColumnCount();
			
			for(int i = columnCount; i > 0; i--){
				sb.insert(0, rsmd.getColumnName(i) + "\t");
			}
			
			sb.append("\n");
			
			// 每行数据处理
			while(resultSet.next()) {
				// 每列处理
				for(int i = 1; i < columnCount +1; i++) {
					sb.append(resultSet.getString(i));
					sb.append("\t");
				}
				sb.append("\n");
			}
			
			return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * mysql 数据库中找到所有的列表, 依照其中的数据类型随机填充数据. (null able 不一定填充, 11位填充0~11)
	 * 编写中
	 * @param conn
	 */
	public void dateCreater(Connection conn) {
		
		List<String> listTableNames = new ArrayList<String>();
		Statement stmtSingle = null;
		
		try {
			stmtSingle = conn.createStatement();
			
			listTableNames = getTableNameListByShowTables(conn);

			System.out.println("get tables list: \n");
			System.out.println(listTableNames.toString());
			
			if(listTableNames.isEmpty()){
				return;
			}

			String getTableInfo = "show full columns from ";
			List<String> colName = new ArrayList<String>();
			List<String> colType = new ArrayList<String>();
//			List<String> colNullFlag = new ArrayList<String>();
//			List<String> colKey = new ArrayList<String>();
//			List<String> colDeafultValue = new ArrayList<String>();

			ResultSet rs01 = stmtSingle.executeQuery(getTableInfo + listTableNames.get(0));
			ResultSetMetaData rsmd01 = rs01.getMetaData();

			int colCount = rsmd01.getColumnCount();
			for (; colCount > 0; colCount--) {
				System.out.println(rsmd01.getColumnTypeName(colCount)); // 想输出Field
																		// type
																		// 之列??
			}
			// System.out.println(rsmd01.getColumnCount());
			while (rs01.next()) {
				colName.add(rs01.getString(1));
				colType.add(rs01.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 已经被提取 但做到一半的功能
	 * @param allList
	 * @param outputPath
	 */
	public void creatTableStatement(List<List<String>> allList, String outputPath) {

		outputPath = (tool01.separatorFilter(outputPath));

		List<String> sqlStatement = new ArrayList<String>();

		List<String> columnNameList = allList.get(0);
		List<String> columnTypeList = tool01.modifyListToMySqlDataType(allList.get(1));
//		List<String> collationNameList = allList.get(2);
//		List<String> isNullableList = allList.get(3);
//		List<String> columnKeyList = allList.get(4);
		List<String> columnDefaultList = allList.get(5);
//		List<String> extraList = allList.get(6);
//		List<String> privilegesList = allList.get(7);
		List<String> columnCommentList = allList.get(8);
		String tableName = allList.get(10).get(0);
		String tableComment = allList.get(9).get(0);

		sqlStatement.add("\n" + "create table " + tableName + "( " + "\n");

		// 默认第一个字段为主键, 并且自增
		sqlStatement.add(columnNameList.get(0) + " " + columnTypeList.get(0) + " not null auto_increment comment \'"
				+ columnCommentList.get(0) + "\' , " + "\n");

		// 遍历各个txt文件, 按文件生成数据库字段
		for (int i = 1; i < (columnNameList.size() - 1); i++) {
			sqlStatement.add(columnNameList.get(i) + " ");
			sqlStatement.add(columnTypeList.get(i));

			// 判断是否需要添加备注
			if (columnCommentList.size() != 0) {
				// if (!columnCommentList.get(i).equals("")) {
				// sqlStatement.add(" comment \'" + columnCommentList.get(i) +
				// "\' ");
				// }
				sqlStatement.add(optionWord(1, 8, columnCommentList.get(i)));
			}

			// 判断是否有默认值
			if (columnDefaultList.size() != 0) {
				if (!columnDefaultList.get(i).equals("")) {
					boolean flag = false;

					// 依据对应数据类型判断是否需要给默认值加引号
					for (String ele : tool01.mysqlStringDataType) {
						if (columnTypeList.get(i).toLowerCase().contains(ele)) {
							flag = true;
							break;
						}
					}
					if (flag) {
						sqlStatement.add(" default '" + columnDefaultList.get(i) + "'");
					} else {
						sqlStatement.add(" default " + columnDefaultList.get(i));
					}
				}
			}
			sqlStatement.add(", \n");
		}

		sqlStatement.add("primary key (" + columnNameList.get(0) + ") \n");
		sqlStatement.add(")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT=\'" + tableComment + "\';\n");

		// 真正转换成String语句
		StringBuilder sb = new StringBuilder();
		for (String s : sqlStatement) {
			sb.append(s);
		}

		System.out.println(sb);

		// 将生成的sql语句保存至对应位置
		try {
			FileOutputStream fos = new FileOutputStream(outputPath + "create_" + tableName + ".txt", true);
			byte[] sql;
			// sql = String.valueOf(sb).getBytes();
			sql = sb.toString().getBytes();
			fos.write(sql);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据语句类型,输入的列表容器是否为空,返回对应类型.
	 * @param sqlTypeCode
	 * @param listCode
	 * @param inputListElement
	 * @return
	 */
	public String optionWord(Integer sqlTypeCode, Integer listCode, String inputListElement) {
		String outputStr = null;
		if (!inputListElement.equals("")) {
			outputStr = (" comment \'" + inputListElement + "\' ");
		}
		return outputStr;
	}


	/**
	 * 用show tables指令获取所有表名
	 * @param conn
	 * @return
	 */
	public List<String> getTableNameListByShowTables(Connection conn) {
		
		List<String> tableNameList = new ArrayList<String>();
		
		try {
			Statement stmtSingle = conn.createStatement();

			ResultSet resultSetTableNames = stmtSingle.executeQuery("show tables");

			while (resultSetTableNames.next()) {
				tableNameList.add(resultSetTableNames.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return tableNameList;
		}
		
		return tableNameList;
	}
	
	
	/**
	 * 用 DatabaseMetaData 获取整个数据库所有表名
	 * @param conn
	 * @return
	 */
	public List<String> getTableNameListOfWholeDataBaseByMetaData(Connection conn) {
		
		List<String> tableNameList = new ArrayList<String>();
		
		try {
			DatabaseMetaData md = conn.getMetaData();

			ResultSet resultSetTableNames = md.getTables(null, null, "%", null);

			while (resultSetTableNames.next()) {
				tableNameList.add(resultSetTableNames.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return tableNameList;
		}
		
		return tableNameList;
	}
	
	/**
	 * get table name list from information_schema
	 * @param conn
	 * @param databaseName
	 * @return
	 */
	public List<String> getTableNameListFromInformationSchema(Connection conn, String databaseName) {
		List<String> tableNameList = new ArrayList<String>();
		
		String query = null;
		String queryWithDatabaseName = "SELECT table_name FROM information_schema.tables where table_schema = ? ";
		String queryWithoutDatabaseName = "SELECT table_name FROM information_schema.tables";
		
		PreparedStatement stmtSingle = null;
		try {

			if(StringUtils.isEmpty(databaseName)) {
				query = queryWithoutDatabaseName;
				stmtSingle = conn.prepareStatement(query);
			} else {
				query = queryWithDatabaseName;
				stmtSingle = conn.prepareStatement(query);
				stmtSingle.setString(1, databaseName);
			}
			
			ResultSet resultSetTableNames = stmtSingle.executeQuery();

			while (resultSetTableNames.next()) {
				tableNameList.add(resultSetTableNames.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return tableNameList;
		}
		
		return tableNameList;
	}
	
	/**
	 * get table name list from information_schema
	 * @param conn
	 * @param databaseName
	 * @return
	 */
	public List<String> getTableNameListFromInformationSchema(Connection conn) {
		return getTableNameListFromInformationSchema(conn, null);
	}
	
	public String resultToForm(ResultSet rs) {
		return resultToForm(rs, true);
	}
	
	public String resultToForm(ResultSet rs, boolean withRowNumAndColumnName) {
		StringBuffer sb = new StringBuffer();
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			if(withRowNumAndColumnName) {
				sb.append("row\t");
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					sb.append(rsmd.getColumnName(i) + "\t");
				}
				sb.append("\n");
			}
			
			while (rs.next()) {
				if(withRowNumAndColumnName) {
					sb.append(rs.getRow() + "\t");
				}
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					sb.append(rs.getString(i) + "\t");
				}
				sb.append("\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public void getResultToTxt(Connection conn, String sql, String resultName, Boolean appenFlag) {
		getResultToTxt(conn, sql, resultName, appenFlag, true);
	}
	
	public void getResultToTxt(Connection conn, String sql, String resultName, Boolean appenFlag, Boolean withRowNumAndColumnName) {

		Statement statement = null;
		// 输出的文件路径
		String tmpPath = (File.separator + "auxiliary" + File.separator + "tmp" + File.separator);
		// 获取列表的信息 的sql语句

		try {
			statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery(sql);
			String result = resultToForm(rs, withRowNumAndColumnName);
			
			FileOutputStream fos2 = new FileOutputStream(tmpPath + resultName + ".txt", appenFlag);
			fos2.write(result.toString().getBytes());
			fos2.close();
			
			System.out.println("get:\n" + result.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getResultToCsv(Connection conn, String sql, String resultName, Boolean appenFlag, String codeType) {

		Statement statement = null;
		// 输出的文件路径
		String tmpPath = (File.separator + "auxiliary" + File.separator + "tmp" + File.separator);
		// 获取列表的信息 的sql语句

		try {
			statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			StringBuffer sb = new StringBuffer();
			
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				sb.append(rsmd.getColumnName(i) + ",");
			}
			sb.append("\n");
			
//			marrk
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			while (rs.next()) {
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					if(String.valueOf(rs.getString(i)).matches("\\d{13}")) {
						sb.append(sdf.format(new Date(Long.parseLong(rs.getString(i)))) + ",");
					} else {
						sb.append(rs.getString(i) + ",");
					}
				}
				sb.append("\n");
			}
			
			FileOutputStream fos2 = new FileOutputStream(tmpPath + resultName + ".csv", appenFlag);
			fos2.write(sb.toString().getBytes(codeType));
			fos2.close();
			
//			System.out.println("get:\n" + sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void backupInfoToExcl(Connection conn, String outputPath) {
		List<String> tables = getTableNameListByShowTables(conn);
		String selectAllSql = "select * from ";
		Statement selectAllStatement = null;
		
		try {
			selectAllStatement = conn.prepareStatement(selectAllSql);

			for (String singleTableName : tables) {
				ResultSet rs = selectAllStatement.executeQuery(selectAllSql + singleTableName);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				int rowCount = 0;
				
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet();  
				HSSFRow row = null;
				while (rs.next()) {
					row = sheet.createRow(rowCount++);
					for(int i = 1; i <= columnCount; i++) {
						row.createCell(i-1).setCellValue(rs.getString(i));
					}
				}
				FileOutputStream fileOut = new FileOutputStream(outputPath + singleTableName + ".xls");
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
				System.out.println(singleTableName + " excel file has been generated!");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void backupInfoToTxt(Connection conn, String outputPath) {
		List<String> tables = getTableNameListByShowTables(conn);
		try {
			for (String singleTableName : tables) {
				backupInfoToTxt(conn, outputPath, singleTableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void backupInfoToTxt(Connection conn, String outputPath, String tableName) {
		String selectAllSql = "select * from ";
		Statement selectAllStatement = null;
		
		StringBuffer sb = new StringBuffer();
		FileUtilCustom ioTool = new FileUtilCustom();
		
		try {
			selectAllStatement = conn.prepareStatement(selectAllSql);

			sb.setLength(0);
			ResultSet rs = selectAllStatement.executeQuery(selectAllSql + tableName);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
//				增加列名
//				for(int i = 1; i <= columnCount; i++) {
//					sb.append(rsmd.getColumnName(i));
//					if(i < columnCount) {
//						sb.append("\t");
//					}
//				}
//				sb.append("\n");
			
			while (rs.next()) {
				for(int i = 1; i <= columnCount; i++) {
					sb.append(String.valueOf(rs.getString(i)));
					if(i < columnCount) {
						sb.append("\t");
					}
				}
				sb.append("\n");
			}
			ioTool.byteToFile(sb.toString().getBytes("utf8"), outputPath + tableName + ".txt");
			System.out.println(tableName + ".txt created");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected Long rowCount(Connection conn, String outputPath, String tableName) {
		String rowCountSql = "select count(*) from ";
		Statement rowCountStatement = null;
		
		Long rowCount = 0L;
		try {
			rowCountStatement = conn.prepareStatement(rowCountSql);

			ResultSet rs = rowCountStatement.executeQuery(rowCountSql + tableName);
			
			while (rs.next()) {
				rowCount = rs.getLong(0);
			}
			System.out.println(tableName + "row count: " + rowCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}
}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		MySqlTools tool = new MySqlTools();
//		List<String> tableNameList = tool.getTableNameList(MySQLDBUtility.getConnection(LocalEnvironmentConstant.propertiesFile), "bank_api");
//		
//		for(String ele : tableNameList) {
//			System.out.print(ele + ", ");
//		}
//		System.out.println();
//	}


// 提取 各个列表 表名 备注
// SELECT table_comment, table_name
// FROM INFORMATION_SCHEMA.TABLES
// WHERE table_schema='qy_lfs_dev' ;
