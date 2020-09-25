package tp4.ocho;

import java.util.Random;

public class atleta implements Runnable {

    private int numAtleta;
    private Testigo testigo;
    private Random r;
    private static final int Low = 9;
    private static final int High = 11;
    public atleta(final int numAtleta, final Testigo testigo) {
        this.numAtleta = numAtleta;
        this.testigo = testigo;
        this.r= new Random();
    }

    public void correr() throws InterruptedException {
        this.testigo.getTestigo().acquire();
        System.out.println("El corredor "+ Thread.currentThread().getName()+" comenzó a correr!");
        int tiempo = r.nextInt(High - Low) + Low;
        Thread.sleep(tiempo);
        System.out.println("El corredor "+ Thread.currentThread().getName()+" entregó el testigo en "+tiempo+" segundos!");
        this.testigo.getTestigo().release();
    }

    @Override
    public void run() {

        try {
            this.correr();
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
