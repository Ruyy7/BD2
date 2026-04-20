package unlp.info.bd2.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.Stop;
import unlp.info.bd2.utils.ToursException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

    public class StopRepository {
    
    @Autowired
    private SessionFactory sessionFactory;

    public void save (Stop stop) throws ToursException{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.save(stop);
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

    public void update (Stop stop){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(stop);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (Stop stop){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(stop);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Optional<Stop> getStopById(Long id){
        return this.sessionFactory.getCurrentSession().createQuery("from Stop where id = :id").setParameter("id", id).uniqueResultOptional();
    }

}