package tp4.nueve;

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

   
    public Semaphore getSemTaxistaConducir() {
        return semTaxistaConducir;
    }
    
    public Semaphore getSemDisponible() {
        return semDisponible;
    }
    public Semaphore getSemSePuedeBajar() {
        return semSePuedeBajar;
    }
    public Semaphore getSemTaxistaDespierto() {
        return semTaxistaDespierto;
    }

    
}
