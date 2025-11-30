package app.pagamento;

public class Credito implements FormaDePagamento {
    private double limite;

    public Credito(double limite) {
        this.limite = limite;
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= limite) {
            limite -= valorDebitado;
            return true;
        }
        return false;
    }
}
