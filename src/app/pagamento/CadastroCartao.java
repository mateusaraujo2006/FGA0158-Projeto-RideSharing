package app.pagamento;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CadastroCartao {
    private String numeroCartao;
    private String nomeTitular;
    private YearMonth dataValidade;
    private String codigoSeguranca;


    public CadastroCartao(String numeroCartao, String nomeTitular, String dataValidade, String codigoSeguranca) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = YearMonth.parse(dataValidade, DateTimeFormatter.ofPattern("MM/yyyy"));
        this.codigoSeguranca = codigoSeguranca;

    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public YearMonth getDataValidade() {
        return dataValidade;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }


}