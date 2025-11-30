package app.veiculo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cnh {
    private final String id;
    private final LocalDateTime validade;
    private final LocalDateTime dataDeHoje = LocalDateTime.now();
    
    public Cnh(String id, String validade) {
        this.id = id;
        this.validade = LocalDateTime.parse(validade, DateTimeFormatter.ofPattern("MM/yyyy"));
        
    }
    public Boolean VerificarValidadeCnh () {
        if (this.id == null || this.id.trim().isEmpty()) {
            return false;
        }
        if (this.validade == null) {
            return false;
        }
        if ((validade.getYear() >= dataDeHoje.getYear()) && (validade.getMonthValue() >= dataDeHoje.getMonthValue())) {
            return false;
        }
        return this.id.length() == 9;

    }

}


