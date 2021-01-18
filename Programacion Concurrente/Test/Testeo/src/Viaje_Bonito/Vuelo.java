package Viaje_Bonito;

public class Vuelo {

    private int horario;
    private int terminal;
    private int puertoEmbarque;
    public Vuelo(int horario, int terminal,int puertoEmbarque){
        
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
