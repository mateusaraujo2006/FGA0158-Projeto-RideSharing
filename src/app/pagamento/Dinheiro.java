package app.pagamento;

public class Dinheiro implements FormaDePagamento {
    private double DinheiroDisponivel;

    public Dinheiro(double dinheiroDisponivel) {
        DinheiroDisponivel = dinheiroDisponivel;
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= DinheiroDisponivel) {
            DinheiroDisponivel -= valorDebitado;
            return true;
        }
        return false;
}
}
