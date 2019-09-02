package tool_package.violent_test;

import java.util.ArrayList;
import java.util.HashMap;

public class DynamicDictionary {
	
	private static ArrayList<Character> tmpDictionary;
	
	private static HashMap<Integer, char[]> dictioaryTypeMap = new HashMap<Integer, char[]>();
	
	static{
		dictioaryTypeMap.put(1, BaseCharacter.numeric);
		dictioaryTypeMap.put(2, BaseCharacter.englishLowerCaseLetters);
		dictioaryTypeMap.put(3, BaseCharacter.englishUpperCaseLetters);
		dictioaryTypeMap.put(4, BaseCharacter.commonSymbol);
		dictioaryTypeMap.put(5, BaseCharacter.specialSymbol);
	}
	
	public static void setTmpDictionary(Integer... dictonaryType){
		tmpDictionary = new ArrayList<Character>();
		for(Integer i : dictonaryType) {
			char[] tmpCharArray = dictioaryTypeMap.get(i);
			for(Character ch : tmpCharArray) {
				tmpDictionary.add(ch);
			}
		}
	}

	public static ArrayList<Character> getTmpDictionary(Integer... dictonaryType) {
		setTmpDictionary(dictonaryType);
		return tmpDictionary;
	}

//	public static void main(String[] args) {
//		DynamicDictionary dTest = new DynamicDictionary();
//		dTest.setTmpDictionary(1, 3, 2);
//		System.out.println(dTest.getTmpDictionary());
//	}

}
