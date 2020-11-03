package tp5.Comedor2;

public class Main {
    public static void main(String[] args) {
        int cantPerros=6;
        int cantGatos=4;
        int cantidadComederos=4;
        C0medor comedor=new C0medor(cantidadComederos);
        Thread[] gatos=new Thread[cantGatos];
        Thread[] perros=new Thread[cantPerros];
        for(int i=0;i<cantPerros;i++){
            perros[i]=new Thread(new Perro(i,comedor));  
            perros[i].start();
        }
        for(int i=0;i<cantGatos;i++){
            gatos[i]=new Thread(new Gato(i, comedor));
            gatos[i].start();
        }
        
    }
}
