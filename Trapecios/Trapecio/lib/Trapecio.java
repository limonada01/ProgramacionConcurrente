import java.rmi.server.SocketSecurityException;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class Trapecio {
    public static double f(double x){
        double res=(double)(Math.pow((Math.E),x));
        return res;
    }
    public static double h(double a,double b,int n){
        double res=(double)((b-a)/n);
        return res;
    }
    public static double sumatoria(double a,double h,int n){
        double res=0;
        double x=a+h;
        for(int i=1;i<=n-1;i++){
            res+=f(x);
            x+=h;
        }
        return res;
    }
    public static void principal(double a,double b,int n){
        double res=(double)((h(a,b,n)/2)*(f(a)+f(b)+2*(double)sumatoria(a,(double)h(a,b,n),n)));
        System.out.println("resultado: "+res);
        

    }
    public static void main(String[] args){
        principal(2.8,5.2,4);
    }
}