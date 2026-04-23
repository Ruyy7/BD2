package unlp.info.bd2.repositories;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.ItemService;
import unlp.info.bd2.utils.ToursException;

public class ItemServiceRepository implements ItemServiceRepositoryInterface{
    @Autowired
    private SessionFactory sessionFactory;

    public void save (ItemService itemService) throws ToursException{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(itemService);
        }
        catch (Exception e){
            if (e.getClass().equals(org.hibernate.exception.ConstraintViolationException.class)){
                throw new ToursException("Constraint Violation");}
            else {
                System.out.println(e.toString());
                throw new ToursException("Object can't be save");
            }
        }
    }

    public void update (ItemService itemService){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.merge(itemService);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (ItemService itemService){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.remove(itemService);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Optional<ItemService> getItemServiceById(Long id){
        return this.sessionFactory.getCurrentSession().createQuery("from ItemService where id = :id").setParameter("id", id).uniqueResultOptional();
    }
}
