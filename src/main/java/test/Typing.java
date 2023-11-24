package test;

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
		String str = null;
//		str= "<plugin><groupId>org.apache.maven.plugins.boot</groupId><artifactId>maven-resources-plugin</artifactId><version>3.1.0</version>/plugin>";
		
//		str = t.getStringFromFile("D:\\myMavenProject\\ToolPacks\\src\\main\\java\\toolPack\\ioHandle/FileUtilCustom.java");
		str = t.getStringFromFile("D:/tmp/tmp.txt");
		
		t.type(str);
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
		} catch (IOException ex) {
			ex.printStackTrace(); // handle an exception here
		}
		return result.toString();
	}

	public String getStringFromFile(String filePath) {
		return getStringFromFile(filePath, StandardCharsets.UTF_8.displayName());
	}

	public void type(char character) {
		switch (character) {
		case 'a':
			doType(KeyEvent.VK_A);
			break;
		case 'b':
			doType(KeyEvent.VK_B);
			break;
		case 'c':
			doType(KeyEvent.VK_C);
			break;
		case 'd':
			doType(KeyEvent.VK_D);
			break;
		case 'e':
			doType(KeyEvent.VK_E);
			break;
		case 'f':
			doType(KeyEvent.VK_F);
			break;
		case 'g':
			doType(KeyEvent.VK_G);
			break;
		case 'h':
			doType(KeyEvent.VK_H);
			break;
		case 'i':
			doType(KeyEvent.VK_I);
			break;
		case 'j':
			doType(KeyEvent.VK_J);
			break;
		case 'k':
			doType(KeyEvent.VK_K);
			break;
		case 'l':
			doType(KeyEvent.VK_L);
			break;
		case 'm':
			doType(KeyEvent.VK_M);
			break;
		case 'n':
			doType(KeyEvent.VK_N);
			break;
		case 'o':
			doType(KeyEvent.VK_O);
			break;
		case 'p':
			doType(KeyEvent.VK_P);
			break;
		case 'q':
			doType(KeyEvent.VK_Q);
			break;
		case 'r':
			doType(KeyEvent.VK_R);
			break;
		case 's':
			doType(KeyEvent.VK_S);
			break;
		case 't':
			doType(KeyEvent.VK_T);
			break;
		case 'u':
			doType(KeyEvent.VK_U);
			break;
		case 'v':
			doType(KeyEvent.VK_V);
			break;
		case 'w':
			doType(KeyEvent.VK_W);
			break;
		case 'x':
			doType(KeyEvent.VK_X);
			break;
		case 'y':
			doType(KeyEvent.VK_Y);
			break;
		case 'z':
			doType(KeyEvent.VK_Z);
			break;
		case 'A':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'B':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_B);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'C':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'D':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_D);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'E':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_E);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'F':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_F);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'G':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_G);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'H':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_H);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'I':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_I);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'J':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_J);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'K':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_K);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'L':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_L);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'M':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_M);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'N':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'O':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_O);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'P':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_P);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Q':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_Q);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'R':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'S':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_S);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'T':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'U':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_U);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'V':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'W':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_W);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'X':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_X);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Y':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_Y);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Z':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '`':
			doType(KeyEvent.VK_BACK_QUOTE);
			break;
		case '0':
			doType(KeyEvent.VK_0);
			break;
		case '1':
			doType(KeyEvent.VK_1);
			break;
		case '2':
			doType(KeyEvent.VK_2);
			break;
		case '3':
			doType(KeyEvent.VK_3);
			break;
		case '4':
			doType(KeyEvent.VK_4);
			break;
		case '5':
			doType(KeyEvent.VK_5);
			break;
		case '6':
			doType(KeyEvent.VK_6);
			break;
		case '7':
			doType(KeyEvent.VK_7);
			break;
		case '8':
			doType(KeyEvent.VK_8);
			break;
		case '9':
			doType(KeyEvent.VK_9);
			break;
		case '-':
			doType(KeyEvent.VK_MINUS);
			break;
		case '=':
			doType(KeyEvent.VK_EQUALS);
			break;
		case '~':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_BACK_QUOTE);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '!':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_1);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '@':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '#':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_3);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '$':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_4);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '%':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_5);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '^':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_6);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '&':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_7);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '*':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_8);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '(':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ')':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_0);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '_':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_MINUS);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '+':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_EQUALS);
			r.keyRelease(KeyEvent.VK_SHIFT);
		case '\t':
			doType(KeyEvent.VK_TAB);
			break;
		case '\n':
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			break;
		case '\r':
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			break;
		case '[':
			doType(KeyEvent.VK_OPEN_BRACKET);
			break;
		case ']':
			doType(KeyEvent.VK_CLOSE_BRACKET);
			break;
		case '\\':
			doType(KeyEvent.VK_BACK_SLASH);
			break;
		case '{':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_OPEN_BRACKET);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '}':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_CLOSE_BRACKET);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '|':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_BACK_SLASH);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ';':
			doType(KeyEvent.VK_SEMICOLON);
			break;
		case ':':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '\'':
			doType(KeyEvent.VK_QUOTE);
			break;
		case '\"':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_QUOTE);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ',':
			doType(KeyEvent.VK_COMMA);
			break;
		case '<':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_COMMA);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '.':
			doType(KeyEvent.VK_PERIOD);
			break;
		case '>':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_PERIOD);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '/':
			doType(KeyEvent.VK_SLASH);
			break;
		case '?':
			r.keyPress(KeyEvent.VK_SHIFT);
			doType(KeyEvent.VK_SLASH);
			r.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ' ':
			doType(KeyEvent.VK_SPACE);
			break;
		default:
			System.out.println("Cannot type character " + character);
		}
	}

	public void type(CharSequence characters) {
		int length = characters.length();
		for (int i = 0; i < length; i++) {
			char character = characters.charAt(i);
			type(character);
			try {
				Thread.sleep(30L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void doType(int... keyCodes) {
		doType(keyCodes, 0, keyCodes.length);
	}

	private void doType(int[] keyCodes, int offset, int length) {
		if (length == 0)
			return;

		r.keyPress(keyCodes[offset]);
		doType(keyCodes, offset + 1, length - 1);
		r.keyRelease(keyCodes[offset]);
	}
}
