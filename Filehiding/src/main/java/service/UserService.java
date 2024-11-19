package service;

import DAO.UserDAO;
import db.model.User;

import java.sql.SQLException;

public class UserService {
    public static int SaveUser(User user) {
        try{
            if(UserDAO.isexists(user.getEmail())){
                return 1;
            }
            else {
                UserDAO.saveUser(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
