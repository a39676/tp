package useTools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang.StringUtils;

public class createCustomKey {

	public static void main(String[] args) {
		List<List<Character>> keys = buildKeys();
		String id = "100";
		String encryptId = customEncrypt(keys, id);
		System.out.println(encryptId);
		
		String decryptId = customDecrypt(keys, encryptId);
		System.out.println(decryptId);
	}
	
	public static List<List<Character>> buildKeys() {
		String keyStr = "1234567890_qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		List<Character> tmpKeyChar = new ArrayList<Character>();
		
		int randInt = 0;
		List<List<Character>> keys = new ArrayList<List<Character>>();
		List<Character> key = null;

		for(int i = 0; i < 10; i++) {
			key = new ArrayList<Character>();
			reFill(tmpKeyChar, keyStr);
			for(int j = 0;j < 10; j++) {
				randInt = ThreadLocalRandom.current().nextInt(0, tmpKeyChar.size());
				key.add(tmpKeyChar.remove(randInt));
			}
			keys.add(key);
			System.out.println(key.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", ""));
		}
		return keys;
	}
	
	public static void reFill(List<Character> ca, String str) {
		ca.clear();
		for(int i = 0; i < str.length(); i++) {
			ca.add(str.charAt(i));
		}
	}
	
	public static String customEncrypt(List<List<Character>> keys, String inputStr) {
		StringBuffer builder = new StringBuffer();
		int keyIndex = 0;
		for(int i = 0; i < inputStr.length(); i++) {
			if(i == inputStr.length() - 1) {
				builder.append(inputStr.charAt(0));
				keyIndex = Integer.parseInt(String.valueOf(inputStr.charAt(0)));
				builder.append(keys.get(keyIndex).get(Integer.parseInt(String.valueOf(inputStr.charAt(i)))));
			} else {
				builder.append(inputStr.charAt(i + 1));
				keyIndex = Integer.parseInt(String.valueOf(inputStr.charAt(i + 1)));
				builder.append(keys.get(keyIndex).get(Integer.parseInt(String.valueOf(inputStr.charAt(i)))));
			}
		}
		
		for(int i = 0; i < builder.length(); i = i + 2) {
			builder.replace(i, i+1, keys.get(0).get(Integer.parseInt(String.valueOf(builder.charAt(i)))).toString());
		}
		
		return builder.toString();
	}
	
	public static String customDecrypt(List<List<Character>> keys, String inputStr) {
		if(keys == null || keys.size() < 1 || StringUtils.isBlank(inputStr)) {
			return null;
		}
		StringBuffer builder = new StringBuffer(inputStr);
		int keyIndex = 0;
		for(int i = 0; i < builder.length(); i = i + 2) {
			builder.replace(i, i+1, String.valueOf(keys.get(0).indexOf((builder.charAt(i)))));
		}
		
		StringBuffer resultBuilder = new StringBuffer();
		for(int i = 0; i < builder.length(); i = i + 2) {
			keyIndex = Integer.parseInt(String.valueOf(builder.charAt(i)));
			resultBuilder.append(keys.get(keyIndex).indexOf(builder.charAt(i + 1)));
		}
		
		return resultBuilder.toString();
	}

}
