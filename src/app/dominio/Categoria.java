package app.dominio;

public abstract class Categoria {
    private double distancia;

    public abstract double calcularPreco(double distancia);

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}
