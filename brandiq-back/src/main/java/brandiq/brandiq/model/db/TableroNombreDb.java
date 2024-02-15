package brandiq.brandiq.model.db;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tableros")
public class TableroNombreDb implements Serializable {
    private static final long serialVersionUID = -818542778373595260L;
    @Id
    private int id;
    private String titulo;
    private String id_jugador;
    private int finalizada;
}
