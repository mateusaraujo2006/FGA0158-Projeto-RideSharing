package app.dominio;
import app.excecoes.EstadoInvalidoDaCorridaException;
import app.usuarios.Motorista;
import app.usuarios.StatusDisponibilidade;

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
        System.out.println("Distância: " + distancia + " km");
        System.out.println("Categoria: " + CATEGORIA.getNome());
        System.out.println("Preço: " + calcularPreco());
    }
    

    private void calcularDistancia() {
        this.distancia = Math.round((1 + (Math.random() * 999) * 100) / 100.0); // Valor entre 1 e 1000
    }

    public void iniciar(Motorista motorista) {
        statusCorrida = StatusCorrida.EM_ANDAMENTO;
        motorista.setStatusDisponibilidade(StatusDisponibilidade.EM_CORRIDA);
        System.out.println("Corrida Iniciada.");
        new Thread(() -> {
            try {
                Thread.sleep(15000); // 15 segundos
                this.chegou = true;
                System.out.println("\nO veiculo chegou a:" + DESTINO);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("A corrida foi interrompida.");
            }
        }).start();
    }

    public boolean finalizar(Motorista motorista) {
        try {
            if (!chegou) {
                throw new EstadoInvalidoDaCorridaException("\nO veiculo não chegou em " + DESTINO);
            }
        } catch (EstadoInvalidoDaCorridaException e) {
            System.out.println(e.getMessage());
            return false;
        }
        statusCorrida = StatusCorrida.FINALIZADA;
        motorista.setStatusDisponibilidade(StatusDisponibilidade.ONLINE);
        System.out.println("Corrida Finalizada.");
        return true;
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
        return Math.round((CATEGORIA.calcularPreco(distancia)) * 100 / 100.0);
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

    public String getCATEGORIA() {
        return CATEGORIA instanceof CategoriaComum ? "Comum" : "Luxo";
    }
}
