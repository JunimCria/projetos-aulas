import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("##################################################");
        System.out.println("#                                                #");
        System.out.println("# Feliz dia das mulheres!!! Vocês são especiais. #");
        System.out.println("#                                                #");
        System.out.println("##################################################");
        
        Conta c1 = new Conta();
        ClienteFisica p = new ClienteFisica();
        p.setNome("Leo");
        p.setCpf("11111111111");
        c1.setNumero("123-4");
        c1.setCliente(p);
        c1.setSaldo(0.0);

        c1.deposita(1000.0);
        if (c1.saca(100)){
            System.out.println("Saque realizado com suceso.");
            System.out.println("O saldo de " + c1.getCliente().getNome() + " é: " + c1.getSaldo());
        }else{
            System.out.println("Saldo insuficiente!");
        }

        // criando uma segunda conta
        Conta c2 = new Conta();
        ClienteJuridica j = new ClienteJuridica();
        j.setNome("Americanas");
        j.setCnpj("009999900099900099");
        c2.setNumero("222-2");
        c2.setCliente(j);
        c2.setSaldo(0);

        if (c1.transfere(c2, 500)){
            System.out.println("Transferencia efetuada com sucesso.");
        }else{
            System.out.println("Saldo insuficiente");
        }
        System.out.println("O saldo de " + c1.getCliente().getNome() + " é: " + c1.getSaldo());
        System.out.println("O saldo de " + c2.getCliente().getNome() + " é: " + c2.getSaldo());

        // testando herança e polimorfismo
        Conta cc = new Corrente();
        cc.setNumero("4444-5");
        cc.setSaldo(1000);
        cc.setCliente(j);        
        System.out.println("O rendimento da conta corrente foi: " + cc.rendimento());

        Conta cp = new Poupanca();
        cp.setNumero("4444-5");
        cp.setSaldo(1000);
        cp.setCliente(j); 
        System.out.println("O rendimento da conta Poupança foi: " + cp.rendimento());

        System.out.println(listaTodas());
    } // fecha o void main()

    //persistencia com jdbc

    public static Connection getConexao(){
        Connection conexao = null;
        try{
            //carrega o drive do mySQL
            Class.forName("com.mysql.jbdc.Driver");
            // configurar os parametros de conexao com o bd
            String url = "jbdc:mysql://10.28.0.35:3306/bancoifba";
            String usuario = "remoto";
            String senha = "remoto";
            // carrega a conexao com o banco de dados
            conexao = DriverManager.getConnection(url, usuario, senha);
            return conexao;
        }catch(ClassNotFoundException e){
            System.out.println("O driver especificado não foi encontrado");
            return null;
        }catch(SQLException ex){
            System.out.println("Não foi possível conectar ao banco de dados");
            return null;
        }
    }

    public static List<Conta> listaTodas() throws SQLException{
        // pegar uma conexao com o banco de dados
        Connection con = getConexao();
        List<Conta> contas = new ArrayList<>(); //cria uma lista para receber as contas
        try{
            String sql = "SELECT * FROM conta"; //Consulta SQL que retorna todas as contas
            // Cria um objeto Statement para executar o comando sql
            Statement stmt = con.createStatement();
            //guardar o resultado da consulta
            ResultSet rs = stmt.executeQuery(sql);
            // guarda o resultado numa lista
            while(rs.next()){
                Conta c = new Conta();
                c.setNumero(rs.getString("numero"));
                c.setSaldo(rs.getDouble("saldo"));
                contas.add(c);
            }
        
        }catch(SQLException ex){
            System.out.println("Não foi possível lista as contas do banco de dados");
        }finally{
            con.close();
        }
        return contas;
    }

    public static void inserir(Conta conta) throws SQLException {
        Connection conn = getConexao(); // 1 - CONEXÃO
        try{
            String adicionar = "INSERT INTO conta (numero, cliente, saldo) VALUES (?,?,?)";

            // 2 - Um objeto Statement para executar a consulta sql
            PreparedStatement ps = conn.prepareStatement(adicionar);
            ps.setString(1, conta.getNumero());
            ps.setString(2,conta.getCliente().getNome());
            ps.setDouble(3, conta.getSaldo());
            int res = ps.executeUpdate(); // 3 - Resulted - guarda o resultado dA CONSULTA
            if(res == 1){
                System.out.println("conta inserida com sucesso.");
            }
        }catch(SQLException ex){ // TRATAMENTO DE EXCESSÃO
            System.out.println("Não conseguiu adicionar uma conta no BD.");
        }finally{ //FINALIZAÇÃO
            conn.close();
        }
    }



   // CARLA E LINDA 
}


