/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

import teamproject.Accounts.AccountController;
import teamproject.Accounts.Customer;
import teamproject.Tasks.Task;
import teamproject.Jobs.Job;
import teamproject.Payment.PaymentRecord;
import teamproject.Database.Jdbc;
import teamproject.Payment.PaymentController;
import teamproject.Reports.ReportController;


/**
 *
 * @author Ramee
 */
public class Main {
  
    
    public static void main(String[] args) throws Exception{
      
        //connect to database
        Jdbc jdbc = new Jdbc();
        Logout logout = new Logout();
        
        
        //login page
        Login login = new Login();
        login.setVisible(true);
        
        //bapers system
        Bapers bapers = new Bapers();
        
        //bapers customer
       
        
        // acccount controller
        AccountController accController = new AccountController();
        
        //payment controller
        
        PaymentController payController = new PaymentController();
        
        //reports controller
        ReportController reportController = new ReportController();
        
        //main controller
        Controller controller = new Controller(login,bapers,jdbc,logout);
    
    }
 
    
  
}
