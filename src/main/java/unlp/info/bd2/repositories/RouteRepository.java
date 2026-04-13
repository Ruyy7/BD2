package unlp.info.bd2.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.Route;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RouteRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (Route route) throws Exception{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.save(route);
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

    public void update (Route route){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(route);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (Route route){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(route);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List getAllRoutes(){
        return this.sessionFactory.getCurrentSession().createQuery("from Route").list();
    }
}
