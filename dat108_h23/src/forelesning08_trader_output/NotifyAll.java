package forelesning08_trader_output;

public class NotifyAll {
    public static void main(String[] args) {
        Bankkonto konto = new Bankkonto();

        Runnable innskuddsTråd = () -> {
            for (int i = 0; i < 5; i++) {
                konto.gjorInnskudd(1000.0);
            }
        };

        Runnable uttaksTråd = () -> {
            for (int i = 0; i < 20; i++) {
			    konto.gjorUttak(500.0);
			}
        };

        Thread tråd1 = new Thread(innskuddsTråd);
        Thread tråd2 = new Thread(uttaksTråd);
        Thread tråd3 = new Thread(uttaksTråd);

        tråd1.start();
        tråd2.start();
        tråd3.start();
    }
}

class Bankkonto {
	
	private double paKonto = 0;

	public synchronized void gjorInnskudd(double d) {
		System.out.println("Setter in " + d + "kroner om ett sekund");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.paKonto += d;
		notifyAll();
		
	}

	public synchronized void gjorUttak(double d) {
		
		System.out.println("Prøver ta ut " + d + "kroner");
		while (this.paKonto < d) {
			try {

				System.out.println("Det var ikke nok penger på konto... ");
				notifyAll();
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Tok ut " + d + "kroner");
		
		this.paKonto -= d;
		
	}
	
	
}