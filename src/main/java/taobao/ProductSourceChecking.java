package taobao;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class ProductSourceChecking {

	private static Robot robot = null;
	private static Clipboard clipboard = null;

	public static void main(String[] args) {
		List<String> sourceIdList = SourceIdList.getIdList();

		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 将文本封装成 StringSelection 对象
		StringSelection stringSelection = null;

		String sourceIdStr = null;
		for (int i = 0; i < sourceIdList.size(); i++) {
//		for (int i = 3; i < 7; i++) {
			System.out.println("Index: " + (i + 1) + "/" + sourceIdList.size());
			sourceIdStr = sourceIdList.get(i);
			System.out.println(sourceIdStr);
			RobotScript.handler(robot, clipboard, stringSelection, sourceIdStr);
		}

	}

}
