package useTools;

import java.util.Arrays;
import java.util.List;

import ioHandle.FileUtilCustom;
import stringHandle.StringUtilCustom;

public class StringToolImpl {
	
	public boolean isTargetLine(String line) {
//		TODO 需要设定吗?
//		return line.startsWith("\tprivate");
//		return line.contains("@param");
		return true;
	}
	
	public boolean tmpCount(String line) {
		return line.contains("_");
	}
	
	public void conventLinesFromFile(String filePath) {
		FileUtilCustom io = new FileUtilCustom();
		List<String> lines = Arrays.asList(io.getStringFromFile(filePath).split("\n"));
		StringUtilCustom st = new StringUtilCustom();
		
		
		int targetLineCount = 0;
		int changeCount = 0;
		
		for(String line : lines) {
			if(isTargetLine(line)) {
				System.out.println(st.underLineToCamel(line));
				targetLineCount++;
				if(tmpCount(line)) {
					changeCount++;
				}
			} else {
				System.out.println(line);
			}
		}
		
		System.out.println("targetLineCount = " + targetLineCount);
		System.out.println("changeCount = " + changeCount);
	}
	
	public static void main(String[] args) {
		StringToolImpl t = new StringToolImpl();
		String filePath;
//		filePath = "D:\\wen-server\\git\\wen-server\\wen-common\\src\\main\\java\\com\\wen\\rongbao\\entity\\result\\FastMemberSplitBindCardResult.java";
		filePath = "D:\\woqu\\defines\\src\\main\\java\\com\\wowqu\\defines\\notify\\message\\finance\\RejectPaymentMessage.java";
		
		t.conventLinesFromFile(filePath);
	}

}
