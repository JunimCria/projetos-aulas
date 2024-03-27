import java.util.Scanner;

public class Mensagem {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("entre com o nome: ");
        String nome = sc.nextLine();

        System.out.println("entre com a senha: ");
        String senha = sc.nextLine();

        String senhapadrao = "12345";

        if(senha.equals(senhapadrao)){
            System.out.println("Login válida!");
        }else{
            System.out.println("Login Inválida!");
        }

    }

}
