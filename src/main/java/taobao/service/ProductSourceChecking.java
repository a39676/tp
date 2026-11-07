package taobao.service;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

import toolPack.ioHandle.FileUtilCustom;

public class ProductSourceChecking {

	private static Robot robot = null;
	private static Clipboard clipboard = null;
	private static Integer nextPageLoadingDelay = 3000;
	private static Double randomDelayRate = 3D;
	private static final Random random = new Random();

	public static void main(String[] args) {
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		System.out.println("脚本已启动，等待 3 秒以便你切换到目标窗口...");
		robot.delay(3000); // 延迟 3 秒，留出时间让你把光标放到输入框

		List<String> sourceIdList = SourceIdList.getIdList();

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 将文本封装成 StringSelection 对象
		StringSelection stringSelection = null;

		String sourceIdStr = null;
//		for (int i = 0; i < sourceIdList.size(); i++) {
		for (int i = TaobaoOption.start; i < TaobaoOption.end || i < sourceIdList.size(); i++) {
			System.out.println("Index: " + (i + 1) + "/" + sourceIdList.size());
			sourceIdStr = sourceIdList.get(i);
			System.out.println(sourceIdStr);
			boolean flag = RobotScript.downloadSkuDetailFile(robot, clipboard, stringSelection, sourceIdStr);
			String msg = "";
			if (!flag) {
				msg = "productID: " + sourceIdStr + ", 已消失";
				saveDetailFile(sourceIdStr, msg);
				continue;
			}
			try {
				robot.delay(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			SkuDetailFileMoving.moveToOptionFile();
			msg = SkuDataHandler.compareSkuDetail(sourceIdStr);
			if (msg != null) {
				saveDetailFile(sourceIdStr, msg);
			}
			randomizedDelay(robot, nextPageLoadingDelay);
		}

		RobotScript.openNewTab(robot);
		RobotScript.openNewTab(robot);
	}

	private static void saveDetailFile(String productId, String message) {
		FileUtilCustom ioUtil = new FileUtilCustom();
		File folder = new File(TaobaoOption.UPDATE_INFO.toString());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		ioUtil.byteToFile(message.getBytes(StandardCharsets.UTF_8),
				TaobaoOption.UPDATE_INFO.toString() + "/" + productId + ".txt");
	}

	/**
	 * 带有随机浮动的延时方法
	 * 
	 * @_param baseDelay 基础延时（毫秒）
	 */
	private static void randomizedDelay(Robot robot, int baseDelay) {
		int additionalDelay = (int) (baseDelay * random.nextDouble() * randomDelayRate);
		int finalDelay = baseDelay + additionalDelay;
		robot.delay(finalDelay);
	}
}
