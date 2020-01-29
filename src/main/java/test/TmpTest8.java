package test;

public class TmpTest8 {
	
	public static void main(String[] args) throws Exception {
		
		String desc = "12500000（含加奖2500000）";
		String[] s = desc.split("[^0-9]");
		for(String i : s) {
			System.out.println(i);
		}
	}

}
