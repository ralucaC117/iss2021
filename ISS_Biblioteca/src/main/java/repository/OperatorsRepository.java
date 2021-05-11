package repository;

import domain.Operator;
import domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OperatorsRepository implements IRepository<Integer, Operator>{
    SessionFactory sessionFactory;

    public OperatorsRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Operator findOneByCnpAndPassword(String cnp, String password){
        Operator operator = new Operator();

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                operator = (Operator) session.createQuery("from Operator where cnp = ?1 and password= ?2", Operator.class)
                        .setParameter(1, cnp).setParameter(2, password).setMaxResults(1).uniqueResult();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return operator;
    }
    @Override
    public Operator findOne(Integer integer) {
        return null;
    }

    @Override
    public List<Operator> findAll() {
        return null;
    }

    @Override
    public void save(Operator entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Operator entity) {

    }
}
