public class Corrente extends Conta{

    @Override
    double rendimento() {
        return this.getSaldo() * 0;
    }
    


}
