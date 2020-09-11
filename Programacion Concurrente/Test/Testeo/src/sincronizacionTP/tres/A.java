package sincronizacionTP.tres;

public class A extends Letra {

    public A(char letter, Turno turno) {
        super(letter, turno);
    }

    public void mostrar() {
        if (Thread.currentThread().getName().equals("A")) {
            System.out.print(Thread.currentThread().getName());
            this.getTurno().setLetter('B');
            
        } else {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }
}
