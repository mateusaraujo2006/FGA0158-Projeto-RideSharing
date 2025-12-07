package app.pagamento;

import app.excecoes.PagamentoRecusadoException;

public class Debito implements FormaDePagamento {
    private CadastroCartao cartao;
    private double valorEmConta;

    public Debito(CadastroCartao cartao, double saldo) {
        this.cartao = cartao;
        this.valorEmConta = saldo;
    }

    @Override
    public void processarPagamento(double valorDebitado) {
        if (valorDebitado <= valorEmConta) {
            valorEmConta -= valorDebitado;
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
