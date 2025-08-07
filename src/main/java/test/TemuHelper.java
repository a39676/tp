package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.google.common.io.Files;

public class TemuHelper {

	private static final String infoExcelSuffixName = ".xls";
	private static final String mainFolderStr = System.getProperty("user.home") + "/auxiliary/pf/temu/products";
	private static final String targetDateFolderStr = "20250806_1_1688";
	@SuppressWarnings("unused")
	private static final String subFolderName = "霸王龙骨头";
	private static List<String> imageSuffixList = new ArrayList<>();
	static {
		imageSuffixList.add("jpg");
		imageSuffixList.add("jpeg");
		imageSuffixList.add("png");
		imageSuffixList.add("webp");
	}

	// TODO 1.从excel 中直接获取货名, 重命名文件夹; 2.将货品文件夹转移到月份文件夹内 
	public static void main(String[] args) {
		TemuHelper t = new TemuHelper();
		t.renameImgAndExcelFiles();
//		t.renameProductFolder();  // TODO 更新后未测试
//		t.convertSkcBarcodePdfToImageByDateFolder();
//		t.convertSkcBarcodePdfToImage(mainFolderStr + "/" + targetDateFolderStr + "/" + subFolderName);
	}

	public void convertSkcBarcodePdfToImage(String targetFolderStr) {
		File folder = new File(targetFolderStr);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			File tmpFile = files[i];
			String tmpFileName = tmpFile.getName();
			if (tmpFileName.endsWith(".pdf") && tmpFileName.startsWith("skc")) {
				String imageFilePathStr = targetFolderStr + "/" + tmpFileName.replaceAll("pdf", "png");
				File destinationFile = new File(imageFilePathStr);
				try {
					PDDocument document = PDDocument.load(tmpFile);
					@SuppressWarnings("unchecked")
					List<PDPage> pdfPageList = document.getDocumentCatalog().getAllPages();
					// Only convert page 1 here
					for (int pageNumber = 0; pageNumber < pdfPageList.size() && pageNumber < 1; pageNumber++) {
						PDPage page = pdfPageList.get(pageNumber);
						BufferedImage image = page.convertToImage();
						System.out.println("Image Created -> " + destinationFile.getAbsolutePath());
						ImageIO.write(image, "png", destinationFile);
						pageNumber++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void convertSkcBarcodePdfToImageByDateFolder() {
		String targetFolderStr = mainFolderStr + "/" + targetDateFolderStr;
		File targetDateFolder = new File(targetFolderStr);
		File[] files = targetDateFolder.listFiles();
		for (int i = 0; i < files.length; i++) {
			File tmpFile = files[i];
			if (!tmpFile.isDirectory()) {
				continue;
			}
			convertSkcBarcodePdfToImage(tmpFile.getAbsolutePath());
		}
	}

	public void renameImgAndExcelFiles() {
		String dateFolderStr = mainFolderStr + "/" + targetDateFolderStr;
		File folders = new File(dateFolderStr);
		File[] files = folders.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (!file.isDirectory()) {
				continue;
			}
			renameImgAndExcelFiles(file.getAbsolutePath());
		}
	}

	public void renameImgAndExcelFiles(String targetFolderStr) {
		boolean handleImg = true;
//		String targetFolderStr = mainFolderStr + "/" + targetDateFolderStr + "/" + subFolderName;
		File targetFolder = new File(targetFolderStr);
		File[] files = targetFolder.listFiles();
		List<File> targetImageFileList = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			String fileName = file.getName();
			System.out.println(file.getName());
			if (fileName.endsWith(infoExcelSuffixName)) {
				file.renameTo(new File(file.getParentFile() + "/" + targetFolder.getName() + "_info.xls"));
			} else if (!fileName.contains("size") && isEndWithImageSuffix(fileName) != null) {
				if (handleImg) {
					targetImageFileList.add(file);
				}
			}
		}

		// rename image files
		for (int i = 0; i < targetImageFileList.size(); i++) {
			File oldFile = targetImageFileList.get(i);
			System.out.println(oldFile.getAbsolutePath());
			String suffixName = isEndWithImageSuffix(oldFile.getName());
			File newFile = new File(targetFolderStr + "/" + i + "." + suffixName);
			try {
				Files.copy(oldFile, newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		for (int c = 1; c < pcsList.size(); c++) {
//			File oldFile = targetImageFileList.get(0);
//			String suffixName = isEndWithImageSuffix(oldFile.getName());
//			File newFile = new File(targetFolderStr + "/" + pcsList.get(c) + "_" + 0 + "." + suffixName);
//			try {
//				Files.copy(oldFile, newFile);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		// move backup files
		File backupFolder = new File(targetFolderStr + "/backup");
		backupFolder.mkdirs();
		for (int i = 0; i < targetImageFileList.size(); i++) {
			File oldFile = targetImageFileList.get(i);
			File newFile = new File(backupFolder + "/" + oldFile.getName());
			try {
				Files.move(oldFile, newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String isEndWithImageSuffix(String filename) {
		for (int i = 0; i < imageSuffixList.size(); i++) {
			if (filename.endsWith(imageSuffixList.get(i))) {
				return imageSuffixList.get(i);
			}
		}
		return null;
	}

	public void renameProductFolder() {
		String preStr = targetDateFolderStr.substring(0, 10);
		String dateEndWithMonthStr = targetDateFolderStr.substring(0, 6);
//		TODO 未测试
		File dataFolder = new File(mainFolderStr + "/" + targetDateFolderStr);
		File[] files = dataFolder.listFiles();
		for (int i = 0; i < files.length; i++) {
			File sourceFile = files[i];
			if (sourceFile.isFile()) {
				continue;
			}
			if (sourceFile.getName().startsWith(preStr)) {
				continue;
			}
			File newFolder = new File(
					mainFolderStr + "/" + dateEndWithMonthStr + "/" + preStr + "_" + sourceFile.getName());
			try {
				FileUtils.moveDirectory(sourceFile, newFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
