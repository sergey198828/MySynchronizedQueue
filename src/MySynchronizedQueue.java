import java.util.LinkedList;
import java.util.Queue;

public class MySynchronizedQueue<T> {
	private Queue<T> queue;
	private final int MAX_SIZE;
	
	public MySynchronizedQueue(int maxSize){
		this.MAX_SIZE = maxSize;
		queue = new LinkedList<T>();
	}
	
	public synchronized void put(T element) throws InterruptedException{
		while(queue.size()==MAX_SIZE){
			this.wait();
		}
		queue.add(element);
		this.notifyAll();		
	}

	public synchronized T get() throws InterruptedException{
		while(queue.isEmpty()){
			this.wait();
		}
		T element = queue.remove();
		this.notifyAll();
		return element;
	}
}
