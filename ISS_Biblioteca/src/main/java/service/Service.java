package service;

import domain.Exemplar;
import domain.Operator;
import domain.User;
import repository.ExemplarsRepository;
import repository.OperatorsRepository;
import repository.ReviewsRepository;
import repository.UsersRepository;

import java.util.List;

public class Service {
    private UsersRepository usersRepository;
    private OperatorsRepository operatorsRepository;
    private ReviewsRepository reviewsRepository;
    private ExemplarsRepository exemplarsRepository;

    public Service(UsersRepository usersRepository, OperatorsRepository operatorsRepository, ReviewsRepository reviewsRepository, ExemplarsRepository exemplarsRepository) {
        this.usersRepository = usersRepository;
        this.operatorsRepository = operatorsRepository;
        this.reviewsRepository = reviewsRepository;
        this.exemplarsRepository = exemplarsRepository;
    }

    public User findUserByCnpAndPassword(String cnp, String password){
        return usersRepository.findOneByCnpAndPassword(cnp, password);
    }

    public Operator findOperatorByCnpAndPassword(String cnp, String password){
        return operatorsRepository.findOneByCnpAndPassword(cnp, password);
    }

    public List<Exemplar> findAllExemplars(){
        return exemplarsRepository.findAll();
    }
}
