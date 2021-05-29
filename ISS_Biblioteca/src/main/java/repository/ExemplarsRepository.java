package repository;

import domain.Exemplar;
import domain.Imprumut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ExemplarsRepository implements IRepository<Integer, Exemplar>{
    SessionFactory sessionFactory;

    public ExemplarsRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Exemplar findOne(Integer id) {
        Exemplar exemplar = null;

        try(Session session = sessionFactory.openSession())
        {

            Transaction tx=null;
            try
            {
                tx = session.beginTransaction();
                exemplar = session.createQuery("FROM Exemplar WHERE id = "+id, Exemplar.class).setMaxResults(1).uniqueResult();
                tx.commit();
            }
            catch(RuntimeException ex)
            {
                if (tx!=null)
                    tx.rollback();
            }
        }
        return exemplar;
    }


    public List<Exemplar> findAllAvailable() {
        List<Exemplar> exemplars = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                exemplars = session.createQuery("FROM Exemplar WHERE imprumutid = "+null, Exemplar.class)
                        .list();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return exemplars;
    }
    @Override
    public List<Exemplar> findAll(){
        List<Exemplar> exemplars = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                exemplars = session.createQuery("from Exemplar", Exemplar.class)
                        .list();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return exemplars;
    }

    @Override
    public void save(Exemplar entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Exemplar entity) {


    }

    public void updateImprumutId(Exemplar entity, Integer idImprumut){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Query q = session.createQuery("update Exemplar set imprumutid=:n where id=:i");
                q.setParameter("n",idImprumut);
                q.setParameter("i",entity.getId());
                q.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
    }
}
