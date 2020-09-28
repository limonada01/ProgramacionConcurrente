package tp4.ocho2;

public class Carrera {
    public static void main(String[] args) {
        Testigo testigo=new Testigo();
        Thread[] atletas=new Thread[4];
        for(int i=0;i<4;i++){
            atletas[i]=new Thread(new atleta(i, testigo),Integer.toString(i));
            atletas[i].start();
        }
        
    }
}
