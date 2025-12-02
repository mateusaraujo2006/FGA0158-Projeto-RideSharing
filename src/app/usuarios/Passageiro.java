package app.usuarios;

import app.dominio.*;
import app.pagamento.Dinheiro;
import app.pagamento.FormaDePagamento;

import java.util.Scanner;

public class Passageiro extends Usuario {
    private FormaDePagamento pagamento;
    private double divida;

    private static final Scanner input = new Scanner(System.in);

    public Passageiro(String nome, String email, String senha, String cpf, String telefone, FormaDePagamento pagamento) {
        super(nome, email, senha, cpf, telefone);
        this.pagamento = pagamento;
    }

    @Override
    public void login() {
        char resp;
        do {
            System.out.println("===============================");
            System.out.println("A. Solicitar corrida");
            System.out.println("B. Mudar dados pessoais");
            System.out.println("C. Mudar forma de pagamento");
            System.out.println("D. Informações da conta");
            System.out.println("E. Logout");
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
                    mudarFormaDePagamento();
                    break;
                case 'D':
                    System.out.println("Por segurança, digite seus dados de login:");
                    verificadorDeSeguranca();
                    System.out.println(this);
                    break;
                case 'E':
                    System.out.println("Deslogando do Sistema...");
                    Sistema.main(null);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (resp != 'E');
        input.close();
    }

    public static void mudarFormaDePagamento() {

    }

    public Corrida solicitarCorrida() {
        System.out.print("Informe o seu ponto de origem: ");
        String origem = input.nextLine();
        System.out.print("Informe o seu ponto de destino: ");
        String destino = input.nextLine();

        Categoria categoria = null;
        int opc;
        do {
            System.out.println("Informe a sua categoria: ");
            System.out.println("1. Comum");
            System.out.println("2. Luxo");
            System.out.print  ("Escolha: ");
            opc = input.nextInt();
            switch (opc) {
                case 1 -> categoria = new CategoriaComum();
                case 2 -> categoria = new CategoriaLuxo();
                default -> System.out.println("Opção Invalída. Tente novamente.");
            }

        } while (opc != 1 && opc != 2);

        return new Corrida(origem, destino, categoria);
    }

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

}
