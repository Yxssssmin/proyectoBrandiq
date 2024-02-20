package brandiq.brandiq.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CasillasList implements Serializable{
    private Integer id;
    private Integer id_tablero;
    private String nombre;
    private Integer posicionX;
    private Integer posicionY;
}
