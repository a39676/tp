package test;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class TmpTest11 {

	private static Long coldDownBeforeStart = 5L * 1000;

	private static Robot robot = null;
	private static int clickDeletePointX = 1399;
	private static int clickDeletePointY = 631;
	private static int clickOkPointX = 897;
	private static int clickOkPointY = 484;
	static {
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TmpTest11 t = new TmpTest11();
		t.clickStopByMove();
	}

	public void clickStopByMove() throws InterruptedException {
		Thread.sleep(coldDownBeforeStart);
		System.out.println("Start");

		robot.mouseMove(clickDeletePointX, clickDeletePointY);
		System.out.println("Moved to 'Delete'");

		PointerInfo mouseStartPointInfo = MouseInfo.getPointerInfo();
		Point mouseStartPoint = mouseStartPointInfo.getLocation();

		boolean flag = true;
		int newX = 0;
		int newY = 0;
		while (flag) {
			Thread.sleep(2000L);
			mouseStartPointInfo = MouseInfo.getPointerInfo();
			mouseStartPoint = mouseStartPointInfo.getLocation();
			newX = (int) mouseStartPoint.getX();
			newY = (int) mouseStartPoint.getY();

//			flag = ((newX - mouseOldX < 10 && newX - mouseOldX > -10)
//					&& (newY - mouseOldY < 10 && newY - mouseOldY > -10));
			flag = (newX == clickDeletePointX && newY == clickDeletePointY);
			if (!flag) {
				System.out.println("Moved, X: " + newX + ", new Y: " + newY);
				return;
			}

			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			System.out.println("Clicked");
			Thread.sleep(150L);

			robot.mouseMove(clickOkPointX, clickOkPointY);
			Thread.sleep(1000L);
			System.out.println("Moved to 'OK'");

			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			System.out.println("Clicked");
			Thread.sleep(120L);

			robot.mouseMove(clickDeletePointX, clickDeletePointY);
			System.out.println("Moved to 'Delete'");

//			robot.keyPress(KeyEvent.VK_SPACE);
//			robot.keyRelease(KeyEvent.VK_SPACE);
//			System.out.println("Space pressed");
		}

	}
}