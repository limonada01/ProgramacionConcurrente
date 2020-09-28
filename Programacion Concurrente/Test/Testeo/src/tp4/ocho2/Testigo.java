package tp4.ocho2;

import java.util.concurrent.Semaphore;

public class Testigo {
    private Semaphore testigo;

    public Testigo(){
        this.testigo=new Semaphore(1);
    }

    public Semaphore getTestigo(){
        return this.testigo;
    }

    public void correr(int id, int tiempo) throws InterruptedException {
        testigo.acquire();
        System.out.println("El corredor numero "+ id+" comenzó a correr!");
        Thread.sleep(tiempo*1000);
        System.out.println("El corredor numero "+ id+" entregó el testigo en "+tiempo+" segundos!");
        testigo.release();
    }
}
