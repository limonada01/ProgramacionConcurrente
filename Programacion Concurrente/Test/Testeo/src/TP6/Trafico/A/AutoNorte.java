package TP6.Trafico.A;

public class AutoNorte extends Auto implements Runnable {
    public AutoNorte(int id, Puente puente) {
        super(id, puente);
    }

    @Override
    public void run() {
        boolean exito = false;
        while (!exito) {
            try {
                exito = this.getPuente().pasarNorte(this.getId());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
