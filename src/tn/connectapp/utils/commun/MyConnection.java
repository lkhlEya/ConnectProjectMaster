/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.connectapp.utils.commun;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author i_dkh
 */
public class MyConnection {
    
     //1er etape
    private static MyConnection instance;

    private String url = "jdbc:mysql://localhost:3306/remboursement";
    private String login = "root";
    private String mdp = "";
   
    private java.sql.Connection cnx;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static MyConnection getInstance(){
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance ;
    }

    public java.sql.Connection getCnx() {
        return cnx;
    }

}

   


