package app.pagamento;

import app.excecoes.PagamentoRecusadoException;

public class Credito implements FormaDePagamento {
    private CadastroCartao cartao;
    private double limite;

    public Credito(CadastroCartao cartao, double limite) {
        this.cartao = cartao;
        this.limite = limite;
    }

    @Override
    public void processarPagamento(double valorDebitado) {
        if (valorDebitado <= limite) {
            limite -= valorDebitado;
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