package dev.pierce.services;

import dev.pierce.repository.ReimbursementDAO;

public class ReimbursmentService {

    ReimbursementDAO rDAO = new ReimbursementDAO();

    //Submit
    //Should take user id, requested amount, and the date to make a reimbursement request
    //call rDAO.submitRequest()

    //Deny
    //Should allow user with Finance manager role to deny request
    //call rDAO.processRequest() with status param = Denied

    //Approve
    //Should allow user with Finance manager role to approve request
    //call rDAO.processRequest() with status param = Approved

}
