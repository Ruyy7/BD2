package unlp.info.bd2.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;

public interface RouteRepository extends CrudRepository<Route,Long>{
    List<Route> findByStopsContaining(Stop stop);
    List<Route> findByPriceLessThan(float price);

    @Query("SELECT MAX(SIZE(r.stops)) FROM Route r")
    Long findMaxStopsOfRoute();

    @Query("SELECT r FROM Route r WHERE NOT IN (SELECT DISTINCT p.route FROM Purchase p)")
    List<Route> findRoutesNotSell();

    @Query("SELECT p.route FROM Purchase p WHERE p.review IS NOT NULL GROUP BY p.route ORDER BY AVG(p.review.rating) DESC")
    List<Route> findTop3RoutesWithMaxRating(Pageable pageable);

}
