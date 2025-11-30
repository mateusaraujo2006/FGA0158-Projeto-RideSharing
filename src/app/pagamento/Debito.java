package app.pagamento;

public class Debito implements FormaDePagamento {
    private double ValorEmConta;

    public Debito(double valorEmConta) {
        ValorEmConta = valorEmConta;
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= ValorEmConta) {
            ValorEmConta -= valorDebitado;
            return true;
        }
        return false;
    }
}
