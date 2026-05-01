package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import unlp.info.bd2.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier,Long>{
    @Query ("SELECT sev.supplier FROM Purchase p JOIN p.itemServiceList i JOIN i.service sev GROUP BY sev.supplier ORDER BY COUNT(p) DESC")
    List<Supplier> findMostUsedSuppliers(Pageable pageable);

    Optional<Supplier> findByAuthorizationNumber(String authorizationNumber);
}
