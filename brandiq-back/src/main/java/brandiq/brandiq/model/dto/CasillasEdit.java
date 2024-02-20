package brandiq.brandiq.model.dto;

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
public class CasillasEdit {
    private Integer id;
    private Integer id_tablero;
    private byte[] imagen;
    private String nombre;
    private Integer posicionX;
    private Integer posicionY;
}
