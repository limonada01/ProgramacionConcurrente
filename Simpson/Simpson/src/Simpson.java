public class Simpson {
    public static double f(double x){
        double res=(double)(5*((double)(Math.pow(x,2)))+((double)2*x)-3);
        return res;
    }
    public static double h(double a,double b,int n){
        double res=(double)((b-a)/n);
        return res;
    }
    public static double sumatoriaImpar(double a,double h,int n){
        double res=0;
        double x=a+h;
        for(int i=1;i<=n-1;i++){
            if(i%2!=0){
                res+=f(x);
            }
            x+=h;
        }
        return res;
    }
    public static double sumatoriaPar(double a,double h,int n){
        double res=0;
        double x=a+h;
        for(int i=1;i<=n-1;i++){
            if(i%2==0){
                res+=f(x);
            }
            x+=h;
        }
        return res;
    }
    public static void principal(double a,double b,int n){
        if(n>=2 && n%2==0){
            double res=(double)((h(a,b,n)/3)*(f(a)+f(b)+(4*((double)sumatoriaImpar(a,(double)h(a,b,n),n)))+(2*((double)sumatoriaPar(a,(double)h(a,b,n),n)))));
            System.out.println("resultado: "+res);
        }else System.out.println("No cumple");
        

    }
    public static void main(String[] args){
        principal(2,4,4);
        
    }
}