package brandiq.brandiq.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JugadorSalaInfo implements Serializable{
    private Integer puntos;
    private Integer fallos;
    private Integer aciertos;
    private Integer posicionX;
    private Integer posicionY;
    private boolean turno;
}
