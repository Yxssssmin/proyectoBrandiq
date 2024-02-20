package brandiq.brandiq.model.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "jugador_en_sala")
public class JugadorSalaEditDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String id_jugador;
    
    private Integer id_tablero;

    private Integer puntos;
    private Integer fallos;
    private Integer aciertos;
    private Integer posicionX;
    private Integer posicionY;  
    private boolean turno;
    public JugadorSalaEditDb(String id_jugador, Integer id_tablero, Integer puntos, Integer fallos, Integer aciertos,
            Integer posicionX, Integer posicionY, boolean turno) {
        this.id_jugador = id_jugador;
        this.id_tablero = id_tablero;
        this.puntos = puntos;
        this.fallos = fallos;
        this.aciertos = aciertos;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.turno = turno;
    }

    

    
}
