package CamposFuentesCarlos_FAI2438.uno;

public class Main {
    public static void main(String[] args) {
        int cantInsertores=3;
        int cantExtractores=5;
        
        Buffer buffer=new Buffer();
        Thread[] insertores=new Thread[cantInsertores];
        Thread[] extractores=new Thread[cantExtractores];

        for(int i=0;i<cantInsertores;i++){
            insertores[i]=new Thread(new Insertor(i+1, buffer));
            insertores[i].start();
        }
        for(int i=0;i<cantExtractores;i++){
            extractores[i]=new Thread(new Extractor(i+1, buffer));
            extractores[i].start();
        }


    }
}
