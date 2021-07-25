/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.entities.user;

import java.sql.Date;

/**
 *
 * @author Wissal
 */
public class User {
    private int id_user;
    private String FirstName;
    private String LastName;
    private String Club;
    private String Email;
    private String Password;
  //  private Date DateBirth ;
    private String DateBirth ;
    private String Gender;
    private String Role;
    
    
       public User () {
    }
  

    public User( String FirstName, String LastName, String Club, String Email, String Password, String DateBirth, String Gender ) {
      //  this.id_user = id_user;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Club = Club;
        this.Email = Email;
        this.Password = Password;
        this.DateBirth= DateBirth;
        this.Gender= Gender;
        
    }

    public User(String FirstName, String LastName) {
       this.FirstName = FirstName;
        this.LastName = LastName;
    }
    public User( int id_user, String FirstName, String LastName) {
      
        this.id_user = id_user;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

   
  
       public String getDateBirth() {
        return DateBirth;
    }

    public void setDateBirth(String DateBirth) {
        this.DateBirth = DateBirth;
    }
    

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getClub() {
        return Club;
    }

    public void setClub(String Club) {
        this.Club = Club;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

  //  public Date getDateBirth() {
    //    return DateBirth;
    //}

    //public void setDateBirth(Date DateBirth) {
      //  this.DateBirth = DateBirth;
    //}

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }



    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Club=" + Club + ", Email=" + Email + ", Password=" + Password + ", DateBirth=" + DateBirth + ", Gender=" + Gender + ", Role=" + Role + '}';
    }

    
}
    
   
    

