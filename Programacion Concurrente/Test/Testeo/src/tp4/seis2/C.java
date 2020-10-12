package tp4.seis2;

public class C extends Letra{
    public C(char letter, Turno turno,int cant) {
        super(letter, turno,cant);
    }
    public void mostrar() throws InterruptedException {
        this.getTurno().mostrarC(this.getCantRepeticiones());
    }
}
