package dev.pierce.models;

import java.util.Objects;

public class AbstractUser {

    private int id;
    private String username;
    private String password;
    private Role role;

    public AbstractUser() {
        super();
    }

    public AbstractUser(int id, String username, String password, Role role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUser that = (AbstractUser) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password) && role == that.role;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString(){
        return "AbstractUser{" + "id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "}";
    }
}
