package tool_package.io_tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/* 
 * build a read function
 * */
public class IOToolWithExcl {

	public static void main(String[] args) throws IOException {

		// String path = "d:" + File.separator + "auxiliary" + File.separator +
		// "tmp" + File.separator;
		// String file = "test01.xlsx";

		String path = "d:/auxiliary/tmp/test01.xlsx";

//		Workbook workbook = null;
//		try {
//			workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		if (workbook != null) {
//			findElementsTest(workbook, 0, "ta");
//		}
		
		readExcl(path);
	}

	public static void readExcl(String path) throws IOException {

		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
			Sheet firstSheet = workbook.getSheetAt(0);
			Cell tmpCell = null;
			Row tmpRow = null;
			for(int i = 0; i < firstSheet.getLastRowNum() + 1; i++) {
				tmpRow = firstSheet.getRow(i);
				if(tmpRow != null) {
					for (int j = 0; j < tmpRow.getLastCellNum(); j++) {
						if (tmpRow.getCell(j) != null) {
							tmpCell = tmpRow.getCell(j);
						} else {
							tmpCell.setCellValue("");
						}
						System.out.println(tmpCell.toString());
					}
				}
			}

			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(workbook != null) {
				workbook.close();
			}
		}
	}

	public static void findElements(Workbook workbook, int targetSheet, String targetWord) {
		Sheet sheet = workbook.getSheetAt(targetSheet);
		Iterator<Row> rowIterator = sheet.iterator();

//		String tableCommentMather = "(\\d.\\d.\\d.)(?:\\s*)(\\S{1,40})";
//		String tableNameMather = "表名：(?:\\s{0,3})(?:\\w{1,40})";

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();

			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.toString().contains(targetWord)) {
					System.out.println("found" + cell.getRowIndex() + "," + cell.getColumnIndex());
				}
			}
		}
	}

	public static void findElementsTest(Workbook workbook, int targetSheet, String targetWord) {

		Sheet sheet = workbook.getSheetAt(targetSheet);
		Iterator<Row> rowIterator = sheet.iterator();


		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();

			for (int i = 0; i < 10; i++) {
				Cell tmpCell = nextRow.getCell(i);
				if (tmpCell != null) {

				} else {

				}
			}
		}
	}

	

	

	

}
