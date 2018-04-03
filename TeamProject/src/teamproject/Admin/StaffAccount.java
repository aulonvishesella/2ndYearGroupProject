/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Admin;

/**
 *
 * @author Ardit
 */
public class StaffAccount {
    private int staffID;
    private String staffRole;
    private String staffFirstName;
    private String staffLastName;
  
    
    
    public StaffAccount(int staffID, String staffRole, String staffFirstName,String staffLastName){
        this.staffID= staffID;
        this.staffRole=staffRole;
        this.staffFirstName= staffFirstName;
        this.staffLastName=staffLastName;
        
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
  
}


