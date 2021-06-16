package test;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.LocalDateTime;

public class TmpTest11 {

	private static Long coldDownBeforeStart = 5L * 1000;

	public static void main(String[] args) throws InterruptedException {
		TmpTest11 t = new TmpTest11();
//		t.clickStopByMove();
		t.clickStopByTime();
	}

	@SuppressWarnings("deprecation")
	public void clickStopByMove() throws InterruptedException {
		Thread.sleep(coldDownBeforeStart);

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		PointerInfo mouseP = MouseInfo.getPointerInfo();
		Point b = mouseP.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();

		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		boolean flag = true;
		int newX = 0;
		int newY = 0;
		while (flag) {
			Thread.sleep(20L);
			mouseP = MouseInfo.getPointerInfo();
			b = mouseP.getLocation();
			newX = (int) b.getX();
			newY = (int) b.getY();

			flag = ((newX - x < 10 && newX - x > -10) && (newY - y < 10 && newY - y > -10));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void clickStopByTime() throws InterruptedException {
		Thread.sleep(coldDownBeforeStart);

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		LocalDateTime end = LocalDateTime.now().plusMinutes(2);
		LocalDateTime now = LocalDateTime.now();

		while(now.isBefore(end)) {
			Thread.sleep(20L);

			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			now = LocalDateTime.now();
		}
	}
}