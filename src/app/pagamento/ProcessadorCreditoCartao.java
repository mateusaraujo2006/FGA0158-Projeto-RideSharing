package app.pagamento;

public class ProcessadorCreditoCartao {
    private Debito credito;

    public ProcessadorCreditoCartao(CadastroCartao cartao, double saldoInicial) {
        this.credito = new Debito(cartao, saldoInicial);
    }

    public boolean tentarPagar(double valor) {
        return this.credito.processarPagamento(valor);
    }

    public String tentarPagarComMensagem(double valor) {
        boolean ok = tentarPagar(valor);
        if (ok) {
            return "Pagamento de R$ " + valor + " realizado com sucesso.";
        }
        return "Pagamento de R$ " + valor + " recusado: saldo insuficiente.";
    }
}