package dev.pierce.services;

import dev.pierce.models.Reimbursement;
import dev.pierce.models.Status;
import dev.pierce.repository.ReimbursementDAO;

import java.util.List;

public class ReimbursementService {

    ReimbursementDAO rDAO = new ReimbursementDAO();

    //Submit
    //Should take user id, requested amount, and the date to make a reimbursement request
    //call rDAO.submitRequest()
    public void submitRequest(int id, double amount, String reason){
        rDAO.submitRequest(id, amount, reason);
    }

    //process()
    //Should allow user with Finance manager role to approve or deny request
    //call rDAO.processRequest() with status param
    public void processRequest(int requestId, int id, String reason, String status ){
        rDAO.processRequest(requestId, id, reason, status);
    }

    public List<Reimbursement> getReimbursementsByStatus(Status status){
        List<Reimbursement> reList;
        reList = rDAO.getByStatus(status);
        return reList;
    }

}
