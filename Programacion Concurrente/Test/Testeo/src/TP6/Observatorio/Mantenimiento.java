package TP6.Observatorio;

public class Mantenimiento extends Persona implements Runnable {
    public Mantenimiento(int id,Observatorio obs){
        super(id, obs);
    }

    @Override
    public void run() {
        int espera=this.getRandom().nextInt(20)+5;
        try {
            Thread.sleep(espera*1000);
            this.getObservatorio().entrarMantenimiento(this.getId());
            Thread.sleep(6000);
            this.getObservatorio().salirMantenimiento(this.getId());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
