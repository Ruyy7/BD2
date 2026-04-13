package unlp.info.bd2.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;
import unlp.info.bd2.utils.ToursException;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RouteRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (Route route) throws ToursException{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.save(route);
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

    public Optional<Route> getRouteById(Long id){
        return this.sessionFactory.getCurrentSession().createQuery("from Route where id = :id").setParameter("id", id).uniqueResultOptional();
    }

    public List<Route> getRoutesBelowPrice(float price){
        return this.sessionFactory.getCurrentSession().createQuery("from Route where price <= :price").setParameter("price", price).list();
    }

    public List<Route> getAllRoutes(){
        return this.sessionFactory.getCurrentSession().createQuery("from Route").list();
    }

    public List<Route> getRoutesWithStop(Stop stop){
        return this.sessionFactory.getCurrentSession().createQuery("select distinct r from Route r join r.stops s where s = :stop").setParameter("stop", stop).getResultList();
    }

    public int getMaxStopOfRoutes(){
        return (int)this.sessionFactory.getCurrentSession().createQuery("select max(size(r.stops)) from Route r").uniqueResult();
    }

    public List<Route> getRoutesNotSell(){
        return this.sessionFactory.getCurrentSession().createQuery("select r from Route r where not in (select p.route from Purchase p)").getResultList();
    }

    public List<Route> getTop3RoutesWithMaxRating(){
        return this.sessionFactory.getCurrentSession().createQuery("select p.route from Purchase p join p.review r group by p.route order by avg(r.rating) desc").setMaxResults(3).getResultList();
    }
}
