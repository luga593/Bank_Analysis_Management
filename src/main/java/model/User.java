package model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {
    private int userID;
    private String username;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int userID, String username, String password, String email) {
        super();
        this.userID = userID;
        this.password = password;
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        email = "";
        userID = 0;
    }

    public User() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
