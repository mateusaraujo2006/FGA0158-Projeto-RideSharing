package app.usuarios;
import app.veiculo.*;
public class Motorista extends Usuario {
    private Veiculo veiculo;
    private Cnh cnh;
    private StatusDisponibilidade statusDisponibilidade;

    public Motorista(String nome, String email, String senha, String cpf, String telefone, Veiculo veiculo, Cnh cnh, StatusDisponibilidade statusDisponibilidade) {
        super(nome, email, senha, cpf, telefone);
        this.veiculo = veiculo;
        this.cnh = cnh;
        this.statusDisponibilidade = statusDisponibilidade;
    }

    public StatusDisponibilidade getStatusDisponibilidade() {
        return statusDisponibilidade;
    }

    @Override
    public void login() {

    }
}
