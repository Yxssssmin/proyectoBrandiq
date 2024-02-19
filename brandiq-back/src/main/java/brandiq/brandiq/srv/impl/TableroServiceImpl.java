package brandiq.brandiq.srv.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Table;
import org.springframework.stereotype.Service;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.CasillasEdit;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTableroEditById'");
    }

    @Override
    public Optional<TableroInfo> getTableroInfoById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTableroInfoById'");
    }

}
