package brandiq.brandiq.model.db;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "casillas")
public class CasillasEditDb implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    
    private Integer id_tablero;

    @Lob
    @Column(name = "imagen", columnDefinition = "MEDIUMBLOB")
    private byte[] imagen;
    private String nombre;
    private Integer posicionX;
    private Integer posicionY;

    public CasillasEditDb(Integer id_tablero, byte[] imagen, String nombre, Integer posicionX, Integer posicionY) {
        this.id_tablero = id_tablero;
        this.imagen = imagen;
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
    
}
