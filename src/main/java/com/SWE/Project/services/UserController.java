package com.SWE.Project.services;

import com.SWE.Project.models.DB;
import com.SWE.Project.models.User;
import com.SWE.Project.models.UserRegister;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class UserController {

    @RequestMapping("/registerAdmin")
    public String addUser(@RequestParam("UserName") String uName,
                          @RequestParam("Email") String email,
                          @RequestParam("FirstName") String fName,
                          @RequestParam("LastName") String lName,
                          @RequestParam("Password1") String pass1,
                          @RequestParam("Password2") String pass2,
                          @RequestParam("Key") String key
    ) throws SQLException {
        UserRegister newUser = new UserRegister(email, uName, fName, lName, pass1, pass2, "admin", key);
        return newUser.addUser();
    }

    @RequestMapping("/register")
    public String addUser(@RequestParam("UserName") String uName,
                          @RequestParam("Email") String email,
                          @RequestParam("FirstName") String fName,
                          @RequestParam("LastName") String lName,
                          @RequestParam("Password1") String pass1,
                          @RequestParam("Password2") String pass2
    ) throws SQLException {
        UserRegister newUser = new UserRegister(email, uName, fName, lName, pass1, pass2, "user");
        return newUser.addUser();
    }

}
