package app.usuarios;
import app.veiculo.*;
public class Motorista extends Usuario {
    private Veiculo veiculo;
    private Cnh cnh;

    public Motorista(String nome, String email, String senha, String cpf, String telefone, Veiculo veiculo, Cnh cnh) {
        super(nome, email, senha, cpf, telefone);
        this.veiculo = veiculo;
        this.cnh = cnh;
    }


}
