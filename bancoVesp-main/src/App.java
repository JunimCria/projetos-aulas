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
    
    Conta c1 = new Conta();
    Fisica p1 = new Fisica();
    p1.setNome("leonardo");
    p1.setCpf("12312312378");
    c1.setNumero("123-4");
    c1.setCliente(p1);
    c1.setSaldo(0);

    c1.depositar(1000);
    System.out.println("O saldo atual de c1: " + c1.getSaldo());

    Conta c2 = new Conta();
    Juridica p2 = new Juridica();
    p2.setNome("Americanas LTDA");
    p2.setCnpj("1111111111111111");
    c2.setNumero("123-5");
    c2.setCliente(p2);
    c2.setSaldo(0);

    if (c1.transferir(c2, 1010)) {
      System.out.println("Transferencia realizada com sucesso.");
      System.out.println("O saldo atual de c1: " + c1.getSaldo());
    } else {
      System.out.println("Transferencia cancelada. Verirfique o saldo");
    }

    System.out.println("O saldo atual de " + c1.getCliente().getNome() + " é: " + c1.getSaldo());
    System.out.println("O saldo atual de " + c2.getCliente().getNome() + " é: " + c2.getSaldo());

    Conta cp1 = new Poupanca();
    cp1.setNumero("2212-3");
    cp1.setCliente(p1);
    cp1.setSaldo(0);

    cp1.depositar(1000);

    System.out.println("O rendimento da poupança foi: " + cp1.rendimento());

    Conta cc1 = new Corrente();
    cc1.setNumero("3321-1");
    cc1.setCliente(p1);
    cp1.setSaldo(0);

    cc1.depositar(1000);

    System.out.println("O rendimento da conta corrente foi: " + cc1.rendimento());

    // trabalhando a persistencia dos dados

    // Criando uma conexão com o BD
    //getConexao();
    System.out.println(listaTodos());
    Fisica cliente = new Fisica();
    cliente.setCpf("0000");
    cliente.setNome("nicolau");
    //teste inserir
    Conta conta = new Conta();
    conta.setCliente(cliente);
    conta.setNumero("555-6");
    conta.setSaldo(1000000);
    conta.setLimite(100);
    inserir(conta);
    //inserir teste
    System.out.println(listaTodos());
    //testando update
    conta.setNumero("999-x");
    conta.setCliente(p1);
    conta.setSaldo(0);
    atualizar(conta, "000-1");
    //teste delete
    conta.setNumero("999-x");
    deletar(conta);
    System.out.println(listaTodos());

  }

  public static Connection getConexao() {
    Connection conexao = null;

    try {
      // Carregando o JDBC Driver padrão
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Configurando a nossa conexão com um banco de dados//
      String url = "jdbc:mysql://10.28.0.35:3306/bancoifba"; //"jdbc:mysql://10.28.0.35:3306/bancoifba"; // caminho e nome do BD
      String username = "remoto"; // nome de um usuário de seu BD
      String password = "remoto"; // sua senha de acesso

      conexao = DriverManager.getConnection(url, username, password);
      System.out.println("Conectado ao BD");

      return conexao;

    } catch (ClassNotFoundException e) { // Driver não encontrado
      System.out.println("O driver expecificado nao foi encontrado.");
      return null;
    } catch (SQLException e) {
      // Não conseguindo se conectar ao banco
      System.out.println("Nao foi possivel conectar ao Banco de Dados.");
      return null;
    }

  }

  public static List<Conta> listaTodos() throws SQLException {

    Connection conn = getConexao();
    List<Conta> contas = new ArrayList<>();
    try {
      String sql = "SELECT * FROM conta";

      Statement stmt = conn.createStatement();

      // guarda no objeto o resultado da consulta
      ResultSet rs = stmt.executeQuery(sql);

      
      while (rs.next()) {
        Conta c = new Conta();
        c.setNumero(rs.getString("numero"));
        //c.setCliente(rs.getString("cliente"));
        c.setSaldo(rs.getDouble("saldo"));

        contas.add(c);
      }

    } catch (SQLException ex) {
      System.out.println("Não conseguiu listar as contas do BD.");
    } finally {
      conn.close();      
    }
    return contas;
  }

  public static void inserir(Conta conta) throws SQLException {
    Connection conn = getConexao();
    try {
      String adicionar = "INSERT INTO conta (numero, cliente, saldo) VALUES (?,?,?)";

      PreparedStatement ps = conn.prepareStatement(adicionar);
      ps.setString(1, conta.getNumero());
      ps.setString(2, conta.getCliente().getNome());
      ps.setDouble(3, conta.getSaldo());
      int res = ps.executeUpdate();
      if (res == 1) {
        System.out.println("Conta inserida com sucesso.");
      }
    } catch (SQLException ex) {
      System.out.println("Não conseguiu adicionar uma conta no BD.");
    } finally {
      conn.close();
    }
  }

  public static void atualizar(Conta conta, String numero) throws SQLException{

  Connection conn = getConexao();
  try{
    String atualizar = "Update conta SET numero = ?, cliente = ?, saldo = ? WHERE numero = ?";

    PreparedStatement ps = conn.prepareStatement(atualizar);
      ps.setString(1, conta.getNumero());
      ps.setString(2, conta.getCliente().getNome());
      ps.setDouble(3, conta.getSaldo());
      ps.setString(4, numero);
      int res = ps.executeUpdate();
      if (res == 1) {
        System.out.println("Conta atualizada com sucesso.");
      }
      System.out.println("Atualizou o cliente da conta.");
    } catch (SQLException ex) {
      System.out.println("Não conseguiu atualizar uma conta no BD.");
    } finally {
      conn.close();
    }
  }

  public static void deletar(Conta conta) throws SQLException{

  Connection conn = getConexao();
  try{
    String deletar = "DELETE FROM conta WHERE numero = ?";

    PreparedStatement ps = conn.prepareStatement(deletar);
      ps.setString(1, conta.getNumero());
      int res = ps.executeUpdate();
      if (res == 1) {
        System.out.println("Conta deletada com sucesso.");
      }
    } catch (SQLException ex) {
      System.out.println("Não conseguiu deletar uma conta no BD.");
    } finally {
      conn.close();
    }
  }

}
