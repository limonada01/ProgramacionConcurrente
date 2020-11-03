package tp5.TorreControl;



public class Avion implements Runnable {
    private int id;
    private PistaAeropuerto pista;

    public Avion(int id, PistaAeropuerto pista) {
        this.id = id;
        this.pista = pista;
    }

    @Override
    public void run() {

        try {
            pista.aterrizar(id);
            while (true) {
                
                pista.despegar(id);
                int tiempo = (int) (Math.random() * 10) + 3;
                System.out.println("AVION: "+id+": **VOLANDO**");
                Thread.sleep(tiempo * 1000);
                pista.aterrizar(id);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
