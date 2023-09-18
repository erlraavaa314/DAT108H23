package forelesning08_trader_output;

public class WaitOgNotify03 {
	
	
	/* Kjedereaksjon
	 * 
	 * Et program som simulerer en kjedereaksjon
	 */

    public static void main(String[] args) {
    	
    	// Oppretter 10 Uranatomer
        
    	Fisjon fisjonObjekt = new Fisjon();
    	
    	for(int i = 0; i < 10; i++) {
    		Thread atom = new Atom(i, fisjonObjekt);
    		atom.setPriority(10);
    		atom.start();
        }
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

		Thread nøytron = new Atom(fisjonObjekt);
		nøytron.setPriority(1);
		nøytron.setDaemon(true);
		nøytron.start();
    	
    }
}

class Atom extends Thread {
	private String navn;
	private Fisjon fisjonObjekt;
	
	
	Atom(int i, Fisjon fisjonObjekt){
		this.navn = "Atom nummer " + i;
		this.fisjonObjekt = fisjonObjekt;
	}
	
	Atom(Fisjon fisjonObjekt){
		this.navn = new String("Nøytron");
		this.fisjonObjekt = fisjonObjekt;
	}
	
	@Override
	public
	void run() {
		fisjonObjekt.boooooooom(this.navn);
	}
}

class Fisjon {
	
    public synchronized void boooooooom(String name) {
    	System.out.println(name + " er klar til å fisjonere");
    	
    	if(name.equals("Nøytron")) {
    		notify();
    	}
    	
    	try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	

    	if(name.equals("Nøytron")) {
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	System.out.println(name + " fisjonerer om ett sekund!");
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    	
    	System.out.println(name + " har fisjonert, BOOOOOOOM!");
    	System.out.println("Åhnei, kjedereaksjon! Et nytt atom kommer til å fisjonere...");
    	notify();
    }
}
