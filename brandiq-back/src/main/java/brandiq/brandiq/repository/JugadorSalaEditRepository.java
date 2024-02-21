package brandiq.brandiq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.db.TableroEditDb;

@Repository
public interface JugadorSalaEditRepository extends JpaRepository<JugadorSalaEditDb,Integer>{
        Optional<JugadorSalaEditDb> findById(Integer id);
}
