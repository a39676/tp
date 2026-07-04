package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import toolPack.ioHandle.FileUtilCustom;

public class Tmp43 {

	static List<String> list = new ArrayList<>();
	static int delay = 135;
	static boolean createMode = true;

	public static void main(String[] args) {
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
				list.add(line);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			String line = list.get(i);
			if (!line.contains("#")) {
				line = i + "# " + line;
			}
			list.set(i, line);
		}
		System.out.println(list);
	}

	private static void copying() {
		try {
			// 创建 Robot 实例
			Robot robot = new Robot();

			System.out.println("脚本已启动，等待 3 秒以便你切换到目标窗口...");
			robot.delay(3000); // 延迟 3 秒，留出时间让你把光标放到输入框

			// 1. 将文本封装成 StringSelection 对象
			StringSelection stringSelection = null;

			// 2. 获取系统剪贴板
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

			for (int i = 0; i < list.size(); i++) {
				stringSelection = new StringSelection(list.get(i));
				// 3. 将文本设置到剪贴板中（第二个参数通常传入 null）
				clipboard.setContents(stringSelection, null);
				System.out.println("成功将文本放入剪贴板：" + list.get(i));
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

				if (createMode) {
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

			System.out.println("操作完成！");

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
