package dev.pierce.services;

import dev.pierce.models.User;
import dev.pierce.repository.UserDAO;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password){
        User user = userDAO.getByUsername(username);
        if (user != null && password.equals(user.getPassword())){
                return user;
        }
        else {
            System.out.println("The account does not exist.");
        }
        return null;
    }

}
