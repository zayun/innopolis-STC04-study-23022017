package com.smoldyrev.models.pojo;

/**
 * Created by smoldyrev on 23.02.17.
 */
public class User {

    private int id;
    private String login;
    private String password;
    private String role;
    private String email;

    public User(int id, String login, String password, String role, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
