import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
	
	public static void main(String[] args) throws InterruptedException{
		Integer maxQueueSize = Integer.parseInt(args[0]);
		MySynchronizedQueue<Object> queue = new MySynchronizedQueue<Object>(maxQueueSize);
		String userInput;
		int producerId = 1;
		int consumerId = 1;
		ExecutorService service = Executors.newCachedThreadPool();
		while(true){
			System.out.println("Enter p/c/e to create producer/consumer/exit");
			Scanner scanner = new Scanner(System.in);
			userInput = scanner.nextLine();
			if(userInput.equals("p")){
				service.submit(new Producer<Object>(producerId, queue, new Object()));
				producerId++;
			}else if(userInput.equals("c")){
				service.submit(new Consumer<Object>(consumerId, queue));
				consumerId++;
			}else if(userInput.equals("e")){
				System.out.println("Exit app, bye bye");
				scanner.close();
				service.shutdownNow();
				service.awaitTermination(1, TimeUnit.MINUTES);
				return;
			}else{
				System.out.println("Incorrect input, please enter p/c/e to create producer/consumer/exit");
			}
		}
	}
}
