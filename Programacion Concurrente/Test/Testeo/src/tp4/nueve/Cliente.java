package tp4.nueve;

import java.util.concurrent.Semaphore;

public class Cliente implements Runnable {
    private final int id;
    private final Taxi taxi;

    public Cliente(final int id, Taxi taxi) {
        this.taxi = taxi;
        this.id = id;

    }

    public void despertarTaxista() throws InterruptedException {
        this.taxi.getSemTaxistaDespierto().release();// despierta al tachero para que conduzca
        System.out.println("El cliente " + this.id + " consiguió despertar al tachero");
        
        this.taxi.getSemTaxistaConducir().release();
    }

    public void bajarse() throws InterruptedException {
        System.out.println("El cliente "+id+" solicita que le avise cuando llegen a destino para poder bajarse");
        this.taxi.getSemSePuedeBajar().acquire();// no se podrá bajar hasta que el taxi arribe al destino
        
        System.out.println("El cliente "+id+" se bajó con exito del TAXI");
        this.taxi.getSemSePuedeBajar().release();

    }

    public void tomarTaxi() throws InterruptedException {
        this.taxi.getSemDisponible().acquire();// intenta tomar el taxi

        System.out.println("El cliente " + this.id + " consiguió subirse al taxi");
        this.despertarTaxista();

        this.bajarse();

    }

    @Override
    public void run() {
        try {
            tomarTaxi();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    

}
