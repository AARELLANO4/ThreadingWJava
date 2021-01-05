/* Alexis Arellano Tuesday November 24/2020 */

package task2;

public class ReverseThread implements Runnable {

	private int threadNum;
	private int nextThread;
	
	public ReverseThread(int a) {
		this.setThreadNum(a);
		this.setNextThread();
	}
	
	private int getThreadNum() {
		return this.threadNum;
	}
	
	private void setThreadNum(int a) {
		this.threadNum = a;
	}
	
	private int getNextThread() {
		return this.nextThread;
	}
	
	private void setNextThread() {
		this.nextThread = getThreadNum() + 1;
	}
	
	private void display() {
		System.out.println("Hello from thread " + getThreadNum());
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if (getThreadNum() < 51) {
				Thread thread = new Thread(new ReverseThread(getNextThread()));
				thread.start();
				thread.join(); // will reverse order of the threads
				display();	
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public static void main(String[] args) {
		Thread reverseThread = new Thread(new ReverseThread(1));
		reverseThread.start();
	}

}
