package tp4.seis2;

public class Letra implements Runnable {
    private char letter;
    private int cantidadRepeticiones;
    private Turno turno;

    public Letra(char letter, Turno turno,int cant) {
        this.letter = letter;
        this.turno = turno;
        this.cantidadRepeticiones=cant;
    }

    public Turno getTurno() {
        return this.turno;
    }
    public int getCantRepeticiones(){
        return this.cantidadRepeticiones;
    }

    public void mostrar() throws InterruptedException {
    }

    public void run() {
        //boolean flag = true;
        for (int i = 0; i < 3; i++) {
            try {
                this.mostrar();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }   
    }

    public static void main(String[] args) throws Exception {
        Turno turno = new Turno();
        A a = new A('A', turno,1);
        B b = new B('B', turno,2);
        C c = new C('C', turno,3);
        Thread aa = new Thread(a, "A");
        Thread bb = new Thread(b, "B");
        Thread cc = new Thread(c, "C");
        aa.start();
        bb.start();
        cc.start();
    }
}
