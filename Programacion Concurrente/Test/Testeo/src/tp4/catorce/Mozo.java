package tp4.catorce;

public class Mozo extends Persona implements Runnable {
    public Mozo(int id, Confiteria confiteria) {
        super(id, confiteria);
    }

    public void atender() throws InterruptedException {
        while (true) {
            this.getConfiteria().servirBebida();
            
        }
    }

    @Override
    public void run() {
        try {
            this.atender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
