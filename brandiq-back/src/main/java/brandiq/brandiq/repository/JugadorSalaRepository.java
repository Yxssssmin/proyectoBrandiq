package brandiq.brandiq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.dto.JugadorSalaEdit;

public interface JugadorSalaRepository extends JpaRepository<JugadorSalaDb, Integer>{

        Optional<JugadorSalaDb> findById(Integer id);

        void save(JugadorSalaEditDb jugadorSalaEditDb);
}
