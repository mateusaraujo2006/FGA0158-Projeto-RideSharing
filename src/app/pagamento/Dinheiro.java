package app.pagamento;

public class Dinheiro implements FormaDePagamento {
    private double DinheiroDisponivel;

    public Dinheiro(double dinheiroDisponivel) {
        DinheiroDisponivel = dinheiroDisponivel;
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= DinheiroDisponivel) {
            System.out.println("Pagamento concluído");
            return true;
        }
        else {
            System.out.println("Pagamento da corrida pendente!");
            return false;
        }


    }

    @Override
    public String toString() {
        return "'Dinheiro'";
    }
}
