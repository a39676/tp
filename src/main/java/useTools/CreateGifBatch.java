package useTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.IIOException;

import tool_package.io_tools.IOToolsGetPath;
import tool_package.io_tools.image_tools.CreateGif01;

public class CreateGifBatch {
	
	public static void main(String[] args) throws IIOException, IOException {
		
		String masterFolder = "D:\\joyxyExtract\\ui\\efx\\1";
		
		IOToolsGetPath getPath = new IOToolsGetPath();
		getPath.collectAllFolderPaths(masterFolder);
		
		List<String> allFolderPath = getPath.getFolderPaths();
		allFolderPath.add(masterFolder);
		List<String> filePaths = null;
		
		System.out.println("get " + allFolderPath.size() + " folders");
		
		for(String folderPath : allFolderPath) {
			filePaths = getPath.getFilePaths(folderPath);
			
			for(int i = 0; i < filePaths.size(); i++) {
				filePaths.add(i, filePaths.get(i).replaceAll("\\\\", "/"));
				filePaths.remove(i+1);
			}
			
			List<List<String>> categorizedList = categorized(filePaths);
			
			System.out.println("will make " + categorizedList.size() + " gifs in " + folderPath);
			

			for(int i = 0; i < categorizedList.size(); i++) {
				CreateGif01.generateFromFiles(categorizedList.get(i), categorizedList.get(i).get(0).substring(0, categorizedList.get(i).get(0).length() - 4) + ".gif", 10, true);
				System.out.println("make gif of " + categorizedList.get(i).get(0) + " " + i + "/" + categorizedList.size() + " " + (categorizedList.size()-i));
			}
		}
		
		
		
		
	}
	
	/**
	 * 按对应的文件名前缀归类
	 * @param filePaths
	 * @return
	 */
	public static List<List<String>> categorized(List<String> filePaths) {
		
		List<List<String>> finalList = new ArrayList<List<String>>();
		
		List<String> tmpFileGroupPathsList = new ArrayList<String>();
		
		Set<String> regexSet = new HashSet<String>();
		
		String suffixRegex = "^(.*)(-\\d{1,2}\\.png)$";
		Pattern suffixPattern = Pattern.compile(suffixRegex);
		
		Matcher matcher = null;
		
		for(int i = 0; i < filePaths.size(); i ++) {
			if(!filePaths.get(i).endsWith(".png")) {
				filePaths.remove(i);
				i --;
			}
		}
		
		for(int i = 0; i < filePaths.size(); i ++) {
			matcher = suffixPattern.matcher(filePaths.get(i));
			
			if(matcher.find()) {
				regexSet.add(matcher.group(1));
			}
		}
		
		for(String ele : regexSet) {
			for(int i = 0; i < filePaths.size(); i ++) {
				if(filePaths.get(i).startsWith(ele)) {
					tmpFileGroupPathsList.add(filePaths.get(i));
				}
			}
			finalList.add(tmpFileGroupPathsList);
			tmpFileGroupPathsList = new ArrayList<String>();
		}
		
		
		return finalList;
	}

}
