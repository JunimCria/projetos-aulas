public class Conta {
    private String numero;
    private Cliente cliente;
    private double saldo;
    private double limite;

    public boolean saca(double valor){
        if (this.saldo < valor) {
            return false;
        }else{
            this.saldo = this.saldo - valor;
            return true;
        }
    }

    public void deposita(double valor){
        this.saldo += valor;
    }

    public boolean transfere(Conta destino, double valor){
        if (saca(valor)){
            destino.deposita(valor);
            return true;
        }else{
            return false;
        }
    }

    public double rendimento(){
        return 0;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    
}
