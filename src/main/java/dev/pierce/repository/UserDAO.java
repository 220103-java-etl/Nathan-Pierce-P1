package dev.pierce.repository;

import dev.pierce.models.User;
import dev.pierce.util.ConnectionUtil;
import dev.pierce.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements GenericDAO <User>{


    ConnectionUtil connection = ConnectionUtil.getConnectionUtil();

    @Override
    public User getByUsername(String username){
        String sql = "select * from users where username = ?";

        try(Connection conn = connection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                User user = new User(rs.getInt("id"),
                        rs.getString("users_full_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role").toUpperCase()));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getById(Integer id) {
        String sql = "select * from Users where id = ?";

        try(Connection conn = connection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                User user = new User(rs.getInt("id"),
                        rs.getString("users_full_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
