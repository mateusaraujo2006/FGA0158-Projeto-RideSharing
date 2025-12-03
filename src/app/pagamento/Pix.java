package app.pagamento;

import app.excecoes.SaldoInsuficienteException;

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

    public boolean foiPago() {
        return this.pagamentoConfirmado;
    }


    @Override
    public void processarPagamento(double valorDebitado) {
        
        if (valorDebitado <= DinheironaConta) {
            DinheironaConta -= valorDebitado;
            pagamentoConfirmado = true;
            System.out.println("Pagamento realizado com sucesso");
        }
        pagamentoConfirmado = false;
        throw new SaldoInsuficienteException("Você não possui saldo suficiente para realizar o pagamento!");
    }

    @Override
    public String toString() {
        return "'Pix'";
    }
}