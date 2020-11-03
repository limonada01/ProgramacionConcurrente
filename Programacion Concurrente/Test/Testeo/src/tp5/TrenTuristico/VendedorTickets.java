package tp5.TrenTuristico;

public class VendedorTickets implements Runnable {
    private int id;
    private Estacion estacion;

    public VendedorTickets(int id, Estacion estacion) {
        this.id = id;
        this.estacion = estacion;
    }

    public void vender() throws InterruptedException {
        while (true) {
            estacion.venderTicket();
        }
    }

    @Override
    public void run() {
        try {
            vender();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
