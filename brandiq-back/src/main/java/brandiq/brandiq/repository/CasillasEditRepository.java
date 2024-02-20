package brandiq.brandiq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brandiq.brandiq.model.db.CasillasEditDb;

@Repository
public interface CasillasEditRepository extends JpaRepository<CasillasEditDb, Integer> {
    Optional<CasillasEditDb> findById(Integer id);
}
