package tmpTrans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import at.report.pojo.dto.WordReportDTO;

public class ATWordReportService {
	
	public void appendReport(WordReportDTO dto) {
		appendReport(dto.getWordDocument(), dto.getContent(), dto.getImgPath());
	}
	
	public void appendImage(WordReportDTO dto) {
		appendReport(dto.getWordDocument(), null, dto.getImgPath());
	}

	private void appendReport(XWPFDocument document, String content, String imagePath) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
		String formatDateTimeStr = LocalDateTime.now().format(formatter);

		if (document == null) {
			document = new XWPFDocument();
		}

		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();

		if (StringUtils.isNotBlank(content)) {
			run.setText(formatDateTimeStr + " " + content);
			run.addBreak();
		}

		if (StringUtils.isNotBlank(imagePath)) {
			FileInputStream is = null;
			try {
				is = new FileInputStream(imagePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			if (is != null) {
				try {
					BufferedImage bimg = ImageIO.read(new File(imagePath));
					Double width = Double.parseDouble(String.valueOf(bimg.getWidth()));
					Double height = Double.parseDouble(String.valueOf(bimg.getHeight()));
					if(width > 500 || height > 500) {
						Double f = 500 / width;
						width = width * f;
						height = height * f;
					}
					run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imagePath, Units.toEMU(width), Units.toEMU(height));
					System.out.println("report append image: " + formatDateTimeStr + " " + imagePath);
					run.addBreak();
					is.close();
				} catch (InvalidFormatException | IOException e) {
					e.printStackTrace();
				}
			}
			
		}

	}

	public void outputReport(WordReportDTO dto) {
//		long minutes = ChronoUnit.MINUTES.between(te.getCreateTime(), LocalDateTime.now());
//		appendReport(document, "测试完成, 耗时: " + minutes + " 分钟");
		
		FileOutputStream fos = null;
		try {
			File reportFolder = new File(dto.getOutputReportPath());
			if(!reportFolder.exists() || !reportFolder.isDirectory()) {
				reportFolder.mkdirs();
			}
			fos = new FileOutputStream(dto.getOutputReportPath() + File.separatorChar + dto.getReportFileName() + ".docx");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if(fos != null) {
			try {
				dto.getWordDocument().write(fos);
				fos.close();     
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
