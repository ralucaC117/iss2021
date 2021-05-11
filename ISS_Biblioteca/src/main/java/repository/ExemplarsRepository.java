package repository;

import domain.Exemplar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ExemplarsRepository implements IRepository<Integer, Exemplar>{
    SessionFactory sessionFactory;

    public ExemplarsRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Exemplar findOne(Integer integer) {
        return null;
    }

    @Override
    public List<Exemplar> findAll() {
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
}
