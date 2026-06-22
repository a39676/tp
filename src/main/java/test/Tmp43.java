package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Tmp43 {

	static List<String> list = new ArrayList<>();
	static {
		list.add("26# 西瓜晶色");
		list.add("27# 玫粉色");
		list.add("28# 浅紫色");
		list.add("29# 蓝紫色");
		list.add("30# 紫蓝色");
		list.add("31# 紫色");
		list.add("32# 葡萄紫");
		list.add("33# 透紫色");
		list.add("34# 酒红色");
		list.add("35# 红色");
		list.add("36# 橙红色");
		list.add("37# 玛瑙红");
		list.add("38# 朱砂大红");
		list.add("39# 朱砂红");
		list.add("40# 朱砂深红");
		list.add("41# 淡绿色");
		list.add("42# 浅绿");
		list.add("43# 青绿色");
		list.add("44# 碧玉色");
		list.add("45# 东陵玉浅色");
		list.add("46# 东陵玉中色");
		list.add("47# 东陵玉深色");
		list.add("48# 和田玉");
		list.add("49# 绿色");
		list.add("50# 玛瑙绿");
		list.add("51# 军绿");
	}

	public static void main(String[] args) {
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
				robot.delay(200); // 每次点击之间稍微停顿 200 毫秒，防止速度过快系统反应不过来
				robot.keyRelease(KeyEvent.VK_V); 
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.delay(200); 
	            
				// 循环按 4 次 TAB 键
				for (int tabCounting = 0; tabCounting < 4; tabCounting++) {
					robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
					robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键

					robot.delay(200); // 每次点击之间稍微停顿 200 毫秒，防止速度过快系统反应不过来
					System.out.println("已发送第 " + (tabCounting + 1) + " 次 TAB");
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
	            robot.keyRelease(KeyEvent.VK_SPACE);
	            robot.delay(200);

				robot.keyPress(KeyEvent.VK_SHIFT); // 按下 SHIFT 键
				robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
				robot.delay(200); // 每次点击之间稍微停顿 200 毫秒，防止速度过快系统反应不过来
				robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
				robot.delay(200); // 每次点击之间稍微停顿 200 毫秒，防止速度过快系统反应不过来
				robot.keyPress(KeyEvent.VK_TAB); // 按下 TAB 键
				robot.delay(200); // 每次点击之间稍微停顿 200 毫秒，防止速度过快系统反应不过来
				robot.keyRelease(KeyEvent.VK_TAB); // 释放 TAB 键
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}

			System.out.println("操作完成！");

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
