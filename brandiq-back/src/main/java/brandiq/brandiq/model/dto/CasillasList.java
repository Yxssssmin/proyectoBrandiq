package brandiq.brandiq.model.dto;

import java.io.Serializable;
import java.sql.Blob;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CasillasList implements Serializable{
       
    private int id;
    private String nombre;
    private Integer posicionX;
    private Integer posicionY;
}
