package app.pagamento;

import app.excecoes.SaldoInsuficienteException;

public class Dinheiro implements FormaDePagamento {
    private double dinheiroDisponivel;

    public Dinheiro(double dinheiroDisponivel) {
        this.dinheiroDisponivel = dinheiroDisponivel;
    }

    @Override
    public void processarPagamento(double valorDebitado) {
        if (valorDebitado <= dinheiroDisponivel) {
            System.out.println("Pagamento concluído");
            dinheiroDisponivel -= valorDebitado;
        }
        else {
            throw new SaldoInsuficienteException("Você não possui dinheiro suficiente para realizar o pagamento!");
        }


    }

    @Override
    public String toString() {
        return "'Dinheiro'";
    }
}
