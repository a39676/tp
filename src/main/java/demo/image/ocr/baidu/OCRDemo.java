package demo.image.ocr.baidu;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ioHandle.FileUtilCustom;

public class OCRDemo {
	
	public static String resultFilePath = "D:\\auxiliary\\tmp\\nameCardResult03.txt";
	
	public static void main(String[] args) {
		
		File mainFolder = new File("D:\\auxiliary\\platenogroup\\窝趣通讯录截图");
		List<String> filePathList = new ArrayList<String>();
		
		File[] files = mainFolder.listFiles();
		for(File f : files) {
			if(f.isFile()) {
				filePathList.add(f.getAbsolutePath());
			}
		}
		
		List<String> resultList = BaiduOCRUtil.orc(filePathList);
		System.out.println(resultList);
		FileUtilCustom io = new FileUtilCustom();
		
		io.byteToFile(resultList.toString().getBytes(StandardCharsets.UTF_8), resultFilePath);
	}

}
