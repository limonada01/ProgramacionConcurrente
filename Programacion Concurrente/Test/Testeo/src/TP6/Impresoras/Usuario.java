package TP6.Impresoras;

import java.util.Random;

public class Usuario implements Runnable{
    private int id;
    private Random random;
    private CentroImpresion centro;

    public Usuario(int id,CentroImpresion centro){
        this.id=id;
        this.centro=centro;
        this.random=new Random();
    }

    @Override
    public void run(){
        int tipo=random.nextInt(3); //0 random, 1 imprimir A , 2 imprimir B
        try{
        switch(tipo){
            case 0: {
                int tipoImpresora=centro.imprimir(id);
                Thread.sleep(6000);
                centro.terminarImp(id,tipoImpresora);
            }break;
            case 1:{
                centro.imprimirA(id);
                Thread.sleep(6000);
                centro.terminarImpA(id);
            }break;  
            case 2:{
                centro.imprimirB(id);
                Thread.sleep(6000);
                centro.terminarImpB(id);
            }break;        
        }

        }catch(Exception e){
            e.getStackTrace();
        }
        
        
    }
}
