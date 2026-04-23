package unlp.info.bd2.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import unlp.info.bd2.model.Purchase;

public interface PurchaseRepositoryInterface extends RepositoryInterface<Purchase>{
    Optional<Purchase> getPurchaseByCode(String code);
    List<Purchase> getAllPurchases();
    List<Purchase> getAllPurchasesOfUsername(String username);
    int getCountOfPurchasesBetweenDates (Date start, Date end);
}
