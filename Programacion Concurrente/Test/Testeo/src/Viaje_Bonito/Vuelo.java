package Viaje_Bonito;

public class Vuelo {

    private int horario;
    private char terminal;
    private int puertoEmbarque;
    public Vuelo(int horario, char terminal,int puertoEmbarque){
        
        this.horario=horario;
        this.terminal=terminal;
        this.puertoEmbarque=puertoEmbarque;

    }

    

    public int getHorario() {
        return horario;
    }

    public int getTerminal() {
        return terminal;
    }

    public int getPuertoEmbarque() {
        return puertoEmbarque;
    }

    
}
