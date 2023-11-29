package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Tmp29 {

	public static void main(String[] args) {
		File mainFolder = new File("./");
		System.out.println(mainFolder.getAbsolutePath());

		List<File> subFiles = new ArrayList<>(Arrays.asList(mainFolder.listFiles()));
		List<String> folderNameList = new ArrayList<>();
		for (int i = 0; i < subFiles.size(); i++) {
			if (subFiles.get(i).isDirectory()) {
				folderNameList.add(subFiles.get(i).getName());
			}
		}

		boolean contains = folderNameList.contains("ATTool") && folderNameList.contains("brt");

		if (!contains) {
			return;
		}

		List<String> deleteingPart = new ArrayList<>(Arrays.asList(".settings", ".mvn", "target"));

		for (int i = 0; i < subFiles.size(); i++) {
			for (int j = 0; j < deleteingPart.size(); j++) {
				File subFile = subFiles.get(i);
				String path = subFile.getAbsolutePath() + "/" + deleteingPart.get(j);
				System.out.println(path);
				File tmpFile = new File(path);
				if (tmpFile.isDirectory()) {
					try {
						FileUtils.deleteDirectory(tmpFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					tmpFile.delete();
				}
			}
		}
	}
}
