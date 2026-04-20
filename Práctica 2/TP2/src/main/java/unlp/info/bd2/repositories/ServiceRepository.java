package unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.Service;
import unlp.info.bd2.utils.ToursException;

public class ServiceRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (Service service) throws ToursException{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(service);
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

    public void update (Service service){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.merge(service);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (Service service){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.remove(service);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Optional<Service> getServiceById(Long id){
        return this.sessionFactory.getCurrentSession().createQuery("from Service where id = :id").setParameter("id", id).uniqueResultOptional();
    }    

    public Optional<Service> getServiceByNameAndSupplierId(String name, Long id){
        return this.sessionFactory.getCurrentSession().createQuery("from Service s where s.name = :name and s.supplier.id = :id").setParameter("name", name).setParameter("id", id).uniqueResultOptional();
    }

    public List<Service> getAllServices(){
        return this.sessionFactory.getCurrentSession().createQuery("from Service").list();
    }
    
    public Service getMostDemandedService(){
        return (Service)this.sessionFactory.getCurrentSession().createQuery("select s from Purchase p join p.itemServiceList i join i.service s group by s order by sum(i.quantity) desc").setMaxResults(1).uniqueResult();
    }
}
