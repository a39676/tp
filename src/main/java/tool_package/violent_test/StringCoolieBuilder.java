package tool_package.violent_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 悲剧的字符堆砌类
 * @author SomeGuy
 *
 */
public class StringCoolieBuilder {

	private int minLength = 0;
	private int maxLength = 0;
	
	private StringBuilder sb = new StringBuilder();
	
	private int stepGroup = 3;
	
	private ArrayList<Character> characterDictionary;
//	private ArrayList<String> tmpStringDictionary;
	
	private List<Integer> indexList = new ArrayList<Integer>(); 
	private List<String> tmpResultList = new ArrayList<String>();
	
	private int dictionarySize;
	
	private String finalWord;
	

	private List<Character> includeCharacters;
	private List<String> includeStrings;
	
	public int getFinalResultMaxSize() {
		return maxLength;
	}
	
	public int getTmpWordSize() {
		return indexList.size();
	}
	
	private void setFinalWord() {
		String tmpStr = "";
		for(int i = maxLength-1; i >= 0; i--) {
			tmpStr += characterDictionary.get(dictionarySize - 1);
		}
		finalWord = tmpStr;
	}
	
	public String getFinalWord() {
		return finalWord;
	}
	
	public void setWordLength(Integer minLength, Integer maxLength) {
		this.minLength = minLength;
		this.maxLength = maxLength;
		
		for(int i = 0; i < minLength; i++) {
			indexList.add(0);
		}
	}
	
	/**
	 * 构成字符串
	 * @param sb
	 * @return
	 */
	synchronized private void makeSubString() {
		if(sb.length() == 0) {
			for(int i : indexList) {
				sb.append(characterDictionary.get(i));
			}
			tmpResultList.add(sb.toString());
		} else {
			tickString();
		}
	}
	
	/**
	 * 字符串步进
	 * @param sb
	 * @param strReplaec
	 * @return
	 */
	synchronized private void tickString() {
		int gap = tickIndexList();
		for(;gap < indexList.size(); gap++) {
			// 进位到最高位|在单个位字符的情况,整个字符串重组
			if(gap == 0) {
				sb.setLength(0);
				for(int i : indexList) {
					sb.append(characterDictionary.get(i));
				}

			} else {
				// 一般情况下,只替换最后一位
				if(gap == sb.length() -1) {
					String tmpChar = characterDictionary.get(indexList.get(gap)).toString();
					sb.replace(sb.length() -1, sb.length(), tmpChar);
				// 替换多位
				} else {
					for(int i = gap; i < sb.length(); i++) {
						sb.replace(i, i+1, characterDictionary.get(indexList.get(i)).toString());
					}
				}
				
			}
		}
		tmpResultList.add(sb.toString());
	}
	
	/**
	 * 坐标列表步进
	 * 返回被改变的最左边index
	 * @param tick
	 * @return
	 */
	synchronized private int tickIndexList() {
		int gap = indexList.size() - 1;
		
		if(indexList.get(indexList.size() - 1) == dictionarySize - 1) {
			gap = carryIndexList(indexList.size() - 1);
		} else {
			indexList.set(indexList.size() - 1, indexList.get(indexList.size() - 1) + 1);
		}
		
		return gap;
	}
	
	/**
	 * 坐标列表进位处理
	 * 返回被改变的最左边坐标
	 * 如果到尽头,清空列表
	 * @param i
	 * @return
	 */
	synchronized private int carryIndexList(int i) {
		
		// 进位到最左位
		if(i == 0 && indexList.get(i) == dictionarySize -1) {
			// 未到尽头,加一位
			if(indexList.size() < maxLength) {
				indexList.set(i, 0);
				indexList.add(0, 0);
				return 0;
			// 已经到尽头,列表归0
			} else {
				indexList.clear();
				return 0;
			}
		}
		
		// 普通进位
		else if(i > 0) {
			
			indexList.set(i, 0);
			
			// 连续进位,递归
			if(indexList.get(i - 1) == dictionarySize -1) {
				i = carryIndexList(i - 1);

			// 单次进位
			} else {
				indexList.set(i - 1, indexList.get(i - 1) + 1);
				return i - 1;
			}
		}
		
		return i;
		
	}
	
	/**
	 * 构成基础字符表
	 * @param i
	 */
	public void setTmpDictionary(Integer... i) {
		characterDictionary = DynamicDictionary.getTmpDictionary(i);
		dictionarySize = characterDictionary.size();
		setFinalWord();
	}
	
	/**
	 * 不包含指定字符
	 * @param chars
	 */
	public void exceptTheseCharacters(Character... chars) {
		for(Character charEle : chars) {
			characterDictionary.remove(charEle);
		}
		dictionarySize = characterDictionary.size();
	}
	
	/**
	 * 附加指定字符
	 * @param chars
	 */
	public void addTheseCharacters(Character[] chars) {
		for(Character charEle : chars) {
			characterDictionary.add(charEle);
		}
		dictionarySize = characterDictionary.size();
	}
	
	/**
	 * 过滤,留下必定含有指定字符的words
	 * @param outputList
	 */
	private void matchIncludeCharacters(List<String> outputList) {
		
		if(includeCharacters == null || includeCharacters.isEmpty()) {
			return;
		}
		
		boolean flag = false;
		for(int i = 0; i < outputList.size(); i++) {
			flag = false;
			
			includeCheckLoop:
			for(Character eleChar : includeCharacters) {
				if(outputList.get(i).contains(eleChar.toString())) {
					flag = true;
					break includeCheckLoop;
				}
			}
			
			if(!flag) {
				outputList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * 过滤,留下必定含有指定字符的words
	 * @param outputList
	 */
	private void matchIncludeStrings(List<String> outputList) {
		
		if(includeStrings == null || includeStrings.isEmpty()) {
			return;
		}
		
		boolean flag = false;
		for(int i = 0; i < outputList.size(); i++) {
			flag = false;
			
			includeCheckLoop:
			for(String eleString : includeStrings) {
				if(outputList.get(i).contains(eleString)) {
					flag = true;
					break includeCheckLoop;
				}
			}
			
			if(!flag) {
				outputList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * 输入指定必须含有的字符
	 * @param chars
	 */
	public void setIncludeCharacters(Character... chars) {
		List<Character> notInDictionAry = new ArrayList<Character>();
		// 检查设定的字典 是否包含 提示的字符,若提示的字符不存在基础字典.终止操作
		for(Character eleChar : chars) {
			if(!characterDictionary.contains(eleChar)) {
				notInDictionAry.add(eleChar);
			}
		}
		if(!notInDictionAry.isEmpty()) {
			System.out.println("'" + notInDictionAry + "' not in dictionary,"
					+ "please check your input,"
					+ "or insert it to dictionary.");
			// 终止操作
			indexList.clear();
			return;
		}
		includeCharacters = new ArrayList<Character>(Arrays.asList(chars));
	}
	
	public void setIncludeString(String... strings) {
		List<Character> notInDictionAry = new ArrayList<Character>();
		// 检查设定的字典 是否包含 提示的字符串,若提示的字符串不存在基础字典.终止操作
		for(String eleString : strings) {
			for(Character eleChar : eleString.toCharArray()) {
				if(!characterDictionary.contains(eleChar)) {
					notInDictionAry.add(eleChar);
				}
			}
		}
		if(!notInDictionAry.isEmpty()) {
			System.out.println("'" + notInDictionAry + "' not in dictionary,"
					+ "please check your input,"
					+ "or insert it to dictionary.");
			// 终止操作
			indexList.clear();
			return;
		}
		includeStrings = new ArrayList<String>(Arrays.asList(strings));
	}
	
	
	/**
	 * 设想中
	 * @param strInput
	 */
	public void endWithStr(String strInput) {
		
	}
	
	/**
	 * 从指定字符串开始
	 * @param strInput
	 */
	public void startWithStr(String strInput) {
		if(strInput.length() > maxLength || strInput.length() < minLength) {
			System.out.println("wrong size input");
			indexList.clear();
			return;
		}
		
		int tmpIndex = -1;
		indexList = new ArrayList<Integer>();
		for(int j = 0; j < strInput.length(); j++) {
			indexList.add(0);
		}
		for(int i = 0; i < strInput.length(); i++){
			tmpIndex = characterDictionary.indexOf(strInput.charAt(i));
			if(tmpIndex != -1) {
				indexList.set(i, tmpIndex);
			} else {
				indexList.clear();
				System.out.println("wrong character input,\n"
						+ "some characters of input not in dictionary "
						+ "please check input or reset character type of dictionary");
				return;
			}
		}
		
		makeSomeWords(stepGroup);
		
	}
	
	
	/**
	 * 批量获取一份words
	 * @param pieces
	 * @return
	 */
	synchronized private void makeSomeWords(Integer pieces) {
		
		if(pieces == null || pieces == 0) {
			pieces = stepGroup;
		}
		
		int yield = pieces * dictionarySize;
		
		for(; yield > 0 && indexList.size() <= maxLength && indexList.size() > 0; yield--) {
			makeSubString();
		}
		
	}
	
	synchronized private void makeSomeWords() {
		makeSomeWords(stepGroup);
	}
	
	
	synchronized public List<String> outputWords(Integer setpLong) {
		if (setpLong != null && setpLong > 0) {
			makeSomeWords(setpLong);
		} else {
			makeSomeWords();
		}
		List<String> outputList = new ArrayList<String>(tmpResultList);
		
		matchIncludeCharacters(outputList);
		matchIncludeStrings(outputList);
		
		tmpResultList.clear();
		return outputList;
	}
	
	synchronized public List<String> outputWords() {
		return outputWords(null);
	}
	
	
//	public static void main(String[] args) {
//		StringCoolieBuilder test = new StringCoolieBuilder();
//		test.setTmpDictionary(1);
//		test.exceptTheseCharacters('9', '3', '5');
//		test.setIncludeCharacters('1', '2');
////		for(int i = 0; indexList.size() <= maxLength && i <20000 && indexList.size() > 0; i++) {
////			test.makeSubString();
////		}
////		
////		System.out.println(test.tmpResultList);
//		test.makeSomeWords(3);
//		System.out.println(test.outputWords());
//		
//		
//		
//	}
}
