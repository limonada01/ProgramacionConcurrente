package sincronizacionTP.tres;

public class Letra implements Runnable {
    private char letter;
    private char nextLetter;
    private String cadena;
    private Turno turno;

    public Letra(char letter, char nextLetter, String cadena, Turno turno) {
        this.letter = letter;
        this.nextLetter = nextLetter;
        this.cadena = cadena;
        this.turno = turno;
    }

    public void run() {
        boolean flag=true;
        do{
            flag=this.turno.mostrar(letter, nextLetter, cadena);
            
        }while(flag);    
    }

    public static void main(String[] args) throws Exception {
        Turno turno = new Turno();
        Letra a = new Letra('A', 'B', "A", turno);
        Letra b = new Letra('B', 'C', "BB", turno);
        Letra c = new Letra('C', 'A', "CCC", turno);
        Thread aa = new Thread(a, "A");
        Thread bb = new Thread(b, "B");
        Thread cc = new Thread(c, "C");
        aa.start();
        bb.start();
        cc.start();
    }
}
