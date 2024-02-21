package brandiq.brandiq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import brandiq.brandiq.model.db.TableroDb;

@Repository
public interface TableroRepository extends JpaRepository<TableroDb, Integer> {
    /* Optional<TableroDb> findById(String nickname); */
    /* List<TableroDb> findTablerosAllOrderByidAsc(int id); */
    List<TableroDb> findAll();

    
}
