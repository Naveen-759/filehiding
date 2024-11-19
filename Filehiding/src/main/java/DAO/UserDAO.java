package DAO;

import db.Myconnection;
import db.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isexists(String email) throws SQLException
    {
        Connection con= Myconnection.getconnection();
        {
            PreparedStatement ps = con.prepareStatement("select email from user ");
//            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String e = rs.getString(1);
                if (e.equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }
    public  static int saveUser(User user) throws SQLException{
        Connection con=Myconnection.getconnection();
        PreparedStatement ps=con.prepareStatement("insert into user values (default,?,?) ");
//        System.out.println(ps);
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());
        return ps.executeUpdate();
    }
}
