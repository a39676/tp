package demo.cloudinary.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FilenameUtils;

public class RandomFileMove {
	
	private static final List<String> targetFileSuffix = Arrays.asList("jpg", "jpeg", "gif", "webm", "mpeg");
	
	public void randomFileMove(String sourceFolderPath, String targetFloderPath, int movingCount) throws IOException {
		
		File sourceFolder = new File(sourceFolderPath);
		File targetFolder = new File(targetFloderPath);
		if(!targetFolder.exists()) {
			targetFolder.mkdirs();
		} else {
			if(targetFolder.listFiles().length > 0) {
				return;
			}
		}
		List<File> files = Arrays.asList(sourceFolder.listFiles());
		
		int tmpIndex = 0;
		List<String> fileNameRecord = new ArrayList<String>();
		File tmpFile = null;
		String extension = null;
		for(int i = 0; i < movingCount && files.size() > 0; i++) {
			files = Arrays.asList(sourceFolder.listFiles());
			if(files.size() > 1) {
				tmpIndex = ThreadLocalRandom.current().nextInt(0, files.size() - 1);
			} else {
				tmpIndex = 0;
			}
			tmpFile = files.get(tmpIndex);
			extension = FilenameUtils.getExtension(tmpFile.getName());
			
			if(fileNameRecord.contains(tmpFile.getName()) || !targetFileSuffix.contains(extension)) {
				i--;
				continue;
			} else {
				System.out.println(tmpIndex + " moving " + "(" + (i + 1) + "/" + movingCount + ") : " + tmpFile.getName());
				fileNameRecord.add(tmpFile.getName());
				Files.move(Paths.get(tmpFile.getAbsolutePath()), Paths.get(targetFloderPath + tmpFile.getName()), StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}
}
