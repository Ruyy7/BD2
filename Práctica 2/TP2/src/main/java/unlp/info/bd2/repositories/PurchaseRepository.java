package unlp.info.bd2.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import unlp.info.bd2.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase,Long>{
    List<Purchase> findByUserUsername(String username);
    int countByDateBetween(Date from, Date to);
    Optional<Purchase> findByCode(String code);
}