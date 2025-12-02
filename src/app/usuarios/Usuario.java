package app.usuarios;

import app.dominio.Sistema;

import java.util.ArrayList;
import java.util.Scanner;

import static app.dominio.Sistema.getUsuarios;

public abstract class Usuario {
    private String nome, email, senha, cpf, telefone;
    private ArrayList<Avaliacao> avaliacoes = new ArrayList<>();


    private static final Scanner scanner = new Scanner(System.in);

    public Usuario(String nome, String email, String senha, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;

    }

    public String getNome() {
        return nome;
    }

    public String getMediaAvaliacao() {
        if (avaliacoes.isEmpty()) {
            return nome + "ainda não foi avaliada.";
        }
        int soma = 0;
        for (Avaliacao av : avaliacoes) {
            soma += av.getNota();
        }
        return nome + " tem uma média de " + soma/avaliacoes.size() + " estrelas.";
    }

    public abstract void login();

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
        Usuario usuarioLogado = null;

        for (Usuario usuario : getUsuarios()) {
            if(usuario.getCpf().equals(usuario.cpf)) {
                usuarioLogado = usuario;
            }
        }

        if (usuarioLogado != null) {
            int resposta;
            do {
                System.out.println("===========================");
                System.out.println("1. Mudar nome");
                System.out.println("2. Mudar email");
                System.out.println("3. Mudar senha");
                System.out.println("4. Mudar telefone");
                System.out.println("5. Voltar");
                System.out.println("===========================");
                System.out.print("Escolha sua opção: ");
                resposta = scanner.nextInt();


                switch (resposta) {
                    case 1:
                        System.out.print("Digite o novo nome: ");
                        usuarioLogado.setNome(scanner.next());
                        break;
                    case 2:
                        System.out.print("Digite o novo email: ");
                        usuarioLogado.setEmail(scanner.next());
                        break;
                    case 3:
                        System.out.print("Digite a nova senha: ");
                        usuarioLogado.setSenha(scanner.next());
                        break;
                    case 4:
                        System.out.print("Digite o novo telefone: ");
                        usuarioLogado.setTelefone(scanner.next());
                        break;

                    default:
                        System.out.println("Opção invalída. Digite novamente.");
                }
            } while (resposta != 5);
        }
        System.out.println();
        Sistema.main(null);

    }

    @Override
    public String toString() {
        return "++++=======================++++\n" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'';
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}