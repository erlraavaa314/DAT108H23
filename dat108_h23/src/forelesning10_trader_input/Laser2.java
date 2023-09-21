package forelesning10_trader_input;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Laser2 {
	
	public static void main(String[] args) {
		
	}
}


class Reentrant {

    private int count = 0;
    // Oppretter en lås
    private final Lock lock = new ReentrantLock();
	
    public void increment() {
    	
    	// Låser låsen
        lock.lock();
    	try {

    		// Låser samme låsen igjen!
    	    lock.lock();
    	    try {
    	        this.count++;
    	    } finally {
    	    	
    	    	// Frigjør den indre låsen
    	        lock.unlock();
    	    }
    	} finally {
    		
    		// Frigjør den ytre låsen 
    	    lock.unlock(); 
    	}

    }
    
    void printCount() {
    	System.out.println(count);
    }

}
