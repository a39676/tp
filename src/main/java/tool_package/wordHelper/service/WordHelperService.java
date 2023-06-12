package tool_package.wordHelper.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import toolPack.ioHandle.FileUtilCustom;
import tool_package.wordHelper.pojo.dto.CustomerDictionaryDTO;
import tool_package.wordHelper.pojo.dto.WordDTO;
import tool_package.wordHelper.pojo.dto.WordDayLineDTO;

public class WordHelperService {

	private String customerDictionaryPathStr = "D:/home/u2/wordHelper/milaDictionary.json";
//	private String customerDictionaryPathStr = "D:/home/u2/wordHelper/customerDictionary.json";

	public static void main(String[] args) {
		WordHelperService s = new WordHelperService();

		CustomerDictionaryDTO dictionary = s.getCustomerDictionaryDTO();

		List<WordDTO> newWordList = new ArrayList<>();
		newWordList.add(new WordDTO("Monday", "周一"));
//		s.addNewWord(dictionary, newWordList);
		s.printRandomWords(dictionary, 2);
	}

	public CustomerDictionaryDTO getCustomerDictionaryDTO() {
		FileUtilCustom fileUtil = new FileUtilCustom();
		String content = fileUtil.getStringFromFile(customerDictionaryPathStr);
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(content, CustomerDictionaryDTO.class);
	}

	public void save(String content) {
		FileUtilCustom fileUtil = new FileUtilCustom();
		fileUtil.byteToFile(content, customerDictionaryPathStr);
	}

	public boolean containWord(CustomerDictionaryDTO dictionary, WordDTO word) {
		if (dictionary.getWordDateLineList() == null || dictionary.getWordDateLineList().isEmpty()) {
			return false;
		}

		for (WordDayLineDTO wordDateLine : dictionary.getWordDateLineList()) {
			for (WordDTO wordExists : wordDateLine.getWordList()) {
				if (wordExists.getEn().equals(word.getEn())) {
					System.out.println(word.toString() + ", exists");
					return true;
				}
			}
		}

		return false;
	}

	public void addNewWord(CustomerDictionaryDTO dictionary, List<WordDTO> inputWords) {
		WordDayLineDTO lastLine = null;

		Set<WordDTO> newWords = new HashSet<>();
		for (WordDTO inputWord : inputWords) {
			if (!containWord(dictionary, inputWord)) {
				newWords.add(inputWord);
			}
		}

		if (newWords.isEmpty()) {
			System.out.println("No new words");
			return;
		}

		System.out.println(newWords.size() + " new words");

		LocalDate today = LocalDate.now();
		boolean newLineFlag = false;

		List<WordDayLineDTO> wordRecordList = dictionary.getWordDateLineList();
		if (wordRecordList == null || wordRecordList.isEmpty()) {
			wordRecordList = new ArrayList<>();
			lastLine = new WordDayLineDTO();
			newLineFlag = true;
			lastLine.setDateStr(today.getYear() + "-" + today.getMonthValue() + "-" + today.getDayOfMonth());
		} else {
			lastLine = wordRecordList.get(wordRecordList.size() - 1);
			String firstLineDateStr = lastLine.getDateStr();
			String todayStr = today.getYear() + "-" + today.getMonthValue() + "-" + today.getDayOfMonth();
			if (!todayStr.equals(firstLineDateStr)) {
				lastLine = new WordDayLineDTO();
				newLineFlag = true;
				lastLine.setDateStr(today.getYear() + "-" + today.getMonthValue() + "-" + today.getDayOfMonth());
			}
		}

		if (lastLine.getWordList() == null) {
			lastLine.setWordList(new ArrayList<>());
		}

		for (WordDTO word : newWords) {
			lastLine.getWordList().add(word);
		}

		if (newLineFlag) {
			wordRecordList.add(lastLine);
		} else {
			wordRecordList.set(wordRecordList.size() - 1, lastLine);
		}

		dictionary.setWordDateLineList(wordRecordList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dictionary);

		save(jsonString);
	}

	public void printRandomWords(CustomerDictionaryDTO dictionary, int count) {
		Random random = new Random();
		int randomIndex = 0;

		for (int i = 0; i < count; i++) {
			randomIndex = random.nextInt(dictionary.getWordDateLineList().size());
			WordDayLineDTO wordRecord = dictionary.getWordDateLineList().get(randomIndex);
			randomIndex = random.nextInt(wordRecord.getWordList().size());
			System.out.println(wordRecord.getWordList().get(randomIndex));
		}
	}
}
