package tp4.nueve2;

import java.util.concurrent.Semaphore;

public class Taxi {
    private Semaphore semTaxistaConducir;//inicia en 0, hasta que lo despierten para conducir
    private Semaphore semTaxistaDespierto;//despierto en 1 , dormido en 0
    private Semaphore semDisponible;//si va con un cliente(0) o está disponible (1)
    private Semaphore semSePuedeBajar;// el cliente se puede bajar del taxi(1)
    public Taxi(){
        this.semTaxistaConducir=new Semaphore(0);
        this.semDisponible=new Semaphore(1);
        this.semSePuedeBajar=new Semaphore(1);
        this.semTaxistaDespierto=new Semaphore(0);
    }


    public void subirAlTaxi(int idCliente) throws InterruptedException {
        semDisponible.acquire();
        System.out.println("El cliente " + idCliente + " consiguió subirse al taxi");
        this.despertarTaxista(idCliente);
        this.bajarse(idCliente);
    }
    public void despertarTaxista(int id) throws InterruptedException {
        semTaxistaDespierto.release();// despierta al tachero para que conduzca
        System.out.println("El cliente " + id + " consiguió despertar al tachero");
        
        semTaxistaConducir.release();
    }
    public void bajarse(int id) throws InterruptedException {
        System.out.println("El cliente "+id+" solicita que le avise cuando llegen a destino para poder bajarse");
        semSePuedeBajar.acquire();// no se podrá bajar hasta que el taxi arribe al destino
        
        System.out.println("El cliente "+id+" se bajó con exito del TAXI");
        semSePuedeBajar.release();

    }
    public void comenzarViaje(int id) throws InterruptedException {
        semSePuedeBajar.acquire();//no se puede bajar hasta que el taxista lo permita
        semTaxistaConducir.acquire();
        
        System.out.println("El taxista " + id + " comienza el viaje...");
        Thread.sleep(5000);
    }
    public void arriboADestino(int id) throws InterruptedException {
        System.out.println("Llegamos al destino, bajese en este mismo instante!!");
        semSePuedeBajar.release();
        
        System.out.println("El taxista "+id+ " para de conducir y se pone disponible.");
        semTaxistaDespierto.acquire();

        System.out.println("El taxista "+id+ " se toma una siestas hasta que llegue un nuevo pasajero...");
        
        Thread.sleep(5000);
        semDisponible.release();
    }
}
