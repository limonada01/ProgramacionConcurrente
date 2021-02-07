package Viaje_Bonito;

public class Temporizador implements Runnable {
    private Aeropuerto aeropuerto;

    public Temporizador(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public void on() throws InterruptedException {
        while (true) {
            aeropuerto.temporizadorON();//espera aca hasta que suba al menos 1 pasajero al tren
            Thread.sleep(1500);// cada 1 hora de simulacion se va a largar el tren en caso de que aun no esté en viaje y tenga al menos a una persona dentro
            aeropuerto.temporizadorShot();//permite que el tren arranque aunq no se haya llenado aun, siempre y cuendo no esté viajando ya
        }
    }

    @Override
    public void run() {

        try {
            on();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
