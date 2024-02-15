package brandiq.brandiq.model.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import brandiq.brandiq.security.entity.UsuarioDb;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "jugadores")
public class JugadorDb implements Serializable {
    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioDb usuarioDb;
    private int victorias;
    private int derrotas;
    @Column(name = "puntos_totales")
    private int puntosTotales;
    private int partidas_jugadas;

    // Constructor
    public JugadorDb(String id, UsuarioDb usuarioDb, int victorias, int derrotas, int puntosTotales,
            int partidas_jugadas) {
        this.id = id;
        this.usuarioDb = usuarioDb;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.puntosTotales = puntosTotales;
        this.partidas_jugadas = partidas_jugadas;
    }

    @OneToMany(mappedBy = "id_jugador")
    private Set<TableroNombreDb> tablerosNombresDb = new HashSet<>();// Tablero creados
}
