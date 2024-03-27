import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaService {

    public static void inserir(Conta conta) throws SQLException {
    Connection conn = App.getConexao();
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
      ex.printStackTrace(); //mostra a linha exata onde houve erro
    }
  }



  public static void atualizar(Conta conta, String numero) throws SQLException{

  Connection conn = App.getConexao();
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
    ex.printStackTrace(); //mostra a linha exata onde houve erro
    }
  }

   public static void deletar(Conta conta) throws SQLException{

  Connection conn = App.getConexao();
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
    ex.printStackTrace(); //mostra a linha exata onde houve erro
    }
  }



  public static List<Conta> listaTodos() throws SQLException {

    Connection conn = App.getConexao();
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
    ex.printStackTrace(); //mostra a linha exata onde houve erro
    }
    return contas;
  }


}
