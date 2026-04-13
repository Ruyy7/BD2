package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.User;

public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (User user) throws Exception{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.save(user);
        }
        catch (Exception e){
            if (e.getClass().equals(org.hibernate.exception.ConstraintViolationException.class)){
                throw new Exception("Constraint Violation");}
            else {
                System.out.println(e.toString());
                throw new Exception("Object can't be save");
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

    public List getAllUsers(){
        return this.sessionFactory.getCurrentSession().createQuery("from User").list();
    }
}
