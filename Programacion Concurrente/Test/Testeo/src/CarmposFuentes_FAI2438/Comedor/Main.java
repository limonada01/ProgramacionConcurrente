package CarmposFuentes_FAI2438.Comedor;

public class Main {
    public static void main(String[] args) {
        C0medor comedor=new C0medor(2);//2 comederos
        Thread[] gatos=new Thread[6];
        Thread[] perros=new Thread[6];
        for(int i=0;i<6;i++){
            gatos[i]=new Thread(new Gato(i, comedor));
            perros[i]=new Thread(new Perro(i,comedor)); 
            gatos[i].start();
            perros[i].start();
        }
        
    }
}
