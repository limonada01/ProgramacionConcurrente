package PracticaParcial.Trafico;

public class Auto implements Runnable {
    private int id;
    private char direccion;// N norte, S sur
    private Puente puente;

    public Auto(int id,char direccion, Puente puente) {
        this.id = id;
        this.direccion=direccion;
        this.puente = puente;
    }

    @Override

    public void run() {
        /*if (Math.random() > 0.5) {
            direccion = 'S';
        } else {
            direccion = 'N';
        }*/
        try {
            puente.entrarAlPuente(id, direccion);
            Thread.sleep(3000);
            puente.salirDelPuente(id, direccion);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
