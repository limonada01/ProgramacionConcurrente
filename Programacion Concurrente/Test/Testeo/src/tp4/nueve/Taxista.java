package tp4.nueve;

public class Taxista implements Runnable {
    private int id;
    private Taxi taxi;

    public Taxista(int id, Taxi taxi) {
        this.id = id;
        this.taxi = taxi;
    }

    public void arriboADestino() throws InterruptedException {
        System.out.println("Llegamos al destino, bajese en este mismo instante!!");
        this.taxi.getSemSePuedeBajar().release();
        
        System.out.println("El taxista "+id+ " para de conducir y se pone disponible.");
        this.taxi.getSemTaxistaDespierto().acquire();

        System.out.println("El taxista "+id+ " se toma una siestas hasta que llegue un nuevo pasajero...");
        
        Thread.sleep(5000);
        this.taxi.getSemDisponible().release();
    }

    public void comenzarViaje() throws InterruptedException {
        this.taxi.getSemSePuedeBajar().acquire();//no se puede bajar hasta que el taxista lo permita
        this.taxi.getSemTaxistaConducir().acquire();
        
        System.out.println("El taxista " + this.id + " comienza el viaje...");
        Thread.sleep(5000);
    }

    @Override
    public void run() {
        try {
            while(true){
            comenzarViaje();
            arriboADestino();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    
    

}
