package test;

import java.util.Date;

public class Tmp29 {

	public static void main(String[] args) {
		Tmp29 t = new Tmp29();
		t.send();
	}

	public void send() {
		Date d = new Date();
		System.out.println(d);
		System.out.println(d.getTime());
	}

}
