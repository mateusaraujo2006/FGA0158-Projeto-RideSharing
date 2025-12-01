package app.dominio;

import app.usuarios.*;
import app.veiculo.*;
import app.pagamento.*;

import java.util.*;

public class Sistema {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Corrida> corridas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
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

    public static Motorista procurarMotorista() {
        List<Motorista> motoristasOnline = usuarios.stream()
                .filter(u -> u instanceof Motorista)
                .map(u -> (Motorista) u)
                .filter(m -> m.getStatusDisponibilidade() == StatusDisponibilidade.ONLINE)
                .toList();
        if (motoristasOnline.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(motoristasOnline.size());
        return motoristasOnline.get(index);
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
        int tipoDeUsuario;
        System.out.println("1. Passageiro");
        System.out.println("2. Motorista");
        System.out.print(" Digite o tipo de usuário: ");
        tipoDeUsuario = scanner.nextInt();
        
        switch (tipoDeUsuario) {
            
            case 1:
                FormaDePagamento pagamento = null;
                System.out.println("1. Credito");
                System.out.println("2. Dinheiro");
                System.out.println("3. Pix");
                System.out.println("4. Debito");
                System.out.print("Escolha a forma de pagamento que irá usar: ");
                int formaDePagamento = scanner.nextInt();
                switch (formaDePagamento) {
                    case 1:
                        pagamento = new Credito(cadastrarCartao());
                        break;
                    case 2:
                        System.out.print("Digite o dinheiro disponível: ");
                        double dinheiro = scanner.nextDouble();
                        pagamento = new Dinheiro(dinheiro);
                        break;
                    case 3:
                        System.out.print("Digite a chave pix: ");
                        double valorNaConta = scanner.nextDouble();
                        pagamento = new Pix(valorNaConta);
                        break;
                    case 4:
                        System.out.println("Digite o seu saldo inicial: ");
                        double saldoInicial = scanner.nextDouble();
                        pagamento = new Debito(cadastrarCartao(), saldoInicial);
                        break;
                    default:
                        System.out.println("Opção de pagamento inválida.");
                        break;
                }
                usuarios.add(new Passageiro(nome, email, senha, cpf, telefone, pagamento));
                break;
            case 2:
                System.out.print("Digite o número da CNH: ");
                String numeroCnh = scanner.next();
                System.out.print("Digite a categoria da CNH: ");
                String categoriaCnh = scanner.next();
                Cnh cnh = new Cnh(numeroCnh, categoriaCnh);

                System.out.print("Digite a cor do veículo: ");
                String cor = scanner.next();
                System.out.print("Digite o modelo do veículo: ");
                String modelo = scanner.next();
                System.out.print("Digite o ano do veículo: ");
                int ano = scanner.nextInt();
                System.out.print("Digite a placa do veículo: ");
                String placa = scanner.next();
                Veiculo veiculo = new Veiculo(placa, modelo, cor, ano);
                int disponivel;
                System.out.println("O motorista pode começar a trabalhar agora?");
                System.out.println("1. Sim");
                System.out.println("2. Não");
                System.out.print("Escolha uma opção: ");
                disponivel = scanner.nextInt();
                StatusDisponibilidade disponibilidade;
                switch (disponivel) {
                    case 1:
                        disponibilidade = StatusDisponibilidade.ONLINE;
                        break;
                    case 2:
                        disponibilidade = StatusDisponibilidade.OFFLINE;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        return;
                }
                usuarios.add(new Motorista(nome, email, senha, cpf, telefone, veiculo, cnh, disponibilidade));
                break;
        }

        System.out.println("Usuário cadastrado com sucesso!");
    }
    public static void logarEmUsuario() {
        Usuario usuarioLogado = null;
        System.out.print("Digite o CPF: ");
        String cpf = scanner.next();
        for(Usuario usuario : usuarios) {
            if(usuario.getCpf().equals(cpf)) {
                usuarioLogado = usuario;
                break;
            }
        }
        if(usuarioLogado == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        System.out.print("Digite a senha: ");
        String senha = scanner.next();
        if(!usuarioLogado.getSenha().equals(senha)) {
            do {
                System.out.println("Senha incorreta.");
                System.out.print("Digite a senha: ");
                senha = scanner.next();
            }while (!usuarioLogado.getSenha().equals(senha));

        }
        usuarioLogado.login();
    }

    public static CadastroCartao cadastrarCartao() {
        System.out.print("Digite o numero do cartão: ");
        String numeroCartao = scanner.next();
        System.out.print("Digite o nome do Titular: ");
        String nomeTitular = scanner.next();
        System.out.print("Digite a data de validade: ");
        String dataDeValidade = scanner.next();
        System.out.print("Digite o código de segurança: ");
        String codigoSeguranca = scanner.next();
        return new CadastroCartao(numeroCartao, nomeTitular, dataDeValidade, codigoSeguranca);
    }
}
