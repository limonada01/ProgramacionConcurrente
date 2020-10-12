package tp4.seis2;

public class B extends Letra{
    public B(char letter, Turno turno,int cant) {
        super(letter, turno,cant);
    }
    public void mostrar() throws InterruptedException {
        this.getTurno().mostrarB(this.getCantRepeticiones());
    }
}
