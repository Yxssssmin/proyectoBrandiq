package brandiq.brandiq.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CasillasInfoNombre {
    private int id;
    private Integer id_tablero;
    private String nombre;
    private Integer posicionX;
    private Integer posicionY;
}
