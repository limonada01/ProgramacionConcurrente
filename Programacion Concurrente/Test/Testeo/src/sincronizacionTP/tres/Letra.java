package sincronizacionTP.tres;

public class Letra implements Runnable {
    private char letter;
    private Turno turno;

    public Letra(char letter, Turno turno) {
        this.letter = letter;
        this.turno = turno;
    }

    public void mostrar() {
    };

    public void run() {

        for (int i = 0; i < 5; i++) {
            mostrar();
        }

    }

    public Turno getTurno() {
        return this.turno;
    }

    public static void main(String[] args) throws Exception {
        Turno turno = new Turno();
        A a = new A('A', turno);
        B b = new B('B', turno);
        C c = new C('C', turno);
        Thread aa = new Thread(a, "A");
        Thread bb = new Thread(b, "B");
        Thread cc = new Thread(c, "C");
        aa.start();
        bb.start();
        cc.start();
    }
}
