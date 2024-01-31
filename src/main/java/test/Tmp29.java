package test;

import java.util.ArrayList;
import java.util.List;

public class Tmp29 {

	public static void main(String[] args) {
		A2_extends_A1 ele = new A2_extends_A1();
		ele.setId("id 1");
		List<A2_extends_A1> listOfA2 = new ArrayList<>();
		listOfA2.add(ele);

		printList(listOfA2);
	}

	public static <E extends A1> void printList(List<E> eleList) {
//	public static void printList(List<A1> eleList) {
		for (A1 ele : eleList) {
			System.out.println(ele.getId());
			System.out.println(ele.test());
		}
	}
}
