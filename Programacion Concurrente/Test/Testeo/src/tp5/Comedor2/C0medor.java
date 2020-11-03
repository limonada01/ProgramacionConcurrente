package tp5.Comedor2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.midi.Soundbank;

public class C0medor {
    private Semaphore comederos;
    private int totalComederos;
    private ReentrantLock lock;
    private int flagEspecie = -1;// 0 gatos | 1 perros
    private boolean otraEspecieEspera;// si hay otra especie intentando entrar y no pudo
    private boolean mismaEspecieEspera;// si hay de la misma especie intentado entrar y no pudo

    public C0medor(int comederos) {
        this.comederos = new Semaphore(comederos);
        this.lock = new ReentrantLock();
        this.totalComederos = comederos;
        this.otraEspecieEspera = false;
        this.mismaEspecieEspera = false;
    }

    public boolean entrarComer(int especie) throws InterruptedException {
        lock.lock();
        boolean flag = false;
        if ((especie == flagEspecie || flagEspecie == -1) && !otraEspecieEspera) {
            if (flagEspecie == -1) {
                flagEspecie = especie;
            }
            switch(flagEspecie){
                case 1:if(comederos.tryAcquire(2)){
                    flag=true;
                }break;
                case 0:if(comederos.tryAcquire(1)){
                    flag=true;
                }break;
            }
        } else {
            if (especie != flagEspecie && comederos.availablePermits() == 0) {
                otraEspecieEspera = true;
            } 
        }
        if ( flag==false && especie == flagEspecie) {
            mismaEspecieEspera = true;
        }
        lock.unlock();
        return flag;
    }

    public void salirComedero() throws InterruptedException {
        lock.lock();
        if (flagEspecie == 1) {
            comederos.release(2);
        } else {
            comederos.release(1);
        }
        if (comederos.availablePermits() == totalComederos && (otraEspecieEspera || !mismaEspecieEspera)) {
            cambiarEspecie();
        }
        lock.unlock();
    }

    public void cambiarEspecie() {
        if (flagEspecie == 0) {
            System.out.println("Cambio TURNO: ahora entran PERROS");
            flagEspecie = 1;
        } else {
            System.out.println("Cambio TURNO: ahora entran GATOS");
            flagEspecie = 0;
        }
        otraEspecieEspera = false;
        mismaEspecieEspera = false;
    }
}
