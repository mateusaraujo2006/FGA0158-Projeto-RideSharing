package app.usuarios;

public class Usuario {
    private String nome,email,senha,cpf,telefone;
    private Avaliacao avaliação;

    public Usuario(String nome, String email, String senha, String cpf, String telefone, Avaliacao avaliação) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.avaliação=avaliação;

    };
    public void login(){
        System.out.println("Insira o cpf:");

    }
}