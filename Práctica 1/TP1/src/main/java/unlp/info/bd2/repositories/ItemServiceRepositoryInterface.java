package unlp.info.bd2.repositories;

import java.util.Optional;

import unlp.info.bd2.model.ItemService;

public interface ItemServiceRepositoryInterface extends RepositoryInterface<ItemService> {
    Optional<ItemService> getItemServiceById(Long id);
}
