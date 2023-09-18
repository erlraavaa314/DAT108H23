package forelesning08_trader_output;

public class LiveLock {
    static class Skje {
        private Person eier;

        public Skje(Person person) {
            eier = person;
        }

        public Person getEier() {
            return eier;
        }

        public void setEier(Person person) {
            this.eier = person;
        }

        public synchronized void bruk() {
            System.out.println(eier.getNavn() + " bruker skjeen.");
        }
    }

    static class Person {
        private String navn;
        private boolean erSulten;

        public Person(String navn) {
            this.navn = navn;
            this.erSulten = true;
        }

        public String getNavn() {
            return navn;
        }

        public void spisMed(Skje skje, Person partner) {
            while (erSulten) {
                if (skje.getEier() != this) {
                    try {
                        Thread.sleep(1000); // Simulerer en kort pause
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                if (partner.erSulten) {
                    System.out.println(navn + ": Du spiser først, kjære " + partner.getNavn() + "!");
                    skje.setEier(partner);
                    continue;
                }

                System.out.println(navn + ": Jeg er ferdig å spise.");
                erSulten = false;
                skje.setEier(partner);
            }
        }
    }

    public static void main(String[] args) {
        Person alice = new Person("Alice");
        Person bob = new Person("Bodhild");
        Skje skje = new Skje(alice);

        new Thread(() -> alice.spisMed(skje, bob)).start();
        new Thread(() -> bob.spisMed(skje, alice)).start();
    }
}
