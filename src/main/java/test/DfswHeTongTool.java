package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import toolPack.ioHandle.FileUtilCustom;

public class DfswHeTongTool {
	
	private static final String outputPackPath = "d:/Cnt";
	private static final String outputBinPath = outputPackPath + "/bin";
	private static final String updateFilePath = "d:/tmp/updates.txt";
	private static final String localPathPrefix = "D:/work/合同系统/03实现/001程序代码";
	private static final String copyToTargetFolderPath = "d:/tmp/copyTarget2";
	
	public static void main(String[] args) {
		
		List<String> sourceLines = findSourceFile();
		List<String> lines = new ArrayList<String>();
		for(String l : sourceLines) {
			if(!lines.contains(l)) {
				lines.add(l);
			}
		}
		
		Collections.sort(lines);
		
		List<String> localSourceCodeUpdateFilePathList = new ArrayList<String>();
		for(String s : lines) {
			localSourceCodeUpdateFilePathList.add(buildLocalFilePathFromBugFreeSource(localPathPrefix, s));
		}
		for(String s : localSourceCodeUpdateFilePathList) {
			System.out.println(s);
		}
		System.out.println();
	
		List<String> svnCommondLines = buildSVNUpdateCommondLine(localSourceCodeUpdateFilePathList);
		for(String s : svnCommondLines) {
			System.out.println(s);
		}
		System.out.println();
		
		List<String> allDLLFileNames = findAllDllNames();
		
		List<String> dllFilePathList = filterTargetDLLFilePath(localSourceCodeUpdateFilePathList, allDLLFileNames);
		List<String> pdbFilePathList = filterTargetPDBFilePath(localSourceCodeUpdateFilePathList, allDLLFileNames, dllFilePathList);
		List<String> frontSideOutputFilePathList = filterFrontSideFilePath(localSourceCodeUpdateFilePathList);
		
		List<String> copyTargetList = new ArrayList<String>();
		
		List<String> backGroundFilePath = new ArrayList<String>();
		backGroundFilePath.addAll(dllFilePathList);
		backGroundFilePath.addAll(pdbFilePathList);
		
		List<String> frontGroundFilePath = new ArrayList<String>();
		frontGroundFilePath.addAll(frontSideOutputFilePathList);
		
		Collections.sort(backGroundFilePath);
		Collections.sort(frontGroundFilePath);
		
		List<String> copyCommondLine = new ArrayList<String>();
		
		String subPath = null;
		List<String> subPathList = new ArrayList<String>();
		
		for(String l : backGroundFilePath) {
			subPath = l.replaceAll(outputBinPath, "/OT.WebSite/bin");
			copyTargetList.add(copyToTargetFolderPath + subPath);
			copyCommondLine.add("cp " + l + " " + copyToTargetFolderPath + subPath);
			subPathList.add(subPath);
		}
		
		for(String l : frontGroundFilePath) {
			subPath = l.replaceAll(outputPackPath, "");
			copyTargetList.add(copyToTargetFolderPath + subPath);
			copyCommondLine.add("cp " + l + " " + copyToTargetFolderPath + subPath);
			subPathList.add("/OT.WebSite" + subPath);
		}
		
		File f = null;
		for(String l : copyTargetList) {
			f = new File(l);
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
		}
		
		for(String s : copyCommondLine) {
			System.out.println(s);
		}
		System.out.println();
		
		String numberStr = null;
		for(int i = 0; i < subPathList.size(); i++) {
			if(i < 10) {
				numberStr = "0" + i;
			} else {
				numberStr = String.valueOf(i);
			}
			System.out.println(numberStr + ": " + subPathList.get(i));
		}
	}

	private static List<String> findSourceFile() {
		FileUtilCustom io = new FileUtilCustom();
		String sourceStr = io.getStringFromFile(updateFilePath);
		List<String> lines = Arrays.asList(sourceStr.split(System.lineSeparator()));
		return lines;
	}
	
	private static List<String> findAllDllNames() {
		
		File projectBinFolder = new File(outputBinPath);
		
		List<File> sourceFiles = Arrays.asList(projectBinFolder.listFiles());
		List<String> dllFileNameList = new ArrayList<String>();
		for(File f : sourceFiles) {		
			if(f.getName().endsWith("dll")) {
				dllFileNameList.add(f.getName().replaceAll(".dll", ""));
			}
		}
		
		return dllFileNameList;
	}
	
	private static String findTargetPdbFile(String dllFilePath) {
		
		File pdbFile = new File(dllFilePath.replaceAll(".dll", ".pdb"));
		if(pdbFile.exists()) {
			return pdbFile.getAbsolutePath().replaceAll("\\\\", "/");
		}
		return null;
	}
	
	private static String findTargetDll(String filePath, List<String> dllNames) {
		
		File f = new File(filePath);
		if("OT.CNT.Web".equals(f.getName())) {
			return outputBinPath + "/OT.CNT.WebController.dll";
		}
		if(f.getName().equals("OT.Workflow3.APIs")) {
			System.out.println("");
		}
		if(dllNames.contains(f.getName())) {
			return outputBinPath + "/" + f.getName() + ".dll";
		} else if(f.getParentFile() != null) {
			return findTargetDll(f.getParentFile().getAbsolutePath(), dllNames);
		}
		
		return null;
	}
	
	private static List<String> buildSVNUpdateCommondLine(List<String> inputLines) {
		List<String> resultLines = new ArrayList<String>();
		for(String s : inputLines) {
			resultLines.add("svn update " + s);
		}
		return resultLines;
	}
	
	private static String buildLocalFilePathFromBugFreeSource(String localPath, String bugFreePath) {
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
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < localPathIndex; i++) {
			sb.append(localPathSplit.get(i) + File.separator);
		}
		
		for(int i = bugFreeTargetIndex; i < bugFreeSplit.size(); i++) {
			sb.append(bugFreeSplit.get(i) + File.separator);
		}
		
		String result = sb.substring(0, sb.length() - 1);
		
		return result;
	}

	private static List<String> filterTargetDLLFilePath(List<String> localSourceCodeUpdateFilePathList, List<String> allDLLFileNames) {
		String dllPath = null;
		List<String> dllFilePathList = new ArrayList<String>();
		for(String l : localSourceCodeUpdateFilePathList) {
			if(l.endsWith(".cs")) {
				dllPath = findTargetDll(l, allDLLFileNames);
				if(!dllFilePathList.contains(dllPath)) {
					dllFilePathList.add(dllPath);
				}
			}
		}
		
		return dllFilePathList;
	}
	
	private static List<String> filterTargetPDBFilePath(List<String> localSourceCodeUpdateFilePathList, List<String> allDLLFileNames, List<String> dllFilePathList) {
		String dllPath = null;
		List<String> pdbFilePathList = new ArrayList<String>();
		
		String tmpPdbFilePath = null;
		for(String l : localSourceCodeUpdateFilePathList) {
			if(l.endsWith(".cs")) {
				dllPath = findTargetDll(l, allDLLFileNames);
				if(dllFilePathList.contains(dllPath)) {
					if(!l.contains("平台代码")) {
						tmpPdbFilePath = findTargetPdbFile(dllPath);
						if(tmpPdbFilePath != null && !pdbFilePathList.contains(tmpPdbFilePath)) {
							pdbFilePathList.add(tmpPdbFilePath);
						}
					}
				}
			}
		}
		
		return pdbFilePathList;
	}

	private static List<String> filterFrontSideFilePath(List<String> localSourceCodeUpdateFilePathList) {
		List<String> frontSideFilePathList = new ArrayList<String>();
		for(String l : localSourceCodeUpdateFilePathList) {
			if(!l.endsWith(".cs")) {
				if(!frontSideFilePathList.contains(l)) {
					l = l.replaceAll("\\\\", "/").replaceAll(localPathPrefix + "/OT.CNT/OT.WebSite", outputPackPath);
					frontSideFilePathList.add(l);
				}
			}
		}
		
		return frontSideFilePathList;
	}
}
