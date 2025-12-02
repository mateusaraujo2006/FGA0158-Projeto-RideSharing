package app.usuarios;
import app.dominio.Sistema;
import app.veiculo.*;

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

    public StatusDisponibilidade getStatusDisponibilidade() {
        return statusDisponibilidade;
    }

    @Override
    public void login() {
        char resp;
        do {
            System.out.println("===============================");
            System.out.println("A. Procura corrida");
            System.out.println("B. Mudar dados pessoais");
            System.out.println("C. Mudar o veiculo/atualizar a cnh");
            System.out.println("D. Logout");
            System.out.println("===============================");
            System.out.print("Escolha a sua opção de ação que desejar: ");
            resp = input.nextLine().toUpperCase().charAt(0);

            switch (resp) {
                case 'A':

                    break;
                case 'B':
                    System.out.println("Por segurança, digite seus dados de login:");
                    verificadorDeSeguranca();
                    mudarDados();
                    break;
                case 'C':
                    System.out.println("Por segurança, digite seus dados de login:");
                    verificadorDeSeguranca();
                    atualizarValidadeCnhOuAlterarVeiculo();
                    break;
                case 'D':
                    System.out.println("Deslogando do Sistema...");
                    Sistema.main(null);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");


            }

        } while (resp != 'D');

        input.close();
    }
    private void atualizarValidadeCnhOuAlterarVeiculo() {
        char resp;
        do {
            System.out.println("==============================");
            System.out.println("\n--- Gerenciamento de Veículo e CNH ---");
            System.out.println("1. Mudar Veículo (Modelo, Placa, Categoria)");
            System.out.println("2. Atualizar Validade da CNH");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha a opção: ");
            resp = input.nextLine().toUpperCase().charAt(0);
            switch (resp) {
                case '1':
                    mudarVeiculo();
                    break;
               case '2':
                   atualizarValidadeCnh();
                   break;
              case '3':
                  System.out.println("Voltando...");
                  break;
              default:
                  System.out.println("Opção inválida. Tente novamente.");
            }
        }while (resp != '3');
    }
    private void mudarVeiculo() {
        System.out.println("\n--- Cadastro do Novo Veículo ---");

        System.out.print("Digite o novo Modelo do Veículo: ");
        String novoModelo = input.nextLine();

        System.out.print("Digite a nova Placa do Veículo: ");
        String novaPlaca = input.nextLine();

        System.out.print("Digite a Categoria do Veículo (Ex: Comum, Luxo): ");
        String novaCategoria = input.nextLine();


        System.out.println("\nVeículo atualizado para: " + novoModelo + " (" + novaPlaca + ").");
    }
    private void atualizarValidadeCnh() {
        System.out.println("\n--- Atualizar Validade da CNH ---");

        System.out.print("Digite a nova data de validade da CNH (formato DD/MM/AAAA): ");
        String novaValidade = input.nextLine();


        System.out.println("\nValidade da CNH atualizada para: " + novaValidade + ".");
    }
}
