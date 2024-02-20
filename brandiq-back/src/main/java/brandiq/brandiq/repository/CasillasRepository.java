package brandiq.brandiq.repository;

import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEditDb;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasillasRepository extends JpaRepository<CasillasDb, Integer> {
    Optional<CasillasDb> findById(Integer id);

    void save(CasillasEditDb CasillasEditDb);
}
