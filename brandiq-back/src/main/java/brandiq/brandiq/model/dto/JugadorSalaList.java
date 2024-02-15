package brandiq.brandiq.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JugadorSalaList implements Serializable{
    private String id_jugador;
}
