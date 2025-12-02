package app.dominio;

import app.excecoes.NenhumMotoristaDisponivelException;
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
                    Usuario.verificadorDeSeguranca().login();
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

    public static List<Motorista> procurarMotorista() {
        List<Motorista> motoristasOnline = null;
            motoristasOnline = usuarios.stream()
                    .filter(u -> u instanceof Motorista)
                    .map(u -> (Motorista) u)
                    .filter(m -> m.getStatusDisponibilidade() == StatusDisponibilidade.ONLINE && m.getValidadeCnh())
                    .toList();
            if (motoristasOnline.isEmpty()) {
                throw new NenhumMotoristaDisponivelException("\nNenhum motorista disponível no momento.");
            }
        return motoristasOnline;
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

        String[] dados;
        dados = adquirirDadosComuns();
        int tipoDeUsuario;
        System.out.println("1. Passageiro");
        System.out.println("2. Motorista");
        System.out.print(" Digite o tipo de usuário: ");
        tipoDeUsuario = scanner.nextInt();
        
        switch (tipoDeUsuario) {
            
            case 1:
                cadastrarPassageiro(dados);
                break;
            case 2:
                cadastraMotorista(dados);
                break;
        }

        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastraMotorista(String[] dados) {
        System.out.print("Digite o número da CNH: ");
        String numeroCnh = scanner.next();
        System.out.print("Digite a data de validade da CNH: ");
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
        int disponivel =0;
        StatusDisponibilidade disponibilidade = null;
        do {
            System.out.println("O motorista pode começar a trabalhar agora?");
            System.out.println("1. Sim");
            System.out.println("2. Não");
            System.out.print("Escolha uma opção: ");
            disponivel = scanner.nextInt();

            switch (disponivel) {
                case 1:
                    disponibilidade = StatusDisponibilidade.ONLINE;
                    break;
                case 2:
                    disponibilidade = StatusDisponibilidade.OFFLINE;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(disponivel < 1 || disponivel > 2);
        usuarios.add(new Motorista(dados[0], dados[1], dados[2], dados[3], dados[4], veiculo, cnh, disponibilidade));
    }

    private static void cadastrarPassageiro(String[] dados) {
        FormaDePagamento pagamento = null;
        int formaDePagamento = 0;
        do {
            System.out.println("1. Credito");
            System.out.println("2. Dinheiro");
            System.out.println("3. Pix");
            System.out.println("4. Debito");
            System.out.print("Escolha a forma de pagamento que irá usar: ");
            formaDePagamento = scanner.nextInt();
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
                    System.out.print("Digite o valor na conta: ");
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
        }while (formaDePagamento < 1 || formaDePagamento > 4);
        usuarios.add(new Passageiro(dados[0], dados[1], dados[2], dados[3], dados[4], pagamento));
    }

    private static String[] adquirirDadosComuns() {
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
        return new String[]{nome, email, senha, cpf, telefone};
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

    public static void processarCorrida(Corrida corrida, Passageiro passageiro) {
        corridas.add(corrida);
        corrida.imprimir();
        int opcao = 0;
        boolean confirmacao = false;
        List<Motorista> motoristasDisponiveis = null;
        try {
            motoristasDisponiveis = procurarMotorista();
        } catch (NenhumMotoristaDisponivelException e) {
            System.out.println(e.getMessage());
            return;
        }
        Motorista motorista = null;
        for (Motorista i : motoristasDisponiveis) {
            System.out.println("Encontramos o motorista: " + i.getNome());
            System.out.println(i.getMediaAvaliacao());
            do {
                System.out.println("Aceita o motorista?");
                System.out.println("1. Sim");
                System.out.println("2. Não");
                System.out.println("3. Cancelar corrida");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        System.out.println("O Cliente " + passageiro.getNome() + "está solicitando uma corrida");
                        System.out.println(passageiro.getMediaAvaliacao());
                        do {
                            System.out.println("Aceita o passageiro?");
                            System.out.println("1. Sim");
                            System.out.println("2. Não");
                            System.out.print("Escolha uma opção: ");
                            opcao = scanner.nextInt();
                            switch (opcao) {
                                case 1:
                                    confirmacao = true;
                                    motorista = i;
                                    break;
                                case 2:
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }

                        }while (opcao < 1 || opcao > 3);
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("Corrida cancelada");
                        corrida.cancelar();
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }

            }while (opcao < 1 || opcao > 3);
            if (confirmacao) {
                break;
            }
        }
        try {
            if (motorista != null) {
                throw new NenhumMotoristaDisponivelException("Não encontramos nenhum motorista disponível");
            }
        } catch (NenhumMotoristaDisponivelException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("1. Cancelar");
        System.out.println("2. Finalizar");
        System.out.println("3. Realizar Pagamento");
        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                corrida.cancelar();
                break;
            case 2:
                corrida.finalizar();
                break;
            case 3:
                if (corrida.verificarEstadoParaPagar()) {
                    passageiro.realizarPagamento(corrida.calcularPreco());
                }
                break;
        }
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static ArrayList<Corrida> getCorridas() {
        return corridas;
    }
}
