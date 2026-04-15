package unlp.info.bd2.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import unlp.info.bd2.model.Purchase;
import unlp.info.bd2.utils.ToursException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PurchaseRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save (Purchase purchase) throws ToursException{
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(purchase);
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

    public void update (Purchase purchase){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.merge(purchase);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete (Purchase purchase){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.remove(purchase);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Optional<Purchase> getPurchaseByCode(String code){
        return this.sessionFactory.getCurrentSession().createQuery("from Purchase where code = :code").setParameter("code", code).uniqueResultOptional();
    }

    public List<Purchase> getAllPurchases(){
        return this.sessionFactory.getCurrentSession().createQuery("from Purchase").list();
    }

    public List<Purchase> getAllPurchasesOfUsername(String username){
        return this.sessionFactory.getCurrentSession().createQuery("select purchaseList from User where username = :username").setParameter("username", username).getResultList();
    }

    public int getCountOfPurchasesBetweenDates (Date start, Date end){
        return (int)this.sessionFactory.getCurrentSession().createQuery("select count(p) from Purchase p where p.date >= :start and p.date <= :end").setParameter("start", start).setParameter("end", end).uniqueResult();
    }

}