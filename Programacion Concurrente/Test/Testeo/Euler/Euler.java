import java.util.Scanner;

public class Euler {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        float xi,yi,h,xinicial,xfin;
        System.out.println("Ingrese xi");
        xi=sc.nextFloat();
        System.out.println("Ingrese yi");
        yi=sc.nextFloat();
        
        System.out.println("Ingrese h");
        h=sc.nextFloat();
        
        System.out.println("Ingrese inicio del intervalo");
        xinicial=sc.nextFloat();
        System.out.println("Ingrese fin del intervalo");
        xfin=sc.nextFloat();
        int i=0;
        while(xi<=xfin){
            System.out.println("x"+i+": "+xi+" --- y"+i+": "+yi);
            
            yi=yi+h*(f(xi,yi)); 
            xi+=h;
            i++;
        }
    }/*
    public static float f(float x,float y){

        return (float) (x+0.2*y);
    }*/
    public static float f(float x,float y){

        return (float) (2-(0.4)*(y));
    }
}
