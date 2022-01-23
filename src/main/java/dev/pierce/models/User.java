package dev.pierce.models;

public class User extends AbstractUser{

    public User(){
        super();
    }

    public User(int id, String firstName, String lastName, String username, String password, Role role) {
        super(id, firstName, lastName, username, password, role);
    }
}