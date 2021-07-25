/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.connectapp.utils.commun;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author i_dkh
 */
public class MyConnection {
    
     //1er etape
    private static MyConnection instance;

    private String url  = "jdbc:mysql://localhost:3306/connect";
    private String login  = "root";
    private String password = "";
    private Connection cnx;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}

   


