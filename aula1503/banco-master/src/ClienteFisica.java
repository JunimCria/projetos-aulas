public class ClienteFisica extends Cliente {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public boolean validaCpf(String cpf){
        return true;
    }
}
