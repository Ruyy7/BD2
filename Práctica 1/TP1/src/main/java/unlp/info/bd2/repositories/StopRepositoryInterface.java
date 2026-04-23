package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import unlp.info.bd2.model.Stop;

public interface StopRepositoryInterface extends RepositoryInterface<Stop>{
    Optional<Stop> getStopById(Long id);
    List<Stop> getStopByNameStart (String name);
}
