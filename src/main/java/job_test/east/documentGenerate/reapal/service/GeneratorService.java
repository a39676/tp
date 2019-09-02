package job_test.east.documentGenerate.reapal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ioHandle.FileUtilCustom;
import job_test.east.documentGenerate.DocumentDomain;
import job_test.east.documentGenerate.common.LineConstant;
import job_test.east.documentGenerate.reapal.common.LineStatus;
import job_test.east.documentGenerate.reapal.domain.ParamAttr;
import stringHandle.StringUtilCustom;

public class GeneratorService {
	
	private static String isNecessaryDesc = "(必填)";

	/**
	 * 字段转java脚本格式
	 * 
	 * @param attr
	 * @param su
	 * @return
	 */
	private String paramAttrToStringForJava(ParamAttr attr, StringUtilCustom su) {
		String n = "";
		if (attr.getIsNecessary()) {
			n = " " + isNecessaryDesc + " ";
		}
		
		return LineConstant.commentStart + " " + attr.getAttrChineseName() + n + " " + attr.getCommentPrefix() + LineConstant.commentEnd + "\n" 
			+ attrAnnotationToString(attr) + "\n"
			+ attr.getAuthorityPrefix() + " " + attr.getDataType() + " " + su.underLineToCamel(attr.getAttrName()) + ";";
	}

	/**
	 * 字段转service备注格式
	 * 
	 * @param attr
	 * @param su
	 * @return
	 */
	private String ParamAttrToServerComment(ParamAttr attr, StringUtilCustom su) {
		String n = "";
		if (attr.getIsNecessary()) {
			n = " " + isNecessaryDesc + " ";
		}

		return "\t " + " " + LineConstant.commentBody + " " + LineConstant.paramAnnotation + " " + su.underLineToCamel(attr.getAttrName()) + "\n" 
			+ "\t " + " " + LineConstant.commentBody + " " + attr.getAttrChineseName() + n + " " + attr.getCommentPrefix();
	}

	/**
	 * 根据字段实体类,返回注解部分脚本文字
	 * @param attr
	 * @return
	 */
	private String attrAnnotationToString(ParamAttr attr) {
		if(attr != null && attr.getAnnotation() != null && attr.getAnnotation().length() > 0) {
			return attr.getAnnotation();
		} else {
			return "";
		}
	}

	/**
	 * 从文档中生成字段类列表
	 * 
	 * @param filePath
	 * @return
	 */
	public List<ParamAttr> makeAttrsFromFile(String filePath) {
		FileUtilCustom io = new FileUtilCustom();

		List<String> lines = Arrays.asList(io.getStringFromFile(filePath).split("\n"));
		List<ParamAttr> attrs = new ArrayList<ParamAttr>();

		for (String line : lines) {
			attrs.add(makeAttrFromLine(line));
		}
		return attrs;
	}

	/**
	 * 字段列表转java代码格式
	 * 
	 * @param attrs
	 * @return
	 */
	public String paramAttrToStringForJava(List<ParamAttr> attrs) {
		StringBuilder sb = new StringBuilder();
		StringUtilCustom su = new StringUtilCustom();
		for (ParamAttr attr : attrs) {
			sb.append(paramAttrToStringForJava(attr, su) + "\n");
		}

		return sb.toString();

	}

	/**
	 * 字段列表转service备注格式
	 * 
	 * @param attrs
	 * @return
	 */
	public String paramAttrToStringForServerComment(List<ParamAttr> attrs) {
		StringBuilder sb = new StringBuilder();
		StringUtilCustom su = new StringUtilCustom();
		for (ParamAttr attr : attrs) {
			sb.append(ParamAttrToServerComment(attr, su) + "\n");
		}

		return sb.toString();
	}

	/**
	 * 从一行文本中生成一个字段类
	 * 
	 * @param line
	 * @return
	 */
	private ParamAttr makeAttrFromLine(String line) {
		List<String> elements = Arrays.asList(line.split("\t"));
		ParamAttr tmpAttr = new ParamAttr();
		String lastElement = elements.get(elements.size() - 1);

		tmpAttr.setAnnotation(LineConstant.JsonPropertyAnnotation + "(\"" + elements.get(0) + "\")");
		if (lastElement != null && !lastElement.equals("null") && lastElement.length() > 0 && !lastElement.equals("Y")
				&& !lastElement.equals("N") && elements.size() >= 4) {
			tmpAttr.setCommentPrefix(String.valueOf(elements.get(elements.size() - 1)));
		} else {
			tmpAttr.setCommentPrefix("");
		}
		if (elements.size() >= 4 && elements.get(3).equals("Y")) {
			tmpAttr.setIsNecessary(true);
		} else {
			tmpAttr.setIsNecessary(false);
		}
		tmpAttr.setAttrName(elements.get(0));
		tmpAttr.setAttrChineseName(elements.get(1));
		tmpAttr.setAuthorityPrefix("private");
		tmpAttr.setDataType("String");

		return tmpAttr;
	}

	// marrk 未完工
	public List<DocumentDomain> linesToDomains(List<String> lines) {
		LineStatus ls = new LineStatus();
		DocumentDomain domain;

		for (String line : lines) {
			//
			if (ls.getStatus().equals(ls.notStart) || ls.getStatus().equals(ls.commentStart)) {
				domain = new DocumentDomain();
				if (line.startsWith(LineConstant.commentStart) && line.endsWith(LineConstant.commentEnd)) {
					ls.setStatus(ls.commentEnd);
					domain.setComment(
							line.replaceAll(LineConstant.commentStart, "").replaceAll(LineConstant.commentEnd, ""));
				} else if ((line.startsWith(LineConstant.commentStart) || line.startsWith(LineConstant.commentBody))
						&& !line.endsWith(LineConstant.commentEnd)) {
					domain.addComment(line.replaceAll(LineConstant.commentStart, ""));
					ls.setStatus(ls.commentEnd);
				}
			}
		}

		String line;

		for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
			line = lines.get(lineIndex);

			if (ls.getStatus().equals(ls.notStart) || ls.getStatus().equals(ls.commentStart)) {
				domain = new DocumentDomain();
				if (line.startsWith(LineConstant.commentStart) || line.startsWith(LineConstant.commentBody)) {
					ls.setStatus(ls.commentStart);
					domain.addComment(line.replaceAll(LineConstant.commentStart, ""));
				}
				if (line.endsWith(LineConstant.commentEnd)) {
					ls.setStatus(ls.commentEnd);
					if (!domain.getComment().endsWith(line)) {
						domain.addComment(line.replaceAll(LineConstant.commentEnd, ""));
					}
				}
			}
			if (ls.getStatus().equals(ls.commentEnd)) {

			}
		}

		return null;
	}
	
	public List<String> strToLines(String inputStr) {
		String[] lines = inputStr.split("\n");

		List<String> resultLines = new ArrayList<String>();
		for (String line : lines) {
			if (!line.matches("\\W")) {
				resultLines.add(line.trim());
			}
		}
		return resultLines;
	}

}
