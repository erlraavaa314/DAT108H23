package forelesning10_trader_input;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Laser1 {
	
	public static void main(String[] args) {
	

	}
	
}


class SynchCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}


class LockedCounter {
    private int count = 0;
    
    // Oppretter en lås
    private final Lock lock = new ReentrantLock();

    public void increment() {

        // Prøver å få tak i/låse låsen
        lock.lock();

        // Kritisk seksjon i koden
        try {
            count++;
            
        // Sikrer at låsen blir frigitt selv om vi kaster et unntak.
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        
    	// Prøver å få tak i/låse låsen
        lock.lock();

        // Kritisk seksjon i koden
        try {
            return count;
            
        // Sikrer at låsen blir frigitt selv om vi kaster et unntak.
        } finally {
            lock.unlock();
        }
    }
}


