import java.util.Scanner;

public class Simpson {
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
        
        for(int i=0;i<nodosY;i++){
            for(int j=0;j<nodosX;j++){
                datos[i][j]=f(h*j,k*i);//calculo f(x0,y0) de cada nodo y lo almaceno en la matriz de datos
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
            areas[i]= calculoSimpson1(h, datos, i); //areas
            
        }
        System.out.println("\n Areas obtenidas de la primera aplicacion: ");
        for(int i=0;i<nodosY;i++){
            System.out.println(areas[i]);
        }
        System.out.println("");
        
        float resultado= calculoSimpson2(k, areas);
        System.out.println("RESULTADO FINAL (VOLUMEN): "+resultado);
    }
    
    public static float f(float x,float y){

        return (float) (Math.pow(x, 2)+Math.pow(y, 2));
    }/*
    public static float f(float x,float y){

        return (float) Math.log(x+2*y);
    }*/
    public static float sumatoriaImpar(float[][] datos,int fila){
        float res=0;
        int posUltimoNodo=datos[0].length-1;
        for(int i=1;i<posUltimoNodo;i++){
            if(i%2!=0){
                res+=datos[fila][i];
            }
        }
        return res;
    }
    public static float sumatoriaPar(float[][] datos,int fila){
        float res=0;
        int posUltimoNodo=datos[0].length-1;
        for(int i=1;i<posUltimoNodo;i++){
            if(i%2==0){
                res+=datos[fila][i];
            }
        }
        return res;
    }
    public static float calculoSimpson1(float h,float[][] datos,int  indice){
        return (float) ((h/3)*(datos[indice][0]+4*sumatoriaImpar(datos, indice)+2*sumatoriaPar(datos, indice)+datos[indice][datos[0].length-1])) ;
    }
    public static float sumatoriaPar(float[] areas){
        float res=0;
        int ultimaPos=areas.length-1;
        for(int i=1;i<ultimaPos;i++){
            if(i%2==0){
                res+=areas[i];
            }
        }
        return res;
    }
    public static float sumatoriaImpar(float[] areas){
        float res=0;
        int ultimaPos=areas.length-1;
        for(int i=1;i<ultimaPos;i++){
            if(i%2!=0){
                res+=areas[i];
            }
        }
        return res;
    }
    public static float calculoSimpson2(float k,float[] areas){
        return (float) ((k/3)*(areas[0]+4*sumatoriaImpar(areas)+2*sumatoriaPar(areas)+areas[areas.length-1])) ;
    }
    
}
