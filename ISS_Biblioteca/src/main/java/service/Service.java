package service;

import domain.*;
import repository.*;

import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private UsersRepository usersRepository;
    private OperatorsRepository operatorsRepository;
    private ReviewsRepository reviewsRepository;
    private ExemplarsRepository exemplarsRepository;
    private ImprumuturiRepository imprumuturiRepository;

    public Service(UsersRepository usersRepository, OperatorsRepository operatorsRepository, ReviewsRepository reviewsRepository, ExemplarsRepository exemplarsRepository, ImprumuturiRepository imprumuturiRepository) {
        this.usersRepository = usersRepository;
        this.operatorsRepository = operatorsRepository;
        this.reviewsRepository = reviewsRepository;
        this.exemplarsRepository = exemplarsRepository;
        this.imprumuturiRepository = imprumuturiRepository;
    }

    public User findUserByCnpAndPassword(String cnp, String password){
        return usersRepository.findOneByCnpAndPassword(cnp, password);
    }

    public Operator findOperatorByCnpAndPassword(String cnp, String password){
        return operatorsRepository.findOneByCnpAndPassword(cnp, password);
    }

    public List<Exemplar> findAllExemplars(){
        return exemplarsRepository.findAllAvailable();
    }

    public void saveImprumut(Imprumut imprumut){
        imprumuturiRepository.save(imprumut);
    }

    public User findOneUser(Integer id){
        return usersRepository.findOne(id);
    }

    public List<Imprumut> findImprumuturiUser(Integer id){
        return imprumuturiRepository.findAll();
    }

    public void updateExemplar(Exemplar exemplar, Integer idImprumut){
        exemplarsRepository.updateImprumutId(exemplar, idImprumut);
    }

    public Integer findLastImprumutId(){
        List<Imprumut> all = imprumuturiRepository.findAll();
        Integer maxId = -1;
        for(Imprumut imprumut: all){
            if(imprumut.getId()>maxId)
                maxId = imprumut.getId();
        }
        return maxId;
    }

    public List<Review> findAllReviewsForExemplar(Integer idExemplar){
        return reviewsRepository.findAll().stream().filter(review -> review.getExemplarId().getId().equals(idExemplar)).collect(Collectors.toList());
    }

    public Review findReviewByUser(Integer idExemplar, Integer idUser){
//        Review review = new Review();
//        review.setText("3 stele");
//        return review;

        return  reviewsRepository.findAll()
                .stream().filter(review -> review.getExemplarId().getId().equals(idExemplar))
                .filter(review -> review.getUserId().getId().equals(idUser))
                .collect(Collectors.toList()).get(0);

    }
    public Exemplar findOneExemplar(Integer id){
        return exemplarsRepository.findOne(id);
    }

    public void addReview(Review review){
        reviewsRepository.save(review);
    }
}
