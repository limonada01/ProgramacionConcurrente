package tp4.seis2;

public class B extends Letra{
    public B(char letter, Turno turno) {
        super(letter, turno);
    }
    public void mostrar() throws InterruptedException {
        this.getTurno().mostrarB();
    }
}
