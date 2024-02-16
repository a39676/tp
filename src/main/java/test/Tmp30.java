package test;

import java.util.concurrent.ThreadLocalRandom;

public class Tmp30 extends Tmp29 {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("sometimes");
		if (sb.length() <= 3) {
			for (int i = 0; i < sb.length(); i++) {
				sb.setCharAt(i, '_');
			}
		}
		int length = sb.length();
		int notReplaceCounting = length / 3;
		if (notReplaceCounting < 3) {
			notReplaceCounting = 3;
		}
		int replaceCount = length - notReplaceCounting;
		int randomIndex = 0;
		for (int i = 0; i < replaceCount; i++) {
			randomIndex = ThreadLocalRandom.current().nextInt(0, sb.length());
			char tmpChar = sb.charAt(randomIndex);
			if (Character.isLetter(tmpChar)) {
				sb.replace(randomIndex, randomIndex + 1, "_");
			} else {
				i--;
				continue;
			}
		}
		System.out.println(sb.toString());
	}
}
