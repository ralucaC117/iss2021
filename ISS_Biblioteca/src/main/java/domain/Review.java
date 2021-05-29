package domain;

public class Review extends Entity<Integer>{
    private String text;
    private User userId;
    private Exemplar exemplarId;

    public Review(String text, User userId, Exemplar exemplarId) {
        this.text = text;
        this.userId = userId;
        this.exemplarId = exemplarId;
    }

    public Review(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Exemplar getExemplarId() {
        return exemplarId;
    }

    public void setExemplarId(Exemplar exemplarId) {
        this.exemplarId = exemplarId;
    }
}
