package utils.sql_utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class MySqlComponents {
	
//	private static final List<String> sqlMetaDataTypeList = new ArrayList<String>(
//			Arrays.asList("columnName", "columnType", "collationName", "inNullable", "columnKey", "columnDefault",
//					"extra", "privileges", "columnComment"));
//
//	private static final List<String> mysqlStringDataType = Arrays.asList("char", "varchar", "text", "binary",
//			"varbinary", "enum", "set");
	
	public static boolean metaFlag;
	
	public static Map<Integer, Integer> indexRecord = new HashMap<Integer, Integer>();

	public static final HashMap<Integer, String> mateDateMatchRefence;
	
	static {
		mateDateMatchRefence = new HashMap<Integer, String>();
		mateDateMatchRefence.put(0, "字段名称");
		mateDateMatchRefence.put(1, "数据类型");
		mateDateMatchRefence.put(2, "");
		mateDateMatchRefence.put(3, "");
		mateDateMatchRefence.put(4, "是否主键");
		mateDateMatchRefence.put(5, "默认(值)?");
		mateDateMatchRefence.put(6, "");
		mateDateMatchRefence.put(7, "");
		mateDateMatchRefence.put(8, "描述");
		mateDateMatchRefence.put(9, "(\\d\\.\\d{1,2}\\.\\d{1,2}\\.)(?:\\s*)(\\S{1,40})");
		mateDateMatchRefence.put(10, "表名：(?:\\S{1,40})");
	}
	
	// 判断是否 metaData
	public static void isMateData (String cellInfo) {
		int mapSize = mateDateMatchRefence.size();
		boolean rowFlag = false;
		for(int i = 0; (i < mapSize) && rowFlag == false; i++) {
			if (cellInfo.matches(mateDateMatchRefence.get(i))) {
				rowFlag = true;
			}
		}
		if (rowFlag) {
			metaFlag = true;
		} else {
			metaFlag = false;
		}
	}
	

	// 11 toolList reference numOfList
	public static List<List<String>> allList = new ArrayList<List<String>>() {
		private static final long serialVersionUID = 8316456133068386969L;

		{
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
			add(new ArrayList<String>());
		}
	};
	
	// 放置 inputRow 所有 metaData
	public static void mateInfoHandle(Row inputRow) {

		Cell tmpCell;
		String cellInfo;
		int endCellNum = inputRow.getLastCellNum();
			
		for (int i = 0; i < endCellNum; i++) {
			tmpCell = inputRow.getCell(i);
			if ((tmpCell == null) || (tmpCell.toString().equals(""))) {
				cellInfo = "nothing so don`t wanna match anything"; 
			} else { 
				cellInfo = tmpCell.toString().trim();
			}
				
			if (cellInfo.matches(mateDateMatchRefence.get(0))) {
				indexRecord.put(tmpCell.getColumnIndex(), 0);
			} else if (cellInfo.matches(mateDateMatchRefence.get(1))) {
				indexRecord.put(tmpCell.getColumnIndex(), 1);
			} else if (cellInfo.matches(mateDateMatchRefence.get(2))) {
				indexRecord.put(tmpCell.getColumnIndex(), 2);
			} else if (cellInfo.matches(mateDateMatchRefence.get(3))) {
				indexRecord.put(tmpCell.getColumnIndex(), 3);
			} else if (cellInfo.matches(mateDateMatchRefence.get(4))) {
				indexRecord.put(tmpCell.getColumnIndex(), 4);
			} else if (cellInfo.matches(mateDateMatchRefence.get(5))) {
				indexRecord.put(tmpCell.getColumnIndex(), 5);
			} else if (cellInfo.matches(mateDateMatchRefence.get(6))) {
				indexRecord.put(tmpCell.getColumnIndex(), 6);
			} else if (cellInfo.matches(mateDateMatchRefence.get(7))) {
				indexRecord.put(tmpCell.getColumnIndex(), 7);
			} else if (cellInfo.matches(mateDateMatchRefence.get(8))) {
				indexRecord.put(tmpCell.getColumnIndex(), 8);
			} else if (cellInfo.matches(mateDateMatchRefence.get(9))) {
//				indexRecord.put(9, 9);
				cellInfo = cellInfo.replaceAll("(\\d\\.\\d{1,2}\\.\\d{1,2}\\.)(?:\\s*)", "");
				allList.get(9).add(cellInfo);
			} else if (cellInfo.matches(mateDateMatchRefence.get(10))) {
//				indexRecord.put(10, 10);
				cellInfo = cellInfo.replaceAll("表名：", "");
				allList.get(10).add(cellInfo);
			} 
		}
	}
		
	// 放置 inputRow 所有 tableInfo
	public static void tableInfoHandle(Row inputRow) {

		Cell tmpCell;
		String cellInfo;
		
		int endColNum = indexRecord.size();
		
		// if cell=null can`t get cellColumnIndex, then set columnIndexCounter
		int columnIndexCounter = inputRow.getCell(0).getColumnIndex();

		for (int i = 0; i < endColNum; i++,columnIndexCounter++) {
			tmpCell = inputRow.getCell(i);
			cellInfo = tmpCell == null ? "" : tmpCell.toString();

			int targetListIndex = indexRecord.get(columnIndexCounter);
			allList.get(targetListIndex).add(cellInfo);

		}
	}

	// 清理容器
	public static void cleanupInfoContainer(List<List<String>> list, Map<?, ?> map) {
		for (List<String> subList : list) {
			subList.clear();
		}
		map.clear();
	}

}
