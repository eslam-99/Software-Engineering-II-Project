package com.SWE.Project.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRegister{
    private User newUser;
    private String pass2;
    private String key;
    private Connection conn;

    public UserRegister() {

    }

    public UserRegister(String email, String uName, String fName, String lName, String pass, String pass2, String accType) {
        newUser = new User(email, uName, fName, lName, pass, accType);
        this.pass2 = pass2;
    }

    public UserRegister(String email, String uName, String fName, String lName, String pass, String pass2, String accType, String key) {
        newUser = new User(email, uName, fName, lName, pass, accType);
        this.pass2 = pass2;
        this.key = key;
    }

    public String signup() throws SQLException {
        String msg = "";
        if(!newUser.getEmail().contains("@")) msg += "Invalid Email Address";
        if(!newUser.getPass().equals(pass2)) msg += "Passwords don't match";
        if(newUser.getPass().length() < 8) msg += "Passwords must be atlest 8 digits";
        if(msg.length() != 0) return msg;

        Statement stmt = (new DB()).getConn().createStatement();
        ResultSet res;
        boolean chk1 = true, chk2 = true, chk3 = true;
        if (newUser.getAccType().toLowerCase().equals("admin")) {
            if (!key.equals("AdminKey00")) chk1 = false;
            if (!chk1) {
                if (Session.getSession() == null) {
                    res = stmt.executeQuery("select * from users");
                    if (res.next())
                        chk2 = false;
                } else if (!Session.getSession().getAccType().toLowerCase().equals("admin"))
                    chk3 = false;
            }
        }
        if (chk1 || (chk2 && chk3)) {
            res = stmt.executeQuery("select * from users where email = '" + newUser.getEmail() +
                    "' or user_name = '" + newUser.getuName() + "'");
            if (!res.next()) {
                stmt.executeUpdate("insert into users values ('" + newUser.getEmail() +
                        "', '" + newUser.getuName() +
                        "', '" + newUser.getfName() +
                        "', '" + newUser.getlName() +
                        "', '" + newUser.getPass() +
                        "', '" + newUser.getAccType() + "')");
                return "User Added Successfully";
            } else
                return "Username or Email Address Already Exists";
        } else
            return "You don't have privileges to register a new admin";
    }
}
