package tool_package.violent_test;

import java.util.ArrayList;
import java.util.List;

public class SubThreadRunner extends OptionSet implements Runnable {

	public void run() {

		toMatch();

	}

	public void start() {

	}

	public void singleRun() {
		toMatch();
	}

	private void toMatch() {
		List<String> tmpWords = new ArrayList<String>();

//		Long tWS = 0L;
//		Long tWSP = 0L;
//		Long tMS = 0L;
//		Long tMSP = 0L;
		System.out.println("flag: " + flag);
		while (flag) {
//			tWS = System.currentTimeMillis();
			tmpWords = wordBuilder.outputWords(150);
//			tWSP += System.currentTimeMillis() - tWS;

//			tMS = System.currentTimeMillis();
			for (String word : tmpWords) {
				if (chameleon.matcher(word)) {
					flag = true;
					System.out.println("\nget target word : " + word);
				}
			}
			if (wordBuilder.getTmpWordSize() == 0 || tmpWords.contains(wordBuilder.getFinalWord())) {
				flag = true;
				System.out.println("run over, can not match anything. please reset options");
			}
//			tMSP += System.currentTimeMillis() - tMS;
			if (tmpWords == null || tmpWords.isEmpty()) {
//				System.out.println("get no words, continue...");
			} else {
				System.out.println(tmpWords.get(0));
			}
//			System.out.println(tWSP + ":" + tMSP);

		}
	}
}
