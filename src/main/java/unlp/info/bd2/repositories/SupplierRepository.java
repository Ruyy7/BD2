package unlp.info.bd2.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.Supplier;

public class SupplierRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (Supplier supplier) throws Exception{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.save(supplier);
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

    public void update (Supplier supplier){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(supplier);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (Supplier supplier){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(supplier);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Supplier> getAllSuppliers(){
        return this.sessionFactory.getCurrentSession().createQuery("from Supplier").list();
    }
    
    public List<Supplier> getTopNSuppliersInPurchases(int n){
        return this.sessionFactory.getCurrentSession().createQuery("select sup from Purchase p join p.itemServiceList i join i.service s join s.supplier sup group by sup order by count(i) desc").setMaxResults(n).getResultList();
    }

}
