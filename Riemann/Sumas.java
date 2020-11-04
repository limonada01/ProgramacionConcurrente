import java.util.Scanner;

public class Sumas {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double suma=0;
        double x1, x2, y1, y2, incrementoX, incrementoY, base ,altura,area;
        int n,m;
        System.out.println("ingrese el inicio del intervalo en X");
        x1=sc.nextDouble();
        System.out.println("ingrese el fin del intervalo en X");
        x2=sc.nextDouble();
        System.out.println("ingrese el inicio del intervalo en Y");
        y1=sc.nextDouble();
        System.out.println("ingrese el fin del intervalo en Y");
        y2=sc.nextDouble();
        System.out.println("ingrese M");
        m=sc.nextInt();
        System.out.println("ingrese N");
        n=sc.nextInt();
        
        base= Math.abs(x2-x1);
        altura= Math.abs(y2-y1);
        

        area=base/m * altura/n;
        incrementoX=base/n;
        incrementoY=altura/m;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                
                suma=suma+ funcion(x1 + incrementoX * i, y1 + incrementoY * j) * area;

            }
        }
        System.out.println("El volumen aproximado para M= "+m+" y N= "+n+" ES: "+suma);

    }
    
    public static double funcion(double x,double y){
        return 16-Math.pow(x, 2)-2* Math.pow(y, 2);
    }
    /*
    public static double funcion(double x,double y){
        return 4-(Math.pow(x, 2)/2)-(Math.pow(y, 2)/2);
    }*/
}

