package sincronizacionTP.tres;

public class B extends Letra {
    public B(char letter, Turno turno) {
        super(letter, turno);
    }

    public void mostrar() {
        if (Thread.currentThread().getName().equals("B")) {
            for (int i = 0; i < 2; i++) {
                System.out.print(Thread.currentThread().getName());
            }
            this.getTurno().setLetter('C');

        } else {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }
}
