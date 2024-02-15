package brandiq.brandiq.model.db;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tableros")
public class TableroDb implements Serializable {
    private static final long serialVersionUID = -818542778373595260L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private JugadorDb jugadorDb;// Creador de ls partida
    private String titulo;
    private int finalizada;

     // Constructor para todos los campos
     public TableroDb(JugadorDb jugadorDb, String titulo, int finalizada) {
        this.jugadorDb = jugadorDb;
        this.titulo = titulo;
        this.finalizada = finalizada;
    }
}
