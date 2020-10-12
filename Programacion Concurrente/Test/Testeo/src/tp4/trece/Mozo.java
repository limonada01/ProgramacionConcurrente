package tp4.trece;

public class Mozo extends Persona implements Runnable {
    public Mozo(int id, Confiteria confiteria) {
        super(id, confiteria);
    }

    public void atender() throws InterruptedException {
        while (true) {
            this.getConfiteria().ofrecerComida();
            this.getConfiteria().pedidoAlCocinero();
            this.getConfiteria().darComida();
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
