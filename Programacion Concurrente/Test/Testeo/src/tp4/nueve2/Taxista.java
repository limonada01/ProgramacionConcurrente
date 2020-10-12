package tp4.nueve2;

public class Taxista implements Runnable {
    private int id;
    private Taxi taxi;

    public Taxista(int id, Taxi taxi) {
        this.id = id;
        this.taxi = taxi;
    }


    @Override
    public void run() {
        try {
            while(true){
            taxi.comenzarViaje(id);
            taxi.arriboADestino(id);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    
    

}
