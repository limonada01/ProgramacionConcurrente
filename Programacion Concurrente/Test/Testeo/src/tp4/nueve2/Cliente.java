package tp4.nueve2;

import java.util.concurrent.Semaphore;

public class Cliente implements Runnable {
    private final int id;
    private final Taxi taxi;

    public Cliente(final int id, Taxi taxi) {
        this.taxi = taxi;
        this.id = id;

    }


    @Override
    public void run() {
        try {
            taxi.subirAlTaxi(id);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    

}
