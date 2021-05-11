package domain;

public class User extends Entity<Integer>{
    private String name;
    private String cnp;
    private String password;
    private String telephoneNumber;
    private String email;

    public User(String name, String cnp, String password, String telephoneNumber, String email) {
        this.name = name;
        this.cnp = cnp;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }

    public User(){

    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
