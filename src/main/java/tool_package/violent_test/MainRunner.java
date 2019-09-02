package tool_package.violent_test;

public class MainRunner {
	
	public static void main(String[] args) {
		
		Long startTime = System.currentTimeMillis();
		
//		OptionSet optionSet = new OptionSet();
		
		SubThreadRunner s1 = new SubThreadRunner();
		
//		Thread t1 = new Thread(s1);
//		t1.start();
//		Thread t2 = new Thread(s1);
//		t2.start();
//		Thread t3 = new Thread(s1);
//		t3.start();
//		Thread t4 = new Thread(s1);
//		t4.start();
//		
//		
//		while(t1.isAlive()) {}
		
		s1.singleRun();
		System.out.println("time: " + (System.currentTimeMillis() - startTime));
	}

}
