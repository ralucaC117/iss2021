package repository;

import domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UsersRepository implements IRepository<Integer, User> {
    SessionFactory sessionFactory;

    public UsersRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public User findOneByCnpAndPassword(String cnp, String password){
        User user = new User();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                user = (User) session.createQuery(" from User where cnp = ?1 and password= ?2 ", User.class)
                        .setParameter(1, cnp).setParameter(2, password).setMaxResults(1).uniqueResult();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return user;
    }
    @Override
    public User findOne(Integer integer) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(User entity) {

    }
}
