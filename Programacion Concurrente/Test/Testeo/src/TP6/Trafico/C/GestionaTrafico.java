package TP6.Trafico.C;



public class GestionaTrafico {

    private int direccionSemaforo=-1;//0 Norte, 1 Sur
    private boolean otraDireccionEspera;//hay al menos un auto de la otra direccion q espera para pasar
    private boolean hayColaMismaDireccion;//si aun quedan autos que quieren pasar en una misma direccion
    private boolean cambiarSemaforo;
    private int maxAutosPuente;//cantidad maxima de autos que pueden pasar en una direccion, luego cambia semaforo (10)
    private int cantPasaron;//lleva el conteo de cuantos autos entraron al puente desde una misma direccion en un ciclo
    private int cantEnPuenteActual;//lleva el conteo de cuantos autos hay actualmente en el puente
    private Cola cola;
    public GestionaTrafico(int maxAutosPuente){ 
        
        this.cantPasaron=0;
        this.cantEnPuenteActual=0;
        this.maxAutosPuente=maxAutosPuente;
        this.cola=new Cola();
        this.otraDireccionEspera=false;
        this.hayColaMismaDireccion=false;
        this.cambiarSemaforo=false;
    }

    public synchronized void entrarCocheDelNorte(int id) throws InterruptedException {
        if(direccionSemaforo==-1){//caso particular para el inicio de la ejecucion, setea verde al primero q llegue
            direccionSemaforo=0;//arrancan los del norte
        }
        while(direccionSemaforo!=0 || cantPasaron>=maxAutosPuente ){//si Norte esta en rojo o ya esta al maximo la capacidad
            if(direccionSemaforo==0){
                hayColaMismaDireccion=true;//aviso que aun hay autos que vienen de esta direccion
            }
            if(direccionSemaforo!=0){
                otraDireccionEspera=true;//indico que hay hilos esperando para pasar de la direccion contraria
            }
            this.wait();
        }
        this.cantEnPuenteActual++;
        cola.poner(id);
        this.cantPasaron++;
        System.out.println("*** El auto "+id+" consigue entrar al puente desde el NORTE ");
        if((cantPasaron>=maxAutosPuente || !hayColaMismaDireccion  ) && otraDireccionEspera ){ 
            //si ( se llegó al limite de autos que pasaron o no hay mas autos queriendo pasar) y hay autos esperando en la otra direccion
            cambiarSemaforo=true;
        }
        
    }

    public synchronized void salirCocheDelNorte(int id) throws InterruptedException {
        while((int)cola.obtenerFrente()!=id){
            this.wait();//espera a q sea su turno para salir del puente y respetar las posiciones en las q entraron al puente
        }
        cola.sacar();
        this.cantEnPuenteActual--;
        System.out.println(" El auto "+id+" sale del puente. Viene del NORTE *** ");
        this.notifyAll();//notifica a los autos que estan esperando para salir 
        
        if(cambiarSemaforo && cantEnPuenteActual==0){
            cambiarSemaforo();//pone en verde el semaforo de la otra direccion
        }else if(!cambiarSemaforo && cantPasaron>=maxAutosPuente && !otraDireccionEspera){
            cantPasaron--;
            this.notifyAll();
        }
    }

    public synchronized void entrarCocheDelSur(int id) throws InterruptedException {
        if(direccionSemaforo==-1){//caso particular para el inicio de la ejecucion, setea verde al primero q llegue
            direccionSemaforo=1;//arrancan los del sur
        }
        
        while(direccionSemaforo!=1 || cantPasaron>=maxAutosPuente ){ //si Sur esta en rojo o ya esta al maximo la capacidad
            if(direccionSemaforo==1){
                hayColaMismaDireccion=true;
            }
            if(direccionSemaforo!=1){
                otraDireccionEspera=true;
            }
            
            this.wait();
        }
        
        this.cantEnPuenteActual++;
        cola.poner(id);
        this.cantPasaron++;
        System.out.println("*** El auto "+id+" consigue entrar al puente desde el SUR ");
        if((cantPasaron>=maxAutosPuente || !hayColaMismaDireccion  ) && otraDireccionEspera ){
            //si ( se llegó al limite de autos que pasaron o no hay mas autos queriendo pasar) y hay autos esperando en la otra direccion
            cambiarSemaforo=true;
        }
        
    }

    public synchronized void salirCocheDelSur(int id) throws InterruptedException {
        while((int)cola.obtenerFrente()!=id){
            this.wait();//espera a q sea su turno para salir del puente y respetar las posiciones en las q entraron al puente
        }
        cola.sacar();
        this.cantEnPuenteActual--;
        System.out.println(" El auto "+id+" sale del puente. Viene del Sur *** ");
        this.notifyAll();//notifica a los autos que estan esperando para salir 
        if(cambiarSemaforo && cantEnPuenteActual==0){
            cambiarSemaforo();//pone en verde el semaforo de la otra direccion
        }else if(!cambiarSemaforo && cantPasaron>=maxAutosPuente && !otraDireccionEspera){
            cantPasaron--;
            this.notifyAll();
        }
    }
    public synchronized void cambiarSemaforo() throws InterruptedException {
        if(this.direccionSemaforo==0){
            this.direccionSemaforo=1;
            System.out.println("* SEMAFORO EN VERDE PARA LOS DEL SUR *");
        }else{
            this.direccionSemaforo=0;
            System.out.println("* SEMAFORO EN VERDE PARA LOS DEL NORTE *");
        }
        cantPasaron=0;//pongo en 0 el contador de autos que pasaron en este ciclo
        this.cambiarSemaforo=false;
        this.hayColaMismaDireccion=false;
        this.otraDireccionEspera=false;
    }

   
    
}
