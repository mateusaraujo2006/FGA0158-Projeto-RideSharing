package app.pagamento;

public class ProcessadorDebitoCartao {
    private Debito debito;

    public ProcessadorDebitoCartao(CadastroCartao cartao, double saldoInicial) {
        this.debito = new Debito(cartao, saldoInicial);
    }

    public boolean tentarPagar(double valor) {
        return this.debito.processarPagamento(valor);
    }

    public String tentarPagarComMensagem(double valor) {
        boolean ok = tentarPagar(valor);
        if (ok) {
            return "Pagamento de R$ " + valor + " realizado com sucesso (débito).";
        }
        return "Pagamento de R$ " + valor + " recusado: saldo insuficiente.";
    }
}
