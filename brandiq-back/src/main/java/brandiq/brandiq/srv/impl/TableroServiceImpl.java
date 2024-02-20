package brandiq.brandiq.srv.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroInfo;
import brandiq.brandiq.model.dto.TableroList;
import brandiq.brandiq.repository.JugadorRepository;
import brandiq.brandiq.repository.JugadorSalaEditRepository;
import brandiq.brandiq.repository.JugadorSalaRepository;
import brandiq.brandiq.repository.TableroEditRepository;
import brandiq.brandiq.repository.TableroRepository;
import brandiq.brandiq.srv.TableroService;
import brandiq.brandiq.srv.mapper.TableroMapper;
import lombok.NonNull;

@Service
public class TableroServiceImpl implements TableroService {
    private final TableroRepository tableroRepository;
    private final TableroEditRepository tableroEditRepository;
    private final JugadorSalaRepository jugadorSalaRepository;
    private final JugadorSalaEditRepository jugadorSalaEditRepository;
    private final JugadorRepository jugadorRepository;

    public TableroServiceImpl(TableroRepository tableroRepository, TableroEditRepository tableroEditRepository, JugadorSalaRepository jugadorSalaRepository, JugadorSalaEditRepository jugadorSalaEditRepository, JugadorRepository jugadorRepository) {
        this.tableroRepository = tableroRepository;
        this.tableroEditRepository = tableroEditRepository;
        this.jugadorSalaRepository = jugadorSalaRepository;
        this.jugadorSalaEditRepository = jugadorSalaEditRepository;
        this.jugadorRepository = jugadorRepository;
    }   

    @Override
    public List<TableroList> findAllTableroList() {
        List<TableroEditDb> tablerosEditDbs = tableroEditRepository.findAll();
        return TableroMapper.INSTANCE.tablerosEditDbToTablerosList(tablerosEditDbs);
    }

    @Override
    public TableroEdit save(TableroEdit tableroEdit) {
        return TableroMapper.INSTANCE.tableroEditDbToTableroEdit(
                tableroEditRepository.save(TableroMapper.INSTANCE.tableroEditToTableroEditDb(tableroEdit)));
    }

    @Override
    public Optional<TableroEdit> update(TableroEdit tableroEdit) {
        Optional<TableroEditDb> tableroEditDb = tableroEditRepository.findById(tableroEdit.getId());

        if (tableroEditDb.isPresent()) {
            TableroMapper.INSTANCE.updateTableroEditDbFromTableroEdit(tableroEdit, tableroEditDb.get());
            return Optional.of(
                    TableroMapper.INSTANCE.tableroEditDbToTableroEdit(tableroEditRepository.save(tableroEditDb.get())));
        } else {
            throw new ResourceNotFoundException("Tablero NOT FOUND: " + tableroEdit.getId_jugador());
        }

    }

    @Override
    public Optional<TableroEdit> getTableroEditById(int id) {
        Optional<TableroEditDb> tableroEditDb = tableroEditRepository.findById(id);
        if (tableroEditDb.isPresent())
            return Optional.of(TableroMapper.INSTANCE.tableroEditDbToTableroEdit(tableroEditDb.get()));
        else
            return Optional.empty();
    }

    public Optional<TableroInfo> getTableroInfoById (@NonNull Integer id){
        Optional<TableroDb> tableroDb=tableroRepository.findById(id);
        if (tableroDb.isPresent()) {
            return Optional.of(TableroMapper.INSTANCE.tableroDbToTableroInfo(tableroDb.get()));
        } else{
            return Optional.empty();
        }
    }

    @Override
    public TableroEdit addTableroEdit(TableroEdit tableroEdit) {
        TableroEditDb tableroDb = TableroMapper.INSTANCE.tableroEditToTableroEditDb(tableroEdit);
        TableroEditDb savedTablero=tableroEditRepository.save(tableroDb);

        tableroEdit = TableroMapper.INSTANCE.tableroEditDbToTableroEdit(savedTablero);

        JugadorSalaEditDb jugadorSalaEditDb = new JugadorSalaEditDb(null, tableroEdit.getId_jugador(), tableroEdit.getId(), 0, 0, 0, 0, 0, true);
        jugadorSalaRepository.save(jugadorSalaEditDb);

        return tableroEdit;
    }

    // @Override
    // public ResponseEntity<?> joinTablero(Integer idTablero, Integer idJugador) {
    //     try {
    //         TableroDb tableroEdit = tableroRepository.findById(idTablero)
    //                 .orElseThrow(() -> new IllegalArgumentException("Tablero no encontrado"));

    //         JugadorSalaEditDb jugadorSalaEditDb = jugadorSalaEditRepository.findById(idJugador)
    //                 .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado"));

    //         // Lógica para unir al jugador al tablero (puedes modificar esto según tu modelo de datos)
    //         jugadorSalaEditDb.setId_tablero(idTablero);

    //         // Guardar los cambios en el jugador
    //         jugadorSalaRepository.save(jugadorSalaEditDb);

    //         return ResponseEntity.ok("Jugador unido al tablero exitosamente");
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    @Override
    public ResponseEntity<?> joinTablero(Integer idTablero, String idJugador) {
        try {
            TableroDb tableroEdit = tableroRepository.findById(idTablero)
                    .orElseThrow(() -> new IllegalArgumentException("Tablero no encontrado"));

            JugadorDb jugadorDb = jugadorRepository.findById(idJugador)
                    .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado"));

            // Lógica para unir al jugador al tablero (puedes modificar esto según tu modelo de datos)
            JugadorSalaEditDb jugadorSalaEditDb = new JugadorSalaEditDb(null, idJugador, idTablero, 0, 0, 0, 0, 0, true);
            jugadorSalaEditDb.setId_tablero(idTablero);

            // Guardar los cambios en el jugador
            jugadorSalaRepository.save(jugadorSalaEditDb);

            // Crear un objeto JSON con el mensaje
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Jugador unido al tablero exitosamente");

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Si hay una excepción, devolver un objeto JSON con el mensaje de error
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // @Override
    // public Long obtenerUltimoIdParaJugador(String id_jugador) {
    //     return tableroEditRepository.findLastTableroIdByJugador(id_jugador);
    // }

}
