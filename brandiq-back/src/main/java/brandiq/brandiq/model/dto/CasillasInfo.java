package brandiq.brandiq.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CasillasInfo implements Serializable{
    private byte[] imagen;
    private String nombre;
    private Integer posicionX;
    private Integer posicionY;
}
