package brandiq.brandiq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brandiq.brandiq.model.db.JugadorSalaDb;

public interface JugadorSalaRepository extends JpaRepository<JugadorSalaDb, Integer>{

        Optional<JugadorSalaDb> findById(Integer id);
}
