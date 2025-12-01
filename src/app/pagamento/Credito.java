package app.pagamento;

public class Credito implements FormaDePagamento {
    private CadastroCartao Cartao;
    private double Limite;

    public Credito(CadastroCartao cartao) {
        this.Cartao = cartao;
        this.Limite = cartao.getLimite();
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        if (valorDebitado <= Limite) {
            Limite -= valorDebitado;
            System.out.println("Pagamento realizado com sucesso");
            return true;
        }
        else {
            System.out.println("Pagamento recusado: saldo insuficiente.");
            return false;
        }
    }
}