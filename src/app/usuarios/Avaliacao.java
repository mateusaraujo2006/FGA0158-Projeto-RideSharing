package app.usuarios;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Avaliacao {
    private int nota;
    private String comentario;
    private String data;

    public Avaliacao(int nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
        this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public String getData() {
        return data;
    }
}
