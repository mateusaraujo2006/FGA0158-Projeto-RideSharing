package app.veiculo;

public class Veiculo {
    private final String placa;
    private final String modelo;
    private final String cor;
    private final int ano;

    public Veiculo(String placa, String modelo, String cor, int ano) {
    
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
    }
    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public String getCor() {
        return cor;
    }

    public int getAno() {
        return ano;
    }
}
