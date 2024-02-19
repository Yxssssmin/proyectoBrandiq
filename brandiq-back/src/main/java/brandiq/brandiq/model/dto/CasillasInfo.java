package brandiq.brandiq.model.dto;

import java.io.Serializable;

import brandiq.brandiq.model.db.TableroDb;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
