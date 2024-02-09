package brandiq.brandiq.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorInfo implements Serializable {
    private String nickname;
    private int victorias;
    private int derrotas;
    private int puntosTotales;
    private int partidas_jugadas;
}
