import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File meuArq = new File("filename.txt");

        if(meuArq.createNewFile()){
            System.out.println("Arquivo criadio com sucesso");
        }else{
            System.out.println("O arquivo já existe");

            System.out.println("Nome do arquivo: " + meuArq.getName());
            System.out.println("Caminho absoluto: " + meuArq.getAbsolutePath());
            System.out.println("Liberado para escrita: " + meuArq.canWrite());
            System.out.println("Liberado para leitura: " + meuArq.canRead());
            System.out.println("Tamanho do arquivo em Bytes: " + meuArq.length());
        }

        FileWriter meuArqW = new FileWriter("filename.txt",true);
        meuArqW.write("Escrevendo no arquivo... \npulando uma linha");
        meuArqW.close();

        Scanner sc = new Scanner(meuArq);
        while(sc.hasNextLine()){
            String linha = sc.nextLine();
            System.out.println(linha);
        }
        sc.close();

    }
}
