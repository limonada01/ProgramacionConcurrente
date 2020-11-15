package TP6.Trafico.A;

import java.util.Random;

public class Auto  {
    private int id;
    private Puente puente;
    private Random random;

    public Auto(int id,Puente puente){
        this.id=id;
        this.puente=puente;
        this.random=new Random();

    }

    public int getId() {
        return id;
    }


    public Puente getPuente() {
        return puente;
    }


    public Random getRandom() {
        return random;
    }


   

}
