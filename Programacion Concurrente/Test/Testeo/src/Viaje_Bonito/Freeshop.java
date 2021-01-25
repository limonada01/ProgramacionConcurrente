package Viaje_Bonito;

public class Freeshop {
    private int maxPersonas;
    private int cantPersonasActual=0;
    public Freeshop(int maxPersonas){
        this.maxPersonas=maxPersonas;
    }
    
    public synchronized void entrar(int id,char terminal) throws InterruptedException {
        while(cantPersonasActual>=maxPersonas){
            System.out.println("Pasajero "+id+" aun no puede ingresar a la freeshop de la terminal "+terminal);
            this.wait();
        }
        cantPersonasActual++;
        System.out.println("Pasajero "+id+" entró a la Freeshop de la Terminal "+terminal);
    }

    public synchronized void salir(int id, char terminal){
        System.out.println("Pasajero "+id+" salió de la Freeshop de la Terminal "+terminal);
        cantPersonasActual--;
        this.notify();
    }
    

}
