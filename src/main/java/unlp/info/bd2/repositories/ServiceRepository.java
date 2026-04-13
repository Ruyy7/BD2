package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.Service;

public class ServiceRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (Service service) throws Exception{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.save(service);
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

    public void update (Service service){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(service);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (Service service){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(service);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Service> getAllServices(){
        return this.sessionFactory.getCurrentSession().createQuery("from Service").list();
    }
    
    public Service getMostDemandedService(){
        return (Service)this.sessionFactory.getCurrentSession().createQuery("select s from Purchase p join p.itemServiceList i join i.service s group by s order by sum(i.quantity) desc").setMaxResults(1).uniqueResult();
    }
}
