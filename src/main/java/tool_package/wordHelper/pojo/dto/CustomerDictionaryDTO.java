package tool_package.wordHelper.pojo.dto;

import java.util.List;

public class CustomerDictionaryDTO {

	private List<WordDayLineDTO> wordDateLineList;

	public List<WordDayLineDTO> getWordDateLineList() {
		return wordDateLineList;
	}

	public void setWordDateLineList(List<WordDayLineDTO> wordDateLineList) {
		this.wordDateLineList = wordDateLineList;
	}

	@Override
	public String toString() {
		return "CustomerDictionaryDTO [wordDateLineList=" + wordDateLineList + "]";
	}

}
