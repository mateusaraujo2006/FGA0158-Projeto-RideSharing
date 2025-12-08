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
        int resp;
        do {
            System.out.println("===============================");
            System.out.println("1. Solicitar corrida");
            System.out.println("2. Mudar dados pessoais");
            System.out.println("3. Mudar forma de pagamento");
            System.out.println("4. Informações da conta");
            System.out.println("5. Logout");
            if (divida > 0) System.out.println("6. Pagar dívida - R$" + divida);
            System.out.println("===============================");
            System.out.print("Escolha a sua opção de ação que desejar: ");
            resp = Sistema.lerOpcao();

            switch (resp) {
                case 1:
                    if (divida == 0) {
                        solicitarCorrida();
                    } else {
                        System.out.println("Pagamento da corrida anterior pendente! Não é possível realizar uma nova corrida.");
                    }
                    break;
                case 2:
                    System.out.println("Por segurança, digite seus dados de login:");
                    verificadorDeSeguranca();
                    mudarDados();
                    break;
                case 3:
                    mudarFormaDePagamento(this);
                    break;
                case 4:
                    System.out.println("Por segurança, digite seus dados de login:");
                    verificadorDeSeguranca();
                    System.out.println(this);
                    break;
                case 5:
                    System.out.println("Deslogando do Sistema...");
                    Sistema.main(null);
                    break;
                case 6:
                    if (divida > 0) {
                        realizarPagamento(divida);
                        break;
                    }
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (resp != 5);
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
            resp = Sistema.lerOpcao();
            switch (resp) {
                case 1:
                    CadastroCartao cartaoCredito = Sistema.cadastrarCartao();
                    System.out.print("Digite o limite do Cartão: ");
                    double limite = Sistema.lerPontoFlutuante();
                    p.setPagamento(new Credito(cartaoCredito, limite));
                    break;
                case 2:
                    System.out.print("Digite o dinheiro disponível: ");
                    double dinheiro = Sistema.lerPontoFlutuante();
                    p.setPagamento(new Dinheiro(dinheiro));
                    break;
                case 3:
                    System.out.print("Digite o valor na conta: ");
                    double valorNaConta = Sistema.lerPontoFlutuante();
                    p.setPagamento(new Pix(valorNaConta));
                    break;
                case 4:
                    System.out.println("Digite o seu saldo inicial: ");
                    double saldoInicial = Sistema.lerPontoFlutuante();
                    p.setPagamento(new Debito(Sistema.cadastrarCartao(), saldoInicial));
                    break;
                default:
                    System.out.println("Opção invalída. Tente novamente.");
                }
            } while (resp != 1 && resp != 2 && resp != 3 && resp != 4);
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
            opc = Sistema.lerOpcao();
            switch (opc) {
                case 1 -> categoria = new CategoriaComum();
                case 2 -> categoria = new CategoriaLuxo();
                default -> System.out.println("Opção Invalída. Tente novamente.");
            }

        } while (opc != 1 && opc != 2);
        Sistema.processarCorrida(new Corrida(origem, destino, categoria), this);
    }

    @Override
    public String toString() {
        return super.toString() + ", pagamento=" + pagamento + '}';
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }
    public void realizarPagamento(double valor) {
        try {
            pagamento.processarPagamento(valor);
            setDivida(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setDivida(valor);
        }

    }
    public void setPagamento(FormaDePagamento pagamento) {
        this.pagamento = pagamento;
    }

}
