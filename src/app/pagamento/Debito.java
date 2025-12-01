package app.pagamento;

public class Debito implements FormaDePagamento {
    private CadastroCartao Cartao;
    private double ValorEmConta;

    public Debito(CadastroCartao cartao, double saldoInicial) {
        this.Cartao = cartao;
        this.ValorEmConta = cartao.getSaldo();
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= ValorEmConta) {
            ValorEmConta -= valorDebitado;
            System.out.println("Pagamento realizado com sucesso!");
            return true;
        }
        else {
            System.out.println("Pagamento recusado: saldo insuficiente.");
            return false;
        }
    }
}
