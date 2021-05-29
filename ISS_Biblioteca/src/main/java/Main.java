import org.hibernate.SessionFactory;
import repository.ExemplarsRepository;
import repository.ImprumuturiRepository;
import repository.JdbcUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
          MainFX.main(args);
//        SessionFactory sessionFactory = JdbcUtils.getSessionFactory();
//        ExemplarsRepository exemplarsRepository = new ExemplarsRepository(sessionFactory);
//        ImprumuturiRepository imprumuturiRepository = new ImprumuturiRepository(sessionFactory);
//
//        System.out.println(exemplarsRepository.findOne(1));
//        System.out.println(imprumuturiRepository.findOne(1));

    }
}
