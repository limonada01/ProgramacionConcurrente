package tp4.seis2;

public class A extends Letra{
    public A(char letter, Turno turno) {
        super(letter, turno);
    }
    public void mostrar() throws InterruptedException{
        this.getTurno().mostrarA();
    }
}
