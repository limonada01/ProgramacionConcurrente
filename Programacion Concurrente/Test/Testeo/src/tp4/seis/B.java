package tp4.seis;

public class B extends Letra {
    public B(char letter, Turno turno) {
        super(letter, turno);
    }
    public void mostrar() throws InterruptedException {
        this.getTurno().getSemB().acquire();
        System.out.print("BB");
        this.getTurno().getSemC().release();
    }
}
