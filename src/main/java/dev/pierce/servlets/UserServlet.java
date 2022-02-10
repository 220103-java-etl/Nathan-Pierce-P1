package dev.pierce.servlets;

import dev.pierce.models.Role;
import dev.pierce.models.User;
import dev.pierce.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class UserServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        User u = userService.login(username, password);
        System.out.println(u);

        if (u != null){
           HttpSession session = request.getSession();
           session.setAttribute("user", u);

           if (u.getRole() == Role.FINANCE_MANAGER) {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if(session != null){
            User user = (User) session.getAttribute("currentUser");
            response.getWriter().write(objectMapper.writeValueAsString(user));
        }

    }
}
