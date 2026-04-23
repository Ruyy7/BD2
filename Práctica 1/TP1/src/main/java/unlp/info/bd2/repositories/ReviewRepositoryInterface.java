package unlp.info.bd2.repositories;

import java.util.List;

import unlp.info.bd2.model.Review;

public interface ReviewRepositoryInterface extends RepositoryInterface<Review>{
    List<Review> getAllReviews();
}
