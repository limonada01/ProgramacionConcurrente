package tp4.trece;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;



public class Confiteria {
    
    private Semaphore espacio;//para sentarse a comer
    private Semaphore atender;
    private Semaphore comer;
    private Semaphore seleccionComida;
    private Semaphore traerComida;
    private Semaphore cocinar;
    private Semaphore entregarComida;
    public Confiteria(){
        this.espacio=new Semaphore(1,true);
        this.atender=new Semaphore(0);
        this.comer=new Semaphore(0);
        this.seleccionComida=new Semaphore(0);
        this.traerComida=new Semaphore(0);
        this.cocinar=new Semaphore(0);
        this.entregarComida=new Semaphore(0);
    }

   
    public void ofrecerComida() throws InterruptedException {
        this.atender.acquire();
        System.out.println("El mozo le ofrece el menú ");
        this.seleccionComida.release();
    }
    public void sentarse(int id) throws InterruptedException {
        espacio.acquire();
        System.out.println("El empleado "+id+" se sienta en la silla para que lo atiendan");
        atender.release();
        
    }
    public void pedirComida(int id) throws InterruptedException {
        this.seleccionComida.acquire();
        System.out.println("El empleado "+id +" eligió la comida y queda a la espera de que le sirvan...");
        Thread.sleep(4000);
        this.traerComida.release();;
    }
    public void pedidoAlCocinero() throws InterruptedException {
        this.traerComida.acquire();
        System.out.println("El mozo le entrega el pedido al cocinero para que lo prepare...");
        this.cocinar.release();
    }
    public void darComida() throws InterruptedException {
        this.entregarComida.acquire();
        System.out.println("El mozo le entrega la comida y le avisa que puede comenzar a comer");
        this.comer.release();
    }
    public void comer(int id) throws InterruptedException {
        this.comer.acquire();
        System.out.println("El empleado "+id+" comienza a comer");
        Thread.sleep(5000);
        System.out.println("El empleado "+id+" termina de comer, agradece y se va de la confiteria");
        this.espacio.release();
    }
    public void cocinar() throws InterruptedException {
        this.cocinar.acquire();
        System.out.println("El cocinero prepara el pedido");
        Thread.sleep(5000);
        System.out.println("El cocinero entrega el pedido al mozo para que se lo dé al empleado hambriento");
        this.entregarComida.release();
        
    }
}
