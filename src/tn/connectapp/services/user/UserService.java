/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.services.user;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.connectapp.entities.user.User;
import tn.connectapp.utils.commun.MyConnection;

/**
 *
 * @author Wissal
 */
public class UserService {
    
    private Connection cnx;
    
    public UserService (){
        cnx=MyConnection.getInstance().getCnx();
    }
    
    public void addUser(User u) throws SQLException{
        
  
     //    us.addUser(new User( tfFirstName.getText(), tfLastName.getText(), tfEmail.getText(),
           //         tfPassword.getText(), Date, tfClub.getText()  , null  ));
        
         System.out.println(u.getFirstName()) ;
          System.out.println(u.getLastName()) ;
           System.out.println(u.getEmail()) ;
            System.out.println(u.getPassword()) ;
             System.out.println(u.getDateBirth()) ;
              System.out.println(u.getClub()) ;
         System.out.println( u.getGender()) ;
         System.out.println( u.getRole()) ;
         
         
        //   String req = "INSERT INTO user ( FirstName,LastName,Club,Email,Password,DateBirth,Gender,Role) VALUES "+ "(  '" +  u.getFirstName() + "' , '" +  u.getLastName() + "' ,  '" +  u.getClub() + "','" +  u.getEmail() + "' , '" +  u.getPassword() + "' , '" +  u.getDateBirth() + "', '" +  u.getGender()+ "', role )";

        //Statement ste = cnx.createStatement();

        //ste.executeUpdate(req);
        
        
        String sql = "INSERT INTO user ( FirstName,LastName,Club,Email,Password,DateBirth,Gender,Role) VALUES (?,?,?,?,?,?,?,?);";
        
        PreparedStatement pre = cnx.prepareStatement(sql);

        
       pre.setString(1,u.getFirstName());
       pre.setString(2,u.getLastName());
        pre.setString(3,u.getClub());
       pre.setString(4,u.getEmail());
        pre.setString(5,u.getPassword());
       pre.setString(6,u.getDateBirth());
        pre.setString(7,u.getGender());
       pre.setString(8,u.getRole());
       
       
        pre.executeUpdate();  
       
        System.out.println("INSERTION OK!!");
         
   // public void addUser(String FirstName,String LastName,String Club,String Email,String Password,String DateBirth,String Gender) throws SQLException{
        //String sql = "INSERT INTO user(1,FirstName, LastName, Club, Email, Password,DateBirth, Gender, Role) VALUES (?,?,?,?,?,?,?,?,?);";
       
        
        /*String sql = "INSERT INTO user(1,FirstName, LastName, Club, Email, Password,DateBirth, Gender, Role) VALUES (?,?,?,?,?,?,?,?,?);";
        PreparedStatement pre= cnx.prepareStatement(sql);
        
        pre.setString(1,u.getFirstName());
        pre.setString(2,u.getLastName());
        pre.executeUpdate();  */
        
    }
    
    
    public void updateUser(User u)throws SQLException{
         String sql = "UPDATE user set FirstName where id_user=5 VALUES (?,?);";
        PreparedStatement pre= cnx.prepareStatement(sql);
        
        pre.setString(5,u.getFirstName());
        pre.setString(2,u.getLastName());
        pre.executeUpdate(sql);
        System.out.println("UPDATE Done");
        
    
    }
    
    
    public boolean deleteUser (int id_user)throws SQLException{
         String sql="DELETE  FROM user WHERE id_user =  "+id_user;
         Statement ste= cnx.createStatement();
         ste.executeUpdate(sql);
         System.out.println("DELETE Done ");
        return true;
    }
    
    
    public List <User> showUser() throws SQLException{
        
       List <User> user= new ArrayList<>();
       
        String sql="SELECT * FROM user" ;
               
        Statement ste= cnx.createStatement();
        
        ResultSet rs= ste.executeQuery(sql);
        
        while (rs.next()){
            User u = new User();
            u.setId_user(rs.getInt("id_user"));
            u.setFirstName(rs.getString(2));
            u.setLastName(rs.getString("LastName"));
            user.add(u);
        }
          return user;  
       }
        
    // public user showUser() throws SQLException{
      //  User user ;
     //   String sql="SELECT * FROM user WHERE id_user= " +id_user";
       // Statement ste= cnx.createStatement();
       // ResultSet rs= ste.executeQuery(sql);
       // while (rs.next()){
            //user  = new User();
        //    user.setId_user(rs.getInt("id_user"));
         //   user.setName_user(rs.getString(2));
          //  user.setlastName_user(rs.getString("lastName_user"));

    
            
            
          
       
        //return user;
        
    

}
     
