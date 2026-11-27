package taobao.service;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import taobao.pojo.type.TaobaoSkuNameBuildingType;
import toolPack.ioHandle.FileUtilCustom;

public class TaobaoSkuNameBuilding {

	private static List<String> list = new ArrayList<>();
	private static int delay = 200;
	private static TaobaoSkuNameBuildingType workType = TaobaoSkuNameBuildingType.CREAM_GLUE_EDITING;
	private static boolean numberFlag = false;
	// 将文本封装成 StringSelection 对象
	private static StringSelection stringSelection = null;
	private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	private static Robot robot = null;

	private static int mouseX = 0;
	private static int mouseY = 0;
	private static Point point = MouseInfo.getPointerInfo().getLocation();
	private static int startIndex = 0;

	public static void main(String[] args) throws InterruptedException {
		getLines();
		copying();
	}

	private static void getLines() {
		FileUtilCustom ioUtil = new FileUtilCustom();
		String content = ioUtil.getStringFromFile(System.getProperty("user.home") + "/tmp/tmp.txt");
		String[] linesArray = content.split(System.lineSeparator());

		for (int i = 0; i < linesArray.length; i++) {
			String line = linesArray[i];
			if (StringUtils.isNotBlank(line)) {
				if (line.startsWith("¥") || line.startsWith("库存") || line.equals("0")) {
					continue;
				} else {
					list.add(line);
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			String line = list.get(i);
			if (!line.contains("#") && numberFlag) {
				line = i + "# " + line;
			}
			list.set(i, line);
		}
		System.out.println(list);
	}

	private static boolean hadMove() {
		boolean flag = (point.x == mouseX) && (point.y == mouseY);
		updateMouseLocation(point.x, point.y);
		return !flag;
	}

	private static void updateMouseLocation(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	private static void copying() throws InterruptedException {
		try {
			// 创建 Robot 实例
			robot = new Robot();
			point = MouseInfo.getPointerInfo().getLocation();
			updateMouseLocation(point.x, point.y);
			System.out.println("脚本已启动，等待 3 秒以便你切换到目标窗口...");
			robot.delay(3000); // 延迟 3 秒，留出时间让你把光标放到输入框

			for (int i = startIndex; i < list.size(); i++) {
				point = MouseInfo.getPointerInfo().getLocation();
				if (hadMove()) {
					System.out.println("Had moved, return");
					return;
				}

				System.out.println((i + 1) + "/" + list.size());

				if (workType == TaobaoSkuNameBuildingType.BEAD_CREATING
						|| workType == TaobaoSkuNameBuildingType.BEAD_EDITING) {
					beadMode(list.get(i));
				} else if (workType == TaobaoSkuNameBuildingType.CREAM_GLUE_EDITING) {
					creamGluedMode(list.get(i), i);
				}

			}

			System.out.println("操作完成！");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void beadMode(String skuname) {
		stringSelection = new StringSelection(skuname);
		clipboard.setContents(stringSelection, null);
		System.out.println("成功将文本放入剪贴板：" + skuname);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(delay); // 每次点击之间稍微停顿 200 毫秒，防止速度过快系统反应不过来
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(delay);

		// 循环按 4 次 TAB 键
		for (int tabCounting = 0; tabCounting < 4; tabCounting++) {
			robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
			robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
			robot.delay(delay);
		}

		if (workType == TaobaoSkuNameBuildingType.BEAD_CREATING) {
			System.out.println("create mode");
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.delay(delay);

			robot.keyPress(KeyEvent.VK_SHIFT); // 按下 SHIFT 键
			robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
			robot.delay(delay);
			robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
			robot.delay(delay);
			robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
			robot.delay(delay);
			robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
			robot.keyRelease(KeyEvent.VK_SHIFT);
		} else {
			System.out.println("edit mode");
		}
	}

	private static void creamGluedMode(String skuname, int index) {
		stringSelection = new StringSelection(skuname);
		clipboard.setContents(stringSelection, null);
		System.out.println("成功将文本放入剪贴板：" + skuname);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(delay); // 每次点击之间稍微停顿 200 毫秒，防止速度过快系统反应不过来
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(delay);

		robot.keyPress(KeyEvent.VK_SHIFT); // 按下 SHIFT 键
		robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
		robot.keyRelease(KeyEvent.VK_SHIFT);// 释放 SHIFT 键
		robot.delay(delay);

		// 第一个SKU要多按一次tab
		if (index == 0) {
			robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
			robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
			robot.delay(delay);
		}
		// 循环按 3 次 TAB 键
		for (int tabCounting = 0; tabCounting < 3; tabCounting++) {
			robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
			robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
			robot.delay(delay);
		}

		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
		robot.delay(delay);

		robot.keyPress(KeyEvent.VK_SHIFT); // 按下 SHIFT 键
		robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
		robot.delay(delay);
//		robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
//		robot.delay(delay);
//		robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
//		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_SHIFT);// 释放 SHIFT 键
		robot.delay(delay);

	}
}
