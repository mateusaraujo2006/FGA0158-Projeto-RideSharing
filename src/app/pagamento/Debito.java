package app.pagamento;

public class Debito implements FormaDePagamento {
    private double ValorEmConta;
    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= ValorEmConta) {
            ValorEmConta -= valorDebitado;
            return true;
        }
        return false;
    }
}
