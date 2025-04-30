package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ImageToPDF {

	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
		String sourceFolderPathStr = null;
		sourceFolderPathStr = "C:\\Users\\daven\\nextG\\bookForRead\\comic\\[藤子·F·不二雄][哆啦A梦大全集 数码全彩][青文][双页8K版 by milianaisu][20完]\\[藤子·F·不二雄][哆啦A梦大全集  数码全彩][青文][双页8K版 by milianaisu].Vol.05";
//		sourceFolderPathStr = "C:\\Users\\daven\\nextG\\bookForRead\\english\\TheSeedThatGrew";
//		sourceFolderPathStr = "C:\\Users\\daven\\nextG\\textbook\\4\\english\\2";
		File root = new File(sourceFolderPathStr);
		String outputFile = "output.pdf";
		List<String> filesPathStrList = new ArrayList<String>();
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.getName().endsWith("jpg")) {
				filesPathStrList.add(f.getAbsolutePath());
				System.out.println("Find: " + f.getAbsolutePath());
			}
		}

		Collections.sort(filesPathStrList);

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
		document.open();
		for (int i = 0; i < filesPathStrList.size(); i++) {
			String filePathStr = filesPathStrList.get(i);
//			String filePathStr = sourceFolderPathStr + "/" + i + ".jpg";
			System.out.println("Add image: " + filePathStr);
			document.newPage();
			Image image = Image.getInstance(filePathStr);
			image.setRotationDegrees(90);
//			image.setBorderWidth(0);
//			image.setAbsolutePosition(25, 20);
			image.scaleAbsolute(PageSize.A5.rotate());
//			image.scaleAbsolute(PageSize.A4);
			image.scaleToFit(700, 750);
			document.add(image);
		}
		document.close();
	}
}
