package demo;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.time.LocalDateTime;

public class Moving {
	private static final int DELAY_SECONDS = 60;
	@SuppressWarnings("unused")
	private static final int MAX_X = 3;
	@SuppressWarnings("unused")
	private static final int MAX_Y = 3;
	private static int mouseX = 0;
	private static int mouseY = 0;
	private static Point point = MouseInfo.getPointerInfo().getLocation();

	public static void main(String[] args) {
		System.out.println("Hello world!");
		Moving m = new Moving();
		m.moving();
	}

	private void moving() {
		try {
			Robot robot = new Robot();
//			ThreadLocalRandom random = ThreadLocalRandom.current();
//			int nextX = 500;
//			int nextY = 500;
			while (true) {
				point = MouseInfo.getPointerInfo().getLocation();
				if (hadMove()) {
					System.out.println("Had moved, update mouse location, x:" + point.x + ", y:" + point.y);
					updateMouseLocation(point.x, point.y);
				} else {
//					nextX = random.nextInt(nextX - MAX_X, nextX + MAX_X + 1);
//					if (nextX < 0) {
//						nextX = 0;
//					}
//					nextY = random.nextInt(nextY - MAX_Y, nextY + MAX_Y + 1);
//					if (nextY < 0) {
//						nextY = 0;
//					}
//					int oldX = point.x ;
//					int oldY = point.y;
//					robot.mouseMove(nextX, nextY);
//					System.out.println("Move to x: " + nextX + ", y: " + nextY + ", time: " + LocalDateTime.now());
					int oldX = point.x;
					int oldY = point.y;
					robot.mouseMove(oldX + 1, oldY);
					robot.mouseMove(oldX - 1, oldY);
					robot.mouseMove(oldX, oldY + 1);
					robot.mouseMove(oldX, oldY - 1);
					robot.mouseMove(oldX, oldY);
					System.out.println("Auto move, time: " + LocalDateTime.now());
					updateMouseLocation(point.x, point.y);
				}
				Thread.sleep(DELAY_SECONDS * 1000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean hadMove() {
		boolean flag = (point.x == mouseX) && (point.y == mouseY);
		updateMouseLocation(point.x, point.y);
		return !flag;
	}

	private void updateMouseLocation(int x, int y) {
		mouseX = x;
		mouseY = y;
	}
}