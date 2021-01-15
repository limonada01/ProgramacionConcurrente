package TP6.ObservatorioMODReadWriteLock;

public class Investigador extends Persona implements Runnable {
    public Investigador(int id, Observatorio obs) {
        super(id, obs);
    }

    @Override
    public void run() {
        int espera=this.getRandom().nextInt(20)+6;
        try {
            Thread.sleep(espera*1000);
            this.getObservatorio().entrarInvestigador(this.getId());
            Thread.sleep(6000);
            this.getObservatorio().salirInvestigador(this.getId());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
