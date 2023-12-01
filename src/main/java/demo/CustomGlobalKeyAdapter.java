package demo;

import java.util.HashMap;
import java.util.Map;

import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class CustomGlobalKeyAdapter extends GlobalKeyAdapter {

	private boolean shiftPressed = false;
	private boolean altPressed = false;
	private boolean ctrlPressed = false;
	private static Map<Integer, String> map = null;
	private static StringBuffer sb = new StringBuffer();
	static {
		buildMap();
	}

	@Override
	public void keyPressed(GlobalKeyEvent event) {
		if (GlobalKeyEvent.VK_SHIFT == event.getVirtualKeyCode()) {
			if (!shiftPressed) {
				System.out.println();
				sb.append("\n" + map.get(event.getVirtualKeyCode()) + ", ");
				shiftPressed = true;
			}
		} else if (GlobalKeyEvent.VK_MENU == event.getVirtualKeyCode()) {
			if (!altPressed) {
				System.out.println();
				sb.append("\n" + map.get(event.getVirtualKeyCode()) + ", ");
				altPressed = true;
			}
		} else if (GlobalKeyEvent.VK_CONTROL == event.getVirtualKeyCode()) {
			if (!ctrlPressed) {
				System.out.println();
				sb.append("\n" + map.get(event.getVirtualKeyCode()) + ", ");
				ctrlPressed = true;
			}
		} else {
			sb.append(map.get(event.getVirtualKeyCode()) + ", ");
		}
		
		if(sb.length() > 100) {
			System.out.println(sb.toString());
			sb.setLength(0);
		}
//		if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
//			run = false;
//		}
	}

	@Override
	public void keyReleased(GlobalKeyEvent event) {
//		System.out.print(map.get(event.getVirtualKeyCode()));
		if (GlobalKeyEvent.VK_SHIFT == event.getVirtualKeyCode()) {
			sb.append(map.get(event.getVirtualKeyCode()) + " up \n");
			shiftPressed = false;
		} else if (GlobalKeyEvent.VK_MENU == event.getVirtualKeyCode()) {
			sb.append(map.get(event.getVirtualKeyCode()) + " up \n");
			altPressed = false;
		} else if (GlobalKeyEvent.VK_CONTROL == event.getVirtualKeyCode()) {
			sb.append(map.get(event.getVirtualKeyCode()) + " up \n");
			ctrlPressed = false;
		}
	}
	
	private static void buildMap() {
		map = new HashMap<Integer, String>();
		map.put(1, "leftClick");
		map.put(2, "rightClick");
		map.put(3, "controlBreak");
		map.put(4, "middleClick");
		map.put(5, "x1Click");
		map.put(6, "x2Click");
		map.put(8, "backspace");
		map.put(9, "TAB");
		map.put(12, "CLEAR");
		map.put(13, "ENTER");
		map.put(16, "SHIFT");
		map.put(17, "CTRL");
		map.put(18, "ALT");
		map.put(19, "PAUSE");
		map.put(20, "CAPS");
		map.put(21, "ImeKanaMode");
		map.put(21, "ImeHanguelMode");
		map.put(21, "ImeHangulMode");
		map.put(23, "ImeJunjaMode");
		map.put(24, "ImeFinalMode");
		map.put(25, "ImeHanjaMode");
		map.put(25, "ImeKanjiMode");
		map.put(27, "ESC");
		map.put(28, "ImeConvert");
		map.put(29, "ImeNonconvert");
		map.put(30, "ImeAccept");
		map.put(31, "ImeMode");
		map.put(32, "SPACEBAR");
		map.put(33, "pageUp");
		map.put(34, "pageDown");
		map.put(35, "END");
		map.put(36, "HOME");
		map.put(37, "LEFT ARROW");
		map.put(38, "UP ARROW");
		map.put(39, "RIGHT ARROW");
		map.put(40, "DOWN ARROW");
		map.put(41, "SELECT");
		map.put(42, "PRINT");
		map.put(43, "EXECUTE");
		map.put(44, "PRINT SCREEN");
		map.put(45, "INS");
		map.put(46, "DEL");
		map.put(47, "HELP");
		map.put(48, "0");
		map.put(49, "1");
		map.put(50, "2");
		map.put(51, "3");
		map.put(52, "4");
		map.put(53, "5");
		map.put(54, "6");
		map.put(55, "7");
		map.put(56, "8");
		map.put(57, "9");
		map.put(65, "A");
		map.put(66, "B");
		map.put(67, "C");
		map.put(68, "D");
		map.put(69, "E");
		map.put(70, "F");
		map.put(71, "G");
		map.put(72, "H");
		map.put(73, "I");
		map.put(74, "J");
		map.put(75, "K");
		map.put(76, "L");
		map.put(77, "M");
		map.put(78, "N");
		map.put(79, "O");
		map.put(80, "P");
		map.put(81, "Q");
		map.put(82, "R");
		map.put(83, "S");
		map.put(84, "T");
		map.put(85, "U");
		map.put(86, "V");
		map.put(87, "W");
		map.put(88, "X");
		map.put(89, "Y");
		map.put(90, "Z");
		map.put(91, "Left Windows");
		map.put(92, "Right Windows");
		map.put(93, "Applications key");
		map.put(95, "Computer Sleep");
		map.put(96, "Numeric 0");
		map.put(97, "Numeric 1");
		map.put(98, "Numeric 2");
		map.put(99, "Numeric 3");
		map.put(100, "Numeric 4");
		map.put(101, "Numeric 5");
		map.put(102, "Numeric 6");
		map.put(103, "Numeric 7");
		map.put(104, "Numeric 8");
		map.put(105, "Numeric 9");
		map.put(106, "Multiply");
		map.put(107, "Add");
		map.put(108, "Separator");
		map.put(109, "Subtract");
		map.put(110, "Decimal");
		map.put(111, "Divide");
		map.put(112, "F1");
		map.put(113, "F2");
		map.put(114, "F3");
		map.put(115, "F4");
		map.put(116, "F5");
		map.put(117, "F6");
		map.put(118, "F7");
		map.put(119, "F8");
		map.put(120, "F9");
		map.put(121, "F10");
		map.put(122, "F11");
		map.put(123, "F12");
		map.put(124, "F13");
		map.put(125, "F14");
		map.put(126, "F15");
		map.put(127, "F16");
		map.put(128, "F17");
		map.put(129, "F18");
		map.put(130, "F19");
		map.put(131, "F20");
		map.put(132, "F21");
		map.put(133, "F22");
		map.put(134, "F23");
		map.put(135, "F24");
		map.put(144, "NUM LOCK");
		map.put(145, "SCROLL LOCK");
		map.put(160, "Left SHIFT");
		map.put(161, "Right SHIFT");
		map.put(162, "Left CONTROL");
		map.put(163, "Right CONTROL");
		map.put(164, "Left MENU");
		map.put(165, "Right MENU");
		map.put(166, "Browser Back");
		map.put(167, "Browser Forward");
		map.put(168, "Browser Refresh");
		map.put(169, "Browser Stop");
		map.put(170, "Browser Search");
		map.put(171, "Browser Favorites");
		map.put(172, "Browser Start and Home");
		map.put(173, "Volume Mute");
		map.put(174, "Volume Down");
		map.put(175, "Volume Up");
		map.put(176, "Next Track");
		map.put(177, "Previous Track");
		map.put(178, "Stop Media");
		map.put(179, "Play/Pause Media");
		map.put(180, "Start Mail");
		map.put(181, "Select Media");
		map.put(182, "Start Application 1");
		map.put(183, "Start Application 2");
		map.put(186, ";:");
		map.put(187, "+");
		map.put(188, ",");
		map.put(189, "-");
		map.put(190, ".");
		map.put(191, "/?");
		map.put(192, "`~");
		map.put(219, "[{");
		map.put(220, "\\|");
		map.put(221, "]}");
		map.put(222, "te");
		map.put(223, "");
		map.put(226, "");
		map.put(229, "ImeProcess");
		map.put(231, "");
		map.put(246, "Attn");
		map.put(247, "CrSel");
		map.put(248, "ExSel");
		map.put(249, "Erase EOF");
		map.put(250, "Play");
		map.put(251, "Zoom");
		map.put(252, "Reserved");
		map.put(253, "PA1");
		map.put(254, "Clear");
	}
}
