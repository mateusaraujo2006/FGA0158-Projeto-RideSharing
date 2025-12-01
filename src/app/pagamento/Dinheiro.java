package app.pagamento;

public class Dinheiro implements FormaDePagamento {
    private boolean CorridaPaga = false;
    private double DinheiroDisponivel;

    public Dinheiro(double dinheiroDisponivel) {
        DinheiroDisponivel = dinheiroDisponivel;
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= dinheiroDisponivel) {
            CorridaPaga = true;
            return CorridaPaga
        }
        else {
            return CorridaPaga;
        }
}
}
