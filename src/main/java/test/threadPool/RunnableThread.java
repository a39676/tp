package test.threadPool;

public class RunnableThread implements Runnable {

	private ShareData shateData;
	private String threadName;

	RunnableThread(ShareData startCount, String threadName) {
		this.shateData = startCount;
		this.threadName = threadName;
	}

	public ShareData getShateData() {
		return shateData;
	}

	public void setShateData(ShareData shateData) {
		this.shateData = shateData;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		while (shateData.getCount() < Integer.MAX_VALUE) {
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			addOne();
		}
	}

	public synchronized void addOne() {
		shateData.setCount(shateData.getCount() + 1);
		System.out.println(this.threadName + ": " + shateData.getCount());
	}
}
