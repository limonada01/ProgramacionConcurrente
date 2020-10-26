package CarmposFuentes_FAI2438.Comedor2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class C0medor {
    private int comederos;
    private int comederosStatic;
    private ReentrantLock lockComederos;
    private int turno = 0;// 0:gatos 1:perros
    private Semaphore entrada;

    public C0medor(int comederos) {
        this.comederos = comederos;
        this.comederosStatic = comederos;
        this.lockComederos = new ReentrantLock();
        this.entrada = new Semaphore(1);
    }

    public boolean entrarPerro() {
        boolean flag = false;
        if (entrada.tryAcquire()) {
            lockComederos.lock();
            if (comederos > 0 && turno == 1) {
                comederos--;
                flag = true;
            }
            lockComederos.unlock();
            entrada.release();
        }
        return flag;
    }

    public boolean entrarGato() {
        boolean flag = false;
        if (entrada.tryAcquire()) {
            lockComederos.lock();
            if (comederos > 0 && turno == 0) {
                comederos--;
                flag = true;
            }
            lockComederos.unlock();
            entrada.release();
        }
        return flag;
    }

    public void salirComedero(){
        lockComederos.lock();
        comederos++;
        if(comederos==comederosStatic){
            switch(turno){
                case 0: turno=1;break;
                case 1: turno=0;break;
            }
        }
        lockComederos.unlock();
    }
}
