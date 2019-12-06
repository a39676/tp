package test;

import java.util.ArrayList;
import java.util.List;

public class TmpTest7 {
	
	public static void main(String[] args) {
		List<String> l = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		
		List<List<String>> mainList = new ArrayList<List<String>>();
		int step = 3;
		List<String> tmpList = null;
		for(int i = 0; i < l.size(); i = i + step) {
			tmpList = new ArrayList<String>();
			if(i + step > l.size()) {
				tmpList = l.subList(i, l.size());
			} else {
				tmpList = l.subList(i, i + step);
			}
			mainList.add(tmpList);
			System.out.println(tmpList);
		}
		
	}

}
