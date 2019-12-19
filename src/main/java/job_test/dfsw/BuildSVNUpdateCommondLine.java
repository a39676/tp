package job_test.dfsw;

public class BuildSVNUpdateCommondLine {
	
	// 本地存放代码的路径前缀
	private static String pathPrefix = "D:/work/合同系统/03实现/001程序代码";
	
	private static String replace(String lineInput) {
		// svn 更新命令的前缀
		String svnPrefix = "svn update ";
		// 定位匹配字符替换的关键字
		String targetStr = "001程序代码";
		
		int index = lineInput.indexOf(targetStr);
		if(index < 0) {
			return "";
		}
		
		String suffix = lineInput.substring(index + targetStr.length(), lineInput.length());
		
		return svnPrefix + pathPrefix + suffix;
	}
	
	private static void batchReplace(String lines) {
		String[] lineArray = lines.split(System.lineSeparator());
		for(String line : lineArray) {
			System.out.println(replace(line));
		}
	}
	

	public static void main(String[] args) {
		
		String lines = "Modified : /1开发库/企业组/集团合同管理系统(二期)/工程类/03实现/001程序代码/平台代码/OT.Platform/OT.OPF.Web/Context/UserContext.cs\r\n" + 
				"Modified : /1开发库/企业组/集团合同管理系统(二期)/工程类/03实现/001程序代码/平台代码/OT.Platform/OT.OPF.Web/Controllers/HomeController.cs";
		
		batchReplace(lines);
	}
}
