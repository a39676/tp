package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ImageToPDF2 {

	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
		String sourceFolderPathStr = null;
		sourceFolderPathStr = "C:\\Users\\daven\\tmp\\englishBook\\tmp";
		File root = new File(sourceFolderPathStr);
		String outputFile = "output.pdf";
		List<String> filesPathStrList = new ArrayList<String>();
		File[] files = root.listFiles();
		for (int i = 0; i < files.length - 1; i++) {
			File f = new File(sourceFolderPathStr + "/" + String.valueOf(i + 1) + ".jpg");
			filesPathStrList.add(f.getAbsolutePath());
			System.out.println("Find: " + f.getAbsolutePath());
		}

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
		document.open();
		for (int i = 0; i < filesPathStrList.size(); i++) {
			String filePathStr = filesPathStrList.get(i);
//			String filePathStr = sourceFolderPathStr + "/" + i + ".jpg";
			System.out.println("Add image: " + filePathStr);
			document.newPage();
			Image image = Image.getInstance(filePathStr);
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
