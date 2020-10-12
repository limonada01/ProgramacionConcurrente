package tp4.seis2;

public class A extends Letra{
    public A(char letter, Turno turno,int cant) {
        super(letter, turno,cant);
    }
    public void mostrar() throws InterruptedException{
        this.getTurno().mostrarA(this.getCantRepeticiones());
    }
}
