package app.dominio;

public class CategoriaLuxo implements Categoria {
    public static final double TARIFA_BASE = 9.00;
    public static final double MULTIPLICADOR = 2.20;

    @Override
    public double calcularPreco(double distancia) {
        return distancia*MULTIPLICADOR + TARIFA_BASE;
    }

}
