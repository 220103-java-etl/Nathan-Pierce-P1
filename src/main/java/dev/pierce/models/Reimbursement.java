package dev.pierce.models;

public class Reimbursement extends AbstractReimbursement{

    public Reimbursement() {
        super();
    }

    public Reimbursement(int id, Status status, User author, User resolver, double amount, String submitReason, String processReason) {
        super(id, status, author, resolver, amount, submitReason, processReason);
    }
}
