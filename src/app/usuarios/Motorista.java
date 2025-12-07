package app.usuarios;
import app.dominio.Categoria;
import app.dominio.CategoriaComum;
import app.dominio.CategoriaLuxo;
import app.dominio.Sistema;
import app.veiculo.*;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Motorista extends Usuario {
    private Veiculo veiculo;
    private Cnh cnh;
    private StatusDisponibilidade statusDisponibilidade;
    private static final Scanner input = new Scanner(System.in);

    public Motorista(String nome, String email, String senha, String cpf, String telefone, Veiculo veiculo, Cnh cnh, StatusDisponibilidade statusDisponibilidade) {
        super(nome, email, senha, cpf, telefone);
        this.veiculo = veiculo;
        this.cnh = cnh;
        this.statusDisponibilidade = statusDisponibilidade;
    }

    public boolean getValidadeCnh() {
        return cnh.VerificarValidadeCnh();
    }

    public boolean getDisponibilidade() {
        if (statusDisponibilidade == StatusDisponibilidade.ONLINE) {
            return true;
        }
        return false;
    }
    public String getCategoriaVeiculo() {
        return  veiculo.getCategoria();
    }

    public void setStatusDisponibilidade(StatusDisponibilidade statusDisponibilidade) {
        this.statusDisponibilidade = statusDisponibilidade;
    }

    @Override
    public void login() {
        int resp;
        do {
            System.out.println("===============================");
            System.out.println("1. Procura corrida");
            System.out.println("2. Mudar dados pessoais");
            System.out.println("3. Mudar o veiculo/atualizar a cnh");
            System.out.println("4. Logout");
            System.out.println("===============================");
            System.out.print("Escolha a sua opção de ação que desejar: ");
            resp = Sistema.lerOpcao();

            switch (resp) {
                case 1:

                    break;
                case 2:
                    System.out.println("Por segurança, digite seus dados de login:");
                    verificadorDeSeguranca();
                    mudarDados();
                    break;
                case 3:
                    System.out.println("Por segurança, digite seus dados de login:");
                    verificadorDeSeguranca();
                    atualizarValidadeCnhOuAlterarVeiculo();
                    break;
                case 4:
                    System.out.println("Deslogando do Sistema...");
                    Sistema.main(null);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");


            }

        } while (resp != 4);

        input.close();
    }
    private void atualizarValidadeCnhOuAlterarVeiculo() {
        int resp;
        do {
            System.out.println("==============================");
            System.out.println("\n--- Gerenciamento de Veículo e CNH ---");
            System.out.println("1. Mudar Veículo (Modelo, Placa, Categoria)");
            System.out.println("2. Atualizar Validade da CNH");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha a opção: ");
            resp = Sistema.lerOpcao();
            switch (resp) {
                case 1:
                    mudarVeiculo();
                    break;
               case 2:
                   atualizarValidadeCnh();
                   break;
              case 3:
                  System.out.println("Voltando...");
                  break;
              default:
                  System.out.println("Opção inválida. Tente novamente.");
            }
        }while (resp != 3);
    }
    private void mudarVeiculo() {
        System.out.println("\n--- Cadastro do Novo Veículo ---");

        System.out.print("Digite o novo Modelo do Veículo: ");
        String novoModelo = input.nextLine();

        System.out.print("Digite a nova Placa do Veículo: ");
        String novaPlaca = input.nextLine();

        System.out.print("Digite a cor do Veículo: ");
        String novaCor = input.nextLine();
        System.out.print("digite o ano do veículo: ");
        int novoAno = Sistema.lerOpcao();
        input.nextLine();
        int escolha;
        Categoria novaCategoria = null;
        do {
            System.out.println("Digite a categoria do veículo (Comum ou luxo): ");
            System.out.println("1. Comum");
            System.out.println("2. Luxo");
            System.out.print("Escolha uma opção: ");
            escolha = Sistema.lerOpcao();
            switch (escolha) {
                case 1:
                    novaCategoria = new CategoriaComum();
                    break;
                case 2:
                    novaCategoria = new CategoriaLuxo();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (escolha != 1 && escolha != 2);
        input.nextLine();

        veiculo = new Veiculo(novoModelo, novaPlaca, novaCor, novoAno, novaCategoria);

        System.out.println("\nVeículo atualizado para: " + novoModelo + " (" + novaPlaca + ").");
    }
    private void atualizarValidadeCnh() {
        System.out.println("\n--- Atualizar Validade da CNH ---");
        while (true) {
            try {
                System.out.print("Digite a nova data de validade da CNH (formato MM/yyyy): ");
                String novaValidade = input.nextLine();
                cnh.setValidade(novaValidade);
                System.out.println("\nValidade da CNH atualizada para: " + novaValidade + ".");
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use o formato MM/yyyy.");
            }
        }
    }
}
