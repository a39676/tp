package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import toolPack.ioHandle.FileUtilCustom;

public class TmpTest7 {
	
	private static final String sourceBinPath = "D:/Cnt/bin";
	
	private static final String sourceFilePath = "d:/tmp/updates.txt";
	
	private static final String localPathPrefix = "D:/work/合同系统/03实现/001程序代码";
	
	
	public static void main(String[] args) {
		List<String> targetFilePath = new ArrayList<String>();
		
		List<String> sourceLines = findSourceFile();
		List<String> lines = new ArrayList<String>();
		for(String l : sourceLines) {
			if(!lines.contains(l)) {
				lines.add(l);
			}
		}
		
		Collections.sort(lines);
	
		System.out.println(lines.size());
		List<String> svnCommondLines = buildSVNUpdateCommondLine(lines);
		for(String l : svnCommondLines) {
			System.out.println(l);
		}
		
		List<String> filePathList = getLocalPathListFromSvnCommondLines(svnCommondLines);
//		for(String l : filePathList) {
//			System.out.println(l);
//		}
		
		List<String> dllFileNames = findAllDllNames();
		System.out.println(dllFileNames);
		
		String dllPath = null;
		List<String> dllFilePathList = new ArrayList<String>();
		List<String> pdbFilePathList = new ArrayList<String>();
		List<String> frontSideFilePathList = new ArrayList<String>();
		
		String tmpPdbFilePath = null;
		for(String l : filePathList) {
			if(l.endsWith(".cs")) {
				dllPath = findTargetDll(l, dllFileNames);
				if(!dllFilePathList.contains(dllPath)) {
					dllFilePathList.add(dllPath);
					if(!l.contains("平台代码")) {
						tmpPdbFilePath = findTargetPdbFile(dllPath);
						if(tmpPdbFilePath != null && !pdbFilePathList.contains(tmpPdbFilePath)) {
							pdbFilePathList.add(tmpPdbFilePath);
						}
					}
				}
			} else {
				if(!frontSideFilePathList.contains(l)) {
					frontSideFilePathList.add(l.replaceAll("\\\\", "/"));
				}
			}
		}
		
		targetFilePath.addAll(dllFilePathList);
		targetFilePath.addAll(pdbFilePathList);
		targetFilePath.addAll(frontSideFilePathList);
		
		Collections.sort(targetFilePath);
		for(String l : targetFilePath) {
			if(l.endsWith("dll") || l.endsWith("pdb")) {
				System.out.println(l.replaceAll(sourceBinPath, "/OT.WebSite/bin"));
			} else if(l.startsWith(localPathPrefix)) {
				System.out.println(l.replaceAll(localPathPrefix + "/OT.CNT", ""));
			}
		}
	}

	public static List<String> findSourceFile() {
		FileUtilCustom io = new FileUtilCustom();
		String sourceStr = io.getStringFromFile(sourceFilePath);
		List<String> lines = Arrays.asList(sourceStr.split(System.lineSeparator()));
		return lines;
	}
	
	public static List<String> findAllDllNames() {
		
		File projectBinFolder = new File(sourceBinPath);
		
		List<File> sourceFiles = Arrays.asList(projectBinFolder.listFiles());
		List<String> dllFileNameList = new ArrayList<String>();
		for(File f : sourceFiles) {		
			if(f.getName().endsWith("dll")) {
				dllFileNameList.add(f.getName().replaceAll(".dll", ""));
			}
		}
		
		return dllFileNameList;
	}
	
	public static String findTargetPdbFile(String dllFilePath) {
		
		File pdbFile = new File(dllFilePath.replaceAll(".dll", ".pdb"));
		if(pdbFile.exists()) {
			return pdbFile.getAbsolutePath().replaceAll("\\\\", "/");
		}
		return null;
	}
	
	public static String findTargetDll(String filePath, List<String> dllNames) {
		
		File f = new File(filePath);
		if("OT.CNT.Web".equals(f.getName())) {
			return sourceBinPath + "/OT.CNT.WebController.dll";
		}
		if(f.getName().equals("OT.Workflow3.APIs")) {
			System.out.println("");
		}
		if(dllNames.contains(f.getName())) {
			return sourceBinPath + "/" + f.getName() + ".dll";
		} else if(f.getParentFile() != null) {
			return findTargetDll(f.getParentFile().getAbsolutePath(), dllNames);
		}
		
		return null;
	}
	
	public static List<String> buildSVNUpdateCommondLine(List<String> inputLines) {
		List<String> resultLines = batchReplace(inputLines);
		return resultLines;
	}
	
	private static List<String> batchReplace(List<String> lines) {
		List<String> resultLines = new ArrayList<String>();
		String resultLine = null;
		for (String sourceLine : lines) {
			resultLine = replace(localPathPrefix, sourceLine);
			resultLines.add(resultLine);
		}
		return resultLines;
	}
	
	private static String replace(String localPath, String bugFreePath) {
		List<String> localPathSplit = Arrays.asList(localPath.split("/"));
		List<String> bugFreeSplit = Arrays.asList(bugFreePath.split("/"));
		
		int bugFreeTargetIndex = -1;
		int localPathIndex = -1;
		String targetWord = null;
		for(int i = 0; i < localPathSplit.size() && bugFreeTargetIndex < 0; i++) {
			targetWord = localPathSplit.get(i);
			if(bugFreeSplit.contains(targetWord)) {
				bugFreeTargetIndex = bugFreeSplit.indexOf(targetWord);
				localPathIndex = i;
			}
		}
		
		if(bugFreeTargetIndex < 0) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer("svn update ");
		for(int i = 0; i < localPathIndex; i++) {
			sb.append(localPathSplit.get(i) + File.separator);
		}
		
		for(int i = bugFreeTargetIndex; i < bugFreeSplit.size(); i++) {
			sb.append(bugFreeSplit.get(i) + File.separator);
		}
		
		String result = sb.substring(0, sb.length() - 1);
		
		return result;
	}

	public static List<String> getLocalPathListFromSvnCommondLines(List<String> svnCommondLines) {
		List<String> result = new ArrayList<String>();
		for(String s : svnCommondLines) {
			result.add(s.replaceAll("svn update ", ""));
		}
		return result;
	}
}
