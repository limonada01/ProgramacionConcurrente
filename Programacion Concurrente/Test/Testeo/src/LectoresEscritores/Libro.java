package LectoresEscritores;

import java.util.concurrent.locks.ReentrantLock;

public class Libro {
    private int cantPaginasEscritas;
    private int cantLectoresActual;
    private final int maxPaginas;
    private boolean escribirLibro;//hay un escritor que quiere escribir
    private boolean escribiendo;//hay un escritor escribiendo
    public Libro(int maxPaginas){
        this.cantPaginasEscritas=0;
        this.cantLectoresActual=0;
        this.maxPaginas=maxPaginas;
        this.escribirLibro=false;
        this.escribiendo=false;
    }

    public synchronized boolean finalizado(){
        return cantPaginasEscritas==maxPaginas; //retorna true si estan todas las paginas escritas
    }
   
    public synchronized void empezarLeer(int id) throws InterruptedException {
        while(!hayEscrito() || escribirLibro || escribiendo){
            System.out.println("** Lector "+id+" no puede leer aun! **" );
            this.wait();  
        }
        System.out.println("** Lector "+id+" empieza a leer! **" );
        cantLectoresActual++;
    }

    public synchronized void empezarEscribir(int id) throws InterruptedException {
        while( cantLectoresActual!=0 || escribiendo ){
            escribirLibro=true;
            System.out.println("** Escritor "+id+" no puede escribir aun! **" );
            this.wait();
        }
        escribirLibro=false;
        escribiendo=true;
        System.out.println("** Escritor "+id+" empieza a escribir! **" );
    }

    public boolean hayEscrito(){
        return cantPaginasEscritas!=0; //true si hay paginas escritas
    }

    public synchronized void terminarLeer(int id){
        cantLectoresActual--;
        System.out.println("** Lector "+id+" Termina de leer! **" );
        notifyAll();
    }

    public synchronized void terminarEscribir(int id){
        cantPaginasEscritas++;
        System.out.println("** Escritor "+id+" Termina de escribir! **" );
  
        escribiendo=false;
        notifyAll();
    }
}
