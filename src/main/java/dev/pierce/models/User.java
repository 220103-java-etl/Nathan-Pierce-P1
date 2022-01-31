package dev.pierce.models;

public class User extends AbstractUser{

    public User(){
        super();
    }

    public User(int id, String username, String password, Role role){
        super(id, username, password, role);
    }

    public User(int id, String fullName, String username, String password, Role role) {
        super(id, fullName, username, password, role);
    }


}