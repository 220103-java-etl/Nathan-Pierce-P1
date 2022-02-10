package dev.pierce.repository;

import dev.pierce.models.Reimbursement;
import dev.pierce.models.Role;
import dev.pierce.models.Status;
import dev.pierce.models.User;
import dev.pierce.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements GenericDAO<Reimbursement>{

    ConnectionUtil connection = ConnectionUtil.getConnectionUtil();
    UserDAO userDAO = new UserDAO();

    @Override
    public Reimbursement getByUsername(String username) {
        return null;
    }

    @Override
    public Reimbursement getById(Integer id) {
        return null;
    }

    public Reimbursement submitRequest(Reimbursement r){
        if(amountUnderAllowed(r.getAuthor().getId(), r.getAmount()) == true) {

            String sql = "insert into Reimbursement_Requests (user_id, amount, reason) values( ?, ?, ? );";

            try (Connection conn = connection.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, r.getAuthor().getId());
                pstmt.setDouble(2, r.getAmount());
                pstmt.setString(3, r.getSubmitReason());
                pstmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return r;
        }
        return null;
    }

    public boolean amountUnderAllowed(int id, double amount){
        String sql = "select sum(amount) from Reimbursement_Requests where user_id = ? group by user_id;";
        double sum = 0;
        try(Connection conn = connection.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                sum = rs.getDouble("sum");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        if ( sum+amount < 1000 ){
            return true;
        }
        return false;
    }

    public void processRequest(int requestId, int id, String processReason, String status){

        //insert into Processed_Requests
        String insertSql="insert into Processed_Requests (request_id, processing_manager, processing_reason) values ( ?, ?, ? )";
        try(Connection conn = connection.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, requestId);
            pstmt.setInt(2, id);
            pstmt.setString(3, processReason);
            pstmt.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        //update Reimbursment_Requests status
        String updateSql="update Reimbursment_Requests set status = '?' where request_id = ?";
        try(Connection conn = connection.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            pstmt.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Reimbursement> getByStatus(Status status){

        String sql = "select * from Reimbursement_Requests where status = ?";
        String stringStatus = String.valueOf(status);
        List<Reimbursement> statusList = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, stringStatus);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement(rs.getInt("request_id"),
                        Status.valueOf(rs.getString("status")),
                        userDAO.getById(rs.getInt("user_id")),
                        userDAO.getByUsername(rs.getString("manager_username")),
                        rs.getDouble("amount"),
                        rs.getString("reason"),
                        rs.getString("processing_reason"));
                statusList.add(reimbursement);
            }
            return statusList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Reimbursement> getAll(){
        String sql = "select * from Reimbursement_Requests";
        List<Reimbursement> requestList = new ArrayList<>();
        try(Connection conn = connection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(rs.getInt("request_id"),
                        Status.valueOf(rs.getString("status")),
                        userDAO.getById(rs.getInt("user_id")),
                        userDAO.getByUsername(rs.getString("manager_username")),
                        rs.getDouble("amount"),
                        rs.getString("reason"),
                        rs.getString("processing_reason"));
                requestList.add(reimbursement);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return requestList;
    }

    public List<Reimbursement> getMyRequestsById(int id){
        String sql = "select * from reimbursement_requests join processed_requests on reimbursement_requests.request_id = processed_requests.request_id where user_id = ? ;";
        List<Reimbursement> myList = new ArrayList<>();
        try(Connection conn = connection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Reimbursement r = new Reimbursement(rs.getInt("request_id"),
                        Status.valueOf(rs.getString("status")),
                        userDAO.getById(rs.getInt("user_id")),
                        userDAO.getById(rs.getInt("processing_manager_id")),
                        rs.getDouble("amount"),
                        rs.getString("reason"),
                        rs.getString("processing_reason"));
                myList.add(r);
            }
            return myList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
