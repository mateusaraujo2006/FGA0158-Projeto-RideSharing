package app.usuarios;

import app.pagamento.FormaDePagamento;

public class Passageiro extends Usuario {
    private FormaDePagamento pagamento;
    private double divida;

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    public Passageiro(String nome, String email, String senha, String cpf, String telefone, FormaDePagamento pagamento) {
        super(nome, email, senha, cpf, telefone);
        this.pagamento = pagamento;
    }

}
