package tp4.ocho;

import java.util.concurrent.Semaphore;

public class Testigo {
    private Semaphore testigo;

    public Testigo(){
        this.testigo=new Semaphore(1);
    }

    public Semaphore getTestigo(){
        return this.testigo;
    }
}
