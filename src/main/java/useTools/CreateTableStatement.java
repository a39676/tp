package useTools;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import tool_package.io_tools.MysqlIOtools01;

/*
 * 依据对应的文件快生成创建数据表的语句
 * 文件输入方法有待改善.
 * (准备一次输入 4 个或以下文件路径, )
 * 2016/11/16
 * 
 * 准备将构成 SQL 语句主题部分的功能抽取出去
 * */
public class CreateTableStatement {
	

    public static void main(String[] args) {
    	
    	MysqlIOtools01 tool01 = new MysqlIOtools01();
    	
    	String tableName = "arp_cash_car";
    	String tableComment = "";

		String path = "d:/auxiliary//////tmp/";
		String inputPath = (tool01.separatorFilter(path));
    	String outputPath = (tool01.separatorFilter(path));
    	String colNameFile = (inputPath  + "colName.txt");
    	String colTypeFile = (inputPath  + "colType.txt");
//    	String colKeyFile = (inputPath + "colKey.txt");
    	String colCommentFile = (inputPath  + "colComment.txt");
    	String colDefaultValFile = (inputPath + "colDefaultVal.txt");
    	
    	List<String> sqlStatement = new ArrayList<String>();
    	
    	List<String> colNameList = tool01.putIntoList(colNameFile);
    	List<String> colTypeList = tool01.modifyListToMySqlDataType(tool01.putIntoList(colTypeFile));
    	List<String> colCommentList;
    	List<String> colDefaultValList;
        
    	int referenceSize = colNameList.size();
    	colCommentList = tool01.putIntoList(colCommentFile, referenceSize);
    	colDefaultValList = tool01.putIntoList(colDefaultValFile, referenceSize);
    	
    	
    	sqlStatement.add("\n"
    			+ "create table " + tableName + "( "
    					+ "\n");
    	
        // 默认第一个字段为主键, 并且自增
    	sqlStatement.add(colNameList.get(0) + " " + colTypeList.get(0) + " not null auto_increment comment \'" 
    	    	        + colCommentList.get(0) + "\' , "
    	    			+ "\n");
    	
    	// 遍历各个txt文件, 按文件生成数据库字段
    	for (int i = 1; i < (colNameList.size() - 1); i++) {
    		sqlStatement.add(colNameList.get(i) + " "); 
    		sqlStatement.add(colTypeList.get(i));
    		
    		// 判断是否需要添加备注
    		if (!colCommentList.get(i).equals("")) {
    			sqlStatement.add(" comment \'" + colCommentList.get(i) + "\' ");
    		}
    		
    		// 判断是否有默认值
    		if (!colDefaultValList.get(i).equals("")){
    			boolean flag = false;
    			
    			// 依据对应数据类型判断是否需要给默认值加引号
    			for (String ele : tool01.mysqlStringDataType) {
    				if (colTypeList.get(i).toLowerCase().contains(ele)) {
    					flag = true;
    					break;
    				}
    			}
    			if (flag) {
    				sqlStatement.add(" default '" + colDefaultValList.get(i) + "'");
    			} else {
    				sqlStatement.add(" default " + colDefaultValList.get(i));
    			}
    		}
    		sqlStatement.add(", \n");
    	}
    	
    	
    	sqlStatement.add("primary key (" + colNameList.get(0) + ") \n");
    	sqlStatement.add(")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT=\'" + tableComment + "\';\n");
    	
    	
    	// 真正转换成String语句
    	StringBuilder sb = new StringBuilder();
    	for (String s : sqlStatement) {
    		sb.append(s);
    	}
    	
    	System.out.println(sb);
    	
    	
    	// 将生成的sql语句保存至对应位置
    	try {
    		FileOutputStream fos = new FileOutputStream(outputPath + "create" + tableName + ".txt", true);
    		byte[] sql ;
         // sql = String.valueOf(sb).getBytes();
    		sql = sb.toString().getBytes();
    		fos.write(sql);
    		fos.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
