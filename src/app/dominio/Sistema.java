package app.dominio;

import app.usuarios.Usuario;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistema {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Corrida> corridas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    // Lógica de login
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    public static void iniciarSistema(){

    }

    public static void validadorDeCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new InputMismatchException("CPF deve ter 11 dígitos.");
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            throw new InputMismatchException("CPF inválido.");
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Cálculo do 1º Dígito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            // Cálculo do 2º Dígito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if (!((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))) {
                throw new InputMismatchException("CPF inválido.");
            }
        } catch (InputMismatchException erro) {
            throw new InputMismatchException("CPF inválido.");
        }
    }

    public static void cadastrarUsuario() {
        String cpf;
        while (true) {
            try {
                System.out.print("Digite o CPF: ");
                cpf = scanner.next();
                validadorDeCpf(cpf);
                break; 
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " Tente novamente.");
            }
        }
        String nome;
        System.out.print("Digite o nome: ");
        nome = scanner.next();
        String email;
        System.out.print("Digite o email: ");
        email = scanner.next();
        String telefone;
        System.out.print("Digite o telefone: ");
        telefone = scanner.next();
        String senha, senhaDeConfimarcao;
        System.out.print("Digite a senha: ");
        senha = scanner.next();
        System.out.print("Confirme a senha: ");
        senhaDeConfimarcao = scanner.next();
        if (!senha.equals(senhaDeConfimarcao)) {
            do {
                System.out.println("As senhas não coincidem. Tente novamente.");
                System.out.print("Digite a senha: ");
                senha = scanner.next();
                System.out.print("Confirme a senha: ");
                senhaDeConfimarcao = scanner.next();
            }while (!senha.equals(senhaDeConfimarcao));
        }
        Usuario usuario = new Usuario(nome, email, senha, cpf, telefone);
        usuarios.add(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }
    public static void logarEmUsuario() {

    }
}
