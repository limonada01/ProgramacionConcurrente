package tp5.TrenTuristico;

public class Pasajero implements Runnable {
    private int id;
    private Estacion estacion;

    public Pasajero(int id, Estacion estacion) {
        this.id = id;
        this.estacion = estacion;

    }


    /*
     * @Override public void run() { boolean ticket; try { ticket = comprarTicket();
     * if (ticket) { boolean flag = true; while (flag) { if (estacion.subirAlTren())
     * { System.out.println("El pasajero " + id + " logro subir al tren"); flag =
     * false; } else { System.out.println("El pasajero " + id +
     * " no puede subir al tren porque ya arrancó!"); try { Thread.sleep(10000); }
     * catch (InterruptedException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); } } }
     * 
     * } estacion.bajarDelTren(); } catch (InterruptedException e1) { // TODO
     * Auto-generated catch block e1.printStackTrace(); }
     * 
     * }
     */

    @Override
    public void run() {
        try {
            estacion.comprarTicket(id);
            estacion.subirAlTren(id);
            estacion.bajarDelTren(id);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
