package TP6.Observatorio;

import java.util.Random;

public class Persona {
    private int id;
    private Observatorio observatorio;
    private Random random;

    public Persona(int id,Observatorio observatorio){
        this.id=id;
        this.observatorio=observatorio;
        this.random=new Random();
    }

    public int getId() {
        return id;
    }

    public Random getRandom(){
        return random;
    }

    public Observatorio getObservatorio() {
        return observatorio;
    }


}
