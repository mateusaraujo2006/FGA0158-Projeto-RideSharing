package app.dominio;

public class CategoriaLuxo extends Categoria {
    public static final double TARIFA_BASE = 9.00;
    public static final double MULTIPLICADOR = 2.20;

    public CategoriaLuxo(double distancia) {
        setDistancia(distancia);
    }

    @Override
    public double calcularPreco(double distancia) {
        return distancia*MULTIPLICADOR + TARIFA_BASE;
    }

}
