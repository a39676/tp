package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ioHandle.FileUtilCustom;

public class TmpTest2 {
	
//	TODO
//	尚不可删除
//	
	private static FileUtilCustom io = new FileUtilCustom();
	private static String subFolderName = "pet";
	private static String mainFloderPath = "d:/auxiliary/tmp/";
	private static String sourceFolderPath = mainFloderPath + subFolderName + "/";
	private static String resultFolderPath = mainFloderPath + "imageResult/" + subFolderName + "/";

	public static void main(String[] args) throws IOException {
		
		File sourceFolder = new File(sourceFolderPath);
		
		File[] files = sourceFolder.listFiles();
		List<String> imagePathList = new ArrayList<String>();
		String tmpSrc = null;
		for(File f : files) {
			if(f.isFile()) {
				tmpSrc = getTargetImagePath(f.getName());
				if(!StringUtils.isBlank(tmpSrc)) {
					tmpSrc = tmpSrc.substring(2, tmpSrc.length());
					imagePathList.add(sourceFolderPath + tmpSrc);
					System.out.println(sourceFolderPath + tmpSrc);
				}
			}
		}
		
		File tmpFile = null;
		File tmpResultFile = null;
		for(String sf : imagePathList) {
			tmpFile = new File(sf);
			tmpResultFile = new File(resultFolderPath + tmpFile.getName());
			if(!tmpResultFile.getParentFile().exists()) {
				tmpFile = tmpResultFile.getParentFile();
				tmpFile.mkdirs();
				tmpFile = new File(sf);
			}
			FileUtils.copyFile(tmpFile, tmpResultFile);
		}
		
	}
	
	public static String getTargetImagePath(String fileName) {
		Document doc = null;
		String htmlStr = io.getStringFromFile(sourceFolderPath + fileName);
//		doc = Jsoup.connect("https://500px.com/photo/266323287/katarina-s-ass-by-nikita-shvedov").get();
		doc = Jsoup.parse(htmlStr); 
		Elements eleClazzPhoto = doc.getElementsByClass("photo");
		String src = null;
		for(Element ele : eleClazzPhoto) {
			if(!StringUtils.isBlank(ele.attr("src"))) {
				src = ele.attr("src");
			}
		}
		return src;
	}
}
