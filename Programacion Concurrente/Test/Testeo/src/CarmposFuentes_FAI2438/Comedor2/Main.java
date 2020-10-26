package CarmposFuentes_FAI2438.Comedor2;

public class Main {
    public static void main(String[] args) {
        C0medor comedor=new C0medor(6);//2 comederos
        Thread[] gatos=new Thread[20];
        Thread[] perros=new Thread[20];
        for(int i=0;i<20;i++){
            gatos[i]=new Thread(new Gato(i, comedor));
            perros[i]=new Thread(new Perro(i,comedor)); 
            gatos[i].start();
            perros[i].start();
        }
        
    }
}
