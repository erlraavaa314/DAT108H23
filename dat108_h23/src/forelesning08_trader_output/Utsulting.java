package forelesning08_trader_output;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Utsulting {
	private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task = () -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " har låst ressursen.");
                    Thread.sleep(1000); // Simulerer litt arbeid
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread highPriorityThread = new Thread(task);
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        highPriorityThread.start();

        Thread lowPriorityThread = new Thread(task);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        lowPriorityThread.start();
    }
}