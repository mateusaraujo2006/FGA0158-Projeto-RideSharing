package app.dominio;
import app.excecoes.EstadoInvalidoDaCorridaException;
public class Corrida {
    private final String ORIGEM, DESTINO;
    private StatusCorrida statusCorrida;
    private final Categoria CATEGORIA;
    private double distancia;
    private boolean chegou = false;


    public Corrida(String origem, String destino, Categoria categoria ) {
        this.ORIGEM = origem;
        this.DESTINO = destino;
        this.CATEGORIA = categoria;
        calcularDistancia();
        statusCorrida = StatusCorrida.SOLICITADA;
    }

    public void imprimir() {
        System.out.println("------ Dados da Corrida ------");
        System.out.println("Origem: " + ORIGEM);
        System.out.println("Destino: " + DESTINO);
        System.out.println("Distância: " + distancia);
        System.out.println("Categoria: " + CATEGORIA.getNome());
        System.out.println("Preço: " + calcularPreco());
    }
    

    private void calcularDistancia() {
        this.distancia = 1 + (Math.random() * 999);
    }

    public void iniciar() {
        statusCorrida = StatusCorrida.EM_ANDAMENTO;
        System.out.println("Corrida Iniciada.");
        new Thread(() -> {
            try {
                Thread.sleep(25000); // 25 segundos
                this.chegou = true;
                System.out.println("O veiculo chegou a:" + DESTINO);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("A corrida foi interrompida.");
            }
        }).start();
    }

    public void finalizar() {
        try {
            if (!chegou) {
                throw new EstadoInvalidoDaCorridaException("O veiculo não chegou em " + DESTINO);
            }
        } catch (EstadoInvalidoDaCorridaException e) {
            System.out.println(e.getMessage());
            return;
        }
        statusCorrida = StatusCorrida.FINALIZADA;
        System.out.println("Corrida Finalizada.");
    }

    public boolean cancelar() {
        try {
            if (StatusCorrida.SOLICITADA != statusCorrida) {
                throw new EstadoInvalidoDaCorridaException("Não é possível cancelar uma corrida em andamento ou finalizada.");
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
            if (StatusCorrida.FINALIZADA != statusCorrida) {
                throw new EstadoInvalidoDaCorridaException("Espere a corrida terminar para pagar.");
            }

        }catch (EstadoInvalidoDaCorridaException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
