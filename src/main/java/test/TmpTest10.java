package test;

public class TmpTest10 extends TP {

	public String f1() {
		return "tt";
	}
	
	public static void main(String[] args) {
		TmpTest10 t = new TmpTest10();
		System.out.println(t.f2());
	}
}

class TP {

	public String f1() {
		return "tp";
	}

	public String f2() {
		return f1() + "tp2";
	}
}