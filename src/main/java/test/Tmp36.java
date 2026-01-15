package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import toolPack.ioHandle.FileUtilCustom;

public class Tmp36 {

	public static void main(String[] args) throws IOException {
		Map<Long, SourceDTO> commodityMap = getSourceMap();
		Map<Long, ExcelDTO> excelDtoMap = getExcelDtoMap();
		int tmpId = 1;
		for (ExcelDTO excelData : excelDtoMap.values()) {
//			System.out.println(val.toString());
			Long commodityId = null;
			boolean includePostageFlag = excelData.getIdIncludePostage() != null;
			if (includePostageFlag) {
				commodityId = excelData.getIdIncludePostage();
			} else {
				commodityId = excelData.getIdNotIncludePostage();
			}
			SourceDTO commodity = commodityMap.get(commodityId);
			if (commodity == null) {
				System.err.println(commodityId + " CAN NOT FIND");
				continue;
			}

			for (int i = 0; i < excelData.getIdFrom1688().size(); i++) {
				System.out.print("(");
				System.out.print(tmpId);
				System.out.print(", " + commodityId);
				System.out.print(", " + excelData.getIdFrom1688().get(i));
				System.out.print(", " + "\"" + commodity.getName() + "\"");
				System.out.print(", " + "\"" + commodity.getImgName() + "\"");
				System.out.print(", " + includePostageFlag);
				if (StringUtils.isBlank(excelData.getRemark())) {
					System.out.print(", null");
				} else {
					System.out.print(", " + "\"" + excelData.getRemark() + "\"");
				}

				System.out.print(", \"2025-10-24\"),");
				System.out.println();
//			System.out.println("(" + i + ", " + commodityId + ", " + val.getIdFrom1688() + ", " + commodity.getName()
//					+ ", " + commodity.getImgName() + ", " + includePostageFlag + ", " + val.getRemark()
//					+ ", \"2025-10-24\"),");
				tmpId++;
			}
		}
//		(id, commodityId, sourceId, commodityName, imgName, includePostage, remark, createTime);
	}

	private static Map<Long, SourceDTO> getSourceMap() {
		SourceDTO s = null;
		FileUtilCustom ioU = new FileUtilCustom();
		String sourceContent = ioU.getStringFromFile("C:\\Users\\daven\\tmp/source.txt");
		String[] lines = sourceContent.split(System.lineSeparator());
		Map<Long, SourceDTO> sourceMap = new HashMap<>();
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			String[] ele = line.split(",");
			s = new SourceDTO(Long.parseLong(ele[0]), ele[1], ele[2]);
			sourceMap.put(s.getTargetId(), s);
//			System.out.println(s);
		}
		return sourceMap;
	}

	private static Map<Long, ExcelDTO> getExcelDtoMap() throws IOException {
		Map<Long, ExcelDTO> excelDtoMap = new HashMap<>();
		ExcelDTO dto = null;
		FileInputStream file = new FileInputStream(new File("C:\\Users\\daven\\auxiliary\\pf\\shop/仓储临时记录.xlsx"));
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(3);
		for (int rowIndex = 3; rowIndex < 332; rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			Cell productNameCell = null;
			try {
				productNameCell = row.getCell(1);
			} catch (Exception e) {
				System.err.println(rowIndex);
				e.printStackTrace();
				return null;
			}
			if (productNameCell == null) {
				continue;
			}
//			System.out.println(productNameCell.getStringCellValue());
			Cell linksCell = row.getCell(4);
			String linkStr = linksCell.getStringCellValue();
			String[] links = linkStr.split("https://detail.1688.com/offer/");
			List<Long> alibabaIdList = new ArrayList<>();
			for (int i = 0; i < links.length; i++) {
				String link = links[i];
				if (StringUtils.isNotBlank(link)) {
					Long alibabaId = Long.parseLong(link.substring(0, 12));
					alibabaIdList.add(alibabaId);
				}
			}

			Cell remarkCell = row.getCell(5);
			String remark = null;
			if (remarkCell != null) {
				remark = remarkCell.getStringCellValue();
			}

			Cell idNotIncludePostageCell = row.getCell(2);
			if (idNotIncludePostageCell != null
					&& StringUtils.isNotBlank(idNotIncludePostageCell.getStringCellValue())) {
				Long idNotIncludePostage = Long.parseLong(idNotIncludePostageCell.getStringCellValue());
				dto = new ExcelDTO();
				dto.setRemark(remark);
				dto.setIdFrom1688(alibabaIdList);
				dto.setIdNotIncludePostage(idNotIncludePostage);
				excelDtoMap.put(dto.getIdNotIncludePostage(), dto);
			}

			Cell idIncludePostageCell = row.getCell(3);
			if (idIncludePostageCell != null && StringUtils.isNotBlank(idIncludePostageCell.getStringCellValue())) {
				Long idIncludePostage = Long.parseLong(idIncludePostageCell.getStringCellValue());
				dto = new ExcelDTO();
				dto.setRemark(remark);
				dto.setIdFrom1688(alibabaIdList);
				dto.setIdIncludePostage(idIncludePostage);
				excelDtoMap.put(dto.getIdIncludePostage(), dto);
			}
		}
		return excelDtoMap;
	}
}

class SourceDTO {
	private Long targetId;
	private String name;;
	private String imgName;

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	@Override
	public String toString() {
		return "SourceDTO [targetId=" + targetId + ", name=" + name + ", imgName=" + imgName + "]";
	}

	public SourceDTO(Long id, String name, String imgName) {
		this.targetId = id;
		this.name = name;
		this.imgName = imgName;
	}
}

class ExcelDTO {
	private Long idNotIncludePostage;
	private Long idIncludePostage;
	private List<Long> idFrom1688;
	private String remark;

	public Long getIdNotIncludePostage() {
		return idNotIncludePostage;
	}

	public void setIdNotIncludePostage(Long idNotIncludePostage) {
		this.idNotIncludePostage = idNotIncludePostage;
	}

	public Long getIdIncludePostage() {
		return idIncludePostage;
	}

	public void setIdIncludePostage(Long idIncludePostage) {
		this.idIncludePostage = idIncludePostage;
	}

	public List<Long> getIdFrom1688() {
		return idFrom1688;
	}

	public void setIdFrom1688(List<Long> idFrom1688) {
		this.idFrom1688 = idFrom1688;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ExcelDTO [idNotIncludePostage=" + idNotIncludePostage + ", idIncludePostage=" + idIncludePostage
				+ ", idFrom1688=" + idFrom1688 + ", remark=" + remark + "]";
	}

}