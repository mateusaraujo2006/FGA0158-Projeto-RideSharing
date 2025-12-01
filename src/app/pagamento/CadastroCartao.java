package app.pagamento;

public class CadastroCartao {
    private String numeroCartao;
    private String nomeTitular;
    private String dataValidade;
    private String codigoSeguranca;
    private double saldo;
    private double limite;

    public CadastroCartao(String numeroCartao, String nomeTitular, String dataValidade, String codigoSeguranca) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.codigoSeguranca = codigoSeguranca;
        this.saldo = saldo;
        this.limite = limite;
        
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public double getSaldo() {
        return saldo;
    }
    public double getLimite() {
        return limite;
    }   