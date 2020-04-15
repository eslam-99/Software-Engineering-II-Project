package com.SWE.Project.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLoging{
    private String id;
    private String pass;

    public UserLoging(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public String login() throws SQLException{
        if (Session.getSession() == null) {
            User user = getUserData();
            if (user != null) {
                new Session(user);
                return "Login Successful";
            }
            else
                return "Invalid Username or Password";
        }
        else
            return "You are already loged-in";
    }

    public String logOut() {
        if (Session.getSession() != null) {
            Session.delSession();
            return "Loged out successfully";
        }
        else
            return "You already not loged in";
    }

    private User getUserData() throws SQLException {
        Statement stmt = (new DB()).getConn().createStatement();
        String usr = checkData(id), pw = checkData(pass);
        ResultSet res = stmt.executeQuery("select * from users where (user_name = '" +
                usr + "' or email = '" + usr + "') and password = '" + pw + "'");
        if(res.next()) {
            User user = new User();
            user.setEmail(res.getString("email"));
            user.setuName(res.getString("user_name"));
            user.setfName(res.getString("first_name"));
            user.setlName(res.getString("last_name"));
            user.setPass(res.getString("password"));
            user.setAccType(res.getString("acc_type"));
            return user;
        }
        else
            return null;
    }

    private String checkData(String data) {
        if(data.charAt(0) == '\'') data = data.substring(1);
        if(data.charAt(data.length()-1) == '\'') data = data.substring(0, data.length()-1);
        return data;
    }
}
