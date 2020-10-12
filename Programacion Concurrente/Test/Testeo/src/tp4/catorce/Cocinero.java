package tp4.catorce;

public class Cocinero extends Persona implements Runnable {

    public Cocinero(int id, Confiteria confiteria) {
        super(id, confiteria);
    }

    public void atender() throws InterruptedException {
        while (true) {
            this.getConfiteria().servirComida();
        }
    }

    @Override
    public void run() {
        try {
            atender();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
