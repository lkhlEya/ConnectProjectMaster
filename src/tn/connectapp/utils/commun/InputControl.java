/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.utils.commun;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author crist
 */
public class InputControl {
    
    
    public static boolean isString(String text) 
    {
       return text.matches("^[a-zA-Z]+$");
    }    
    
    public static boolean isNull(String text)
    {
             return ("".equals(text) || text == null ); 
    }
     
    public static boolean isGSM(String text)
    {
        return (text.matches("^[0-9]+$")&& text.length()==8) ;
    }
    
        public static boolean isNumber(String text)
    {
        return (text.matches("^[0-9]+$")) ;
    }
    
    public static boolean valiemail(final String hex) 
    {
        String EMAIL_PATTERN   = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(hex).matches();
    }
     public static Date asDate(LocalDate localDate) {
         return new java.sql.Date(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()).getTime());
    }
     
    public static LocalDate asLocalDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
     
    public static Alert genAlert(String msgNum, String alertType, String param1, String param2, String param3)
    {
        Alert alert;
        if ("ERROR".equals(alertType))
          alert = new Alert(Alert.AlertType.ERROR);
        else if ("WARNING".equals(alertType))
          alert = new Alert(Alert.AlertType.WARNING);
        else if ("CONFIRMATION".equals(alertType))
          alert = new Alert(Alert.AlertType.CONFIRMATION);
        else if ("INFORMATION".equals(alertType))
          alert = new Alert(Alert.AlertType.INFORMATION);
        else  alert = new Alert(Alert.AlertType.NONE);
        
        alert.setTitle(alertType);
            switch (msgNum)
            {
                case "100" : alert.setHeaderText("Mandatory fields are empty !");
                break;
                case "101" : alert.setHeaderText(param1 + " is mandatory");
                break;
                case "102" : alert.setHeaderText("Please check the licence terms");
                break;
                case "103" : alert.setHeaderText("Please choose a "+param1);
                break;
                case "104" : alert.setHeaderText("Please select a "+param1);
                break;
                case "105" : alert.setHeaderText("Please check an item to "+param1);
                break;
                case "106" : alert.setHeaderText("Please check "+param1+" "+param2+"  to "+param3);
                break;
                case "200" : alert.setHeaderText(param1 + " is not a numeric form");
                break;
                case "201" : alert.setHeaderText(param1 + " must not contain a numeric character");
                 break;
                case "202" : alert.setHeaderText(param1 + " must not contain an alphabetic character");
                 break;
                case "203" : alert.setHeaderText(param1 + " must not contain special characters");
                 break;
                case "204" : alert.setHeaderText(param1 + " is not a valid email");
                 break;
                case "205" : alert.setHeaderText(param1 + " does not match" + param2);
                 break;
                case "206" : alert.setHeaderText(param1 + " is not a phone number");
                 break;
                case "207" : alert.setHeaderText(param1 + " is not a number");
                 break;
                case "300" : alert.setHeaderText(param1 + "a problem has appeared please try again");
                 break;
                 case "301" : alert.setHeaderText("A problem has appeared while deleting "+param1+" : "+param2+"  please try again");
                 break;  
                  case "302" : alert.setHeaderText("A problem has appeared while archiving "+param1+" : "+param2+"  please try again");
                 break;         
                  case "303" : alert.setHeaderText("A problem has appeared while activating "+param1+" : "+param2+"  please try again");
                 break;                     
                case "500" : alert.setHeaderText( "your request to add a "+param1+" is sent successfully, we will keep you informed ");
                break;
                case "501" : alert.setHeaderText( "your request to update the "+param1+" is sent successfully.");
                break;
            }
            
         if (!isNull(param3))
            {
                  alert.setContentText(param3);
            }
        return alert;
        
    }
    
}
