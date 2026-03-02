package test;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

public class Tmp41 {

	public static void main(String[] args) throws InterruptedException {
		Tmp41 t = new Tmp41();
		t.clickStopByMove();
//		t.clickStopByTime();
	}

	public void clickStopByMove() throws InterruptedException {

		PointerInfo mouseP = MouseInfo.getPointerInfo();
		Point p = mouseP.getLocation();

		while (true) {
			Thread.sleep(100L);
			mouseP = MouseInfo.getPointerInfo();
			p = mouseP.getLocation();
			System.out.println(p.getX() + ", " + p.getY());
		}
	}
	// 1399 631
	// 897 484

}