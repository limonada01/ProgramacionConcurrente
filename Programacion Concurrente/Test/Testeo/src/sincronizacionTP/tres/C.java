package sincronizacionTP.tres;

public class C extends Letra {
    public C(char letter, Turno turno) {
        super(letter, turno);
    }

    public void mostrar() {
        if (Thread.currentThread().getName().equals("C")) {
            for (int i = 0; i < 3; i++) {
                System.out.print(Thread.currentThread().getName());
            }
            this.getTurno().setLetter('A');
        } else {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                // TODO: handle exception
            }

        }

    }
}
