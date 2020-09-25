package tp4.uno;

public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        // solo por cuestiones prácticas se trabaja de esta forma
        Thread hilo = new Thread() {
            public void run() {
            ds.f();
            }
        };
        hilo.start();
        ds.g();
        }

}
