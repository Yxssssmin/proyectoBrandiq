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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="jugador_en_sala")
public class JugadorSalaDb implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private JugadorDb jugadorDb;
    @ManyToOne
    @JoinColumn(name = "id_tablero")
    private TableroDb tableroDb;
    private Integer puntos;
    private Integer fallos;
    private Integer aciertos;
    private Integer posicionX;
    private Integer posicionY;  
    private boolean turno;

    public JugadorSalaDb(JugadorDb jugadorDb, TableroDb tableroDb, Integer puntos, Integer fallos, Integer aciertos,
            Integer posicionX, Integer posicionY, boolean turno) {
        this.jugadorDb = jugadorDb;
        this.tableroDb = tableroDb;
        this.puntos = puntos;
        this.fallos = fallos;
        this.aciertos = aciertos;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.turno = turno;
    }
    
}