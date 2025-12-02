package app.usuarios;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Avaliacao {
    private int nota;

    public Avaliacao(int nota, String comentario) {
        this.nota = nota;
    }
    public int getNota() {
        return nota;
    }

}
