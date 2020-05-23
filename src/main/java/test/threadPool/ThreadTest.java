package test.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadTest {

	public static void main(String[] args) {
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		
		ShareData sd = new ShareData();
		sd.setCount(0);
		RunnableThread r1 = new RunnableThread(sd, "1");
		RunnableThread r2 = new RunnableThread(sd, "2");
		RunnableThread r3 = new RunnableThread(sd, "3");
		
		r1.setThreadName("r1");
		r2.setThreadName("r2");
		r3.setThreadName("r3");
		
		executor.execute(r1);
		executor.execute(r2);
		executor.execute(r3);
	}
}
