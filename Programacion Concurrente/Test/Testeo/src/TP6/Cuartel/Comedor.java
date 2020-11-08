package TP6.Cuartel;

import java.util.concurrent.Semaphore;

public class Comedor {
    private Semaphore comed;//capacidad del comedor  ----100
    private Semaphore mostradorAlmuerzo;//cantidad de mostradores ------5
    private Semaphore mostradorPostre;//cantidad de mostradores q dan postre -----3
    private Semaphore abridores;//para la gaseosa -----10
    public Comedor(int bandejas,int mostradores,int mostradoresPostre,int abridores){
        this.comed=new Semaphore(bandejas);
        this.mostradorAlmuerzo=new Semaphore(mostradores);
        this.mostradorPostre=new Semaphore(mostradoresPostre);
        this.abridores=new Semaphore(abridores);
        
    }
    
    public void entrarComedor(int id) throws InterruptedException {
        comed.acquire();
        System.out.println("**** Soldado "+id+" ingresa al comedor! ");
    }

    public void tomarBandeja(int id) throws InterruptedException {
        mostradorAlmuerzo.acquire();
        System.out.println("**** Soldado "+id+" toma una bandeja con comida de uno de los mostradores! ");
    }
    public void tomarBebida(int id,int opcion) throws InterruptedException {
        if(opcion == 0){//gaseosa
            abridores.acquire();
            System.out.println("**** Soldado "+id+" escoge una gaseosa, por lo que toma un abridor para abrirla!**** ");
            Thread.sleep(3000);
            System.out.println("**** Soldado "+id+" consigue abrir la gaseosa y devuelve el abridor!****");
            abridores.release();
        }else{
            System.out.println("**** Soldado "+id+" escoge un vaso de agua!**** ");
        }
        mostradorAlmuerzo.release();
    }

    public void buscarPostre(int id) throws InterruptedException {
        mostradorPostre.acquire();
        System.out.println("**** Soldado "+id+" busca un postre de uno de los mostradores!****");
        Thread.sleep(5000);
        mostradorPostre.release();
    }

    public void salirComedor(int id){
        System.out.println("Soldado "+id+" devuelve la bandeja y sale del comedor! ****");
        comed.release();
    }
}
