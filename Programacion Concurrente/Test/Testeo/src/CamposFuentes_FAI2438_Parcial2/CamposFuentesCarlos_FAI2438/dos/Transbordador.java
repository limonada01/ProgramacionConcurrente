package CamposFuentesCarlos_FAI2438.dos;

public class Transbordador implements Runnable {
    private Control control;

    public Transbordador(Control control) {
        this.control = control;

    }

    @Override
    public void run() {
        while (true) {
            try {
                control.ir();
                Thread.sleep(5000);
                control.destino();
                control.volver();
                Thread.sleep(5000);
                control.inicio();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
