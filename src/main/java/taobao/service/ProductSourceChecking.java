package taobao.service;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyAdapter;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import toolPack.ioHandle.FileUtilCustom;

@SuppressWarnings("deprecation")
public class ProductSourceChecking {

	private static Robot robot = null;
	private static Clipboard clipboard = null;
	private static Integer nextPageLoadingDelay = 3000;
	private static Double randomDelayRate = 3D;
	private static final Random random = new Random();// === 新增：用于控制暂停/继续的变量 ===
	private static volatile boolean isPaused = false;
	private static boolean isCtrlPressed = false;
	private static final Pattern PRODUCT_ID_PATTERN = Pattern.compile("Product ID:\\s*([A-Za-z0-9_-]+)");

	public static void main(String[] args) {
		initGlobalKeyListener();
		SkuDetailFileMoving.moveNewDetailToOld();
		SkuFileCleaner.main(args);
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		System.out.println("脚本已启动，等待 3 秒以便你切换到目标窗口...");
		System.out.println("【提示】运行过程中随时可以按 [Ctrl + F8] 暂停或继续任务。");
		robot.delay(3000); // 延迟 3 秒，留出时间让你把光标放到输入框

		List<String> sourceIdList = null;
		sourceIdList = SourceIdList.getIdList();
		handleChecking(sourceIdList);

		sourceIdList = getFailedCaseSourceIdList();
		if (sourceIdList != null && sourceIdList.size() > 0) {
			handleChecking(sourceIdList);
		}

		RobotScript.openNewTab(robot);
		RobotScript.openNewTab(robot);

		// 任务结束后注销全局监听
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化全局键盘监听
	 */
	private static void initGlobalKeyListener() {
		// 关闭 JNativeHook 的日志输出，避免控制台被刷屏
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(false);

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("注册全局键盘钩子失败: " + ex.getMessage());
			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new NativeKeyAdapter() {
			@Override
			public void nativeKeyPressed(NativeKeyEvent e) {
				if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL || e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
					isCtrlPressed = true;
				}
				// 检测 Ctrl + F8
				if (isCtrlPressed && e.getKeyCode() == NativeKeyEvent.VC_F8) {
					isPaused = !isPaused;
					if (isPaused) {
						System.out.println("\n >>> 【任务已暂停】 (按 Ctrl + F8 继续) <<< \n");
					} else {
						System.out.println("\n >>> 【任务已继续】 <<< \n");
					}
				}
			}

			@Override
			public void nativeKeyReleased(NativeKeyEvent e) {
				if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL || e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
					isCtrlPressed = false;
				}
			}
		});
	}

	/**
	 * 检查暂停状态：如果处于暂停状态，则让当前线程进入无限等待直到解除暂停
	 */
	private static void checkPaused() {
		while (isPaused) {
			try {
				Thread.sleep(500); // 每 0.5 秒检查一次状态，释放 CPU
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
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

	private static void handleChecking(List<String> sourceIdList) {
		// 2. 如果 randomFlag 为 true，则对 sourceIdList 进行随机打乱
		if (TaobaoOption.randomFlag) {
			Collections.shuffle(sourceIdList, random);
			System.out.println("已开启随机顺序执行模式 (randomFlag = true)");
		}

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 将文本封装成 StringSelection 对象
		StringSelection stringSelection = null;

		String sourceIdStr = null;
//		for (int i = 0; i < sourceIdList.size(); i++) {
		for (int i = TaobaoOption.start; i < TaobaoOption.end && i < sourceIdList.size(); i++) {
			checkPaused();

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

			checkPaused();
			try {
				// 等待下载
				robot.delay(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			SkuDetailFileMoving.moveToOptionFile();
			robot.delay(1000);
			msg = SkuDataHandler.compareSkuDetail(sourceIdStr);
			if (msg != null) {
				saveDetailFile(sourceIdStr, msg);
			}

			checkPaused();
			randomizedDelay(robot, nextPageLoadingDelay);
		}
	}

	private static List<String> getFailedCaseSourceIdList() {
		Path dirPath = Paths.get(TaobaoOption.UPDATE_INFO.toString()); // 例如: Paths.get("D:/logs/products")
		List<String> productIds = new ArrayList<>();
		;
		try {
			productIds = extractProductIds(dirPath, false, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("共提取数量: " + productIds.size());
		productIds.forEach(System.out::println);
		return productIds;
	}

	/**
	 * 从指定目录的 .txt 文件中提取 Product ID
	 *
	 * @param directory   目标目录
	 * @param recursive   是否递归子目录
	 * @param deduplicate 是否去重（保留首次出现顺序）
	 * @return Product ID 列表
	 */
	public static List<String> extractProductIds(Path directory, boolean recursive, boolean deduplicate)
			throws IOException {
		if (!Files.exists(directory) || !Files.isDirectory(directory)) {
			throw new IllegalArgumentException("目录不存在或不是有效文件夹: " + directory);
		}

		int maxDepth = recursive ? Integer.MAX_VALUE : 1;
		Set<String> uniqueIds = new LinkedHashSet<>();
		List<String> orderedIds = new ArrayList<>();

		try (Stream<Path> walk = Files.walk(directory, maxDepth)) {
			List<Path> txtFiles = walk.filter(Files::isRegularFile)
					.filter(p -> p.getFileName().toString().toLowerCase().endsWith(".txt")).toList();

			for (Path file : txtFiles) {
				extractFromFile(file, deduplicate ? uniqueIds : null, orderedIds);
			}
		}

		return deduplicate ? new ArrayList<>(uniqueIds) : orderedIds;
	}

	private static void extractFromFile(Path file, Set<String> uniqueIds, List<String> orderedIds) {
		// 使用 BufferedReader 按行读取，避免大文件一次性加载撑爆内存
		try (var reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.contains("新 SKU 信息文件未找到或为")) {
					continue;
				}
				Matcher matcher = PRODUCT_ID_PATTERN.matcher(line);
				while (matcher.find()) {
					String id = matcher.group(1);
					if (uniqueIds != null) {
						uniqueIds.add(id);
					} else {
						orderedIds.add(id);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("读取文件失败: " + file + "，原因: " + e.getMessage());
		}
	}
}
