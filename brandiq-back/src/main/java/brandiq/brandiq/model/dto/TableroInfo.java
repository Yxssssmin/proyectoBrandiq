package brandiq.brandiq.model.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableroInfo {

    private int id;
    private String titulo;
    private int finalizada;

    private Set<JugadorSalaInfoNombre> jugadoresSalaInfoNombres = new HashSet<>();
}
