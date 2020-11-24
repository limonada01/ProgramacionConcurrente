package TP6.Trafico.B;



public class GestionaTrafico {

    private boolean verdeNorte;
    private boolean verdeSur;
    //private int cantActualPasandoNorte;//cantidad de autos del norte simultaneos en el puente
    //private int cantActualPasandoSur;//cantidad de autos del sur simultaneos en el puente
    private int cantActualPasando;
    private Cola cola;
    public GestionaTrafico(){ 
        //this.cantActualPasandoNorte=0;
        //this.cantActualPasandoSur=0;
        this.cantActualPasando=0;
        this.verdeNorte=true;//semaforo arranca en verde para los que vienen del sur
        this.verdeSur=false;
        this.cola=new Cola();
    }

    public synchronized void entrarCocheDelNorte(int id) throws InterruptedException {
        while(!verdeNorte ){//si Norte esta en rojo 
            this.wait();
        }
        cola.poner(id);
        this.cantActualPasando++;
        System.out.println("*** El auto "+id+" consigue entrar al puente desde el NORTE ");
    }

    public synchronized void salirCocheDelNorte(int id) throws InterruptedException {
        while((int)cola.obtenerFrente()!=id){
            this.wait();//espera a q sea su turno para salir del puente y respetar las posiciones en las q entraron al puente
        }
        cola.sacar();
        this.cantActualPasando--;
        this.notifyAll();//notifica a los autos que estan esperando para salir 
        System.out.println(" El auto "+id+" sale del puente. Viene del NORTE *** ");
        if(this.cantActualPasando==0){
            this.notifyAll();//busca notificar el hilo del semaforo, para q ponga en verde a el SUR
        }
    }

    public synchronized void entrarCocheDelSur(int id) throws InterruptedException {
        while(!verdeSur ){ //si Sur esta en rojo 
            this.wait();
        }
        cola.poner(id);
        this.cantActualPasando++;
        System.out.println("*** El auto "+id+" consigue entrar al puente desde el SUR ");
    }

    public synchronized void salirCocheDelSur(int id) throws InterruptedException {
        while((int)cola.obtenerFrente()!=id){
            this.wait();//espera a q sea su turno para salir del puente y respetar las posiciones en las q entraron al puente
        }
        cola.sacar();
        this.cantActualPasando--;
        this.notifyAll();//notifica a los autos que estan esperando para salir 
        System.out.println(" El auto "+id+" sale del puente. Viene del Sur *** ");
        if(this.cantActualPasando==0){
            this.notifyAll();//busca notificar el hilo del semaforo, para q ponga en verde a el NORTE
        }
    }

    public synchronized void cambiarSemaforo() throws InterruptedException {
        if(this.verdeSur){
            this.verdeSur=false;
            System.out.println("* SEMAFORO EN ROJO PARA LOS DEL SUR *");
            while(cantActualPasando != 0){
                this.wait();//espera hasta q el puente esté vacio
            }
            System.out.println("* SEMAFORO EN VERDE PARA LOS DEL NORTE *");
            this.verdeNorte=true;//recien cuando sale el ultimo que estaba pasando 
        }else{
            this.verdeNorte=false;
            System.out.println("* SEMAFORO EN ROJO PARA LOS DEL NORTE *");
            while(cantActualPasando != 0){
                this.wait();
            }
            System.out.println("* SEMAFORO EN VERDE PARA LOS DEL SUR *");
            this.verdeSur=true;//recien cuando sale el ultimo que estaba pasando 
        }
    }
}
