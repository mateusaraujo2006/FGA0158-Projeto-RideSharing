package app.veiculo;

import app.dominio.*;

public class Veiculo {
    private final String placa;
    private final String modelo;
    private final String cor;
    private final int ano;
    private final Categoria categoria;

    public Veiculo(String placa, String modelo, String cor, int ano, Categoria categoria) {
    
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria instanceof CategoriaComum ? "Comum" : "Luxo";
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
