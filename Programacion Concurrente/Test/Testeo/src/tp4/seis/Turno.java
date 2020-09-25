package tp4.seis;

import java.util.concurrent.Semaphore;

public class Turno {
    private Semaphore semA;
    private Semaphore semB;
    private Semaphore semC;

    public Turno() {
        this.semA=new Semaphore(1);
        this.semB=new Semaphore(0);
        this.semC=new Semaphore(0);
    }

    public Semaphore getSemA() {
        return semA;
    }
    public Semaphore getSemB() {
        return semB;
    }
    public Semaphore getSemC() {
        return semC;
    }

    

    

    
/*
    public boolean mostrar(char letra,char next, String cadena) {
        boolean flag = true;
        
            if (this.letter == letra) {
                System.out.print(cadena);
                this.setLetter(next);
                flag=false;
            }
        
        return flag;
    }
    */
}
