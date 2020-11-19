package tp5.CentroHemoterapia2;

public class Main {
    public static void main(String[] args) {
        int cantPersonas=20;
        int cantCamillas=4;
        int cantRevistas=3;
        Centro centro=new Centro(cantCamillas,cantRevistas);
        
        Thread[] personas=new Thread[cantPersonas];

        for(int i=0;i<cantPersonas;i++){
            personas[i]=new Thread(new Persona(i+1,centro));
            personas[i].start();
        }
    }
}
