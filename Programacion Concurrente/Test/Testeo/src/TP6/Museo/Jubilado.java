package TP6.Museo;

public class Jubilado extends Persona implements Runnable{
    public Jubilado(int id,GestorSala gestor){
        super(id, gestor);
    }
    @Override
    public void run() {
        
        try {
            this.getGestor().entrarSalaJubilado(this.getId());
            int tiempoDentro=this.getR().nextInt(10)*1000;
            Thread.sleep(tiempoDentro);
            this.getGestor().salirSala(this.getId());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
