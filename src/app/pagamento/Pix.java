package app.pagamento;

public class Pix implements FormaDePagamento {
    private double DinheironaConta;
    private String chavePix;
    private boolean pagamentoConfirmado = false;

    public Pix(double dinheironaConta) {
        DinheironaConta = dinheironaConta;
        gerarChavePix();
    }

    // Gera uma chave PIX aleatória 
    public String gerarChavePix() {
        if (this.chavePix == null || this.chavePix.isEmpty()) {
            this.chavePix = java.util.UUID.randomUUID().toString().replace("-", "");
        }
        return this.chavePix;
    }

    public String getChavePix() {
        return this.chavePix;
    }

    // Informa se o último pagamento via PIX foi confirmado
    public boolean foiPago() {
        return this.pagamentoConfirmado;
    }

    @Override
    public boolean processarPagamento(double valorDebitado) {
        
        if (valorDebitado <= DinheironaConta) {
            DinheironaConta -= valorDebitado;
            pagamentoConfirmado = true;
            return true "Pagamento realizado com sucesso";
        }
        pagamentoConfirmado = false;
        return false "Pagamento recusado: saldo insuficiente.";
    }
}