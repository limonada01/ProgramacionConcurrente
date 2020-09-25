package tp4.seis;

public class A extends Letra {

    public A(char letter, Turno turno) {
        super(letter, turno);
    }
    public void mostrar() throws InterruptedException{
        this.getTurno().getSemA().acquire();
        System.out.print("A");
        this.getTurno().getSemB().release();
    }
}
