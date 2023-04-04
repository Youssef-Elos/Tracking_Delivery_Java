package ma.livreurtracking.fstt.livreurtrackingv1.dao.user;

import ma.livreurtracking.fstt.livreurtrackingv1.model.user.User;

import java.sql.*;

public class LoginService {


    public boolean verifyLogin(String username, String password) {
        boolean status = false;
        try {

            UserDao userDao = new UserDaoImplementation();
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    status = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

}
