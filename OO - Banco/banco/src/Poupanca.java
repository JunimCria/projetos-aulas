public class Poupanca extends Conta{

    @Override
    double rendimento() {
        return this.getSaldo() * 0.05;
    }

}