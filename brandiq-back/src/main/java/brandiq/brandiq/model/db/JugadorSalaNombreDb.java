package brandiq.brandiq.model.db;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="jugador_en_sala")
public class JugadorSalaNombreDb implements Serializable{
    private static final long serialVersionUID = -818542778373595260L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer puntos;

    private String id_jugador;

    private boolean turno;

    private Long id_tablero;
}
