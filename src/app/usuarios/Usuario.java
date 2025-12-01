package app.usuarios;

import java.util.Scanner;

import static app.dominio.Sistema.getUsuarios;

public abstract class Usuario {
    private String nome, email, senha, cpf, telefone;
    private Avaliacao avaliacao;

    private static final Scanner scanner = new Scanner(System.in);

    public Usuario(String nome, String email, String senha, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;

    }

    public static Usuario verificadorDeSeguranca() {
        Usuario usuarioLogado = null;
        do { // força o usuário a não ser nulo
            System.out.print("Digite o CPF: ");
            String cpf = scanner.next();
            for(Usuario usuario : getUsuarios()) {
                if(usuario.getCpf().equals(cpf)) {
                    usuarioLogado = usuario;
                    break;
                }
            }
            if(usuarioLogado == null) {
                System.out.println("Usuário não encontrado. Tente novamente.");
            }
        } while (usuarioLogado == null);

        System.out.print("Digite a senha: ");
        String senha = scanner.next();
        if (!usuarioLogado.senha.equals(senha)) {
            do { // força a senha a não ser incorreta
                System.out.println("Senha incorreta.");
                System.out.print("Digite a senha: ");
                senha = scanner.next();
            } while (!usuarioLogado.getSenha().equals(senha));

        }
        return usuarioLogado;
    }

    public static void mudarDados() {

    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public abstract void login();
}