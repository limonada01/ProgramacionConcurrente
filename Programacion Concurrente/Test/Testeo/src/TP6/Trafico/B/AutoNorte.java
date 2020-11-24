package TP6.Trafico.B;

public class AutoNorte extends Auto implements Runnable {
    public AutoNorte(int id, GestionaTrafico puente) {
        super(id, puente);
    }

    @Override
    public void run() {

        try {
            int tiempo=this.getRandom().nextInt(8)+2;
            Thread.sleep(tiempo*1000);
            this.getPuente().entrarCocheDelNorte(this.getId());
            Thread.sleep(3000);
            this.getPuente().salirCocheDelNorte(this.getId());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
