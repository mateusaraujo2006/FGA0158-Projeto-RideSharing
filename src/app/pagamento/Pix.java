package app.pagamento;

public class Pix implements FormaDePagamento {
    private double DinheironaConta;

    public Pix(double dinheironaConta) {
        DinheironaConta = dinheironaConta;
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= DinheironaConta) {
            DinheironaConta -= valorDebitado;
            return true;
        }
        return false;
    }
}
