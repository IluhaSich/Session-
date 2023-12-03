package com.example.session;

public class Users {

    int user_id ;
    String login, password, role;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setLogin(String username) {
        this.login = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Users(int user_id, String login, String password, String role) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}