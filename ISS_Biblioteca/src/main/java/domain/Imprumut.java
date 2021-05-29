package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Imprumut extends Entity<Integer>{
    private User user;
    private List<Exemplar> exemplare = new ArrayList();
    private LocalDate date;

    public Imprumut(User user, List<Exemplar> exemplare, LocalDate date) {
        this.user = user;
        this.exemplare = exemplare;
        this.date = date;
    }

    public Imprumut(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Exemplar> getExemplare() {
        return exemplare;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setExemplare(List<Exemplar> exemplare) {
        this.exemplare = exemplare;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
//                "idUser=" + user.getId() +
                ", exemplare=" + exemplare +
                ", date=" + date +
                '}';
    }
}
