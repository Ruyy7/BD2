package unlp.info.bd2.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;
import unlp.info.bd2.utils.ToursException;

public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (User user) throws ToursException{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.save(user);
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

    public void update (User user){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(user);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (User user){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(user);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Optional<User> getUserById(Long id){
        return this.sessionFactory.getCurrentSession().createQuery("from User where user_id = :id").setParameter("id", id).uniqueResultOptional();
    }

    public Optional<User> getUserByUsername(String username){
        return this.sessionFactory.getCurrentSession().createQuery("from User where username = :username").setParameter("username", username).uniqueResultOptional();
    }

    public List<User> getAllUsers(){
        return this.sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public List<User> getUserSpendingMoreThan(float mount){
        return this.sessionFactory.getCurrentSession().createQuery("select distinct p.user from Purchase p where p.totalPrice >= :mount").setParameter("mount", mount).getResultList();
    }

    public List<TourGuideUser> getTourGuidesWithRating1(){
        return this.sessionFactory.getCurrentSession().createQuery("select distinct tg from Purchase p join p.review rev join p.route r join r.tourGuideList tg where rev.rating = 1").getResultList();
    }
}
