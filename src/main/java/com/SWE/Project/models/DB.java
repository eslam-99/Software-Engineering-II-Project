package com.SWE.Project.models;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    Connection conn;

    public DB() throws SQLException{
        File file = new File("C://BookStore");
        if (!file.isDirectory() || file.list().length == 0) {
            conn = DriverManager.getConnection("jdbc:derby:c://BookStore;create=true");
            conn.createStatement().executeUpdate("create table users (email varchar(30) primary key,"+
                                                                    " user_name varchar(30)," +
                                                                    " first_name varchar(30)," +
                                                                    " last_name varchar(30)," +
                                                                    " password varchar(30)," +
                                                                    " acc_type varchar(30))");
        }
        else
            conn = DriverManager.getConnection("jdbc:derby:c://BookStore;create=true");
    }

    public Connection getConn() {
        return conn;
    }

}
