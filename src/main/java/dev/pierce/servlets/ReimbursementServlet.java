package dev.pierce.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pierce.models.Status;
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

public class ReimbursementServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    ReimbursementService rServ = new ReimbursementService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //submit requests

        double amount = Double.parseDouble(request.getParameter("amount"));
        String reason = request.getParameter("reason");
        System.out.println(amount);

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        Reimbursement r = new Reimbursement();
        r.setAuthor(user);
        r.setAmount(amount);
        r.setSubmitReason(reason);

        r = rServ.submitRequest(r);

        if( r != null){
            response.setStatus(200);
            response.sendRedirect("employee.html");
        }
        else {
            response.setStatus(401);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting all requests

        List<Reimbursement> reimbursementList = rServ.getAllReimbursements();
        String json = objectMapper.writeValueAsString(reimbursementList);

        response.getWriter().write(json);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //for processing requests

        int reqId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        String reason = request.getParameter("reason");

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");

        Reimbursement r = new Reimbursement();
        r.setId(reqId);
        r.setStatus(Status.valueOf(status));
        r.setResolver(user);
        r.setProcessReason(reason);

        r = rServ.processRequest(r);

        if( r != null){
            response.setStatus(200);
        }
        else {
            response.setStatus(401);
        }
    }

}
