package tp5.TrenTuristico;

public class ControlTren implements Runnable {
    private int id;
    private Estacion estacion;

    public ControlTren(int id, Estacion estacion) {
        this.id = id;
        this.estacion = estacion;
    }

    @Override
    public void run() {
        try {
            while(true){
                estacion.recorridoTren();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       

    }

}
