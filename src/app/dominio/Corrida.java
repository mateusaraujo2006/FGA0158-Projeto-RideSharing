package app.dominio;

public class Corrida {
    private String origem, destino;
    private StatusCorrida statusCorrida;

    public Corrida(String origem, String destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public boolean iniciar() {
        statusCorrida = StatusCorrida.SOLICITADA;
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



}
