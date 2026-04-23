package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;

public interface UserRepositoryInterface extends RepositoryInterface<User>{
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    List<User> getUserSpendingMoreThan(float mount);
    List<TourGuideUser> getTourGuidesWithRating1();
}
