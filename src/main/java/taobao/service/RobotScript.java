package taobao.service;

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
	private static Double randomDelayRate = 0.33;
	private static final Random random = new Random();

	/**
	 * 带有随机浮动（0% ~ 20%）的延时方法
	 * 
	 * @_param baseDelay 基础延时（毫秒）
	 */
	private static void randomizedDelay(Robot robot, int baseDelay) {
		// 计算 0% 到 20% 的额外随机毫秒数
		int additionalDelay = (int) (baseDelay * random.nextDouble() * randomDelayRate);
		int finalDelay = baseDelay + additionalDelay;
		robot.delay(finalDelay);
	}

	public static void downloadSkuDetailFile2(Robot robot, Clipboard clipboard, StringSelection stringSelection,
			String sourceIdStr) {
		robot.mouseMove(TaobaoOption.closeTagBtn.getX(), TaobaoOption.closeTagBtn.getY());
	}

	public static void downloadSkuDetailFile(Robot robot, Clipboard clipboard, StringSelection stringSelection,
			String sourceIdStr) {
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

//		 到地址栏
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_L);
		randomizedDelay(robot, normalDelay);
		robot.keyRelease(KeyEvent.VK_L);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		randomizedDelay(robot, normalDelay);

		// 更新 URL 到剪贴板
		stringSelection = new StringSelection("https://detail.1688.com/offer/" + sourceIdStr + ".html");
		clipboard.setContents(stringSelection, null);

		// ctrl + a v
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		randomizedDelay(robot, normalDelay);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		randomizedDelay(robot, normalDelay);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		randomizedDelay(robot, normalDelay);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		randomizedDelay(robot, normalDelay);
		// enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		randomizedDelay(robot, pageLoadingDelay); // 等待页面加载

		// 鼠标移动到 "复制SKU"
		robot.mouseMove(TaobaoOption.copySkuBtn.getX(), TaobaoOption.copySkuBtn.getY());
		randomizedDelay(robot, normalDelay);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		randomizedDelay(robot, pageLoadingDelay); // 等待"复制SKU"列表子页面加载

		// 勾选所有SKU
		robot.mouseMove(TaobaoOption.allSkuCheckBox.getX(), TaobaoOption.allSkuCheckBox.getY());
		randomizedDelay(robot, normalDelay);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		randomizedDelay(robot, normalDelay);

		// 点击"复制导出"
		robot.mouseMove(TaobaoOption.exportBtn.getX(), TaobaoOption.exportBtn.getY());
		randomizedDelay(robot, normalDelay);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		randomizedDelay(robot, normalDelay);

		// 点击"导出文本"
		robot.mouseMove(TaobaoOption.exportAsTextBtn.getX(), TaobaoOption.exportAsTextBtn.getY());
		randomizedDelay(robot, normalDelay);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		randomizedDelay(robot, normalDelay);

		// 点击"空白处"
		robot.mouseMove(TaobaoOption.blankPlace.getX(), TaobaoOption.blankPlace.getY());
		randomizedDelay(robot, normalDelay);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		randomizedDelay(robot, normalDelay);

		// 30% 概率调用 scrollToEnd
		if (random.nextDouble() < 0.3) {
			robot.mouseMove(TaobaoOption.bottomRecomendBar.getX(), TaobaoOption.bottomRecomendBar.getY());
			randomizedDelay(robot, normalDelay);

			scrollToEnd(robot);

			randomizedDelay(robot, normalDelay);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			randomizedDelay(robot, pageLoadingDelay); // 等待页面加载

			// 尝试点击关闭标签, 多次位移点击 防止关闭按钮位移
			// 如果直接使用 CTRL + W 可能会在未打开新标签的情况下，关闭整个浏览器
			robot.mouseMove(TaobaoOption.closeTagBtn.getX(), TaobaoOption.closeTagBtn.getY());
			for (int i = 0; i < 15; i++) {
				randomizedDelay(robot, normalDelay);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseMove(TaobaoOption.closeTagBtn.getX() + i * 4, TaobaoOption.closeTagBtn.getY());
			}
		}
	}

	private static void scrollToEnd(Robot robot) {
		for (int i = 0; i < 18; i++) {
			robot.mouseWheel(28);
			randomizedDelay(robot, normalDelay);
		}
	}
}
