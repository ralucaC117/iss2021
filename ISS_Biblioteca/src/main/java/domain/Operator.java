package domain;

public class Operator extends Entity<Integer> {
    private String cnp;
    private String password;

    public Operator(String cnp, String password) {
        this.cnp = cnp;
        this.password = password;
    }

    public Operator(){

    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
