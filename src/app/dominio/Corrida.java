package app.dominio;
import app.excecoes.EstadoInvalidoDaCorridaException;
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

    public void imprimir() {
        System.out.println("Origem: " + ORIGEM);
        System.out.println("Destino: " + DESTINO);
        System.out.println("Distância" + distancia);
        System.out.println("Categoria: " + CATEGORIA.getNome());
        System.out.println("Preço: " + calcularPreco());
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
        System.out.println("Corrida Finalizada.");
        return true;
    }

    public boolean cancelar() {
        try {
            if (StatusCorrida.SOLICITADA != statusCorrida) {
                throw new EstadoInvalidoDaCorridaException("Não é possível cancelar uma corrida em andamento");
            }
        }catch (EstadoInvalidoDaCorridaException e) {
            System.out.println(e.getMessage());
            return false;
        }
        statusCorrida = StatusCorrida.FINALIZADA;
        System.out.println("Corrida Cancelada.");
        return true;
    }

    public double calcularPreco() {
        return CATEGORIA.calcularPreco(distancia);
    }

    public boolean verificarEstadoParaPagar() {
        try {
            if (StatusCorrida.EM_ANDAMENTO == statusCorrida) {
                throw new EstadoInvalidoDaCorridaException("Espere a corrida terminar para pagar.");
            }

        }catch (EstadoInvalidoDaCorridaException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
