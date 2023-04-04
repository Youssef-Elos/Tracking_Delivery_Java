package ma.livreurtracking.fstt.livreurtrackingv1.dao.user;

import ma.livreurtracking.fstt.livreurtrackingv1.dao.conn.DatabaseConnection;
import ma.livreurtracking.fstt.livreurtrackingv1.model.user.Role;
import ma.livreurtracking.fstt.livreurtrackingv1.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImplementation implements UserDao{
    static Connection con = DatabaseConnection.getConnection();
    @Override
    public int add(User user) throws SQLException {
        String query
                = "insert into usr(username, password, role, phone, "
                + "address) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(   1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getRole());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getAddress());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public User getUser(int id) throws SQLException {
        return null;
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        String query = "select * from usr where username = ?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, username);
        User user = new User();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setAddress(rs.getString("address"));
            user.setPassword(rs.getString("password"));
            System.out.println(rs.getString("role"));
            user.setRole(rs.getString("role"));
            user.setPhone(rs.getString("phone"));
        }

        if (check == true) {
            System.out.println("User found");
            return user;
        }
        else {
            System.out.println("User not found");
            return null;
        }
    }

    @Override
    public List<User> getUser() throws SQLException {
        return null;
    }

    @Override
    public void update(User user) throws SQLException {

    }





}
