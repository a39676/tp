package taobao.service;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class ProductSourceChecking {

	private static Robot robot = null;
	private static Clipboard clipboard = null;

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
		for (int i = 0; i < 10; i++) {
			System.out.println("Index: " + (i + 1) + "/" + sourceIdList.size());
			sourceIdStr = sourceIdList.get(i);
			System.out.println(sourceIdStr);
			RobotScript.downloadSkuDetailFile(robot, clipboard, stringSelection, sourceIdStr);
			try {
				robot.delay(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			SkuDetailFileMoving.moveToOptionFile();
			SkuDataHandler.compareSkuDetail(sourceIdStr);
		}
	}
}
