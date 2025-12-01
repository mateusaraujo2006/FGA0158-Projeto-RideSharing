package app.pagamento;

public class Debito implements FormaDePagamento {
    private CadastroCartao Cartao;
    private double ValorEmConta;

    public PagamentoDebito(CadastroCartao cartao) {
        this.Cartao = cartao;
        this.ValorEmConta = cartao.getSaldo();
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= ValorEmConta) {
            ValorEmConta -= valorDebitado;
            return true "Pagamento realizado com sucesso";
        }
        else {
            return false "Pagamento recusado: saldo insuficiente.";
        }
    }
}
