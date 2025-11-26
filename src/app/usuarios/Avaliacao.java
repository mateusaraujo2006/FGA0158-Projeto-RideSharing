package app.usuarios;
import java.util.Date;

public class Avaliacao {
    private int nota;
    private String comentario;
    private Date data;

    public Avaliacao(int nota, String comentario, Date data) {
        this.nota = nota;
        this.comentario = comentario;
        this.data = new Date();
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public Date getData() {
        return data;
    }
}
