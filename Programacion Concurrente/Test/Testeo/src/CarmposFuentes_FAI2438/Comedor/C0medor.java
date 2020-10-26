package CarmposFuentes_FAI2438.Comedor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class C0medor {
    private int comederos;
    private int perrosComiendo;
    private int gatosComiendo;
    private ReentrantLock lockComedero;
    private Semaphore perros;
    private Semaphore gatos;

    public C0medor(int comederos) {

        this.comederos = comederos;
        this.perrosComiendo = comederos;
        this.gatosComiendo = comederos;
        this.lockComedero = new ReentrantLock(true);// seccion critica
        this.perros = new Semaphore(1);
        this.gatos = new Semaphore(0);
    }

    public boolean entrarPerro() throws InterruptedException {
        boolean flag = false;
        if (perros.tryAcquire()) {
            lockComedero.lock();
            if (perrosComiendo > 0) {
                perrosComiendo--;
                flag = true;
                perros.release();
            }
            lockComedero.unlock();
        }
        return flag;
    }

    public boolean entrarGato(int id) throws InterruptedException {
        boolean flag = false;
        if (gatos.tryAcquire()) {
            lockComedero.lock();
            if (gatosComiendo > 0) {
                gatosComiendo--;
                System.out.println("El GATO " + id + " entro al comedor y se puso a comer!");
                flag = true;
                gatos.release();
            }
            lockComedero.unlock();
        }
        return flag;
    }

    public void salirPerro() {
        lockComedero.lock();
        perrosComiendo++;
        if (perrosComiendo == comederos) {
            gatos.release();
        }
        lockComedero.unlock();
    }

    public void salirGato(int id) {
        lockComedero.lock();
        gatosComiendo++;
        System.out.println("El GATO " + id + " SALIO del comedor!");
        if (gatosComiendo == comederos) {
            perros.release();
        }
        lockComedero.unlock();
    }
}
