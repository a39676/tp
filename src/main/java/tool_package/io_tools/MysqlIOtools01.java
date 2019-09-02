package tool_package.io_tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MysqlIOtools01 {

	public  List<String> mysqlStringDataType = 
			Arrays.asList("char", "varchar", "text", "binary", "varbinary", "enum", "set" );

	public  List<String> putIntoList(String fileName) {

		List<String> outputList = new ArrayList<String>();
		String line;

		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				outputList.add(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputList;
	}
	
	
	/**
	 * 若文件不存在, 或长度不足, 则生成对应长度的空列表, 确保稳定输出.
	 * @param fileName
	 * @param referenceListSize
	 * @return
	 */
	public  List<String> putIntoList(String fileName, int referenceListSize) {

		List<String> outputList = new ArrayList<String>();
		String line;

		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				outputList.add(line);
			}
			br.close();
			
			// 若文件长度不足, 则补足空列表
			if (outputList.size() < referenceListSize) {
				System.out.println(fileName + " too short");
				for (int i = (referenceListSize - outputList.size()); i > 0; i--){
					outputList.add("");
				}
			}
			// 若文件不存在或其他IO异常则生成对应长度的空列表
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(fileName + " not exist");
			outputList = new ArrayList<String>();
			for (int i = referenceListSize; i > 0; i--) {
				outputList.add("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputList;
	}

	
	public  String modifyToMySqlDataType(String inputDataType) {

		String matchNumber = "((?i)NUMBER)\\((\\d{1,2})(?: ?,? ?)(\\d{1,2}?)\\)";
		String matchVarchar2 = "((?i)VARCHAR2)\\((\\d{1,4})(?:.*)\\)";
		String outputDataType;

		if (inputDataType.toLowerCase().contains("number")) {

			Pattern patternR = Pattern.compile(matchNumber);
			Matcher m = patternR.matcher(inputDataType);
			m.find();

			int numPart01 = Integer.parseInt(m.group(2));
			int numPart02 = 0;

			try {
				numPart02 = Integer.parseInt(m.group(3));
			} catch (Exception e) {

			}

			outputDataType = "int(" + numPart01 + ")";
			if (numPart01 > 19) {
				outputDataType = "bigint(" + numPart01 + ")";
			}
			if (numPart02 != 0) {
				outputDataType = "decimal(" + numPart01 + "," + numPart02 + ")";
			}

			// System.out.println(outputDataType);
			return outputDataType;

		} else if (inputDataType.toLowerCase().contains("varchar2")) {

			Pattern patternR = Pattern.compile(matchVarchar2);
			Matcher m = patternR.matcher(inputDataType);
			m.find();
			outputDataType = "varchar(" + m.group(2) + ")";
			// System.out.println(outputDataType);
			return outputDataType;

		} else {
			return inputDataType;
		}

		// if (m.find()) {
		// System.out.println("Found value0: " + m.group(0));
		// System.out.println("Found value1: " + m.group(1));
		// System.out.println("Found value2: " + m.group(2));
		// System.out.println("Found value3: " + m.group(3));
		// System.out.println("Found value4: " + m.group(4));
		// String outputDataType;
		// strOutput = "int(" + m.group(2) + ")";
		// System.out.println(outputDataType);
		// } else {
		// System.out.println("NO MATCH");
		// }
		//
		// return null;

	}

	
	public  List<String> modifyListToMySqlDataType(List<String> listInput) {
		List<String> listOutput = new ArrayList<String>();

		for (String ele : listInput) {
			listOutput.add(modifyToMySqlDataType(ele));
		}

		return listOutput;
	}

	
	public  List<String> modifyListToUpperCase(List<String> listInput) {
		List<String> listOutput = new ArrayList<String>();

		for (String ele : listInput) {
			listOutput.add(ele.toUpperCase());
		}

		return listOutput;
	}
	
	
	/**
	 * 替换所有 斜杠 反斜杠 成系统识别斜杠
	 * @param inputStr
	 * @return
	 */
	public  String separatorFilter(String inputStr) {
		String outputStr = inputStr.replaceAll("/", ("\\" + File.separator)).replaceAll("\\\\", ("\\" + File.separator));
		return outputStr;
	}
	
	
	/**
	 * 将所有非 空格 原点 符号 替换成 "-"
	 * @param inputStr
	 * @return
	 */
	public  String symbolFilter(String inputStr) {
		String outputStr = inputStr.replaceAll("[^\\w .]", ("-"));
		return outputStr;
	}

//	public  void main(String[] args) {
//		String tmp = "number(29,99)";
//		String newOne = IOtools01.modifyToMySqlDataType(tmp);
//		System.out.println(newOne);
//	}
	

}
