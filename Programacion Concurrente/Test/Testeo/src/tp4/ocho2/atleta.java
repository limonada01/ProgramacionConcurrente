package tp4.ocho2;

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
        int tiempo = r.nextInt(High - Low) + Low;
        testigo.correr(numAtleta, tiempo);
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
