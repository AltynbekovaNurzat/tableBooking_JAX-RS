package booking.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private int id;
    private String login;
    private String password;
    private String fullname;

    public User() {}

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public User(int id, String login, String password, String fullname) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
        this.setFullname(fullname);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
