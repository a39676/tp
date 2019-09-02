package tool_package.io_tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IOToolsGetPath {
	
	public static List<String> folderPaths = new ArrayList<String>();
	
	public static List<String> filePaths = new ArrayList<String>();
	
	public List<String> getFolderPaths() {
		return folderPaths;
	}
	
	public List<String> getFilePaths(String path) {
		File folder = new File(path);
		
		filePaths.clear();
		
		for(File subFile : folder.listFiles()) {
			if(subFile.isFile()) {
				filePaths.add(subFile.getPath());
			}
		}
		
		return filePaths;
	}
	
	public void collectAllFolderPaths(String masterPath) {
		File masterFile = new File(masterPath);
		
		for(File subFile : masterFile.listFiles()) {
			if(subFile.isDirectory()) {
				folderPaths.add(subFile.getPath());
				collectAllFolderPaths(subFile.getPath());
			}
		}
	}
	
	
	public static void main(String[] args) {
		IOToolsGetPath tool = new IOToolsGetPath();
		
		tool.collectAllFolderPaths("D:/joyxyExtract");
		System.out.println(tool.getFolderPaths());
	}

}
