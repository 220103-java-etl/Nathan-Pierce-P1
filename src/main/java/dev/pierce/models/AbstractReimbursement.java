package dev.pierce.models;

import java.util.Objects;

public class AbstractReimbursement {

    private int id;
    private Status status;
    private User author;
    private User resolver;
    private double amount;
    private String submitReason;
    private String processReason;

    public AbstractReimbursement() {
        super();
    }

    public AbstractReimbursement(int id, Status status, User author, User resolver, double amount, String submitReason, String processReason ) {
        super();
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
        this.submitReason = submitReason;
        this.processReason = processReason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSubmitReason() {
        return submitReason;
    }

    public void setSubmitReason(String submitReason) {
        this.submitReason = submitReason;
    }

    public String getProcessReason() {
        return processReason;
    }

    public void setProcessReason(String processReason) {
        this.processReason = processReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractReimbursement that = (AbstractReimbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && status == that.status && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver) && submitReason == that.submitReason && processReason == that.processReason;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, author, resolver, amount, submitReason, processReason);
    }

    @Override
    public String toString() {
        return "AbstractReimbursement{" +
                "id=" + id +
                ", status=" + status +
                ", author=" + author +
                ", resolver=" + resolver +
                ", amount=" + amount +
                ", submission reason=" + submitReason +
                ", status reason=" + processReason +
                '}';
    }
}
