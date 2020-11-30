

import java.util.Scanner;

public class Trapecios {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int m,n,nodosX,nodosY;
        float h,k,a,b,c,d,x,y;

        System.out.println("ingrese m");
        m=sc.nextInt();
        System.out.println("ingrese n");
        n=sc.nextInt();

        System.out.println("ingrese inicio de la region en X");
        a=sc.nextFloat();
        System.out.println("ingrese fin de la region en X");
        b=sc.nextFloat();
        System.out.println("ingrese inicio de la region en Y");
        c=sc.nextFloat();
        System.out.println("ingrese fin de la region en Y");
        d=sc.nextFloat();

        h=(b-a)/m;//incremento en X
        k=(d-c)/n;//incremento en Y
        
        nodosX=m+1;
        nodosY=n+1;
        
        float[][] datos=new float[nodosY][nodosX];
        float x0=a;
        float y0=c;     
        for(int i=0;i<nodosY;i++){
            for(int j=0;j<nodosX;j++){
                datos[i][j]=f(x0+h*j,y0+k*i);//calculo f(x0,y0) de cada nodo y lo almaceno en la matriz de datos
            }
        }
        //primera aplicacion , (areas)
        System.out.println("\n Matriz de datos:\n");
        for(int i=0;i<nodosY;i++){
            System.out.println();
            for(int j=0;j<nodosX;j++){
                System.out.print(datos[i][j]+" ");
            }
        }
        float[] areas=new float[nodosY];//por filas
        for(int i=0;i<areas.length;i++){
            areas[i]= calculoTrapecio1(h, datos, i); //areas
            
        }
        System.out.println("\n Areas obtenidas de la primera aplicacion: ");
        for(int i=0;i<nodosY;i++){
            System.out.println(areas[i]);
        }
        System.out.println("");
        
        float resultado= calculoTrapecio2(k, areas);
        System.out.println("RESULTADO FINAL (VOLUMEN): "+resultado);
    }
    /*
    public static float f(float x,float y){

        return (float) (Math.pow(x, 2)+Math.pow(y, 2));
    }*/
    /*
    public static float f(float x,float y){

        return (float) Math.log(x+2*y);
    }*/
    public static float f(float x,float y){

        return (float) x-2*y;
    }
    public static float sumatoria(float[][] datos,int fila){
        float res=0;
        int posUltimoNodo=datos[0].length-1;
        for(int i=1;i<posUltimoNodo;i++){
            res+=datos[fila][i];
        }
        return res;
    }
    public static float calculoTrapecio1(float h,float[][] datos,int  indice){
        return (float) ((h/2)*(datos[indice][0]+2*sumatoria(datos, indice)+datos[indice][datos[0].length-1])) ;
    }
    public static float sumatoria(float[] areas){
        float res=0;
        int ultimaPos=areas.length-1;
        for(int i=1;i<ultimaPos;i++){
            res+=areas[i];
        }
        return res;
    }
    public static float calculoTrapecio2(float k,float[] areas){
        return (float) ((k/2)*(areas[0]+2*sumatoria(areas)+areas[areas.length-1])) ;
    }
    
    
    
}
