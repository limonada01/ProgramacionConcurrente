package tp4.seis2;

public class C extends Letra{
    public C(char letter, Turno turno) {
        super(letter, turno);
    }
    public void mostrar() throws InterruptedException {
        this.getTurno().mostrarC();
    }
}
