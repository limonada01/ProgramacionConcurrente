package tp5.TrenTuristico;

public class Main {
    public static void main(String[] args) {
        int cantAsientos= 3;
        int cantidadPasajeros=6;
        Estacion estacion=new Estacion(cantAsientos);
        Thread controlTren=new Thread(new ControlTren(1, estacion));
        controlTren.start();
        Thread vendedor=new Thread(new VendedorTickets(1, estacion));
        vendedor.start();
        Thread[] pasajeros=new Thread[cantidadPasajeros];
        for(int i=0;i<cantidadPasajeros;i++){
            pasajeros[i]=new Thread(new Pasajero(i, estacion));
            pasajeros[i].start();
        }
    }
}
