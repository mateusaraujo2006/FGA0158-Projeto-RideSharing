package app.usuarios;

import app.dominio.*;
import app.pagamento.*;

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
            resp = input.next().toUpperCase().charAt(0);
            input.nextLine();

            switch (resp) {
                case 'A':
                    solicitarCorrida();
                    break;
                case 'B':
                    System.out.println("Por segurança, digite seus dados de login:");
                    verificadorDeSeguranca();
                    mudarDados();
                    break;
                case 'C':
                    mudarFormaDePagamento(this);
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
        input.close(); // limpeza de buffer
    }

    public static void mudarFormaDePagamento(Passageiro p) {
        int resp;
        do {
            System.out.println("==============================");
            System.out.println("1. Credito");
            System.out.println("2. Dinheiro");
            System.out.println("3. Pix");
            System.out.println("4. Debito");
            System.out.println("===============================");
            System.out.print  ("Escolha a forma de pagamento que irá usar: ");
            resp = input.nextInt();
            switch (resp) {
                case 1:
                    p.setPagamento(new Credito(Sistema.cadastrarCartao()));
                    break;
                case 2:
                    System.out.print("Digite o dinheiro disponível: ");
                    double dinheiro = input.nextDouble();
                    p.setPagamento(new Dinheiro(dinheiro));
                    break;
                case 3:
                    System.out.print("Digite o valor na conta: ");
                    double valorNaConta = input.nextDouble();
                    p.setPagamento(new Pix(valorNaConta));
                    break;
                case 4:
                    System.out.println("Digite o seu saldo inicial: ");
                    double saldoInicial = input.nextDouble();
                    p.setPagamento(new Debito(Sistema.cadastrarCartao(), saldoInicial));
                    break;
                default:
                    System.out.println("Opção invalída. Tente novamente.");
                }
            } while (resp < 1 || resp > 4);
    }

    public void solicitarCorrida() {
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
        input.nextLine(); // limpeza de buffer
        Sistema.processarCorrida(new Corrida(origem, destino, categoria), this);
    }

    @Override
    public String toString() {
        return super.toString() + ", pagamento=" + pagamento + '}';
    }

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    public void setPagamento(FormaDePagamento pagamento) {
        this.pagamento = pagamento;
    }


}
