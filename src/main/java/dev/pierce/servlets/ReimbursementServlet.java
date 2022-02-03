package dev.pierce.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pierce.models.Status;
import dev.pierce.models.Reimbursement;
import dev.pierce.services.ReimbursementService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    ReimbursementService rServ = new ReimbursementService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //submit requests

        Reimbursement r = objectMapper.readValue(request.getReader(), Reimbursement.class);
        rServ.submitRequest(r.getId(), r.getAmount(), r.getSubmitReason());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting requests
        Reimbursement r = objectMapper.readValue(request.getReader(), Reimbursement.class);

        List<Reimbursement> reimbursementList = rServ.getAllReimbursements();

        response.getWriter().write(objectMapper.writeValueAsString(reimbursementList));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //for processing requests

    }

}
