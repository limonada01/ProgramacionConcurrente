package CarmposFuentes_FAI2438.Promocion;

public class Control implements Runnable {

    private GestorCruce gestorCruce;

    public Control(GestorCruce gestoCruce) {
        this.gestorCruce = gestoCruce;
    }

    public void cambiarSem() throws InterruptedException {
        while (true) {
            Thread.sleep(3000);
            this.gestorCruce.cambiarSemaforo();
        }
    }

    @Override
    public void run() {
        try {
            cambiarSem();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
