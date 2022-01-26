package dev.pierce.repository;

import dev.pierce.models.Reimbursement;
import dev.pierce.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReimbursementDAO implements GenericDAO<Reimbursement>{

    ConnectionUtil connection = ConnectionUtil.getConnectionUtil();

    @Override
    public Reimbursement getByUsername(String username) {
        return null;
    }

    @Override
    public Reimbursement getById(Integer id) {
        return null;
    }

    public String submitRequest(int id, double amount, String reason){
        String reply;
        if(amountUnderAllowed(id, amount) == true) {

            String sql = "insert into Reimbursement_Requests (user_id, amount, reason) values( ?, ?, ? );";

            try (Connection conn = connection.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setDouble(2, amount);
                pstmt.setString(3, reason);
                pstmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            reply = "Request submitted.";
        }
        else{
            reply = "Request not allowed. Amount exceeds allowance.";
        }
        return reply;
    }

    public boolean amountUnderAllowed(int id, double amount){
        String sql = "select sum(amount) from Reimbursment_Requests where user_id = ? group by user_id;";
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

    public void processRequest(int requestId, int id, String denyReason, String status){

        //insert into Processed_Requests
        String insertSql="insert into Processed_Requests (request_id, processing_manager, processing_reason) values ( ?, ?, ? )";
        try(Connection conn = connection.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, requestId);
            pstmt.setInt(2, id);
            pstmt.setString(3, denyReason);
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

}
