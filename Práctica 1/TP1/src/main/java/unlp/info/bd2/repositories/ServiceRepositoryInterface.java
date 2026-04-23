package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import unlp.info.bd2.model.Service;

public interface ServiceRepositoryInterface extends RepositoryInterface<Service>{
    Optional<Service> getServiceById(Long id);
    Optional<Service> getServiceByNameAndSupplierId(String name, Long id);
    List<Service> getAllServices();
    Service getMostDemandedService();
}
