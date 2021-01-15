package TP6.ObservatorioMODReadWriteLock;



public class Visitante extends Persona implements Runnable {
    
    private boolean silla;
    public Visitante(int id,Observatorio obs){
        super(id, obs);
        this.silla=false;
    }

    @Override
    public void run() {
        int x=this.getRandom().nextInt(8);
        if(x>=6){
            silla=true;
        }
        int espera=this.getRandom().nextInt(20);
        try {
            Thread.sleep(espera*1000);
            this.getObservatorio().entrarVisitante(this.getId(),silla);
            Thread.sleep(5000);
            this.getObservatorio().salirVisitante(this.getId(),silla);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
