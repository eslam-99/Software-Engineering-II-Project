package com.SWE.Project.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRegister extends User {
    private String pass2;
    private String key;
    private Connection conn;

    public UserRegister() {

    }

    public UserRegister(String email, String uName, String fName, String lName, String pass, String pass2, String accType) {
        super(email, uName, fName, lName, pass, accType);
        this.pass2 = pass2;
    }

    public UserRegister(String email, String uName, String fName, String lName, String pass, String pass2, String accType, String key) {
        super(email, uName, fName, lName, pass, accType);
        this.pass2 = pass2;
        this.key = key;
    }

    public String addUser() throws SQLException {
        Statement stmt = (new DB()).getConn().createStatement();
        ResultSet res;
        boolean chk1 = true, chk2 = true;
        if (this.getAccType().toLowerCase().equals("admin")) {
            if (!key.equals("AdminKey00")) chk1 = false;
            if (!chk1) {
                res = stmt.executeQuery("select * from users");
                if (res.next())
                    chk2 = false;
            }
        }
        if (chk1 || chk2) {
            res = stmt.executeQuery("select * from users where email = '" + this.getEmail() +
                    "' or user_name = '" + this.getuName() + "'");
            if (!res.next()) {
                stmt.executeUpdate("insert into users values ('" + this.getEmail() +
                        "', '" + this.getuName() +
                        "', '" + this.getfName() +
                        "', '" + this.getlName() +
                        "', '" + this.getPass() +
                        "', '" + this.getAccType() + "')");
                return "User Added Successfully";
            } else
                return "Username or Email Address Already Exists";
        } else
            return "You don't have privileges to register a new admin";
    }
}
