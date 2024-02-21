package brandiq.brandiq.srv;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.awt.image.BufferedImage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;

import brandiq.brandiq.model.dto.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasInfo;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroInfo;
import brandiq.brandiq.model.dto.TableroList;

public interface TableroService {
    /* PARA HACER EL UPDATE DEL CONTROLLER */
    public Optional<TableroEdit> getTableroEditById(int id);

    public TableroEdit save(TableroEdit tableroEdit);

    public Optional<TableroEdit> update(TableroEdit tableroEdit);

    public List<TableroList> findAllTableroList();

    public Optional<TableroInfo> getTableroInfoById(Integer id);

    public TableroEdit addTableroEdit(TableroEdit tableroEdit);

    public ResponseEntity<?> joinTablero(Integer idTablero, String idJugador);

    // // QUERY
    // Long obtenerUltimoIdParaJugador(String nombreJugador);
    // Método de serialización de imagen
    public byte[] serializarImagen(String nombreImagen);

    public int generarNumerosAleatoriosSinRepeticion(Set<Integer> numerosGenerados);

    public byte[] deserializarImagen(byte[] imagenSerializada);

    /*
     * public Object[] obtenerCasillasParaElTablero(String id_jugador, Integer
     * id_tablero);
     */

    public String obtenerCasillasParaElTablero(String id_jugador, Integer id_tablero);

    /* public FileSystemResource getImagenUsuario(String fichero); */
}
