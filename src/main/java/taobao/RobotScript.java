package taobao;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class RobotScript {

	private static Integer normalDelay = 500;
	private static Integer pageLoadingDelay = 10000;
	private static final Random random = new Random();
	
	/**
	 * 带有随机浮动（0% ~ 20%）的延时方法
	 * @_param baseDelay 基础延时（毫秒）
	 */
	private static void randomizedDelay(Robot robot, int baseDelay) {
		// 计算 0% 到 20% 的额外随机毫秒数
		int additionalDelay = (int) (baseDelay * random.nextDouble() * 0.20);
		int finalDelay = baseDelay + additionalDelay;
		robot.delay(finalDelay);
	}

	public static void handler(Robot robot, Clipboard clipboard, StringSelection stringSelection, String sourceIdStr) {
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

////		 到地址栏
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_L);
//		randomizedDelay(robot, normalDelay);
//		robot.keyRelease(KeyEvent.VK_L);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		randomizedDelay(robot, normalDelay);
//
//		// 更新 URL 到剪贴板
//		stringSelection = new StringSelection("https://detail.1688.com/offer/" + sourceIdStr + ".html");
//		clipboard.setContents(stringSelection, null);
//
//		// ctrl + a v
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_A);
//		randomizedDelay(robot, normalDelay);
//		robot.keyRelease(KeyEvent.VK_A);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		randomizedDelay(robot, normalDelay);
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_V);
//		randomizedDelay(robot, normalDelay);
//		robot.keyRelease(KeyEvent.VK_V);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		randomizedDelay(robot, normalDelay);
//		// enter
//		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.keyRelease(KeyEvent.VK_ENTER);
//		randomizedDelay(robot, pageLoadingDelay); // 等待页面加载
//
//		// 鼠标移动到 "复制SKU"
//		robot.mouseMove(770, 295);
//		randomizedDelay(robot, normalDelay);
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		randomizedDelay(robot, pageLoadingDelay); // 等待"复制SKU"列表子页面加载
//
//		// 勾选所有SKU
//		robot.mouseMove(245, 275);
//		randomizedDelay(robot, normalDelay);
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		randomizedDelay(robot, normalDelay);
//
//		// 点击"复制导出"
//		robot.mouseMove(1240, 200);
//		randomizedDelay(robot, normalDelay);
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		randomizedDelay(robot, normalDelay);
//
//		// 点击"导出CSV"
		robot.mouseMove(1050, 270);
//		randomizedDelay(robot, normalDelay);
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		randomizedDelay(robot, normalDelay);
//
//		// 点击"空白处"
//		robot.mouseMove(30, 150);
//		randomizedDelay(robot, normalDelay);
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		randomizedDelay(robot, normalDelay);
	}
}
