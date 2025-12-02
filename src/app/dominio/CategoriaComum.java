package app.dominio;

public class CategoriaComum implements Categoria {
    public static final double TARIFA_BASE = 5.00;
    public static final double MULTIPLICADOR = 1.00;

    @Override
    public double calcularPreco(double distancia) {
        return distancia*MULTIPLICADOR + TARIFA_BASE;
    }

    @Override
    public String getNome() {
        return "Comum";
    }

}
