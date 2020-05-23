package test.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadTest2 {

	public static void main(String[] args) throws InterruptedException {
		
		int maxThreadPoolSize = 3;
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxThreadPoolSize);
		
		ShareData sd = new ShareData();
		sd.setCount(0);
		
		for(int i = 1; i <= maxThreadPoolSize; i++) {
			executor.execute(new RunnableThread(sd, String.valueOf(i)));
			Thread.sleep(2000L);
		}
	}
}
