/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Admin;

/**
 *
 * @author Aulon
 */
public class StaffAccount {
    private int staffID;
    private String staffRole;
    private String staffFirstName;
    private String staffLastName;
    private String userID;
    private String password;
  
    
    
    public StaffAccount(int staffID, String staffRole, String staffFirstName,String staffLastName,String userID, String password){
        this.staffID= staffID;
        this.staffRole=staffRole;
        this.staffFirstName= staffFirstName;
        this.staffLastName=staffLastName;
        this.userID= userID;
        this.password=password;
        
    }



   public int getStaffID(){
       return staffID;
   }
   
   public String getStaffRole(){
       return staffRole;
   }
   
   public String getStaffFirstName(){
       return staffFirstName;
   }
   public String getStaffLastName(){
       return staffLastName;
   }
   
   public String getPassword(){
       return password;
   }
  
   public String getUserID(){
       return userID;
   }
}


