package domain;

public class Exemplar extends Entity<Integer>{
    private String title;
    private String author;

    public Exemplar(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Exemplar(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Exemplar{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
