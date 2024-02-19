package brandiq.brandiq.model.db;

import java.io.IOException;
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "casillas")
public class CasillasDb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name ="id_tablero")
    private TableroDb tableroDb;

    @Lob
    @Column(name = "imagen", columnDefinition = "MEDIUMBLOB")
    private byte[] imagen;
    private String nombre;
    private Integer posicionX;
    private Integer posicionY;
    
 
    public CasillasDb (TableroDb tableroDb, String rutaImagen,String nombre,Integer posicionX,Integer posicionY) throws IOException{
        this.tableroDb=tableroDb;
        cargarImagen(rutaImagen);
        this.nombre=nombre;
        this.posicionX=posicionX;
        this.posicionY=posicionY;
    }

    private void cargarImagen(String rutaImagen) throws IOException {
        Path path = Paths.get(rutaImagen);
        this.imagen = Files.readAllBytes(path);
    }

}