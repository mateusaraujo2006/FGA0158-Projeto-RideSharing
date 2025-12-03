package app.pagamento;

import app.excecoes.PagamentoRecusadoException;
import app.excecoes.SaldoInsuficienteException;

public class Debito implements FormaDePagamento {
    private CadastroCartao cartao;
    private double ValorEmConta;

    public Debito(CadastroCartao cartao, double saldoInicial) {
        this.cartao = cartao;
        this.ValorEmConta = cartao.getSaldo();
    }

    @Override
    public void processarPagamento(double valorDebitado) {
        if (valorDebitado <= ValorEmConta) {
            ValorEmConta -= valorDebitado;
            System.out.println("Pagamento realizado com sucesso!");
        }
        else {
            throw new PagamentoRecusadoException("O cartão recusou o pagamento!");
        }
    }

    @Override
    public String toString() {
        return "'Debito'";
    }
}
