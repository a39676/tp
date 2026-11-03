package taobao;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;

public class FindChrome {

	private static final String DEFAULT_WINDOW_TITLE = "千牛商家工作台";
	private static String targetWindowTitle = null;

	public static void main(String[] args) {
		switchWindow(DEFAULT_WINDOW_TITLE);
	}

	public static boolean switchWindow(String windowTitle) {
		if (StringUtils.isBlank(windowTitle)) {
			windowTitle = DEFAULT_WINDOW_TITLE;
			targetWindowTitle = DEFAULT_WINDOW_TITLE;
		} else {
			targetWindowTitle = windowTitle;
		}

		// 构造 PowerShell 命令，利用 WScript.Shell 的 AppActivate 方法激活窗口
		String command = String.format(
				"powershell -Command \"$wshell = New-Object -ComObject WScript.Shell; $wshell.AppActivate('%s')\"",
				windowTitle);

		try {
			@SuppressWarnings("deprecation")
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			System.out.println("已尝试通过 PowerShell 切换窗口。");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return false;
		}

		return isTargetWindow();
	}

	// 用于保存找到的句柄
	private static HWND foundHwnd = null;

	public static HWND findWindowByKeyword() {
		foundHwnd = null;

		// 枚举所有顶级窗口
		User32.INSTANCE.EnumWindows(new WNDENUMPROC() {
			@Override
			public boolean callback(HWND hwnd, com.sun.jna.Pointer pointer) {
				char[] buffer = new char[512];
				User32.INSTANCE.GetWindowText(hwnd, buffer, 512);
				String title = Native.toString(buffer);

				// 只要窗口标题包含你的关键字（并且窗口是可见的）
				if (!title.isEmpty() && title.contains(targetWindowTitle) && User32.INSTANCE.IsWindowVisible(hwnd)) {
					foundHwnd = hwnd;
					return false; // 找到后停止枚举
				}
				return true; // 继续查找下一个
			}
		}, null);

		return foundHwnd;
	}

	public static boolean isTargetWindow() {

		try {
			Thread.sleep(1000L);
			// 1. 通过窗口标题查找窗口句柄 (HWND)
			HWND hwnd = findWindowByKeyword();

			// 获取当前窗口的实时标题以验证
			char[] buffer = new char[512];
			User32.INSTANCE.GetWindowText(hwnd, buffer, 512);
			String currentTitle = Native.toString(buffer);
			System.out.println("当前活动窗口标题: " + currentTitle);

			if (hwnd == null) {
				System.out.println("未找到指定窗口，请检查窗口标题是否正确。");
				return false;
			}

			// 2. 将窗口置于最前端并获取焦点
			User32.INSTANCE.SetForegroundWindow(hwnd);
			User32.INSTANCE.SetFocus(hwnd);

			// 3. 如果窗口处于最小化状态，将其恢复正常显示
			User32.INSTANCE.ShowWindow(hwnd, User32.SW_MAX);

			System.out.println("成功切换到目标窗口！");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
