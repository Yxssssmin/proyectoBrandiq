package brandiq.brandiq.model.db;

import java.io.Serializable;

import brandiq.brandiq.security.entity.UsuarioDb;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
}
