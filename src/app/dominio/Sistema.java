package app.dominio;

import app.excecoes.NenhumMotoristaDisponivelException;
import app.usuarios.*;
import app.veiculo.*;
import app.pagamento.*;

import java.time.format.DateTimeParseException;
import java.util.*;

public class Sistema {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Corrida> corridas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static int lerOpcao() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro");
                scanner.next(); // Limpa o buffer do scanner
            }
        }
    }
    public static double lerPontoFlutuante() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpa o buffer do scanner
            }
        }
    }

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("{------------------ Ride-Sharing    FGA0158 ------------------}");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("================================================================");
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



    public static void validadorDeCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                throw new InputMismatchException("CPF já cadastrado.");
            }
        }
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
                num = cpf.charAt(i) - 48;
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
                num = cpf.charAt(i) - 48;
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

    public static void validadorDeCnh(String cnh) {
        if (!cnh.matches("[0-9]+")) {
            throw new InputMismatchException("CNH deve conter apenas números.");
        }
        if (cnh.length() != 9) {
            throw new InputMismatchException("CNH deve ter 9 dígitos.");
        }
    }

    public static void validadorDeTelefone(String telefone) {
        if (!telefone.matches("[0-9]+")) {
            throw new InputMismatchException("Telefone deve conter apenas números.");
        }
        if (telefone.length() < 12 || telefone.length() > 13) {
            throw new InputMismatchException("Telefone deve ter 12 ou 13 dígitos.");
        }
    }

    public static void cadastrarUsuario() {

        String[] dados;
        dados = adquirirDadosComuns();
        int tipoDeUsuario;
        System.out.println("1. Passageiro");
        System.out.println("2. Motorista");
        System.out.print(" Digite o tipo de usuário: ");
        tipoDeUsuario = lerOpcao();
        
        switch (tipoDeUsuario) {
            
            case 1:
                cadastrarPassageiro(dados);
                break;
            case 2:
                cadastrarMotorista(dados);
                break;
        }
        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println("================================================================");
    }

    private static void cadastrarMotorista(String[] dados) {
        System.out.println("````````````````````````````````````````````````````````````````");
        String numeroCnh;
        while (true) {
            try {
                System.out.print("Digite o número da CNH: ");
                numeroCnh = scanner.next();
                validadorDeCnh(numeroCnh);
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " Tente novamente.");
            }
        }
        Cnh cnh = null;
        while (cnh == null) {
            try {
                System.out.print("Digite a data de validade da CNH (MM/yyyy): ");
                String validade = scanner.next();
                cnh = new Cnh(numeroCnh, validade);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use o formato MM/yyyy.");
            }
        }

        System.out.print("Digite a cor do veículo: ");
        String cor = scanner.next();
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.next();
        System.out.print("Digite o ano do veículo: ");
        int ano = lerOpcao();
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.next();

        Categoria categoria = null;
        int escolha;
        do {
            System.out.println("Digite a categoria do veículo (Comum ou luxo): ");
            System.out.println("1. Comum");
            System.out.println("2. Luxo");
            System.out.print("Escolha uma opção: ");
            escolha = lerOpcao();
            switch (escolha) {
                case 1:
                    categoria = new CategoriaComum();
                    break;
                case 2:
                    categoria = new CategoriaLuxo();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        }while (escolha != 1 && escolha != 2);

        Veiculo veiculo = new Veiculo(placa, modelo, cor, ano, categoria);
        System.out.println("````````````````````````````````````````````````````````````````");
        int disponivel;
        StatusDisponibilidade disponibilidade = null;
        do {
            System.out.println("O motorista pode começar a trabalhar agora?");
            System.out.println("1. Sim");
            System.out.println("2. Não");
            System.out.print("Escolha uma opção: ");
            disponivel = lerOpcao();

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
        }while(disponivel != 1 && disponivel != 2);
        System.out.println("````````````````````````````````````````````````````````````````");
        usuarios.add(new Motorista(dados[0], dados[1], dados[2], dados[3], dados[4], veiculo, cnh, disponibilidade));
    }

    private static void cadastrarPassageiro(String[] dados) {
        FormaDePagamento pagamento = null;
        int formaDePagamento;
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        do {
            System.out.println("1. Credito");
            System.out.println("2. Dinheiro");
            System.out.println("3. Pix");
            System.out.println("4. Debito");
            System.out.print("Escolha a forma de pagamento que irá usar: ");
            formaDePagamento = lerOpcao();
            switch (formaDePagamento) {
                case 1:
                    CadastroCartao cartaoCredito = cadastrarCartao();
                    System.out.print("Digite o limite do Cartão: ");
                    double limite = lerPontoFlutuante();
                    pagamento = new Credito(cartaoCredito, limite);
                    break;
                case 2:
                    System.out.print("Digite o dinheiro disponível: ");
                    double dinheiro = lerPontoFlutuante();
                    pagamento = new Dinheiro(dinheiro);
                    break;
                case 3:
                    System.out.print("Digite o valor na conta: ");
                    double valorNaConta = lerPontoFlutuante();
                    pagamento = new Pix(valorNaConta);
                    break;
                case 4:
                    CadastroCartao cartaoDebito = cadastrarCartao();
                    System.out.println("Digite o seu saldo: ");
                    double saldo = lerPontoFlutuante();
                    pagamento = new Debito(cartaoDebito, saldo);
                    break;
                default:
                    System.out.println("Opção de pagamento inválida.");
                    break;
            }
        }while (formaDePagamento != 1 && formaDePagamento != 2 && formaDePagamento != 3 && formaDePagamento != 4);
        scanner.nextLine();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        usuarios.add(new Passageiro(dados[0], dados[1], dados[2], dados[3], dados[4], pagamento));
    }

    private static String[] adquirirDadosComuns() {
        String cpf;
        while (true) {
            try {
                System.out.print("Digite o CPF(Somente números): ");
                cpf = scanner.next();
                scanner.nextLine(); // Limpeza de buffer
                validadorDeCpf(cpf);
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " Tente novamente.");
            }
        }
        String nome;
        System.out.print("Digite o nome: ");
        nome = scanner.next();
        scanner.nextLine(); // Limpeza de buffer
        String email;
        System.out.print("Digite o email: ");
        email = scanner.next();
        scanner.nextLine(); // Limpeza de buffer
        String telefone;
        while (true) {
            try {
                System.out.print("Digite o telefone: ");
                telefone = scanner.next();
                validadorDeTelefone(telefone);
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " Tente novamente.");
            }
        }
        String senha, senhaDeConfirmacao;
        do {
            System.out.print("Digite a senha: ");
            senha = scanner.next();
            scanner.nextLine(); // Limpeza de buffer
            System.out.print("Confirme a senha: ");
            senhaDeConfirmacao = scanner.next();
            scanner.nextLine(); // Limpeza de buffer
            if (!senha.equals(senhaDeConfirmacao)) {
                System.out.println("As senhas não coincidem. Tente novamente.");
            }
        }while (!senha.equals(senhaDeConfirmacao));
        System.out.println("================================================================");
        return new String[]{nome, email, senha, cpf, telefone};
    }

    public static CadastroCartao cadastrarCartao() {
        System.out.print("Digite o numero do cartão: ");
        String numeroCartao = scanner.next();
        System.out.print("Digite o nome do Titular: ");
        String nomeTitular = scanner.next();
        String dataDeValidade;
        while (true) {
            try {
                System.out.print("Digite a data de validade (MM/AAAA): ");
                dataDeValidade = scanner.next();
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use o formato MM/AAAA.");
            }
        }
        System.out.print("Digite o código de segurança: ");
        String codigoSeguranca = scanner.next();
        return new CadastroCartao(numeroCartao, nomeTitular, dataDeValidade, codigoSeguranca);
    }
    private static List<Motorista> procurarMotoristas(String categoria) {
        List<Motorista> motoristasOnline;
        motoristasOnline = usuarios.stream()
                .filter(u -> u instanceof Motorista)
                .map(u -> (Motorista) u)
                .filter(m -> m.getDisponibilidade() && m.getValidadeCnh() && m.getCategoriaVeiculo().equals(categoria))
                .toList();
        if (motoristasOnline.isEmpty()) {
            throw new NenhumMotoristaDisponivelException("\nNenhum motorista disponível no momento.");
        }
        return motoristasOnline;
    }

    public static void processarCorrida(Corrida corrida, Passageiro passageiro) {
        corridas.add(corrida);
        corrida.imprimir();
        int opcao;
        boolean confirmacao = false;
        List<Motorista> motoristasDisponiveis;
        try {
            motoristasDisponiveis = procurarMotoristas(corrida.getCATEGORIA());
        } catch (NenhumMotoristaDisponivelException e) {
            System.out.println(e.getMessage());
            return;
        }
        Motorista motorista = adquirirMotorista(corrida,passageiro, motoristasDisponiveis, confirmacao);
        if (motorista == null) return;
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        corrida.iniciar(motorista);
       while (true) {
           System.out.println("1. Cancelar");
           System.out.println("2. Finalizar");
           System.out.println("3. Realizar Pagamento");
           System.out.print("Escolha uma opção: ");
           opcao = lerOpcao();
           switch (opcao) {
               case 1:
                   if (corrida.cancelar()) {
                       return;
                   }
                   break;
               case 2:
                   if (corrida.finalizar(motorista)) {
                       motorista.receberAvaliacao();
                       passageiro.receberAvaliacao();
                   }
                   break;
               case 3:
                   if (corrida.verificarEstadoParaPagar()) {
                       passageiro.realizarPagamento(corrida.calcularPreco());
                       return;
                   }
                   break;
               default:
                   System.out.println("Opção inválida.");
           }
       }
    }

    private static Motorista adquirirMotorista(Corrida corrida, Passageiro passageiro, List<Motorista> motoristasDisponiveis, boolean confirmacao) {
        int opcao;
        Motorista motorista = null;
        for (Motorista i : motoristasDisponiveis) {
            System.out.println("================================================================");
            System.out.println("Encontramos o motorista: " + i.getNome());
            System.out.println(i.getMediaAvaliacao());
            System.out.println("================================================================");
            do {
                System.out.println("Aceita o motorista?");
                System.out.println("1. Sim");
                System.out.println("2. Não");
                System.out.println("3. Cancelar corrida");
                System.out.print("Escolha uma opção: ");
                opcao = lerOpcao();
                System.out.println("================================================================");
                switch (opcao) {
                    case 1:
                        System.out.println("O Cliente " + passageiro.getNome() + " está solicitando uma corrida!");
                        System.out.println(passageiro.getMediaAvaliacao());
                        System.out.println("================================================================");
                        corrida.imprimir();
                        int opcao2;
                        do {
                            System.out.println("Aceita o passageiro?");
                            System.out.println("1. Sim");
                            System.out.println("2. Não");
                            System.out.print("Escolha uma opção: ");
                            opcao2 = lerOpcao();
                            switch (opcao2) {
                                case 1:
                                    confirmacao = true;
                                    motorista = i;
                                    i.setStatusDisponibilidade(StatusDisponibilidade.EM_CORRIDA);
                                    break;
                                case 2:
                                    System.out.println("O motorista recusou a corrida.");
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }

                        } while (opcao2 != 1 && opcao2 != 2);
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("Corrida cancelada.");
                        corrida.cancelar();
                        return null;
                    default:
                        System.out.println("Opção inválida.");
                }
            }while (opcao != 1 && opcao != 2);
            System.out.println("================================================================");
            if (confirmacao) {
                break;
            }
        }
        try {
            if (motorista == null) {
                throw new NenhumMotoristaDisponivelException("Não há mais nenhum motorista disponível.");
            }
        } catch (NenhumMotoristaDisponivelException e) {
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println("____________________________________________________________________");
        return motorista;
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

}
