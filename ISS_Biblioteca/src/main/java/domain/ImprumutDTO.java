package domain;

import java.time.LocalDate;

public class ImprumutDTO {
    private Integer id;
    private LocalDate date;
    private LocalDate dataRestituire;

    public ImprumutDTO(Imprumut imprumut){
        this.id = imprumut.getId();
        this.date = imprumut.getDate();
        this.dataRestituire = imprumut.getDate().plusDays(14);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDataRestituire() {
        return dataRestituire;
    }

    public void setDataRestituire(LocalDate dataRestituire) {
        this.dataRestituire = dataRestituire;
    }
}
