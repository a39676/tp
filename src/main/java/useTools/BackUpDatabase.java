package useTools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utils.constant.LocalEnvironmentConstant;

public class BackUpDatabase {
	
	public static void main(String[] args) throws Exception  {
		SqlTool tool = new SqlTool();
//		IOtools iot = new IOtools();
		SqlTool.setPropertiesFilePath(LocalEnvironmentConstant.memoryJoyPropertiesFile);
		
		// 导出数据
//		String outputPath = "d:/ssm/output";
		String outputPath = "d:/joyxyData/output";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		File newFolder = new File(outputPath + format1.format(cal.getTime()) + "/");
		newFolder.mkdir();
		tool.backupInfoToExcl(newFolder.getAbsolutePath() + "/");
		tool.backupInfoToTxt(newFolder.getAbsolutePath() + "/");
		
	}


}
