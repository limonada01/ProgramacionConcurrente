package tp4.seis;

public class Letra implements Runnable {
    private char letter;

    private Turno turno;

    public Letra(char letter, Turno turno) {
        this.letter = letter;
        this.turno = turno;
    }

    public Turno getTurno() {
        return this.turno;
    }

    public void mostrar() throws InterruptedException {
    }

    public void run() {
        boolean flag = true;
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
