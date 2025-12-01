package app.pagamento;

public class Debito implements FormaDePagamento {
    private CadastroCartao Cartao;
    private double Limite;

    public PagamentoDebito(CadastroCartao cartao) {
        this.Cartao = cartao;
        this.Limite = cartao.getLimite();
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= Limite) {
            Limite -= valorDebitado;
            return true "Pagamento realizado com sucesso";
        }
        else {
            return false "Pagamento recusado: saldo insuficiente.";
        }
    }
}