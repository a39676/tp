package test;

public class TmpTest6 {

	private static int count;
	static {
		System.out.println("block 1");
		count = 10;
	}

	private int[] data;
	{
		System.out.println("in block 2");
		data = new int[count];
		for (int i = 0; i < count; i++) {
			data[i] = i;
		}
	}

	public static void main(String[] args) {

		System.out.println(count);
		System.out.println("b");
		TmpTest6 t = new TmpTest6();
		System.out.println("c");
		TmpTest6 t2 = new TmpTest6();
		
		System.out.println(t);
		System.out.println(t2);
	}
}
