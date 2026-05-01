package unlp.info.bd2.repositories;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import unlp.info.bd2.model.Service;

public interface ServiceRepository extends CrudRepository<Service,Long>{
    @Query("SELECT i.service FROM ItemService i GROUP BY i.service ORDER BY SUM(i.quantity) DESC")
    Service findMostDemandedService(Pageable pageable);

    Optional<Service> findByNameAndSupplierId(String name, Long id);
}
