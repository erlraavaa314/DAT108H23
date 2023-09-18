package forelesning08_trader_output;

public class FisjonEkte {


    public static void main(String[] args) {
    	
    	// Oppretter 1000 Uranatomer
        
    	Fisjon2 fisjonObjekt = new Fisjon2();
    	
    	for(int i = 0; i < 1000; i++) {
    		Thread atom = new Atom2(fisjonObjekt);
    		atom.setName("Atom " + i);
    		atom.start();
        }
    }   
}


class Atom2 extends Thread {
	private Fisjon2 fisjonObjekt;
	
	
	Atom2(Fisjon2 fisjonObjekt){
		this.fisjonObjekt = fisjonObjekt;
	}
	@Override
	public
	void run() {
		this.fisjonObjekt.tryToBoom(this.getName());
	}
}
	
	

class Fisjon2 {
	
	static int noytroner = 1;
	
	public void tryToBoom(String name) {
		if( Fisjon2.canAbsorb()) {
			this.boooooooom(name);
		} else {

	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			tryToBoom(name);
		}
		
	}
	
	
    public void boooooooom(String name) {
    	System.out.println(name + " har fisjonert, BOOOOOOOM! Åhnei, kjedereaksjon! To nye atom kommer til å fisjonere...");
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	Fisjon2.emitt();
    }
    
    public synchronized static void absorb() {
    	Fisjon2.noytroner -=1;
    }
    
    public synchronized static void emitt() {
    	Fisjon2.noytroner +=2;
    }

    public synchronized static boolean canAbsorb() {
    	if (Fisjon2.noytroner > 0)
    	{
    		Fisjon2.absorb();
    		return true;
    	} else {
    		return false;
    	}
    }
}

