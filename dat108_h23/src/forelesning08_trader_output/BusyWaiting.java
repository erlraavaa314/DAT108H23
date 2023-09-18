package forelesning08_trader_output;


/*
 * BUSY WAITING
 * 
 * Busy waiting er når datamaskinen eller programmet gjentar 
 * en oppgave igjen og igjen uten å gjøre noe annet, selv om 
 * det ikke er nødvendig. 
 * 
 * Dette er som å spørre "er vi der ennå?" hele tiden mens du 
 * kjører på en bilreise i stedet for å slappe av og vente til 
 * du faktisk er fremme. Det er sløsing med tid og ressurser.
 * 
 * Vi skal se på hvordan vi slipper unna dette om litt.
 * 
 */


public class BusyWaiting {
	
	
	/* VOLATAILE
	 * 
	 *  volatile er et nøkkelord i Java som brukes til å deklarere 
	 *  en variabel. Når du merker en variabel som volatile, 
	 *  indikerer du at variabelen kan endres av flere tråder 
	 *  samtidig. Dette betyr at endringer i variabelen fra én 
	 *  tråd blir synlige umiddelbart for andre tråder, og at 
	 *  ingen tråd skal cache verdien av variabelen lokalt.
	 */
    private static volatile boolean condition = false;

    
    public static void main(String[] args) {
        
    	/* Opretter "thread1" som simulerer noe arbeid. Setter 
    	 * deretter variabelen condition til true. 
    	 * 
    	 */
    	
    	Thread thread1 = new Thread(() -> {
            // Simuler noen arbeid
    		System.out.println("Tråd 1 starter et stykke arbeid...");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Tråd 1 er ferdig, setter betingelsen til sann");
            // Sett betingelsen til sann
            condition = true;
        });
    	
    	/* thread2 sjekker kontinuerlig variabelen 
    	 * condition ved hjelp av en while-løkke. 
    	 * Den gjør ingenting annet enn å sjekke 
    	 * inntil condition blir true.
    	 * 
    	 */
        Thread thread2 = new Thread(() -> {
            System.out.println("Tråd 2 venter på at betingelsen skal bli sann...");
            
            long counter = 0;

            // Busy wait inntil betingelsen blir sann
            while (!condition) {
               counter ++;
            }

            System.out.println("Tråd 2 har oppdaget at betingelsen nå er sann.");
            System.out.println("I det sekundet Tråd 2 har ventet så har den sjekket 'condition' " + counter + " ganger");
            
        });

        thread1.start();
        thread2.start();
    }
    
    /* Busy waiting er altså ikke en effektiv måte å vente 
     * på en betingelse i de fleste tilfeller, fordi den 
     * bruker CPU-ressurser unødvendig. En bedre tilnærming 
     * for synkronisering ville være å bruke høyere nivå 
     * konstrukter som wait() og notify(). Disse lar tråder 
     * vente effektivt og signalisere hverandre når betingelser 
     * er oppfylt. Disse alternativene foretrekkes generelt 
     * fremfor busy waiting.
     * 
     */

}
