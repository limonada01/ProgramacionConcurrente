package sincronizacionTP.dos;

public class bicho implements Runnable {
    private HP vida;
    private int cantidad;

    public bicho(HP hp, int n) {

        this.vida = hp;
        this.cantidad = n;
    }

    

    public void run() {

        for (int i = 0; i < 4; i++) {
            this.vida.modificarVida(cantidad);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        HP vida = new HP(10);
        bicho Orc = new bicho(vida, -3);
        bicho Cur = new bicho(vida, 3);
        Thread orco = new Thread(Orc, "Orco");
        Thread curandero = new Thread(Cur, "Curandero");
        orco.start();
        curandero.start();

    }

}
