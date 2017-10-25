
public class Consumer<T> implements Runnable {

	private int id;
	private MySynchronizedQueue<T> queue;
	public Consumer(int id, MySynchronizedQueue<T> queue){
		this.id = id;
		this.queue = queue;
	}
	@Override
	public void run() {
		long sleepTime;
		try {
			while(true){
				sleepTime = (long)(Math.random()*10000L);
				Thread.sleep(sleepTime);
			    if (Thread.currentThread().isInterrupted()) {
			    	throw new InterruptedException();
			    }
				System.out.println(id + "consumer ready to take product");
				queue.get();
				System.out.println(id + "consumer has taken product");				
			}
		} catch (InterruptedException e) {
			System.out.println("Consumer"+id+" is closing, bye!");
		}
	}

}
