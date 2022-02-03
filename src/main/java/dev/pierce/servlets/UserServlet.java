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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User u = userService.login(username, password);


       if (u != null){

            //redirect based on role
            if(u.getRole().equals(Role.FINANCE_MANAGER)){
                response.sendRedirect("manager.html");
            }
            else{
                response.sendRedirect("employee.html");
            }
        }
        else{
            response.setStatus(401);
        }

    }

}
