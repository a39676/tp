package demo;

import lc.kra.system.keyboard.GlobalKeyboardHook;

public class WritingHistory {

	public static void main(String[] args) {
		WritingHistory w = new WritingHistory();
		w.run();
	}

	public void run() {
		boolean run = true;

		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // Use false here to switch to hook instead of

//		System.out.println(
//				"Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
//		for (Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet()) {
//			System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
//		}

		keyboardHook.addKeyListener(new CustomGlobalKeyAdapter());

		try {
			while (run) {
				Thread.sleep(128);
			}
		} catch (InterruptedException e) {
			// Do nothing
		} finally {
			keyboardHook.shutdownHook();
		}
	}

}
