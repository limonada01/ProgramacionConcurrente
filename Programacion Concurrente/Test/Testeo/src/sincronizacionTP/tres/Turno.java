package sincronizacionTP.tres;

public class Turno {
    private char letter;

    public Turno() {
        this.letter = 'A';
    }

    public char getLetter() {
        return letter;
    }

    private void setLetter(char letter) {
        this.letter = letter;
    }

    public synchronized boolean mostrar(char letra,char next, String cadena) {
        boolean flag = true;
        
            if (this.letter == letra) {
                System.out.print(cadena);
                this.setLetter(next);
                flag=false;
            }
        
        return flag;
    }
}
