import java.util.Scanner;

public class Caixas {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        if(a>=1 && a<=1000 && b>=1 && b<=1000 && c>=1 && c<=1000){

            if((a<b && b<c) || ((b<a && a<c)) || ((b<c && c<a)) || ((c<b && b<a)) || ((a<c && c<b)) || ((c<a && a<b)) || ((a+b) < c) || ((c+b) < a) || ((a+c) < b)){
                System.out.println("1");
            }else if((a == b && b!= c) || (a == c && c!= b) || (c == b && b!= a)){
                System.out.println("2");
            }else{
                System.out.println("3");
            }

        }else{
            System.out.println("digite numeros entre 1 e 1000!!!!!!!!");
        }

    }
}
