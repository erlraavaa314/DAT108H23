package forelesning07_trader_input;


/*
 * I dette eksempelet har vi en enkel klasse Counter 
 * som har en metode increment som øker en teller. 
 * Vi bruker "synchronized" for å sikre at kun én tråd 
 * kan øke telleren om gangen:
 */
public class Synchronizing1 {
	
    public static void main(String[] args) {
    	
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter value: " + counter.getCount());
    }
}

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
