package forelesning08_trader_output;

public class WaitOgNotify02 {
	
	
	/* TENNISKAMP
	 * 
	 * Et program som simulerer en "tenniskamp"
	 */

    public static void main(String[] args) {
    	
    	// match er felles for begge trådene
        TennisMatch match = new TennisMatch();
        
        // Denne tråden representerer spiller 1:
        Thread player1Thread = new Thread(() -> {
            
        	String name = "Ruud";
        	match.play(name);
        	
        });


        // Denne tråden representerer spiller 2:
        Thread player2Thread = new Thread(() -> {
            
        	String name = "Federer";
        	match.play(name);
        	
        });
        
        // Starter spiller 1
        player1Thread.start();
        

        // Venter litt slik at spiller 1 alltid begynner:
        try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        // Starter spiller 2
        player2Thread.start();
        
        for(int i = 0; i <20; i++) {
        	try {
				Thread.sleep(990);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println(player2Thread.getState());
        }
        
    }
}

class TennisMatch {
	
	// Boolsk feltvariabel som holder styr på retningen til ballen
    private volatile boolean ballMotVenstre = true;
    
	// Boolsk feltvariabel som holder styr på om spillet er ferdig
    private volatile boolean ferdig = false;

    
    // Metoden som beskriver hvordan vi spiller
    public synchronized void play(String name) {
    	
    	while(!ferdig) {	
    		
    		// Spilleren begynner med å forsøke å slå ballen
	    	System.out.println(name + " forsøker å slå til ballen.");
    		
	    	// Vi forsinker utskriften litt...
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    		// Sjansen er 10% for at spilleren bommer
    		if(Math.random() < 0.1) {
    			
    			/* Hvis spilleren bommer avslutter vi spillet:
    			 * 	- Vi skriver at spilleren bommet
    			 * 	- Vi setter variabelen ferdig til true
    			 * 	- Vi avslutter hvile løkken.
    			 */
    			notify();
    			System.out.println(name + " bommet!");
    			ferdig = true;
    			break;
    		}
    		
    		//Om spilleren treffer ballen endrer ballen retning
    		ballMotVenstre = !ballMotVenstre;
    		
    		

    		//Vi informerer om vilken retning ballen går
    		if(ballMotVenstre)
    			System.out.println("Ballen fyker mot venstre!");
    		else
    			System.out.println("Ballen går i full fart mot høyre!");
    		
    		
    		//Vi forsinker utskriften litt.
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		// Varsler den andre spilleren om at spilleren har slått ballen
    		notify();

    		
    		// Printer at vi nå venter på å få ballen tilbake
    		System.out.println(name + " venter på å få ballen tilbake.");
    		
    		
    		// Går inn i ventemodus, venter til den andre spilleren prøver å slå ballen
            try {
    			wait();
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
            
            // Hvis spillet er ferdig bommet den andre spilleren
            if(ferdig) {
            	System.out.println(name + " vant!");
            }
            

            // Hvis ikke er det denne spilleren sin tur. Vi venter forsinker den neste utskriften litt
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	

    	}
    }
}
