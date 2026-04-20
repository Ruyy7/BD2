package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.Review;
import unlp.info.bd2.utils.ToursException;

public class ReviewRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (Review review) throws ToursException{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(review);
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

    public void update (Review review){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.merge(review);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (Review review){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.remove(review);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Review> getAllReviews(){
        return this.sessionFactory.getCurrentSession().createQuery("from Review").list();
    }            
}
