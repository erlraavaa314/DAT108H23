package forelesning10_trader_input;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Laser3 {
	
	public static void main(String[] args) {
		
	}
}



class ToLåser {
    
    // Oppretter en lås
    private final Lock lock1 = new ReentrantLock();

    // Oppretter en annen lås
    private final Lock lock2 = new ReentrantLock();
    
    // Låser lås 1 og venter...
    public void gjorNoe() {
        lock1.lock();
        
        try {
        	System.out.println("Lås 1 er nå låst!");
        	Thread.sleep(3000);
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	lock1.unlock();
        	System.out.println("Lås 1 er nå låst opp!");
        }   
    }
    
    // Låser lås 2 og venter...
    public void gjorNoeAnnet() {
        lock2.lock();
        
        try {
        	System.out.println("Lås 2 er nå låst!");
        	Thread.sleep(3000);
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	lock2.unlock();
        	System.out.println("Lås 2 er nå låst opp!");
        }   
    }

}

