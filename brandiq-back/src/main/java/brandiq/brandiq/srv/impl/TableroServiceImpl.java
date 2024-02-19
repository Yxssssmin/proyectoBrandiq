package brandiq.brandiq.srv.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroInfo;
import brandiq.brandiq.model.dto.TableroList;
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

    public TableroServiceImpl(TableroRepository tableroRepository, TableroEditRepository tableroEditRepository, JugadorSalaRepository jugadorSalaRepository) {
        this.tableroRepository = tableroRepository;
        this.tableroEditRepository = tableroEditRepository;
        this.jugadorSalaRepository = jugadorSalaRepository;
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
    // public Long obtenerUltimoIdParaJugador(String id_jugador) {
    //     return tableroEditRepository.findLastTableroIdByJugador(id_jugador);
    // }

}
