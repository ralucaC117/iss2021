package domain;

public class Review extends Entity<Integer>{
    private String text;
    private Integer userId;
    private Integer exemplarId;

    public Review(String text, Integer userId, Integer exemplarId) {
        this.text = text;
        this.userId = userId;
        this.exemplarId = exemplarId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExemplarId() {
        return exemplarId;
    }

    public void setExemplarId(Integer exemplarId) {
        this.exemplarId = exemplarId;
    }
}
