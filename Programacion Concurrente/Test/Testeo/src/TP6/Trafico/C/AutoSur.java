package TP6.Trafico.C;

public class AutoSur extends Auto implements Runnable{
    public AutoSur(int id, GestionaTrafico puente){
        super(id, puente);
    }

    @Override
    public void run() {
        try {
            int tiempo=this.getRandom().nextInt(8)+2;
            Thread.sleep(tiempo*1000);
            this.getPuente().entrarCocheDelSur(this.getId());
            Thread.sleep(3000);
            this.getPuente().salirCocheDelSur(this.getId());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
