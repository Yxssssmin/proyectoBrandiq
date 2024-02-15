package brandiq.brandiq.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableroInfoNombre {
    private int id;
    private String id_jugador;
    private String titulo;
    private int finalizada;
}
