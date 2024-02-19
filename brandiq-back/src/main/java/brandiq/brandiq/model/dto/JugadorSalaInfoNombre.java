package brandiq.brandiq.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorSalaInfoNombre {

    private Integer id;

    private String id_jugador;

    private Integer puntos;

    private boolean turno;
}
