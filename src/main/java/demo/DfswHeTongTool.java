package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DfswHeTongTool {
	
	// 打包完成的输出路径
	private static final String outputPackPath = "d:/Cnt2";
	// 输出路径的 bin 目录
	private static final String outputBinPath = outputPackPath + "/bin";
	// 合同项目本地源码路径
	private static final String localPathPrefix = "D:/work/合同系统/03实现/001程序代码";
	// 此txt文档内, 需要输入 bugFree / Jira 中 需要更新的文件路径, 每行一个
	// bugFree / Jira 中的路径, 需要与 合同项目本地源码路径 有一定重合, 如:
	// D:/work/合同系统/03实现/001程序代码
	//           工程类/03实现/001程序代码/OT.CNT/OT.WebSite/Scripts/jOpf.js
	private static final String updateFilePath = "d:/tmp/updates.txt";
	// 增量更新文件目标路径
	private static final String copyToTargetFolderPath = "d:/ht/newFiles";
	
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
		System.out.println("本次更新的源码文件: ");
		for(String s : localSourceCodeUpdateFilePathList) {
			System.out.println(s);
		}
		System.out.println();
		
		System.out.println("本次更新 svn 指令行: ");
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
		
		File tmpFile = null;
		for(String l : backGroundFilePath) {
			subPath = l.replaceAll(outputBinPath, "/OT.WebSite/bin");
			copyTargetList.add(copyToTargetFolderPath + subPath);
			tmpFile = new File(l);
			if(!tmpFile.exists()) {
				copyCommondLine.add("文件: " + l + " 不存在, 请检查更新输入, 或在 vs2010 执行 \"发布\" 后再重新执行本程序");
			}
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
		
		System.out.println("本次更新 复制用 指令行: ");
		for(String s : copyCommondLine) {
			System.out.println(s);
		}
		System.out.println();
		
		System.out.println("本次更新影响文件: ");
		String numberStr = null;
		for(int i = 0; i < subPathList.size(); i++) {
			if(i < 10) {
				numberStr = "0" + i;
			} else {
				numberStr = String.valueOf(i);
			}
			System.out.println(numberStr + ": " + subPathList.get(i));
		}
		System.out.println();
	}

	private static List<String> findSourceFile() {
		Path path = Paths.get(updateFilePath);
		StringBuffer result = new StringBuffer();
		try {
			BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {
				result.append(currentLine + System.lineSeparator());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		List<String> lines = Arrays.asList(result.toString().split(System.lineSeparator()));
		return lines;
	}
	
	public String getStringFromFile(String filePath, String encodeType) {
		Path path = Paths.get(filePath);
		StringBuffer result = new StringBuffer();
		try {
			BufferedReader reader = Files.newBufferedReader(path, Charset.forName(encodeType));
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {// while there is content on the current line
				result.append(currentLine + System.lineSeparator());
			}
		} catch (IOException ex) {
			ex.printStackTrace(); // handle an exception here
		}
		return result.toString();
	}

	public String getStringFromFile(String filePath) {
		return getStringFromFile(filePath, StandardCharsets.UTF_8.displayName());
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
