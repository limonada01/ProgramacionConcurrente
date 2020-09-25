package tp4.dos;

public class DualSynch {
    private Object syncObject = new Object();
    int dato = 5;

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            dato = dato * 4;
            System.out.println("f()" + dato);
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                dato = dato + 20;
                System.out.println("g()" + dato);
                Thread.yield();
            }
        }
    }

}
