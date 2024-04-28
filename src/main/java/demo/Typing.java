package demo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Typing {

	private static Robot r = null;

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(3000L);
		Typing t = new Typing();
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		System.out.println("Hi");

		String str = null;
//		str = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ `1234567890-=[];',./ ~!@#$%^&*()_+{}:\"<>?"
//				+ "	";

//		str= "Basic Field Validation for Excel Upload";

		str = t.getStringFromFile("D:/tmp/tmp.txt");

		List<String> strList = new ArrayList<>();
		strList.addAll(Arrays.asList(str.split("")));
		String tmpS = null;
		for (int i = 0; i < strList.size(); i++) {
			tmpS = strList.get(i);
			if ("\r".equals(tmpS) && (i < strList.size() - 1) && ("\n".equals(strList.get(i + 1)))) {
				t.type(String.valueOf("\r\n"));
				i++;
			} else {
				t.type(String.valueOf(tmpS));
			}
			Thread.sleep(20L);
		}

		System.out.println("End");
	}

	public String getStringFromFile(String filePath, String encodeType) {
		Path path = Paths.get(filePath);
		StringBuffer result = new StringBuffer();
		try {
			BufferedReader reader = Files.newBufferedReader(path, Charset.forName(encodeType));
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {// while there is content on the current line
				result.append(currentLine + System.lineSeparator());
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace(); // handle an exception here
		}
		return result.toString();
	}

	public String getStringFromFile(String filePath) {
		return getStringFromFile(filePath, StandardCharsets.UTF_8.displayName());
	}

	public void type(String str) {
		switch (str) {
		case "a":
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_A);
			break;
		case "b":
			r.keyPress(KeyEvent.VK_B);
			r.keyRelease(KeyEvent.VK_B);
			break;
		case "c":
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_C);
			break;
		case "d":
			r.keyPress(KeyEvent.VK_D);
			r.keyRelease(KeyEvent.VK_D);
			break;
		case "e":
			r.keyPress(KeyEvent.VK_E);
			r.keyRelease(KeyEvent.VK_E);
			break;
		case "f":
			r.keyPress(KeyEvent.VK_F);
			r.keyRelease(KeyEvent.VK_F);
			break;
		case "g":
			r.keyPress(KeyEvent.VK_G);
			r.keyRelease(KeyEvent.VK_G);
			break;
		case "h":
			r.keyPress(KeyEvent.VK_H);
			r.keyRelease(KeyEvent.VK_H);
			break;
		case "i":
			r.keyPress(KeyEvent.VK_I);
			r.keyRelease(KeyEvent.VK_I);
			break;
		case "j":
			r.keyPress(KeyEvent.VK_J);
			r.keyRelease(KeyEvent.VK_J);
			break;
		case "k":
			r.keyPress(KeyEvent.VK_K);
			r.keyRelease(KeyEvent.VK_K);
			break;
		case "l":
			r.keyPress(KeyEvent.VK_L);
			r.keyRelease(KeyEvent.VK_L);
			break;
		case "m":
			r.keyPress(KeyEvent.VK_M);
			r.keyRelease(KeyEvent.VK_M);
			break;
		case "n":
			r.keyPress(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_N);
			break;
		case "o":
			r.keyPress(KeyEvent.VK_O);
			r.keyRelease(KeyEvent.VK_O);
			break;
		case "p":
			r.keyPress(KeyEvent.VK_P);
			r.keyRelease(KeyEvent.VK_P);
			break;
		case "q":
			r.keyPress(KeyEvent.VK_Q);
			r.keyRelease(KeyEvent.VK_Q);
			break;
		case "r":
			r.keyPress(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_R);
			break;
		case "s":
			r.keyPress(KeyEvent.VK_S);
			r.keyRelease(KeyEvent.VK_S);
			break;
		case "t":
			r.keyPress(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_T);
			break;
		case "u":
			r.keyPress(KeyEvent.VK_U);
			r.keyRelease(KeyEvent.VK_U);
			break;
		case "v":
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			break;
		case "w":
			r.keyPress(KeyEvent.VK_W);
			r.keyRelease(KeyEvent.VK_W);
			break;
		case "x":
			r.keyPress(KeyEvent.VK_X);
			r.keyRelease(KeyEvent.VK_X);
			break;
		case "y":
			r.keyPress(KeyEvent.VK_Y);
			r.keyRelease(KeyEvent.VK_Y);
			break;
		case "z":
			r.keyPress(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_Z);
			break;
		case "A":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "B":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_B);
			r.keyRelease(KeyEvent.VK_B);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "C":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "D":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_D);
			r.keyRelease(KeyEvent.VK_D);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "E":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_E);
			r.keyRelease(KeyEvent.VK_E);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "F":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_F);
			r.keyRelease(KeyEvent.VK_F);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "G":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_G);
			r.keyRelease(KeyEvent.VK_G);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "H":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_H);
			r.keyRelease(KeyEvent.VK_H);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "I":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_I);
			r.keyRelease(KeyEvent.VK_I);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "J":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_J);
			r.keyRelease(KeyEvent.VK_J);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "K":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_K);
			r.keyRelease(KeyEvent.VK_K);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "L":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_L);
			r.keyRelease(KeyEvent.VK_L);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "M":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_M);
			r.keyRelease(KeyEvent.VK_M);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "N":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "O":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_O);
			r.keyRelease(KeyEvent.VK_O);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "P":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_P);
			r.keyRelease(KeyEvent.VK_P);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "Q":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_Q);
			r.keyRelease(KeyEvent.VK_Q);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "R":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "S":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_S);
			r.keyRelease(KeyEvent.VK_S);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "T":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "U":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_U);
			r.keyRelease(KeyEvent.VK_U);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "V":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "W":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_W);
			r.keyRelease(KeyEvent.VK_W);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "X":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_X);
			r.keyRelease(KeyEvent.VK_X);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "Y":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_Y);
			r.keyRelease(KeyEvent.VK_Y);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "Z":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "`":
			r.keyPress(KeyEvent.VK_BACK_QUOTE);
			r.keyRelease(KeyEvent.VK_BACK_QUOTE);
			break;
		case "0":
			r.keyPress(KeyEvent.VK_0);
			r.keyRelease(KeyEvent.VK_0);
			break;
		case "1":
			r.keyPress(KeyEvent.VK_1);
			r.keyRelease(KeyEvent.VK_1);
			break;
		case "2":
			r.keyPress(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_2);
			break;
		case "3":
			r.keyPress(KeyEvent.VK_3);
			r.keyRelease(KeyEvent.VK_3);
			break;
		case "4":
			r.keyPress(KeyEvent.VK_4);
			r.keyRelease(KeyEvent.VK_4);
			break;
		case "5":
			r.keyPress(KeyEvent.VK_5);
			r.keyRelease(KeyEvent.VK_5);
			break;
		case "6":
			r.keyPress(KeyEvent.VK_6);
			r.keyRelease(KeyEvent.VK_6);
			break;
		case "7":
			r.keyPress(KeyEvent.VK_7);
			r.keyRelease(KeyEvent.VK_7);
			break;
		case "8":
			r.keyPress(KeyEvent.VK_8);
			r.keyRelease(KeyEvent.VK_8);
			break;
		case "9":
			r.keyPress(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_9);
			break;
		case "-":
			r.keyPress(KeyEvent.VK_MINUS);
			r.keyRelease(KeyEvent.VK_MINUS);
			break;
		case "=":
			r.keyPress(KeyEvent.VK_EQUALS);
			r.keyRelease(KeyEvent.VK_EQUALS);
			break;
		case "~":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_BACK_QUOTE);
			r.keyRelease(KeyEvent.VK_BACK_QUOTE);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "!":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_1);
			r.keyRelease(KeyEvent.VK_1);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "@":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "#":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_3);
			r.keyRelease(KeyEvent.VK_3);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "$":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_4);
			r.keyRelease(KeyEvent.VK_4);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "%":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_5);
			r.keyRelease(KeyEvent.VK_5);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "^":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_6);
			r.keyRelease(KeyEvent.VK_6);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "&":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_7);
			r.keyRelease(KeyEvent.VK_7);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "*":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_8);
			r.keyRelease(KeyEvent.VK_8);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "(":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ")":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_0);
			r.keyRelease(KeyEvent.VK_0);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "_":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_MINUS);
			r.keyRelease(KeyEvent.VK_MINUS);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "+":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_EQUALS);
			r.keyRelease(KeyEvent.VK_EQUALS);
			r.keyRelease(KeyEvent.VK_SHIFT);
		case "\t":
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			break;
		case "\n":
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			break;
		case "\r":
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			break;
		case "\r\n":
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			break;
		case "[":
			r.keyPress(KeyEvent.VK_OPEN_BRACKET);
			r.keyRelease(KeyEvent.VK_OPEN_BRACKET);
			break;
		case "]":
			r.keyPress(KeyEvent.VK_CLOSE_BRACKET);
			r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			break;
		case "\\":
			r.keyPress(KeyEvent.VK_BACK_SLASH);
			r.keyRelease(KeyEvent.VK_BACK_SLASH);
			break;
		case "{":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_OPEN_BRACKET);
			r.keyRelease(KeyEvent.VK_OPEN_BRACKET);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "}":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_CLOSE_BRACKET);
			r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "|":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_BACK_SLASH);
			r.keyRelease(KeyEvent.VK_BACK_SLASH);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ";":
			r.keyPress(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SEMICOLON);
			break;
		case ":":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "\'":
			r.keyPress(KeyEvent.VK_QUOTE);
			r.keyRelease(KeyEvent.VK_QUOTE);
			break;
		case "\"":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_QUOTE);
			r.keyRelease(KeyEvent.VK_QUOTE);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ",":
			r.keyPress(KeyEvent.VK_COMMA);
			r.keyRelease(KeyEvent.VK_COMMA);
			break;
		case "<":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_COMMA);
			r.keyRelease(KeyEvent.VK_COMMA);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ".":
			r.keyPress(KeyEvent.VK_PERIOD);
			r.keyRelease(KeyEvent.VK_PERIOD);
			break;
		case ">":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_PERIOD);
			r.keyRelease(KeyEvent.VK_PERIOD);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "/":
			r.keyPress(KeyEvent.VK_SLASH);
			r.keyRelease(KeyEvent.VK_SLASH);
			break;
		case "?":
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_SLASH);
			r.keyRelease(KeyEvent.VK_SLASH);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case " ":
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);
			break;
		default:
			System.out.println("Cannot type character " + str);
		}
	}

}
