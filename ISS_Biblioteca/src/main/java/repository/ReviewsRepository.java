package repository;

import domain.Exemplar;
import domain.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReviewsRepository implements IRepository<Integer, Review> {

    SessionFactory sessionFactory;

    public ReviewsRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Review findOne(Integer integer) {
        return null;
    }

//    public Review findOneByUser(Integer id, Integer userId){
//        try(Session session = sessionFactory.openSession()){
//            Transaction transaction = null;
//            try{
//                transaction = session.beginTransaction();
//                Query q = session.createQuery("from Review where userid=:n and exemplarid=:i");
//                q.setParameter("n",userId);
//                q.setParameter("i",id);
//
//                transaction.commit();
//            } catch (Exception e) {
//                e.printStackTrace();
//                if(transaction !=null)
//                    transaction.rollback();
//            }
//        }
//    }

    @Override
    public List<Review> findAll() {
        List<Review> exemplars = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                exemplars = session.createQuery("from Review", Review.class)
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
    public void save(Review entity) {
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
    public void update(Review entity) {

    }
}
