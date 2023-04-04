package ma.livreurtracking.fstt.livreurtrackingv1.dao.user;

import ma.livreurtracking.fstt.livreurtrackingv1.model.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public int add(User user)
            throws SQLException;

    public void delete(int id)
            throws SQLException;
    public User getUser(int id)
            throws SQLException;

    public User getUserByUsername(String username)
            throws SQLException;
    public List<User> getUser()
            throws SQLException;
    public void update(User user)
            throws SQLException;
}
