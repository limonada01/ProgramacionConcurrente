package tp5.TorreControl;

public class Torre implements Runnable {
    private int id;
    private PistaAeropuerto pista;

    public Torre(int id, PistaAeropuerto pista) {
        this.id = id;
        this.pista = pista;
    }

    @Override
    public void run() {
        while (true) {
            try {
                pista.control();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
