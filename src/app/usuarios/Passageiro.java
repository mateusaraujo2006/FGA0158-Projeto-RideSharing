package app.usuarios;

import app.dominio.Sistema;
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
            System.out.println("C. Mudar Forma de Pagamento");
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
                    mudarFormaDePagamento();
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

    public static void mudarFormaDePagamento() {

    }

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }


}
