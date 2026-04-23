package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import unlp.info.bd2.model.Supplier;

public interface SupplierRepositoryInterface extends RepositoryInterface<Supplier>{
    Optional<Supplier> getSupplierById(Long id);
    Optional<Supplier> getSupplierByAuthorizationNumber(String authorizationNumber);
    List<Supplier> getAllSuppliers();
    List<Supplier> getTopNSuppliersInPurchases(int n);
}
