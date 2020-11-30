package CamposFuentesCarlos_FAI2438.uno;

import java.util.concurrent.Semaphore;

public class Buffer {
    private Cola uno;// inicialmente para insertar
    private Cola dos;// inicialmente para extraer
    private Cola insertar;
    private Cola extraer;
    private Semaphore mutexInsertar;
    private Semaphore mutexExtraer;
    private Semaphore mutexOscilar;
    private Semaphore productosParaExtraer;
    public Buffer() {
        this.uno = new Cola();
        this.dos = new Cola();
        this.mutexInsertar = new Semaphore(1);
        this.mutexExtraer = new Semaphore(1);
        this.mutexOscilar=new Semaphore(1);
        this.productosParaExtraer=new Semaphore(0);
        this.insertar = uno;
        this.extraer = dos;
    }

    public void insertar(int id, Object producto) throws InterruptedException {
        mutexInsertar.acquire();

        insertar.poner(producto);
        
        System.out.println("**** Insertor " + id + " inserta un producto");
        mutexOscilar.acquire(); //exclusion
        if (extraer.esVacia()) {
            oscilar();
        }
        mutexOscilar.release();
        productosParaExtraer.release();
        mutexInsertar.release();
    }

    public void extraer(int id) throws InterruptedException {
        productosParaExtraer.acquire();//si hay productos para consumir, consume, sino no puede consumir y espera a que inserten
        mutexExtraer.acquire();//explusion para proteger la cola
        extraer.sacar();
        System.out.println("Extractor " + id + " Consume un producto *****");
        mutexExtraer.release();
        mutexOscilar.acquire(); //exclusion
        if (extraer.esVacia()) {
            oscilar();
        }
        mutexOscilar.release();
        
    }
/*
    public void oscilar() {
        if (uno.esVacia()) {
            insertar=uno;
            extraer = dos;
            System.out.println("COLA UNO VACIA");
            System.out.println("____ahora se Inserta en COLA UNO y se Extrae en COLA DOS____");
        } else {
            insertar=dos;
            extraer = uno;
            System.out.println("COLA DOS VACIA");
            System.out.println("____ahora se Inserta en COLA DOS y se Extrae en COLA UNO____");
        }
    }*/
    public void oscilar(){
        Cola aux=insertar;
        insertar=extraer;
        extraer=aux;
        System.out.println("INTERCAMBIO DE COLAS!");
    }
}
