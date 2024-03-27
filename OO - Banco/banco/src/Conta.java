public class Conta {
    
    private String numero;
    private Cliente cliente;
    private double saldo;
    private double limite;

    public String getNumero() {
        return this. numero;
    }

    public String setNumero(String numero) {
        return this.numero = numero;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return this.limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public boolean sacar(double valor){
        if(this.saldo<valor){
            return false;
        }else{
            this.saldo = this.saldo - valor;
            return true;
        }
    }

    public void depositar(double valor){
        this.saldo+=valor;
    }

    public boolean transfere(Conta destino, double valor){
        if (sacar(valor)){
            destino.depositar(valor);
            return true;
        }else{
            return false;
        }

    }

    double rendimento(){
        return 0;
    }
}
