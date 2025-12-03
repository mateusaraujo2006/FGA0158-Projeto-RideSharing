package app.pagamento;

import app.excecoes.PagamentoRecusadoException;

public class Credito implements FormaDePagamento {
    private CadastroCartao cartao;
    private double Limite;

    public Credito(CadastroCartao cartao) {
        this.cartao = cartao;
        this.Limite = cartao.getLimite();
    }

    @Override
    public void processarPagamento(double valorDebitado) {
        if (valorDebitado <= Limite) {
            Limite -= valorDebitado;
            System.out.println("Pagamento realizado com sucesso");
        }
        else {
            throw new PagamentoRecusadoException("O cartão recusou o pagamento!");
        }
    }

    @Override
    public String toString() {
        return "'Credito'";
    }
}