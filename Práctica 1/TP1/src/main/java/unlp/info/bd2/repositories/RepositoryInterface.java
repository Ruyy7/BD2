package unlp.info.bd2.repositories;

import unlp.info.bd2.utils.ToursException;

public interface RepositoryInterface <T>{
    void save (T entity) throws ToursException;
    void update (T entity);
    void delete (T entity);
}
