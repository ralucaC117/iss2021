package repository;

import domain.Exemplar;
import domain.Imprumut;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ImprumuturiRepository implements IRepository<Integer, Imprumut>{
    SessionFactory sessionFactory;

    public ImprumuturiRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Imprumut findOne(Integer id) {

        Imprumut imprumut = null;

        try(Session session = sessionFactory.openSession())
        {

            Transaction tx=null;
            try
            {
                tx = session.beginTransaction();
                imprumut = session.createQuery("FROM Imprumut WHERE id = "+id, Imprumut.class).setMaxResults(1).uniqueResult();
                tx.commit();
            }
            catch(RuntimeException ex)
            {
                if (tx!=null)
                    tx.rollback();
            }
        }
        return imprumut;
    }

    @Override
    public List<Imprumut> findAll() {
        List<Imprumut> imprumuturi = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                imprumuturi = session.createQuery("from Imprumut", Imprumut.class)
                        .list();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return imprumuturi;
    }

    @Override
    public void save(Imprumut entity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
            }catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Imprumut entity) {

    }
}
