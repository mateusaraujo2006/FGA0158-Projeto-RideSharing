package app.veiculo;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Cnh {
    private final String id;
    private final YearMonth validade;
    private final LocalDateTime dataDeHoje = LocalDateTime.now();
    
    public Cnh(String id, String validade) {
        this.id = id;
        this.validade = YearMonth.parse(validade, DateTimeFormatter.ofPattern("MM/yyyy"));
    }
    public Boolean VerificarValidadeCnh () {
        if (this.id == null || this.id.trim().isEmpty()) {
            return false;
        }
        if (this.validade == null) {
            return false;
        }
        if ((dataDeHoje.getYear() > validade.getYear()) || (dataDeHoje.getYear() == validade.getYear()) && (dataDeHoje.getMonthValue() >= validade.getMonthValue())) {
            return false;
        }
        return this.id.length() == 9;

    }

}


