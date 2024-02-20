package brandiq.brandiq.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorSalaEdit {
    private Integer id;
    private String id_jugador;
    private Integer id_tablero;
    private Integer puntos;
    private Integer fallos;
    private Integer aciertos;
    private Integer posicionX;
    private Integer posicionY;  
    private boolean turno;
}
