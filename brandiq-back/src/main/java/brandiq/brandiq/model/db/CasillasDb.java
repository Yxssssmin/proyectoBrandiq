package brandiq.brandiq.model.db;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
public class CasillasDb implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tablero")
    private TableroDb tableroDb;

    @Lob
    @Column(name = "imagen", columnDefinition = "MEDIUMBLOB")
    private byte[] imagen;
    private String nombre;
    private Integer posicionX;
    private Integer posicionY;

    public CasillasDb(TableroDb tableroDb, byte[] imagen, String nombre, Integer posicionX, Integer posicionY) {
        this.tableroDb = tableroDb;
        this.imagen = imagen;
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
}