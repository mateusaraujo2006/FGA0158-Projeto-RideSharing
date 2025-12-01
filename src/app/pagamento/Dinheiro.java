package app.pagamento;

public class Dinheiro implements FormaDePagamento {
    private double DinheiroDisponivel;

    public Dinheiro(double dinheiroDisponivel) {
        DinheiroDisponivel = dinheiroDisponivel;
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= DinheiroDisponivel) {
            return true "Pagamento concluído";
        }
        else {
            return false "Pagamento da corrida pendente!";
        }
}
}
