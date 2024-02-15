package brandiq.brandiq.srv.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroList;
import brandiq.brandiq.repository.TableroEditRepository;
import brandiq.brandiq.repository.TableroRepository;
import brandiq.brandiq.srv.TableroService;
import brandiq.brandiq.srv.mapper.TableroMapper;

@Service
public class TableroServiceImpl implements TableroService {
    private final TableroRepository tableroRepository;
    private final TableroEditRepository tableroEditRepository;

    public TableroServiceImpl(TableroRepository tableroRepository, TableroEditRepository tableroEditRepository) {
        this.tableroRepository = tableroRepository;
        this.tableroEditRepository = tableroEditRepository;

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

}
