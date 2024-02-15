package brandiq.brandiq.model.db;

import java.io.Serializable;

import brandiq.brandiq.security.entity.UsuarioDb;
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
@Table(name="jugador_en_sala")
public class JugadorSalaDb implements Serializable{

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private JugadorDb jugadorDb;
    /*@ManyToOne
    @JoinColumn(name = "id_tablero")
    private TableroDb tableroDb;*/
    private Integer puntos;
    private Integer fallos;
    private Integer aciertos;
    private Integer posicionX;
    private Integer posicionY;  
    private boolean turno;
    
}