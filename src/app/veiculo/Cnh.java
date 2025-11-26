package app.veiculo;

public class Cnh {
    private final String Id;
    private final String Validade;
    private final String DatadeHoje;
    
    public Cnh(String id, String validade) {
        this.Id = id;
        this.Validade = validade;
        this.DatadeHoje = DatadeHoje;
        
    }
    public Boolean VerificarValidadeCnh () {
        if (this.Id == null || this.Id.trim().isEmpty()) {
            return false;
        }
        if (this.Validade == null || this.Validade.trim().isEmpty()) {
            return false;
        }
        if (this.Id.length() < 9 || this.Id.length() > 9) {
            return false;
        }
        if (validade < DatadeHoje){
            return false;
        }
        else return true;

    }

}


