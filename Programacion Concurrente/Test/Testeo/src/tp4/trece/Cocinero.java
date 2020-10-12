package tp4.trece;

public class Cocinero extends Persona implements Runnable {

    public Cocinero(int id, Confiteria confiteria) {
        super(id, confiteria);
    }

    public void cocinar() throws InterruptedException {
        while (true) {
            this.getConfiteria().cocinar();
        }
    }

    @Override
    public void run() {
        try {
            cocinar();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
