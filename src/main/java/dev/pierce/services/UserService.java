package dev.pierce.services;

import dev.pierce.models.User;
import dev.pierce.repository.UserDAO;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean login(String username, String password){
        User user = userDAO.getByUsername(username);
        if (user != null){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return true;
            }
            else{
                System.out.println("Your username or password is incorrect.");
            }
        }
        else {
            System.out.println("The account does not exist.");
        }

        return false;
    }
}
