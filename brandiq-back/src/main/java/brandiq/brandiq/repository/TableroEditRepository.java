package brandiq.brandiq.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import brandiq.brandiq.model.db.TableroEditDb;

@Repository
public interface TableroEditRepository extends JpaRepository<TableroEditDb,Integer> {
    Optional<TableroEditDb> findById(int id);
}
