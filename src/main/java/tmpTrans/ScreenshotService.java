package tmpTrans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import at.screenshot.pojo.constant.ScreenshotConstant;
import at.screenshot.pojo.dto.TakeScreenshotSaveDTO;
import at.screenshot.pojo.result.ScreenshotSaveResult;
import toolPack.constant.FileSuffixNameConstant;
import toolPack.dateTimeHandle.DateTimeUtilCommon;
import toolPack.dateTimeHandle.LocalDateTimeHandler;

/**
 * 
 * please set screenshow saving foler path before use
 * 
 */
public class ScreenshotService {
	
	public ScreenshotSaveResult screenshotSave(TakeScreenshotSaveDTO dto, String targetFolderPath, String fileNamePrefix) {
		ScreenshotSaveResult result = new ScreenshotSaveResult();
		
		try {
			File f = takeScreenshotFile(dto);
			File targetFolder = new File(targetFolderPath);
			if(!targetFolder.exists()) {
				targetFolder.mkdirs();
			}
			LocalDateTimeHandler localDateTimeHadler = new LocalDateTimeHandler();
			String targetFileName = 
					String.format(
							ScreenshotConstant.screenShotFilenameFormat, 
							fileNamePrefix, 
							localDateTimeHadler.dateToStr(LocalDateTime.now(), DateTimeUtilCommon.dateTimeFormatNoSymbol), 
							FileSuffixNameConstant.PNG);
			String targetFilePath = targetFolderPath + File.separator + targetFileName;
			FileUtils.copyFile(f, new File(targetFilePath));
			
			result.setIsSuccess();
			result.setSavingPath(targetFilePath);
			result.setFileName(targetFileName);
		} catch (Exception e) {
			e.printStackTrace();
			result.failWithMessage(e.toString());
		}
		
		return result;
	}
	
	public File takeScreenshotFile(TakeScreenshotSaveDTO dto) throws IOException {
		// Get entire page screenshot
		File screenshot = ((TakesScreenshot) dto.getDriver()).getScreenshotAs(OutputType.FILE);
		
		if(dto.getStartX() != null && dto.getStartY() != null && dto.getEndX() != null && dto.getEndY() != null) {
			BufferedImage fullImg = ImageIO.read(screenshot);
			
			// Get width and height of the element
			int eleWidth = dto.getEndX() - dto.getStartX();
			int eleHeight = dto.getEndY() - dto.getStartY();
			
			// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot = fullImg.getSubimage(dto.getStartX(), dto.getStartY(), eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, FileSuffixNameConstant.PNG, screenshot);
						
		} else if(dto.getEle() != null) {
			BufferedImage fullImg = ImageIO.read(screenshot);
			
			Point point = dto.getEle().getLocation();
			
			// Get width and height of the element
			int eleWidth = dto.getEle().getSize().getWidth();
			int eleHeight = dto.getEle().getSize().getHeight();
			
			// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, FileSuffixNameConstant.PNG, screenshot);
		}
		
		return screenshot;
	}
}
