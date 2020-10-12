package tp4.doce;

import java.util.concurrent.locks.ReentrantLock;

public class Jaula {
    private ReentrantLock comida;
    private ReentrantLock rueda;
    private ReentrantLock hamaca;
    
    public Jaula(){
        this.comida=new ReentrantLock(true);
        this.rueda=new ReentrantLock(true);
        this.hamaca=new ReentrantLock(true);
    }
     
    public void comer(int id) throws InterruptedException {
        comida.lock();
        System.out.println("El hasmter N° "+id+" está comiendo...");
		Thread.sleep(2000);
        System.out.println("El hasmter N° "+id+" termina de comer!");
        comida.unlock();
    }
    public void correr(int id) throws InterruptedException {
        rueda.lock();
        System.out.println("El hasmter N° "+id+" está corriendo en la rueda...");
		Thread.sleep(3000);
        System.out.println("El hasmter N° "+id+" termina de correr en la rueda!");
        rueda.unlock();
    }
    public void descansar(int id) throws InterruptedException {
        hamaca.lock();
        System.out.println("El hasmter N° "+id+" está descansando en la hamaca...");
		Thread.sleep(5000);
        System.out.println("El hasmter N° "+id+" termina de descansar!");
        hamaca.unlock();
    }
}
