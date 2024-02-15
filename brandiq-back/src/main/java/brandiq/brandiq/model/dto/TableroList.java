package brandiq.brandiq.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableroList {
    private int id; // CODIGO PARTIDA
    private String id_jugador; // CREADOR DEL TABLE
    private String titulo; // TITULO DE LA PARTIDA
    private int finalizada; // ESTADO DE LA PARTIDA: siendo 1 activa y 0 finalizada
}
