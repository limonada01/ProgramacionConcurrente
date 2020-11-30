package CamposFuentesCarlos_FAI2438.dos;

public class Auto implements Runnable {
    private int id;
    private Control control;

    public Auto(int id, Control control) {
        this.id = id;
        this.control = control;
    }

    @Override
    public void run() {
        try {
            control.autoSubir(id);
            control.autoBajar(id);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
