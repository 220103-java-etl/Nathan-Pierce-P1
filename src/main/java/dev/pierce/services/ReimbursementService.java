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
    public Reimbursement submitRequest(Reimbursement reimbursement){
        return rDAO.submitRequest(reimbursement);
    }

    //process()
    //Should allow user with Finance manager role to approve or deny request
    //call rDAO.processRequest() with status param
    public Reimbursement processRequest(Reimbursement reimbursement ){
        return rDAO.processRequest(reimbursement);
    }

    public List<Reimbursement> getReimbursementsByStatus(Status status){
        List<Reimbursement> reList;
        reList = rDAO.getByStatus(status);
        return reList;
    }

    public List<Reimbursement> getAllReimbursements(){
        List<Reimbursement> allList;
        allList = rDAO.getAll();
        return allList;
    }

    public List<Reimbursement> getAllMyRequests(int id){
        List<Reimbursement> myList;
        myList = rDAO.getMyRequestsById(id);
        return myList;
    }
}
