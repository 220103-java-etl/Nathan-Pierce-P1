package dev.pierce.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pierce.models.Reimbursement;
import dev.pierce.models.User;
import dev.pierce.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ReimbursementTableServlet extends HttpServlet {


    ObjectMapper objectMapper = new ObjectMapper();
    ReimbursementService rServ = new ReimbursementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting users requests
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        System.out.println(user);
        List<Reimbursement> reqList = rServ.getAllMyRequests(user.getId());
        String json = objectMapper.writeValueAsString(reqList);
        System.out.println(json);

        if (reqList != null){
            System.out.println("passed if statement");
            response.getWriter().write(json);
        }
        else{
            response.setStatus(401);
        }
    }
}
