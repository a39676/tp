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

public class Tmp33 {

	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
		File root = new File(
				"C:\\Users\\daven\\Downloads\\[藤子·F·不二雄][哆啦A梦大全集 数码全彩][青文][双页8K版 by milianaisu][20完]\\[藤子·F·不二雄][哆啦A梦大全集  数码全彩][青文][双页8K版 by milianaisu][20完]\\[藤子·F·不二雄][哆啦A梦大全集  数码全彩][青文][双页8K版 by milianaisu].Vol.04");
		String outputFile = "output.pdf";
		List<String> filesPathStrList = new ArrayList<String>();
		File[] files = root.listFiles();
		for (int i = 0; i < files.length ; i++) {
			File f = files[i];
			if (f.getName().endsWith("jpg")) {
				filesPathStrList.add(f.getAbsolutePath());
				System.out.println("Find: " + f.getAbsolutePath());
			}
		}

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
		document.open();
		for (String filePathStr : filesPathStrList) {
			System.out.println("Add image: " + filePathStr);
			document.newPage();
			Image image = Image.getInstance(filePathStr);
			image.setRotationDegrees(90);
			image.setBorderWidth(0);
			image.setAbsolutePosition(0, 0);
			image.scaleAbsolute(PageSize.A4.rotate());
			document.add(image);
		}
		document.close();
	}
}
