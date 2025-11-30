package app.dominio;

public class Corrida {
    private final String ORIGEM, DESTINO;
    private StatusCorrida statusCorrida;
    private final Categoria CATEGORIA;
    private double distancia;


    public Corrida(String origem, String destino, Categoria categoria ) {
        this.ORIGEM = origem;
        this.DESTINO = destino;
        this.CATEGORIA = categoria;
        calcularDistancia();
        statusCorrida = StatusCorrida.SOLICITADA;
    }

    public double getDistancia() {
        return distancia;
    }

    public String getOrigem() {
        return ORIGEM;
    }

    public String getDestino() {
        return DESTINO;
    }

    public StatusCorrida getStatusCorrida() {
        return statusCorrida;
    }

    public Categoria getCategoria() {
        return CATEGORIA;
    }

    private void calcularDistancia() {
        this.distancia = 1 + (Math.random() * 999);
    }

    public boolean iniciar() {
        statusCorrida = StatusCorrida.EM_ANDAMENTO;
        return true;
    }

    public boolean finalizar() {
        statusCorrida = StatusCorrida.FINALIZADA;
        return true;
    }

    public boolean cancelar() {
        statusCorrida = StatusCorrida.FINALIZADA;
        return true;
    }

    public double calcularPreco() {
        return CATEGORIA.calcularPreco(distancia);
    }

}
