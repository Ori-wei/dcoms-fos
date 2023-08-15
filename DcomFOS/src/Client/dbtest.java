/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
class dbtest {
    public dbtest() throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor"); //the line to connect to database. Parameter is "database url", "username", "password"
        System.out.println("Connected"); //if see this message, databse is connected
        Statement stm = conn.createStatement(); //Statement class is used for java to identify the sql statement
        stm.executeUpdate("insert into Test values(22)"); 
        conn.commit();
        conn.close(); 
    }
}
