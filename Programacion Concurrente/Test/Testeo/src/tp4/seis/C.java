package tp4.seis;

public class C extends Letra{
    public C(char letter, Turno turno) {
        super(letter, turno);
    }
    public void mostrar() throws InterruptedException {
        this.getTurno().getSemC().acquire();
        System.out.print("CCC");
        this.getTurno().getSemA().release();
    }
}
