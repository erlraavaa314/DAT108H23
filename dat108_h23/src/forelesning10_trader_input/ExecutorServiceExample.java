package forelesning10_trader_input;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
	
	public static void main(String[] args) {
		int threadsNumber = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
		for (int i = 0; i < 20; i++) {
		    executorService.submit(() -> System.out.println("Hello from: " + Thread.currentThread().getName()));
		}
		executorService.submit(() -> System.out.println("Another task executed by " + Thread.currentThread().getName()));
		
		
		executorService.shutdown();

	}
}


