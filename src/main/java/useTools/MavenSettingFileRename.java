package useTools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MavenSettingFileRename {

	private static boolean setMyFile = false;

	private static String mavenFolderPath = "D:/soft/apache-maven-3.6.0";
	private static String mavenConfFolderPath = mavenFolderPath + "/conf/";
	private static String mySettingFileName = "settingsUse.xml";
	private static String jobSettingFileName = "settingsPlatenogroup.xml";
	private static String normalSettingFileName = "settings.xml";
	
	private static String mySettingFilePath = mavenConfFolderPath + mySettingFileName;
	private static String jobSettingFilePath = mavenConfFolderPath + jobSettingFileName;
	private static String normalSettingFilePath = mavenConfFolderPath + normalSettingFileName;
	
	private static File mavenConfFolder = null;

	static {
		mavenConfFolder = new File(mavenConfFolderPath);
	}

	/**
	 * 随时切换maven的配置文件
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (!mavenConfFolder.exists() || mavenConfFolder.isFile()) {
			System.err.println("folder path error");
			return;
		}

//		setMyFile = true;
		if(setMyFile) {
			replaceFile(mySettingFilePath, normalSettingFilePath);
		} else {
			replaceFile(jobSettingFilePath, normalSettingFilePath);
		}

	}

	private static void replaceFile(String sourceFilePath, String targetFilePath) {
		try {
			File sourceFile = new File(sourceFilePath);
			Path targetPath = Paths.get(targetFilePath);
			File targetFile = new File(targetFilePath);
			Path originalPath = sourceFile.toPath();
			Files.copy(originalPath, targetPath, StandardCopyOption.REPLACE_EXISTING);

			if (targetFile.exists()) {
				Files.readAllLines(originalPath).equals(Files.readAllLines(targetPath));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
