package dev.pierce.servlets;

import dev.pierce.models.Role;
import dev.pierce.models.User;
import dev.pierce.repository.UserDAO;
import dev.pierce.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService();

        User u = objectMapper.readValue(request.getReader(), User.class);
        u = userService.getByLogin(u);
        String username = u.getUsername();
        String password = u.getPassword();

        if(userService.login(username, password)){
            //allow login
            response.setStatus(200);
            //redirect based on role
            if(userDAO.getByUsername(username).getRole().equals(Role.FINANCE_MANAGER)){
                response.sendRedirect("manager.html");
            }
            else{
                response.sendRedirect("employee.html");
            }
        }
        else{
            response.setStatus(418);
        }

        response.getWriter().write(objectMapper.writeValueAsString(u));

    }

}
