package forelesning08_trader_output;


/* DEADLOCK (låst situasjon) 
 * 
 * Oppstår når to eller flere tråder sitter fast i en uendelig 
 * ventetilstand, hver venter på en ressurs som holdes av de andre. Her er et eksempel på et enkelt Java-program som demonstrerer en deadlock-situasjon:
 * 
 */

public class Deadlock {
    public static void main(String[] args) {
        
    	// To objekter, kniv og gaffel:
    	Object kniv = new Object();
        Object gaffel = new Object();

        // Thread 1
        Thread thread1 = new Thread(() -> {
        	
        	// Låser kniven
            synchronized (kniv) {
                System.out.println("Thread 1: Tar kniven...");

            	// Venter i ett sekund
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                System.out.println("Thread 1: Prøver å ta gaffelen...");

            	// Prøver å ta gaffelen...
                synchronized (gaffel) {
                    System.out.println("Thread 1: Fikk tak i gaffel!");
                }
            }
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
        	
        	// Låser gaffelen
            synchronized (gaffel) {
                System.out.println("Thread 2: Tar gaffel...");

            	// Venter i ett sekund
                try { Thread.sleep(1000); } catch (InterruptedException e) {}

            	// Prøver å ta kniven også...
                System.out.println("Thread 2:  Prøver å ta kniven...");
                synchronized (kniv) {
                    System.out.println("Thread 2: Fikk ta i kniv!.");
                }
            }
        });
        

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());        
        
        
        thread1.start();
        thread2.start();
        
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());        
        
        try { Thread.sleep(600); } catch (InterruptedException e) {}

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());        
        

        try { Thread.sleep(600); } catch (InterruptedException e) {}
        
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());   

        try { Thread.sleep(5000); } catch (InterruptedException e) {}
        
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());  
        
        
        /*
         * I dette eksempelet:
         * 
         * To tråder (tråd1 og tråd2) deler to ressurser 
         * (kniv og gaffel). Tråd1 tar tak i kniven og 
         * prøver deretter å ta tak i gaffelen. Samtidig 
         * tar tråd2 tak i gaffelen og prøver deretter å 
         * ta tak i kniven.
         * 
         * Begge trådene når et punkt der de holder en 
         * ressurs og venter på den andre, noe som fører 
         * til en "deadlock" (låst situasjon).
         * 
         * Når du kjører dette programmet, vil du 
         * sannsynligvis se at det sitter fast, og 
         * trådene fullfører aldri fordi de er låst 
         * fast i en deadlock.
         * 
         * Deadlocks kan være utfordrende å feilsøke og 
         * forebygge i virkelige applikasjoner, så det er 
         * viktig å designe trådsikker kode og bruke 
         * synkroniseringsmekanismer nøye for å unngå slike 
         * situasjoner.
         */

        
	}


}
