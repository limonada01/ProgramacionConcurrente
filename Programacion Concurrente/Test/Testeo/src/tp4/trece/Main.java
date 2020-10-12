package tp4.trece;

public class Main {
    public static void main(String[] args) {
        Confiteria confiteria=new Confiteria();
        Thread mozo=new Thread(new Mozo(999,confiteria));
        mozo.start();
        Thread cocinero=new Thread(new Cocinero(111,confiteria));
        cocinero.start();
        Thread[] empleados=new Thread[6];
        for(int i=0;i<6;i++){
            empleados[i]=new Thread(new Empleado(i, confiteria));
            empleados[i].start();
        }
    }
}
