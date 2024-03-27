public class App {
    public static void main(String[] args) throws Exception {
        
        Conta c1 = new Conta();
        ClienteFisico p = new ClienteFisico();

        p.setNome("Nick");
        p.setCpf("00000000000");
        c1.setNumero("1234");
        c1.setCliente(p);
        c1.setSaldo(0.0);

        System.out.println(c1.getNumero());
        System.out.println(c1.getCliente());
        System.out.println("O saldo de " + c1.getCliente().getNome() + "é: " + c1.getSaldo());

        c1.depositar(1000);
        if(c1.sacar(100)){
            System.out.println("saque realizado com sucesso");
            System.out.println("saldo de: " + c1.getCliente().getNome() + "é: " + c1.getSaldo());
        }else{
            System.out.println("saldo insuficiente");
        }

        //teste herança e polimorfismo
        Conta cc = new Corrente();
        cc.setNumero("4444-5");
        cc.setSaldo(1000);
        cc.setCliente(p);

        System.out.println(cc.rendimento());

        Conta cp = new Poupanca();
        cp.setNumero("4444-5");
        cp.setSaldo(1000);
        cp.setCliente(p);

        System.out.println(cp.rendimento());
    }
}
