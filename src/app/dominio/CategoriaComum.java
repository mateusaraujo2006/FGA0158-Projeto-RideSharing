package app.dominio;

public class CategoriaComum extends Categoria {
    public static final double TARIFA_BASE = 5.00;
    public static final double MULTIPLICADOR = 1.00;

    public CategoriaComum(double distancia) {
        setDistancia(distancia);
    }

    @Override
    public double calcularPreco(double distancia) {
        return distancia*MULTIPLICADOR + TARIFA_BASE;
    }

}
