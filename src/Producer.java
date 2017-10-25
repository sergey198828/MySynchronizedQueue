
public class Producer<T> implements Runnable {
	private int id;
	private T product;
	private MySynchronizedQueue<T> queue;
	public Producer(int id, MySynchronizedQueue<T> queue, T product){
		this.id = id;
		this.queue = queue;
		this.product = product;
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
				System.out.println(id + "producer produced product");
				queue.put(product);
				System.out.println(id + "producer put product to queue");				
			}
		} catch (InterruptedException e) {
			System.out.println("Producer"+id+" is closing, bye!");
		}
	}

}
